package com.mbc.sports.soccerrank;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SoccerRankinigController {
	@Autowired
	SqlSession sqlSession;
	
	@RequestMapping(value="/soccerTeamRanking")
	public String scteamrank(Model model) {
		SRankService srs = sqlSession.getMapper(SRankService.class);
		ArrayList<SoccerTeamDTO> strlist = srs.teamRank();
		model.addAttribute("strlist",strlist);
		return "soccerTeamRank";
	}
	@RequestMapping(value="/soccerPlayerRanking")
	public String scplayerrank(Model model) {
		SRankService srs = sqlSession.getMapper(SRankService.class);
		ArrayList<SoccerPlayerDTO> sprlist = srs.playerRank();
		ArrayList<SoccerPlayerDTO> sprassist = srs.playerAssistRank();
		ArrayList<SoccerPlayerDTO> sprmom = srs.playerMOMRank();
		model.addAttribute("sprlist",sprlist);
		model.addAttribute("sprassist",sprassist);
		model.addAttribute("sprmom",sprmom);
		return "soccerPlayerRank";
	}
	@RequestMapping(value="/resultRegist")
	public String sprankregist() {
		return "soccerRankRegist";
	}
	
	@RequestMapping(value="/resultEdit")
	public String sprankedit() {
		return "soccerRankEdit";
	}

	@RequestMapping(value="/selectRankTeam",method = RequestMethod.POST)
	public String selectRankTeam(HttpServletRequest request) {
		return "soccerRankEdit";
	}
}