package com.speedrun.cardPattern.object;

import java.util.List;

public class CardPatternBoardObject {
	private String label;
	private List<CardPatternCardObject> listCards;
	private String winPattern;
	private String rngResult;
	private int frame;
	
	public CardPatternBoardObject() {
		
	}
	
	public CardPatternBoardObject(String label, List<CardPatternCardObject> listCards, String winPattern, String rngResult) {
		this.label = label;
		this.listCards = listCards;
		this.winPattern = winPattern;
		this.rngResult = rngResult;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public List<CardPatternCardObject> getListCards() {
		return listCards;
	}
	
	public void setListCards(List<CardPatternCardObject> listCards) {
		this.listCards = listCards;
	}
	
	public String getWinPattern() {
		return winPattern;
	}
	
	public void setWinPattern(String winPattern) {
		this.winPattern = winPattern;
	}
	
	public String getRngResult() {
		return this.rngResult;
	}
	
	public void setRngResult(String rngResult) {
		this.rngResult = rngResult;
	}

	public int getFrame() {
		return frame;
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}

	@Override
	public String toString() {
		return "CardPatternBoardObject [label=" + label + ", listCards=" + listCards + ", winPattern=" + winPattern
				+ ", rngResult=" + rngResult + ", frame=" + frame + "]";
	}


	
}
