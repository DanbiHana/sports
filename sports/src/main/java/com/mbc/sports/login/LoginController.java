package com.mbc.sports.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mbc.sports.baseballdirect.BaseballDirectController;
import com.mbc.sports.member.MemberDTO;
import com.mbc.sports.member.MemberService;

@Controller
public class LoginController {
	@Autowired
	SqlSession sqlsession;
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	//�α��� â �̵�
	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		String sports = (String)hs.getAttribute("sports");
		if(sports==null) sports="�౸";
		return (sports.equals("�߱�"))?"baseballLogin":"soccerLogin";
	}
	@RequestMapping(value = "/loginCheck",method =RequestMethod.POST)
	public void loginpossible(HttpServletRequest request,HttpServletResponse response) throws IOException {
		HttpSession hs = request.getSession();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter prw = response.getWriter();
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		
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
				prw.print("������ �������� �α��εǾ����ϴ�.");
		}else {//�����ڰ���X
			LoginService ls = sqlsession.getMapper(LoginService.class);
			MemberDTO ldto = ls.logincheck(id);
			if(ldto!=null){
				if(ldto.getPart().equals("�Ϲ�") && ls.access(id).equals("no")) {
					prw.print("1");					
				}//���ܰ���
				else if(ls.access(id).equals("no")){ //���� ���� ��
					prw.print("2");
				}else {// �Ϲݰ���
					PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
					if(passwordEncoder.matches(pw, ldto.getPw())) {
						String name = ldto.getName();
						String sport = ldto.getSport();
						String sportteam = ldto.getTeam();
						if(ldto.getPart().equals("�Ϲ�")) {
							hs.setAttribute("normallogin", true);											
						}else{
							hs.setAttribute("superlogin", true);
						}
						hs.setAttribute("member", ldto);
						hs.setAttribute("sports", sport);
						hs.setAttribute("sportteam", sportteam);
						prw.print(name+"�� ȯ���մϴ�!");
					}else {	prw.print("0");}
				}
			}else {	prw.print("0");}
		}
	}	
	//�α׾ƿ�
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		HttpSession hs=request.getSession();
		hs.setAttribute("normallogin", false);
		hs.setAttribute("superlogin", false);
		hs.setAttribute("adminlogin", false);
		hs.setAttribute("areaset", false);
		hs.setAttribute("area", null);
		return "redirect:/main";
	}
	
	@RequestMapping(value = "/idsearch")
	public String idsearch() {
		return "idsearch";
	}
	
	@RequestMapping(value = "/getid",method =RequestMethod.POST)
	public void getId(String name, String birth, String tel, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter prw = response.getWriter();
		LoginService ls = sqlsession.getMapper(LoginService.class);
		String id = ls.getid(name,birth,tel);
		if(id==null || id=="") {
			prw.print("ȸ�� ������ �����ϴ�.");
		}
		else {
			prw.print("����� ���̵�� '"+id+"'�Դϴ�.");
		}	
	}
	
	@RequestMapping(value = "/pwsearch")
	public String pwsearch() {
		return "pwsearch";
	}

	@RequestMapping(value = "/getpw",method =RequestMethod.POST)
	public void getPw(String id, String name, String tel, String email, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter prw = response.getWriter();
		LoginService ls = sqlsession.getMapper(LoginService.class);
		int result = ls.getpw(id,name,tel,email);
		prw.print(result);
	}
	
	@RequestMapping(value = "/pwupdate")
	public String pwchange(String id, Model model) {
		model.addAttribute("id", id);
		return "pwupdate";
	}
}
