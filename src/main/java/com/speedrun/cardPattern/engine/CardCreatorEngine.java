package com.speedrun.cardPattern.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.speedrun.cardPattern.object.CardPatternBoardObject;
import com.speedrun.cardPattern.object.CardPatternCardObject;
import com.speedrun.cardPattern.object.CardPatternFullGameObject;

public class CardCreatorEngine {

	
	public static CardPatternBoardObject addingBoardObjectDatas(CardPatternBoardObject oldObject, String opponentDeck, List<String> alreadyNamed) {
		CardPatternBoardObject res = oldObject;
		String howToWin = "";
		for(CardPatternCardObject card : oldObject.getListCards()) {
			if(!card.isOpponent() && !card.getLabel().equals("A")) {
				howToWin += card.getLabel() + card.getPosition() + " ";
			}
		}
		String[] names = opponentDeck.split("/");
		String name = Integer.toString(res.getFrame());
		if(names.length > 1) {
			name = names[0].replace(" ", "") + "/" + names[1].replace(" ", "");
		}
		if(alreadyNamed.contains(name) && names.length > 2) {
			name += "/" + names[3].replace(" ","");
		}
		res.setLabel(name);
		res.setWinPattern(howToWin);
		return res;
		
	}
	
	public static List<CardPatternCardObject> createListFromDatas(String frame){
		List<CardPatternCardObject> listRes = new ArrayList<CardPatternCardObject>();
		String[] cards = frame.split(" ");
		List<Integer> position = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
		int playerTurn = 1;
		int opponentTurn = 1;
		for(int i = 0; i < cards.length ; i++) {
			String card = cards[i];
			if(card.contains("(") && card.contains(")")) {
				String[] data = card.replace("(", "").replace(")", "").replace(" ","").split("(?<=\\D)(?=\\d)");
				if(!data[0].equals("") && !data[0].equals("XX") && data.length > 1 && position.contains(Integer.valueOf(data[1]))) {
					listRes.add(new CardPatternCardObject(data[0], Integer.valueOf(data[1]), opponentTurn++, true));
					position.remove(data[1]);
				}
			}else {
				String[] data = card.replace(" ","").split("(?<=\\D)(?=\\d)");
				if(!data[0].equals("") && !data[0].equals("XX") && data.length > 1 && position.contains(Integer.valueOf(data[1]))) {
					listRes.add(new CardPatternCardObject(data[0], Integer.valueOf(data[1]), playerTurn++, false));
					position.remove(data[1]);
				}
			}
		}
		if(listRes.size() < 9 && listRes.size() > 1) {
			for(int unusedPosition : position) {
				if(opponentTurn < 6) {
					listRes.add(new CardPatternCardObject("null", unusedPosition, 0, true));
				}else if(playerTurn < 6){
					listRes.add(new CardPatternCardObject("null", unusedPosition, 0, false));
				}
			}
		}
		return listRes;
	}
	
}
