package com.speedrun.cardPattern.object;

public class CardPatternCardObject {
	
	private String label = "null";
	private int position;
	private int order = 0;
	private boolean opponent;
	
	public CardPatternCardObject() {}
	
	public CardPatternCardObject(String label, int position, int order, boolean opponent) {
		this.label = label;
		this.position = position;
		this.order = order;
		this.opponent = opponent;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public boolean isOpponent() {
		return opponent;
	}
	public void setOpponent(boolean opponent) {
		this.opponent = opponent;
	}

	@Override
	public String toString() {
		return "CardPatternCardObject [label=" + label + ", position=" + position + ", order=" + order + ", opponent="
				+ opponent + "]";
	}


}
