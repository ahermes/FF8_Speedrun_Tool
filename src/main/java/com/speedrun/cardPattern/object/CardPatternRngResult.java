package com.speedrun.cardPattern.object;

import java.util.ArrayList;
import java.util.List;

public class CardPatternRngResult {
	private String rngHexa;
	private int rngIndex;
	private List<String> frames;
	
	public CardPatternRngResult() {

	}
	
	public CardPatternRngResult(String rngHexa, int rngIndex, List<String> frames) {
		this.rngHexa = rngHexa;
		this.rngIndex = rngIndex;
		this.frames = frames;
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
	
	public void setFrames(List<String> frames) {
		this.frames = frames;
	}

	@Override
	public String toString() {
		return "CardPatternRngResult [rngHexa=" + rngHexa + ", rngIndex=" + rngIndex + ", frames=" + frames + "]";
	}
	
	
}
