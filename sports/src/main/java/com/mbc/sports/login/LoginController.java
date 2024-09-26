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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mbc.sports.member.MemberDTO;

@Controller
public class LoginController {
	@Autowired
	SqlSession sqlsession;
	
	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		String sports = (String)hs.getAttribute("sports");
		if(sports==null) sports="�౸";
		return (sports.equals("�߱�"))?"baseballlogin":"soccerlogin";
	}
	
	@RequestMapping(value = "/logincheck",method = RequestMethod.POST)
	public String logincheck(HttpServletRequest request, HttpServletResponse response)  {
		String npath="";
		HttpSession hs = request.getSession();
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		//������ �α���
		String path="classpath:admin.xml";
		AbstractApplicationContext aac = new GenericXmlApplicationContext(path);		
		MemberDTO dto = aac.getBean("member",MemberDTO.class);
		String ADMIN_ID =dto.getADMIN_ID();
		String ADMIN_PW =dto.getADMIN_PW();
		if(id.equals(ADMIN_ID)&&pw.equals(ADMIN_PW)) {
			hs.setAttribute("adminlogin", true);
			npath="redirect:/main";
		}else { //ȸ�� �α���
			LoginService ls = sqlsession.getMapper(LoginService.class);
			LoginDTO ldto = ls.logincheck(id,pw);
			if(ldto!=null){
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
		}
		return npath;
	}
	
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
			LoginDTO ldto = ls.logincheck(id,pw);
			String msg;
			if(ldto!=null){
				String name = ldto.getName();
				prw.print(name+"�� ȯ���մϴ�!");
			}else {
				prw.print("���̵� Ȥ�� ��й�ȣ�� Ʋ���ϴ�.");			
			}
		}
	}
}
