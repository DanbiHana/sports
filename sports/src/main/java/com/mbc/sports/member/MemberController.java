package com.mbc.sports.member;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
public class MemberController {
@Autowired
	SqlSession sqlsession;
	
	String path="C:\\project\\sports\\sports\\src\\main\\webapp\\image\\member\\super";
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup() {
		return "signup";
	}
	@RequestMapping(value = "/signupForm", method = RequestMethod.GET)
	public String signupForm(String part, Model model) {
		model.addAttribute("part",part);
		return "signUpForm";
	}	
	//ȸ�� ����
	@RequestMapping(value = "/memberSave", method = RequestMethod.POST)
	public String memberSave(MultipartHttpServletRequest mul) throws IOException {
		String id = mul.getParameter("id");
		String pw = mul.getParameter("pw");
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		pw=passwordEncoder.encode(pw);
		String name = mul.getParameter("name");
		String birth = mul.getParameter("birth");
		String tel = mul.getParameter("tel");
		String email = mul.getParameter("email");
		String zipp_code = mul.getParameter("zipp_code");
		String user_add1 = mul.getParameter("user_add1");
		String user_add2 = mul.getParameter("user_add2");
		String sport = mul.getParameter("sport");
		String team = mul.getParameter("team");
		String part = mul.getParameter("part");
		String voe="",rr="",access="ok";
		
		if(part.equals("����")) {
			MultipartFile voeFull = mul.getFile("voe");
			MultipartFile rrFull = mul.getFile("rr");
			voe = voeFull.getOriginalFilename();
			rr = rrFull.getOriginalFilename();
			voe = filesave(voe, voeFull.getBytes());
			rr = filesave(rr, rrFull.getBytes());
			voeFull.transferTo(new File(path+"\\"+voe));
			rrFull.transferTo(new File(path+"\\"+rr));
			access="no";
		}
		MemberService ms = sqlsession.getMapper(MemberService.class);
		ms.insert(id,pw,name,birth,tel,email,zipp_code,user_add1,user_add2,sport,team,voe,rr,part,access);
		return "redirect:/login";
	}
	//�̹��� ���� ����
	private String filesave(String fname, byte[] bytes) {
		UUID uuid = UUID.randomUUID();
		return uuid.toString()+"_"+fname;
	}
	//ID �ߺ�Ȯ��
	@RequestMapping(value = "/idcheck", method = RequestMethod.POST)
	public void idCheck(String id, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		MemberService ms = sqlsession.getMapper(MemberService.class);
		int result = ms.getId(id);
		PrintWriter prw =response.getWriter();
		prw.print(result);
	}
	//���ε� ȸ�� ��ȸ
	@RequestMapping(value = "/memberList", method = RequestMethod.GET)
	public String memberList(Model mo) {
				
		MemberService ms = sqlsession.getMapper(MemberService.class);
		ArrayList<MemberDTO> list = ms.memberlist();
		mo.addAttribute("list", list);
		
		return "memberList";
	}
	//���� �ȵ� ȸ�� ��ȸ
	@RequestMapping(value = "/memberNoList", method = RequestMethod.GET)
	public String memberNoList(Model mo) {
		
		MemberService ms = sqlsession.getMapper(MemberService.class);
		ArrayList<MemberDTO> list = ms.memberNotlist();
		mo.addAttribute("list", list);
		
		return "memberList";
	}
	//ȸ�� ���� â �̵�
	@RequestMapping(value = "/memberdelete", method = RequestMethod.GET)
	public String memberdelete(String id, Model mo) {
		MemberService ms = sqlsession.getMapper(MemberService.class);
		MemberDTO member = ms.select(id);
		mo.addAttribute("del", member);
		return "memberDelete";
	}
	//ȸ�� ����
	@RequestMapping(value = "/deleteMember", method = RequestMethod.POST)
	public String delete(HttpServletRequest request) {
		String id = request.getParameter("id");
		String part = request.getParameter("part");		
		if(part.equals("����")) {
			String voe = request.getParameter("voe");
			String rr = request.getParameter("rr");	
			File voeImg = new File(path+"\\"+voe);
			voeImg.delete();		
			File rrImg = new File(path+"\\"+rr);
			rrImg.delete();
		}		
		MemberService ms = sqlsession.getMapper(MemberService.class);
		ms.del_mem(id);
		return "redirect:/memberList";
	}
	//����������(��������)�� �̵�
	@RequestMapping(value = "/mypage")
	public String mypage(String id, Model model) {
		MemberService ms = sqlsession.getMapper(MemberService.class);
		MemberDTO member = ms.select(id);
		model.addAttribute("my", member);
		return "mypage";
	}	
	//��й�ȣ Ȯ��
	@RequestMapping(value = "/pwCheck", method = RequestMethod.POST)
	public void pw_Check(String id, String pw, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter prw = response.getWriter();
		MemberService ms = sqlsession.getMapper(MemberService.class);
		int result;
		String pwcheck = ms.pwCheck(id);		
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if(passwordEncoder.matches(pw, pwcheck)) {
			result=1;
		}
		else {
			result=0;
		}
		prw.print(result);
	}
	//��й�ȣ ����
	@RequestMapping(value = "/pwUpdate", method = RequestMethod.POST)
	public void pwUpdate(String id, String pw, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter prw = response.getWriter();
		PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
		String cpw = passwordEncoder.encode(pw);
		MemberService ms = sqlsession.getMapper(MemberService.class);
		ms.pwupdate(id, cpw);
		prw.print("");
	}
	//���� ���� â���� ����
	@RequestMapping(value = "/memberupdate", method = RequestMethod.GET)
	public String memberupdate(String id, Model mo) {
		
		MemberService ms = sqlsession.getMapper(MemberService.class);
		MemberDTO update_member = ms.select(id);
		mo.addAttribute("my", update_member);
		
		return "memberUpdate";
	}
	//���� ����
	@RequestMapping(value = "/memberUpdate", method = RequestMethod.POST)
	public String memberUpdate(MultipartHttpServletRequest mul, Model mo) throws IOException {
		String voe="", rr="";
		String part=mul.getParameter("part");
		String id=mul.getParameter("id");
		String name=mul.getParameter("name");
		String birth = mul.getParameter("birth");
		String tel=mul.getParameter("tel");
		String zipp_code=mul.getParameter("zipp_code");
		String user_add1=mul.getParameter("user_add1");
		String user_add2=mul.getParameter("user_add2");
		String sport=mul.getParameter("sport");
		String team=mul.getParameter("team");
		//����
		if(part.equals("����")) {
			String refvoe=mul.getParameter("refvoe");
			String refrr=mul.getParameter("refrr");
			MultipartFile voe_mf = mul.getFile("voe");
			MultipartFile rr_mf = mul.getFile("rr");
			voe = voe_mf.getOriginalFilename();		
			rr = rr_mf.getOriginalFilename();		
			if(voe.equals("")) {
				voe = refvoe;
			}else{
				voe = filesave(voe, voe_mf.getBytes());
				voe_mf.transferTo(new File(path+"\\"+voe));
				File file = new File(path+"\\"+refvoe);
				file.delete();
			}
			if(rr.equals("")) {
				rr = refrr;
			}else {
				rr = filesave(rr, rr_mf.getBytes());
				rr_mf.transferTo(new File(path+"\\"+rr));			
				File file = new File(path+"\\"+refrr);
				file.delete();
			}
		}
		MemberService ms = sqlsession.getMapper(MemberService.class);
		ms.update(id,name,birth,tel,zipp_code,user_add1,user_add2,sport,team,voe,rr);
		mo.addAttribute("id", id);
		return "redirect:/mypage";
	}
	//������ ����: ȸ�� ���� ����
	@RequestMapping(value = "/memberAccess")
	public String possible(HttpServletRequest request) {
		String id = request.getParameter("id");
		MemberService ms = sqlsession.getMapper(MemberService.class);
		ms.updateAccess(id);
		
		HttpSession hs = request.getSession();
		hs.setAttribute("access", ms.countmember());
		hs.setAttribute("notAccess", ms.countnotmember());
		return "redirect:/memberList";
	}
}
