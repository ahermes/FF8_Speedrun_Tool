package com.speedrun.cardPattern.front;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.util.Map;

import javax.swing.JPanel;

import com.speedrun.cardPattern.object.CardPatternBoardObject;
import com.speedrun.cardPattern.object.CardPatternCardObject;

public class FrontBoardObject {
	
    public static JPanel getPanel(CardPatternBoardObject object) throws IOException{
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        JPanel panel  = new JPanel(new GridBagLayout());
        new JPanel(new GridBagLayout());
        for(CardPatternCardObject card : object.getListCards()){
            gbc.gridx = (card.getPosition()-1) % 3;
            gbc.gridy = (card.getPosition()-1) / 3;
            panel.add(FrontCardObject.getPanel(card), gbc);
        }
        panel.setOpaque(false);
        return panel;

    }
}
