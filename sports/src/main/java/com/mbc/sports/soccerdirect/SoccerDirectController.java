package com.mbc.sports.soccerdirect;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

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
	 @RequestMapping(value = "/soccercalendar")
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
	 @RequestMapping(value = "/soccerCalendarMove")
	 public String soccerCalendarMove(HttpServletRequest request, Model model) {
	 	 String areaname = request.getParameter("name");//����
	 	 String areaname2 = request.getParameter("area");//�ѱ�
	 	 String calendar_info = "soccer";
	 	 String startdate = request.getParameter("start");
	 	 DirectService ds = sqlSession.getMapper(DirectService.class);
	 	 ArrayList <DirectDTO> list = ds.selectCalendarArea(areaname);
	 	 ArrayList <DirectDTO> list2 = ds.selectCalendarData(areaname2, calendar_info, startdate);
	 	 model.addAttribute("player_list", list);
	 	 model.addAttribute("calendar_list", list2);
	 	 model.addAttribute("calendar_data", startdate);
	 	 return "soccercalendar";
	 }
	 @RequestMapping(value = "/soccerCalendarGet")
	 public String soccerCalendarGet(HttpServletRequest request, Model model) {
	 	 String areaname = request.getParameter("name");
	 	 String areaname2 = request.getParameter("area");
	 	 String calendar_info = "soccer";
	 	 String startdate = request.getParameter("start");
	 	 int number = Integer.parseInt(request.getParameter("number"));
	 	 DirectService ds = sqlSession.getMapper(DirectService.class);
	 	 ArrayList <DirectDTO> list = ds.selectCalendarArea(areaname);
	 	 ArrayList <DirectDTO> list2 = ds.selectCalendarData(areaname2, calendar_info, startdate);
	 	 ArrayList <DirectDTO> list3 = ds.selectCalendarDetail(areaname2, calendar_info, number);
	 	 model.addAttribute("player_list", list);
	 	 model.addAttribute("calendar_list", list2);
	 	 model.addAttribute("calendar_data", startdate);
	 	 model.addAttribute("calendar_detail", list3);
	 	 return "soccercalendar";
	 }
	 @RequestMapping(value = "/soccerCalendarUpdate")
	 public String soccerCalendarUpdate(HttpServletRequest request, Model model) {
	 	 String team = request.getParameter("soccer_team2");//�� �ѱ�
	 	 String team2 = request.getParameter("soccer_team1");//�� ����
	 	 String traning_date_choose = request.getParameter("traning_date_choose2");//����/�����Ʒ� ����
	 	 String traning_select_date1 = request.getParameter("traning_select_date4");//������ �� �޾ƿ��� ��
	 	 String traning_select_date2 = request.getParameter("traning_select_date5");//������ �� �޾ƿ��� ù �� ��
	 	 String traning_select_date3 = request.getParameter("traning_select_date6");//������ �� �޾ƿ��� ������ �� ��
	 	 String traning_people_select = request.getParameter("chked_member_val2");//�Ʒ� ������ ��� ����Ʈ
	 	 String traning_map_input = request.getParameter("traning_map_input2");//���
	 	 String training_memo_txtarea = request.getParameter("training_memo_txtarea2");//�޸�
	 	 String chked_traing_val = request.getParameter("chked_traing_val2");//�Ʒ� ���� ����Ʈ
	 	 String calendar_info_val = request.getParameter("calendar_info_val2");//�౸���� �߱�����
	 	 int trnum = Integer.parseInt(request.getParameter("chked_member_num2"));//
	 	 String startdate = request.getParameter("startdate2");
	 	 String calendar_info = "soccer";
	 	 logger.info("ù��: " + traning_select_date1);
	 	 logger.info("��������: " + traning_select_date2);
	 	 DirectService ds = sqlSession.getMapper(DirectService.class);
	 	 if(traning_date_choose.equals("����")) {
	 	 	 ds.soccerCalendarUpdate(traning_select_date1,traning_select_date1,traning_people_select,traning_map_input,chked_traing_val,training_memo_txtarea,trnum);
	 	 }
	 	 else {
	 	 	 ds.soccerCalendarUpdate(traning_select_date2,traning_select_date3,traning_people_select,traning_map_input,chked_traing_val,training_memo_txtarea,trnum);
	 	 }
	 	 ArrayList <DirectDTO> list = ds.selectCalendarArea(team2);
	 	 ArrayList <DirectDTO> list2 = ds.selectCalendarData(team, calendar_info, startdate);
	 	 ArrayList <DirectDTO> list3 = ds.selectCalendarDetail(team, calendar_info, trnum);
	 	 model.addAttribute("player_list", list);
	 	 model.addAttribute("calendar_list", list2);
	 	 model.addAttribute("calendar_data", startdate);
	 	 model.addAttribute("calendar_detail", list3);
	 	 return "soccercalendar";
	 }
	 @RequestMapping(value = "/soccerCalendarDelete")
	 public String soccerCalendarDelete(HttpServletRequest request, Model model) {
	 	 String areaname = request.getParameter("name");
	 	 String areaname2 = request.getParameter("area");
	 	 String calendar_info = "soccer";
	 	 String startdate = request.getParameter("start");
	 	 int num = Integer.parseInt(request.getParameter("num"));
	 	 DirectService ds = sqlSession.getMapper(DirectService.class);
	 	 ds.soccerCalendarDelete(num);
	 	 ArrayList <DirectDTO> list = ds.selectCalendarArea(areaname);
	 	 ArrayList <DirectDTO> list2 = ds.selectCalendarData(areaname2, calendar_info, startdate);
	 	 model.addAttribute("player_list", list);
	 	 model.addAttribute("calendar_list", list2);
	 	 model.addAttribute("calendar_data", startdate);
		 return "soccercalendar";
	 }
	 

	 //����������
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
	 @RequestMapping(value = "/soccerStrategySave", method = RequestMethod.POST)
	 public String soccerStrategySave(HttpServletRequest request, Model model) {
//	 	 ������: stnum(stnum_seq), �����̸�: stname, 
//	 	 �౸/�߱� ����: stkind, �߰� ��¥: stdate, 
//	 	 ����: starea, �̹��� �� x/y ��ǥ: stifo
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