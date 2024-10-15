package com.mbc.sports.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mbc.sports.member.MemberDTO;
import com.mbc.sports.member.MemberService;

@Controller
public class LoginController {
	@Autowired
	SqlSession sqlsession;

	//�α��� â �̵�
	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		String sports = (String)hs.getAttribute("sports");
		if(sports==null) sports="�౸";
		return (sports.equals("�߱�"))?"baseballLogin":"soccerLogin";
	}
	//�α��� ����
	@RequestMapping(value = "/logincheck",method = RequestMethod.POST)
	public String logincheck(HttpServletRequest request, HttpServletResponse response)  {
		String npath="";
		HttpSession hs = request.getSession();
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		//������ �α���
		String path="classpath:admin.xml";
		AbstractApplicationContext aac = new GenericXmlApplicationContext(path);		
		MemberDTO dto = aac.getBean("member",MemberDTO.class);
		String ADMIN_ID =dto.getADMIN_ID();
		String ADMIN_PW =dto.getADMIN_PW();
		if(id.equals(ADMIN_ID)&&pw.equals(ADMIN_PW)) {
			MemberService ms =sqlsession.getMapper(MemberService.class);			
			hs.setAttribute("access", ms.countmember());
			hs.setAttribute("notAccess", ms.countnotmember());
			hs.setAttribute("adminlogin", true);
			npath="redirect:/main";
		}else { //ȸ�� �α���
			LoginService ls = sqlsession.getMapper(LoginService.class);
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			MemberDTO ldto = ls.logincheck(id);
			if(ldto!=null){
				if(ls.access(id).equals("ok")){
					if(passwordEncoder.matches(pw, ldto.getPw())) {
						String part = ldto.getPart();
						String sport = ldto.getSport();
						if(part.equals("�Ϲ�")) {
							hs.setAttribute("normallogin", true);				
						}
						if(part.equals("����")) {
							hs.setAttribute("superlogin", true);
						}
						hs.setAttribute("member", ldto);
						hs.setAttribute("sports", sport);
						npath="redirect:/main";
					}else {
						npath="redirect:/login";				
					}
				}else {
					npath="redirect:/login";									
				}
			}else {
				npath="redirect:/signup";								
			}
		}
		return npath;
	}
	//�α׾ƿ�
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		HttpSession hs=request.getSession();
		hs.setAttribute("normallogin", false);
		hs.setAttribute("superlogin", false);
		hs.setAttribute("adminlogin", false);
		
		return "redirect:/main";
	}
	//alert�� ���� ����
	@RequestMapping(value = "/loginpossible",method =RequestMethod.POST)
	public void loginpossible(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		
		String path="classpath:admin.xml";
		AbstractApplicationContext aac = new GenericXmlApplicationContext(path);		
		MemberDTO dto = aac.getBean("member",MemberDTO.class);
		String ADMIN_ID =dto.getADMIN_ID();
		String ADMIN_PW =dto.getADMIN_PW();
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter prw = response.getWriter();
		if(id.equals(ADMIN_ID)&&pw.equals(ADMIN_PW)) {
			prw.print("������ �������� �α��εǾ����ϴ�.");				
		}
		else {
			LoginService ls = sqlsession.getMapper(LoginService.class);
			MemberDTO ldto = ls.logincheck(id);
			String msg;
			if(ldto!=null){
				if(ldto.getPart().equals("�Ϲ�") && ls.access(id).equals("no")) {
					prw.print("�����ڰ� ���Ƿ� ������ �����Դϴ�.\n�߰� ���ǻ����� F&Q �Խ����� �̿����ּ���.");					
				}
				else if(ls.access(id).equals("no")){
					prw.print("�������� ������ �ʿ��� �����Դϴ�.");
				}else {
					PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
					if(passwordEncoder.matches(pw, ldto.getPw())) {
						String name = ldto.getName();
						prw.print(name+"�� ȯ���մϴ�!");
					}else {
					prw.print("���̵� Ȥ�� ��й�ȣ�� Ʋ���ϴ�.");			
					}
				}
			}else {
				prw.print("ȸ�������� �����ϴ�.\nȸ������ �������� �̵��մϴ�.");					
			}
		}
	}
	
	@RequestMapping(value = "/idsearch")
	public String idsearch() {
		return "id_search";
	}
	
	@RequestMapping(value = "/getid",method =RequestMethod.POST)
	public void getId(String name, String birth, String tel, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter prw = response.getWriter();
		LoginService ls = sqlsession.getMapper(LoginService.class);
		String id = ls.getid(name,birth,tel);
		if(id==null || id=="") {
			prw.print("���̵� ã�� �� �����ϴ�.");
		}
		else {
			prw.print("����� ���̵�� "+id+"�Դϴ�.");
		}
		
	}
	
	@RequestMapping(value = "/pwsearch")
	public String pwsearch() {
		return "pw_search";
	}
	
	@RequestMapping(value = "/getpw",method =RequestMethod.POST)
	public void getPw(String id, String name, String birth, String tel, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter prw = response.getWriter();
		LoginService ls = sqlsession.getMapper(LoginService.class);
		int result = ls.getpw(id,name,birth,tel);
		prw.print(result);
	}
	
	@RequestMapping(value = "/pwchange")
	public String pwchange(String id, Model mo) {
		mo.addAttribute("id", id);
		return "pw_change";
	}
	
	@RequestMapping(value = "/pwud")
	public void pw_update(HttpServletRequest request) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		pw=passwordEncoder.encode(pw);
		LoginService ls = sqlsession.getMapper(LoginService.class);
		ls.pwchange(id,pw);
	}
}
