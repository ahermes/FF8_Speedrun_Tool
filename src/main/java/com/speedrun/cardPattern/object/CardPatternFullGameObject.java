package com.speedrun.cardPattern.object;

import java.util.ArrayList;
import java.util.List;

public class CardPatternFullGameObject {
	private String rngHexa;
	private int rngIndex;
	private List<String> frames;
	
	public CardPatternFullGameObject() {

	}
	
	public CardPatternFullGameObject(String rngHexa, int rngIndex, List<String> list) {
		this.rngHexa = rngHexa;
		this.rngIndex = rngIndex;
		this.frames = list;
	}
	
	public String getRngHexa() {
		return rngHexa;
	}
	
	public void setRngHexa(String rngHexa) {
		this.rngHexa = rngHexa;
	}
	
	public int getRngIndex() {
		return rngIndex;
	}
	
	public void setRngIndex(int rngIndex) {
		this.rngIndex = rngIndex;
	}
	
	public List<String> getFrames() {
		return frames;
	}
	
	public void setFrames(ArrayList<String> frames) {
		this.frames = frames;
	}

	@Override
	public String toString() {
		return "CardPatternFullGameObject [rngHexa=" + rngHexa + ", rngIndex=" + rngIndex + ", frames=" + frames + "]";
	}
	

}
