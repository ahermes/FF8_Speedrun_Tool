package com.speedrun.cardPattern.front;

import com.speedrun.PrincipalFrames;
import com.speedrun.cardPattern.engine.CardCreatorEngine;
import com.speedrun.cardPattern.object.CardPatternBoardObject;
import com.speedrun.cardPattern.object.CardPatternFullGameObject;
import com.speedrun.cardPattern.object.CardPatternOpponentDeck;
import com.speedrun.cardPattern.object.CardPatternRngResult;
import com.speedrun.option.object.Options;
import com.speedrun.utilities.ComponentResizer;
import com.speedrun.utilities.GlobalValues;
import com.speedrun.utilities.GlobalValuesTitle;
import com.speedrun.utilities.graphics.MainJFrame;
import com.speedrun.utilities.helper.CSVHelper;
import com.speedrun.utilities.toolkit.UtilitiesToolkit;

import org.apache.commons.csv.CSVRecord;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.TabbedPaneUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class ScreenCardPattern implements PrincipalFrames{

	private Options option;
	private int frame;
	private MainJFrame mainFrame;
	private ScreenCardPatternZellExec screenCardPatternZellExec;

	public ScreenCardPattern(int frame, Options option, ScreenCardPatternZellExec screenCardPatternZellExec){
		this.option = option;
		this.frame = frame;
		this.screenCardPatternZellExec = screenCardPatternZellExec;
	}

	public void showFrame(Point position) {
		mainFrame = null;
		mainFrame = new MainJFrame(GlobalValuesTitle.QUISTIS_CARD_PATTERNS,-1,-1,position);
		mainFrame.addPanelWithScroll(getAllTab(addFrameOrderOnQuistisCard(frame), false));
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
			List<String> keys = new ArrayList(framePatternes.keySet());
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

	public void setFrames(int frame) {
		this.frame =frame;
	}

	private Map<String,CardPatternBoardObject> addFrameOrderOnQuistisCard(int index) {
		Map<String,CardPatternBoardObject> listBoard = new LinkedHashMap<String,CardPatternBoardObject>();
		try {
			CardPatternFullGameObject indexFramesGames  = CSVHelper.getListFullGameObjectFromCsvFile(index, Paths.get(GlobalValues.ACCESSIBLE_RESSOURCES_FILES + GlobalValues.FULL_GAME_PATTERNS_FILE));
			CardPatternOpponentDeck indexFramesOpponnentDeck  = CSVHelper.getListOpponentObjectFromCsvFile(index, Paths.get(GlobalValues.ACCESSIBLE_RESSOURCES_FILES + GlobalValues.OPPONENT_DECK_GAME_FILE));
			CardPatternRngResult indexFramesRNGResult  = CSVHelper.getListRngResultsObjectFromCsvFile(index, Paths.get(GlobalValues.ACCESSIBLE_RESSOURCES_FILES + GlobalValues.RNG_RESULT_GAME_FILE));

			List<String> listLabel = new ArrayList<String>();
			for(int i = 0; i < indexFramesGames.getFrames().size(); i++) {
				CardPatternBoardObject newBoard = new CardPatternBoardObject();
				newBoard.setListCards(CardCreatorEngine.createListFromDatas(indexFramesGames.getFrames().get(i)));
				newBoard = CardCreatorEngine.addingBoardObjectDatas(newBoard, indexFramesOpponnentDeck.getFrames().get(i), listLabel);
				newBoard.setRngResult(indexFramesRNGResult.getFrames().get(i));
				newBoard.setFrame(i+1);
				listLabel.add(newBoard.getLabel());
				listBoard.put(newBoard.getLabel(), newBoard);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		if(option.getPatternOrderByFrame()) {
			return listBoard;
		}else {
			return new TreeMap<String,CardPatternBoardObject>(listBoard);
		}
	}

	private JPanel getSouthPanel(CardPatternBoardObject object, boolean zellCard) {
		JPanel res = new JPanel(new BorderLayout());



		JLabel framePanel = new JLabel("Frame " + object.getFrame(), SwingConstants.LEFT);
		framePanel.setForeground(new Color(30,0,0));
		JPanel buttonPanel = new JPanel(new BorderLayout());

		framePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		if(!zellCard) {
			JButton saveRNG = new JButton("Save this pattern");
			saveRNG.setBackground(Color.decode("#c6b083"));
			saveRNG.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					option.setRngQuistis(object.getRngResult());
					JOptionPane.showMessageDialog(res, "Pattern saved!");
				}
			});
			if(this.option.isFromMenu()) {
				JButton button = new JButton("Zell Card");
				button.setBackground(Color.decode("#c6b083"));
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						option.setRngQuistis(object.getRngResult());
						JOptionPane.showMessageDialog(res, "Pattern saved!");
						screenCardPatternZellExec.showFrame(mainFrame.getLocation());
						mainFrame.dispose();
						mainFrame = null;
					}
				});
				buttonPanel.add(button,BorderLayout.EAST);
			}
			buttonPanel.add(saveRNG,BorderLayout.WEST);
		}
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
