package com.speedrun.cardPattern.object;

import java.util.ArrayList;
import java.util.List;

public class CardPatternOpponentDeck {
	private String rngHexa;
	private int rngIndex;
	private String instantMash;
	private List<String> frames;
	
	public CardPatternOpponentDeck() {

	}
	
	public CardPatternOpponentDeck(String rngHexa, int rngIndex, String instantMash, List<String> frames) {
		this.rngHexa = rngHexa;
		this.rngIndex = rngIndex;
		this.instantMash = instantMash;
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
	
	public String getInstantMash() {
		return instantMash;
	}
	
	public void setInstantMash(String instantMash) {
		this.instantMash = instantMash;
	}
	
	public List<String> getFrames() {
		return frames;
	}
	
	public void setFrames(List<String> frames) {
		this.frames = frames;
	}

	@Override
	public String toString() {
		return "CardPatternOpponentDeck [rngHexa=" + rngHexa + ", rngIndex=" + rngIndex + ", instantMash=" + instantMash
				+ ", frames=" + frames + "]";
	}
	

}
