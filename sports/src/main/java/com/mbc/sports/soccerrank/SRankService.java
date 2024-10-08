package com.mbc.sports.soccerrank;

import java.util.ArrayList;

public interface SRankService {

	ArrayList<SoccerTeamDTO> teamRank();

	ArrayList<SoccerPlayerDTO> playerRank();

	ArrayList<SoccerPlayerDTO> playerAssistRank();

	ArrayList<SoccerPlayerDTO> playerMOMRank();
	
}