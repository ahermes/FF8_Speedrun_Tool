package com.speedrun.cardPattern.front;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import com.speedrun.cardPattern.object.CardPatternBoardObject;
import com.speedrun.option.object.Options;
import com.speedrun.utilities.GlobalValues;
import com.speedrun.utilities.GlobalValuesTitle;
import com.speedrun.utilities.graphics.ImagePanel;
import com.speedrun.utilities.toolkit.UtilitiesToolkit;

public class FrontFullGame {
	
	
	public static JPanel getPanel(CardPatternBoardObject object, Options option) throws IOException {
		ImagePanel res = new ImagePanel();
		res.setBackground(new ImageIcon(FrontFullGame.class.getResource("/imageFenetre/tt_bg.png")).getImage());
		JPanel north = FrontBoardObject.getPanel(object);
		res.setLayout(new BoxLayout(res,BoxLayout.PAGE_AXIS));
		
		north.setOpaque(false);
		
		res.add(north);
		res.add(getCenterPanel(object));
//		res.add(getSouthPanel(object,option),c);
//		res.setBackground(Color.RED);
		UtilitiesToolkit.setSizeOfComponent(res, new Dimension(GlobalValues.WIDTH_FRAME/5 * 4,GlobalValues.PATTERN_HEIGHT));
		return res;
	}

	private static JPanel getCenterPanel(CardPatternBoardObject object) {
		JPanel res = new JPanel(new BorderLayout());
		JLabel winPatternLabel = new JLabel(object.getWinPattern(), SwingConstants.LEFT);
		JLabel rngResultLabel = new JLabel(object.getRngResult(), SwingConstants.RIGHT);
		winPatternLabel.setFont(new Font("Arial", 1, 30));
		winPatternLabel.setForeground(new Color(30,0,0));
		rngResultLabel.setFont(new Font("Arial", 1, 30));
		rngResultLabel.setForeground(new Color(30,0,0));
		UtilitiesToolkit.setSizeOfComponent(winPatternLabel, new Dimension((GlobalValues.WIDTH_FRAME/5 * 4)/5 * 3 
				,30));
		UtilitiesToolkit.setSizeOfComponent(rngResultLabel, new Dimension((GlobalValues.WIDTH_FRAME/5 * 4)/5 * 2
				,30));
		res.add(winPatternLabel,BorderLayout.WEST);
		res.add(rngResultLabel,BorderLayout.EAST);
		res.setOpaque(false);
		return res;
	}
	

}
