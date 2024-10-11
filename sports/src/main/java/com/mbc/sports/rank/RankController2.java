package com.mbc.sports.rank;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RankController2 {
	@Autowired
	SqlSession sqlSession;
	
	// KBO ���� �� ���� �������� by Jsoup
	@RequestMapping(value="/baseballTeamRank")
	public String scrapeRank(Model model) {
		ArrayList<BaseballTeamDTO> list = new ArrayList<>();
		try {
			Document doc = Jsoup.connect("https://sports.news.naver.com/kbaseball/record/index.nhn?category=kbo")
					.userAgent(
							"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36")
					.get();
			// select�� �̿��ؼ� tr���� �ҷ�����
			Elements baseballTeams = doc.select("#regularTeamRecordList_table > tr");
			// tr���� �ݺ��� ������
			for (Element baseballTeam : baseballTeams) {
				Element rank = baseballTeam.selectFirst("th"); // �� ��
				Element title = baseballTeam.selectFirst("span:nth-child(2)"); // �� ��
				Element match = baseballTeam.selectFirst("td:nth-child(3)"); // ��� ��
				Element victory = baseballTeam.selectFirst("td:nth-child(4)"); // ��
				Element defeat = baseballTeam.selectFirst("td:nth-child(5)"); // ��
				Element draw = baseballTeam.selectFirst("td:nth-child(6)"); // ��
				Element rate = baseballTeam.selectFirst("td:nth-child(7)"); // �·�
				Element between = baseballTeam.selectFirst("td:nth-child(8)"); // ������
				Element winning = baseballTeam.selectFirst("td:nth-child(9)"); // ����
				Element base = baseballTeam.selectFirst("td:nth-child(10)"); // �����
				Element slugging = baseballTeam.selectFirst("td:nth-child(11)"); // ��Ÿ��
				Element recent = baseballTeam.selectFirst("td:nth-child(12)"); // �ֱ� 10���
				if (title != null) {
					String image = title.text();
					BaseballTeamDTO teamData = new BaseballTeamDTO(rank.text(), image, title.text(), match.text(), victory.text(),
							defeat.text(), draw.text(), rate.text(), between.text(), winning.text(), base.text(), slugging.text(), recent.text());
					list.add(teamData);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.addAttribute("list",list);
		return "baseballTeamRank";
	}

	
	// KBO ���� ���� ���� �������� by Jsoup
	@RequestMapping(value="/baseballPlayerRank")
	public void playerRank(Model model) {
	//	ArrayList<BaseballPlayerDTO> list = new ArrayList<>();
		try {
			Document doc = Jsoup.connect("https://sports.news.naver.com/kbaseball/record/index.nhn?category=kbo")
					.userAgent(
							"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36")
					.get();
			// select�� �̿��ؼ� tr���� �ҷ�����
			Elements baseballSelects = doc.select(".pitcher > tbody tr");
			// tr���� �ݺ��� ������
			for (Element baseballSelect : baseballSelects) {
				Element recent = baseballSelect.selectFirst("td:nth-child(1) li"); 
				System.out.println(recent);
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
//		model.addAttribute("list",list);
//		return "baseballTeamRank";
	}
}