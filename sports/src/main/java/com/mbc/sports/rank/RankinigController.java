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
public class RankinigController {
	@Autowired
	SqlSession sqlSession;
	
	@RequestMapping(value="/soccerTeamRank")
	public String scteamrank(Model model) throws IOException {
		ArrayList<SoccerTeamDTO> list = new ArrayList<>();
			Document doc = Jsoup.connect("https://sports.news.naver.com/kfootball/record/index?category=kleague")
					.userAgent(
							"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36")
					.get();
			// select�� �̿��ؼ� tr���� �ҷ�����
		Elements soccerTeamsA = doc.select("#splitGroupA_table > tr");
		// tr���� �ݺ��� ������
		for (Element soccerTeam : soccerTeamsA) {
			Element rank = soccerTeam.selectFirst("th"); // ����
			Element title = soccerTeam.selectFirst("span:nth-child(2)"); // ����
			Element round = soccerTeam.selectFirst("td:nth-child(3)"); // ��� ��
			Element points = soccerTeam.selectFirst("td:nth-child(4)"); // ����
			Element win = soccerTeam.selectFirst("td:nth-child(5)"); // ��
			Element draw = soccerTeam.selectFirst("td:nth-child(6)"); // ��
			Element lose = soccerTeam.selectFirst("td:nth-child(7)"); // ��
			Element score = soccerTeam.selectFirst("td:nth-child(8)"); // ����
			Element conceded = soccerTeam.selectFirst("td:nth-child(9)"); // ����
			Element difference = soccerTeam.selectFirst("td:nth-child(10)"); // �����
			Element assist = soccerTeam.selectFirst("td:nth-child(11)"); // ����
			Element foul = soccerTeam.selectFirst("td:nth-child(12)"); // �Ŀ�
			if (title != null) {
				SoccerTeamDTO teamData = new SoccerTeamDTO(rank.text(),title.text(), round.text(), points.text(),win.text(), draw.text(), lose.text(), score.text(), conceded.text(), difference.text(), assist.text(), foul.text());
				list.add(teamData);
			}
		}
		//
		Elements soccerTeamsB = doc.select("#splitGroupB_table > tr");
		for (Element soccerTeam : soccerTeamsB) {
			Element rank = soccerTeam.selectFirst("th"); // ����
			Element title = soccerTeam.selectFirst("span:nth-child(2)"); // ����
			Element round = soccerTeam.selectFirst("td:nth-child(3)"); // ��� ��
			Element points = soccerTeam.selectFirst("td:nth-child(4)"); // ����
			Element win = soccerTeam.selectFirst("td:nth-child(5)"); // ��
			Element draw = soccerTeam.selectFirst("td:nth-child(6)"); // ��
			Element lose = soccerTeam.selectFirst("td:nth-child(7)"); // ��
			Element score = soccerTeam.selectFirst("td:nth-child(8)"); // ����
			Element conceded = soccerTeam.selectFirst("td:nth-child(9)"); // ����
			Element difference = soccerTeam.selectFirst("td:nth-child(10)"); // �����
			Element assist = soccerTeam.selectFirst("td:nth-child(11)"); // ����
			Element foul = soccerTeam.selectFirst("td:nth-child(12)"); // �Ŀ�
			if (title != null) {
				SoccerTeamDTO teamData = new SoccerTeamDTO(rank.text(),title.text(), round.text(), points.text(),win.text(), draw.text(), lose.text(), score.text(), conceded.text(), difference.text(), assist.text(), foul.text());
				list.add(teamData);
			}
		}
		model.addAttribute("list",list);
		return "soccerTeamRank";
	}

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
		return "baseballPlayerRank";
	}
	
	
	@RequestMapping(value="/scPlayerRank")
	public String scplayerrank(Model model) throws IOException {
		ArrayList<SoccerPlayerDTO> list = new ArrayList<>();
		Document doc = Jsoup.connect("https://www.kleague.com/record/player.do")
			.userAgent(
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36")
			.get();
		// select�� �̿��ؼ� tr���� �ҷ�����
		Elements soccerPlayers = doc.select("#player_rank > tr");
		// tr���� �ݺ��� ������ 
		for (Element soccerPlayer : soccerPlayers) {
			Element rank = soccerPlayer.selectFirst("td:nth-child(1)"); // ����
			Element playerName = soccerPlayer.selectFirst("td:nth-child(2)"); // �����̸�
			Element teamName = soccerPlayer.selectFirst("td:nth-child(3)"); // ���̸�
			Element score = soccerPlayer.selectFirst("td:nth-child(4)"); // ����
			Element assist = soccerPlayer.selectFirst("td:nth-child(5)"); // ����
			Element attackPoint = soccerPlayer.selectFirst("td:nth-child(6)"); // ��������Ʈ
			Element conceded = soccerPlayer.selectFirst("td:nth-child(7)"); // ����
			Element conner = soccerPlayer.selectFirst("td:nth-child(8)"); // �ڳ�ű
			Element foul = soccerPlayer.selectFirst("td:nth-child(9)"); // �Ŀ�
			Element shooting = soccerPlayer.selectFirst("td:nth-child(10)"); // ����
			Element offside = soccerPlayer.selectFirst("td:nth-child(11)"); // �������̵�
			Element warning = soccerPlayer.selectFirst("td:nth-child(12)"); // ���
			Element exit = soccerPlayer.selectFirst("td:nth-child(13)"); // ����
			Element scoreless = soccerPlayer.selectFirst("td:nth-child(14)"); // ���������
			Element businessTrip = soccerPlayer.selectFirst("td:nth-child(15)"); // ����
			Element change = soccerPlayer.selectFirst("td:nth-child(16)"); // ��ü
			Element record = soccerPlayer.selectFirst("td:nth-child(17)"); // ������
			if (rank != null) {
				SoccerPlayerDTO playerData = new SoccerPlayerDTO(rank.text(),playerName.text(), teamName.text(), score.text(),assist.text(),attackPoint.text(), conceded.text(), conner.text(), foul.text(), shooting.text(), offside.text(),warning.text(), exit.text(), scoreless.text(), businessTrip.text(), change.text(), record.text());
				list.add(playerData);
			}
		}
		model.addAttribute("list",list);
		return "scPlayerRank";
	}
}