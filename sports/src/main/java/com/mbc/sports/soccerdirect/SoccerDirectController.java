package com.mbc.sports.soccerdirect;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SoccerDirectController {
	@Autowired
	SqlSession sqlSession;

	private static final Logger logger = LoggerFactory.getLogger(SoccerDirectController.class);
	@RequestMapping(value = "/soccercalendar", method = RequestMethod.GET)
	public String soccercalendar(HttpServletRequest request, Model model) {
		String areaname = request.getParameter("name");
		String areaname2 = request.getParameter("area");
		String calendar_info = "soccer";
		DirectService ds = sqlSession.getMapper(DirectService.class);
		ArrayList <DirectDTO> list = ds.selectCalendarArea(areaname);
		ArrayList <DirectDTO> list2 = ds.selectCalendarInfo(areaname2, calendar_info);//�޷¿� ����� ������ �� ǥ��
		model.addAttribute("player_list", list);
		model.addAttribute("calendar_list", list2);
		return "soccercalendar";
	}

	@RequestMapping(value = "/soccerstrategy")
	public String soccerstrategy(HttpServletRequest request, Model model) {
		String areaname1 = request.getParameter("name");
		String areaname2 = request.getParameter("area");
		DirectService ds = sqlSession.getMapper(DirectService.class);
		ArrayList <DirectDTO> list = ds.selectCalendarArea(areaname1);
		ArrayList <SoccerStrategyDTO> list2 = ds.selectStrategyList(areaname2);
		model.addAttribute("player_list", list);
		model.addAttribute("strategy_list", list2);
		return "soccerstrategy";
	}

	@RequestMapping(value = "/soccerCalendarSave", method = RequestMethod.POST)
	public String soccerCalendarSave(HttpServletRequest request, Model model) {
		String team = request.getParameter("soccer_calendar_team2");//�� �ѱ�
		String team2 = request.getParameter("soccer_calendar_team1");//�� ����
		String traning_date_choose = request.getParameter("traning_date_choose");//����/�����Ʒ� ����
		String traning_select_date1 = request.getParameter("traning_select_date1");//������ �� �޾ƿ��� ��
		String traning_select_date2 = request.getParameter("traning_select_date2");//������ �� �޾ƿ��� ù �� ��
		String traning_select_date3 = request.getParameter("traning_select_date3");//������ �� �޾ƿ��� ������ �� ��
		String traning_people_select = request.getParameter("chked_member_val");//�Ʒ� ������ ��� ����Ʈ
		String traning_map_input = request.getParameter("traning_map_input");//���
		String training_memo_txtarea = request.getParameter("training_memo_txtarea");//�޸�
		String chked_traing_val = request.getParameter("chked_traing_val");//�Ʒ� ���� ����Ʈ
		String calendar_info_val = request.getParameter("calendar_info_val");//�౸���� �߱�����

		DirectService ds = sqlSession.getMapper(DirectService.class);
		if(traning_date_choose.equals("����")) {
			ds.soccercalendarsave(team,traning_select_date1,traning_select_date1,traning_people_select,traning_map_input,chked_traing_val,training_memo_txtarea,calendar_info_val);
		}
		else {
			ds.soccercalendarsave(team,traning_select_date2,traning_select_date3,traning_people_select,traning_map_input,chked_traing_val,training_memo_txtarea,calendar_info_val);
		}

		model.addAttribute("name", team2);
		model.addAttribute("area", team);

		return "redirect:/soccercalendar";
	}

	@RequestMapping(value = "/soccerStrategySave", method = RequestMethod.POST)
	public String soccerStrategySave(HttpServletRequest request, Model model) {
//		������: stnum(stnum_seq), �����̸�: stname, 
//		�౸/�߱� ����: stkind, �߰� ��¥: stdate, 
//		����: starea, �̹��� �� x/y ��ǥ: stifo
		String strategy_name = request.getParameter("strategy_name");//���� �̸�
		String calendar_info_val = "soccer";//�౸���� �߱�����
		String team = request.getParameter("area_kor");//�� �ѱ�//����
		String team2 = request.getParameter("area_eng");//�� ����
		String chked_member_val = request.getParameter("chked_member_val");//���� �̹��� �� x/y ��ǥ

		DirectService ds = sqlSession.getMapper(DirectService.class);
		ds.soccerstrategysave(strategy_name,calendar_info_val,team,chked_member_val);

		model.addAttribute("name", team2);
		model.addAttribute("area", team);

		return "redirect:/soccerstrategy";
	}
	
	@RequestMapping(value = "/strategyListFind")
	public String strategyListFind(HttpServletRequest request, Model model) {
		String name = request.getParameter("name");
		String area = request.getParameter("area");
		int stnum = Integer.parseInt(request.getParameter("stnum"));
		String calendar_info = "soccer";
		
		DirectService ds = sqlSession.getMapper(DirectService.class);
		SoccerStrategyDTO strategy_player_list = ds.strategylistfind(stnum);
		
		ArrayList <DirectDTO> list = ds.selectCalendarArea(name);
		ArrayList <SoccerStrategyDTO> list2 = ds.selectStrategyList(area);
		model.addAttribute("player_list", list);
		model.addAttribute("strategy_list", list2);
		model.addAttribute("strategyPlayerList", strategy_player_list);
		
		return "soccerstrategy";
	}

	@RequestMapping(value = "/soccerStrategyDelete")
	public String soccerStrategyDelete(HttpServletRequest request, Model model) {
		int stnum = Integer.parseInt(request.getParameter("stnum"));
		String name = request.getParameter("name");
		String area = request.getParameter("area");

		DirectService ds = sqlSession.getMapper(DirectService.class);
		ds.soccerStrategyDelete(stnum);

		model.addAttribute("name", name);
		model.addAttribute("area", area);

		return "redirect:/soccerstrategy";
	}

	@RequestMapping(value = "/soccerStrategyUpdate", method = RequestMethod.POST)
	public String soccerStrategyUpdate(HttpServletRequest request, Model model) {
		int stnum = Integer.parseInt(request.getParameter("strategy_stnum"));//���� ��ȣ
		String strategy_name = request.getParameter("strategy_name");//���� �̸�
		String team = request.getParameter("area_kor");//�� �ѱ�//����
		String team2 = request.getParameter("area_eng");//�� ����
		String chked_member_val = request.getParameter("chked_member_val");//���� �̹��� �� x/y ��ǥ

		DirectService ds = sqlSession.getMapper(DirectService.class);
		ds.soccerStrategyUpdate(stnum,strategy_name,chked_member_val);

		model.addAttribute("name", team2);
		model.addAttribute("area", team);
		model.addAttribute("stnum", stnum);

		return "redirect:/strategyListFind";
	}
}