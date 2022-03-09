package com.speedrun.cardPattern.front;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import com.speedrun.PrincipalFrames;
import com.speedrun.cardPattern.engine.CardCreatorEngine;
import com.speedrun.cardPattern.object.CardPatternBoardObject;
import com.speedrun.cardPattern.object.CardPatternFullGameObject;
import com.speedrun.cardPattern.object.CardPatternOpponentDeck;
import com.speedrun.cardPattern.object.CardPatternRngResult;
import com.speedrun.option.object.Options;
import com.speedrun.utilities.GlobalValues;
import com.speedrun.utilities.GlobalValuesTitle;
import com.speedrun.utilities.graphics.MainJFrame;
import com.speedrun.utilities.toolkit.UtilitiesToolkit;

public class ScreenCardPatternZell implements PrincipalFrames{

			private Options option;
			private MainJFrame mainFrame;

			public ScreenCardPatternZell(Options option){
				this.option = option;
			}

			public void showFrame(Point position) {
				mainFrame = null;
				mainFrame = new MainJFrame(GlobalValuesTitle.ZELL_CARD_PATTERNS,-1,-1,position);
				mainFrame.addPanelWithScroll(getAllTab(addFrameOrderOnZellCard(),true));
			}

			private JTabbedPane getAllTab(Map<String,CardPatternBoardObject> framePatternes, Boolean zellCard) {
				if(framePatternes == null) {
					mainFrame.dispose();
					mainFrame = null;
					return null;
				}
				JTabbedPane mainPane = new JTabbedPane(JTabbedPane.LEFT);
				mainPane.setUI(new BasicTabbedPaneUI(){
					protected void paintContentBorder(Graphics g,int tabPlacement,int selectedIndex){}
				});
				for(Map.Entry<String, CardPatternBoardObject> data : framePatternes.entrySet()) {
					try {
						if (data.getValue().getListCards().size() > 0){
							JPanel pane = FrontFullGame.getPanel(data.getValue(), this.option);
							pane.add(getSouthPanel(data.getValue(),zellCard));
							mainPane.addTab(data.getKey(), pane);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				mainPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

				mainPane.setSelectedIndex(0);
				mainPane.setOpaque(false);
				UtilitiesToolkit.setFont(mainPane, 15, Color.WHITE);

				return mainPane;
			}

			private Map<String,CardPatternBoardObject> addFrameOrderOnZellCard(){
				Map<String,CardPatternBoardObject> listBoard = new LinkedHashMap<String,CardPatternBoardObject>();
				List<String> listGames = new ArrayList<String>();
				listGames.add("B9 () G8 () I7 () M4 () Q3");
				listGames.add("G9 () I8 () M4 () Q3 () A2");
				listGames.add("() G9 () I8 () B3 () Q5 ()");
				List<String> frames = new ArrayList<String>();
				frames.add("");
				frames.add("");
				frames.add("");
				CardPatternFullGameObject indexFramesGames = new CardPatternFullGameObject("0",0,listGames);
				CardPatternOpponentDeck indexFramesOpponnentDeck = new CardPatternOpponentDeck("0",0,"0",frames);
				CardPatternRngResult indexFramesRNGResult = new CardPatternRngResult("0", 0, frames);

				List<String> listLabel = new ArrayList<String>();
				for(int i = 0; i < indexFramesGames.getFrames().size(); i++) {
					CardPatternBoardObject newBoard = new CardPatternBoardObject();
					newBoard.setListCards(CardCreatorEngine.createListFromDatas(indexFramesGames.getFrames().get(i)));
					newBoard = CardCreatorEngine.addingBoardObjectDatas(newBoard, indexFramesOpponnentDeck.getFrames().get(i), listLabel);
					newBoard.setRngResult(indexFramesRNGResult.getFrames().get(i));
					newBoard.setFrame(i+1);
					if(i == 0) {
						newBoard.setLabel("Play First");
					}else if (i ==1 ) {
						newBoard.setLabel("Play Second(1)");
					}else{
						newBoard.setLabel("Play Second(2)");
					}
					listLabel.add(newBoard.getLabel());

					listBoard.put(newBoard.getLabel(), newBoard);

				}

				if(option.getPatternOrderByFrame()) {
					return listBoard;
				}else {
					return new TreeMap<String,CardPatternBoardObject>(listBoard);
				}
			}

			private JPanel getSouthPanel(CardPatternBoardObject object, boolean zellCard) {
				JPanel res = new JPanel(new BorderLayout());



				JLabel framePanel = new JLabel("Frame all", SwingConstants.LEFT);
				framePanel.setForeground(new Color(30,0,0));
				JPanel buttonPanel = new JPanel(new BorderLayout());

				framePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
				UtilitiesToolkit.setSizeOfComponent(framePanel, new Dimension((GlobalValues.WIDTH_FRAME/5 * 4)/5
						,GlobalValues.LINE_BASE_SIZE));
				UtilitiesToolkit.setSizeOfComponent(buttonPanel, new Dimension((GlobalValues.WIDTH_FRAME/5 * 4)/5 * 4
						,GlobalValues.LINE_BASE_SIZE));
				res.add(framePanel,BorderLayout.WEST);
				res.add(buttonPanel,BorderLayout.EAST);
				buttonPanel.setOpaque(false);

				res.setOpaque(false);
				UtilitiesToolkit.setSizeOfComponent(res, new Dimension((GlobalValues.WIDTH_FRAME/5 * 4)
						,40));
				return res;
			}

}
