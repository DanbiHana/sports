package com.mbc.sports.main;
public class MainSoccerTeamDTO {
	String rank,title,win,draw,lose;
	public MainSoccerTeamDTO() {}
	public MainSoccerTeamDTO(String rank, String title, String win, String draw, String lose) {
		super();
		this.rank = rank;
		this.title = title;
		this.win = win;
		this.draw = draw;
		this.lose = lose;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWin() {
		return win;
	}
	public void setWin(String win) {
		this.win = win;
	}
	public String getDraw() {
		return draw;
	}
	public void setDraw(String draw) {
		this.draw = draw;
	}
	public String getLose() {
		return lose;
	}
	public void setLose(String lose) {
		this.lose = lose;
	}
}