package com.mbc.sports;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.mbc.sports.game.GameDTO;
import com.mbc.sports.main.MainService;
import com.mbc.sports.main.MainSoccerTeamDTO;
import com.mbc.sports.main.MainBaseballTeamDTO;
@Controller
public class HomeController {
	@Autowired
	SqlSession sqlSession;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@RequestMapping(value = "/")
	public String main(HttpServletRequest request) {
		HttpSession hs=request.getSession();
		hs.setAttribute("normallogin", false);
		hs.setAttribute("superlogin", false);
		hs.setAttribute("adminlogin", false);
		return "index";
	}
	@RequestMapping(value = "/soccermain")
	public String smain(HttpServletRequest request,Model model) throws IOException {
		HttpSession hs = request.getSession();
		hs.setAttribute("sports", "�౸");
		//������
		ArrayList<MainSoccerTeamDTO> scteamranklist = new ArrayList<>();
		Document doc = Jsoup.connect("https://sports.news.naver.com/kfootball/record/index?category=kleague")
				.userAgent(
						"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36")
				.get();
		String soccer[] = {"#splitGroupA_table","#splitGroupB_table"};
		for(int i=0; i < soccer.length; i++) {
			Elements soccerTeams = doc.select(soccer[i] + " > tr");
			for (Element soccerTeam : soccerTeams) {
				Element rank = soccerTeam.selectFirst("th");//����
				Element title = soccerTeam.selectFirst("span:nth-child(2)");//����
				Element win = soccerTeam.selectFirst("td:nth-child(5)");//��
				Element draw = soccerTeam.selectFirst("td:nth-child(6)");//��
				Element lose = soccerTeam.selectFirst("td:nth-child(7)");//��
				if (title != null) {
					MainSoccerTeamDTO teamData = new MainSoccerTeamDTO(rank.text(),title.text(),win.text(),draw.text(),lose.text());
					scteamranklist.add(teamData);
				}
			}
		}
		MainService hsv = sqlSession.getMapper(MainService.class);
		ArrayList<GameDTO> scgamelist = hsv.soccerSelectToday();
		model.addAttribute("teamlist", scteamranklist);//������
		model.addAttribute("gamelist", scgamelist);//�������
		return "soccermain";
	}
	@RequestMapping(value = "/baseballmain")
	public String bmain(HttpServletRequest request, Model model) {
		HttpSession hs=request.getSession();
		hs.setAttribute("sports", "�߱�");
		ArrayList<MainBaseballTeamDTO> bsteamranklist = new ArrayList<>();
		try {
			Document doc = Jsoup.connect("https://sports.news.naver.com/kbaseball/record/index.nhn?category=kbo")
					.userAgent(
							"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36")
					.get();
			// select�� �̿��ؼ� tr���� �ҷ�����
			Elements baseballTeams = doc.select("#regularTeamRecordList_table > tr");
			// tr���� �ݺ��� ������
			for (Element baseballTeam : baseballTeams) {
				Element rank = baseballTeam.selectFirst("th");//�� ��
				Element title = baseballTeam.selectFirst("span:nth-child(2)");//����
				Element win = baseballTeam.selectFirst("td:nth-child(4)");//��
				Element draw = baseballTeam.selectFirst("td:nth-child(5)");//��
				Element lose = baseballTeam.selectFirst("td:nth-child(6)");//��
				if (title != null) {
					String image = title.text();
					MainBaseballTeamDTO baseTeamData = new MainBaseballTeamDTO(rank.text(),title.text(),win.text(),draw.text(),lose.text());
					bsteamranklist.add(baseTeamData);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		MainService hsv = sqlSession.getMapper(MainService.class);
		ArrayList<GameDTO> bsgamelist = hsv.baseballSelectToday();
		model.addAttribute("teamlist", bsteamranklist);//������
		model.addAttribute("gamelist", bsgamelist);//�������
		return "baseballmain";
	}
	@RequestMapping(value = "/main")
	public String main2(HttpServletRequest request) {
		HttpSession hs=request.getSession();
		String sports = (String)hs.getAttribute("sports");
		String path="redirect:/soccermain";
		if (sports!=null) {
			path = (sports.equals("�౸")) ? "redirect:/soccermain" :"redirect:/baseballmain";			
		}
		return path;
	}
	@RequestMapping(value = "/areaClick", method = RequestMethod.POST)
	public String areaClick(HttpServletRequest request, Model model) {
		String area_en = request.getParameter("area_en");
		String area_ko = request.getParameter("area_ko");
		TopDTO top = new TopDTO();
		top.setArea_eng(area_en);
		top.setArea_kor(area_ko);
		model.addAttribute("area", top);
		HttpSession hs = request.getSession(); 
		hs.setAttribute("areaset", true);
		hs.setAttribute("area", top);
		return "main";
	}
	@RequestMapping(value = "/baseballlike")
	public String baseballlike(HttpServletRequest request) throws IOException {
		String path="C:\\project\\sports\\sports\\src\\main\\webapp\\python\\mvp_project(baseball_team).py";
		ProcessBuilder pb = new ProcessBuilder("python",path);
		pb.start();
		return "redirect:/baseballmain";
	}
	@RequestMapping(value = "/soccerlike")
	public String soccerlike(HttpServletRequest request) throws IOException {
		String path="C:\\project\\sports\\sports\\src\\main\\webapp\\python\\mvp_project(soccer_team).py";
		ProcessBuilder pb = new ProcessBuilder("python",path);
		pb.start();
		return "redirect:/soccermain";
	}
	@RequestMapping(value = "/agelike")
	public String agelike(HttpServletRequest request) throws IOException {
		String play = request.getParameter("play");
		String path="C:\\project\\sports\\sports\\src\\main\\webapp\\python\\mvp_project(age).py";
		ProcessBuilder pb = new ProcessBuilder("python",path);
		pb.start();
		if(play.equals("�౸")) {
			return "redirect:/soccermain";
		} else {
			return "redirect:/baseballmain";
		}
	}
}