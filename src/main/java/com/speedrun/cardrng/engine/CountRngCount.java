package com.speedrun.cardrng.engine;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.speedrun.cardPattern.object.CardPatternOpponentDeck;
import com.speedrun.option.object.Options;
import com.speedrun.utilities.GlobalValues;
import com.speedrun.utilities.graphics.ImagePanel;
import com.speedrun.utilities.graphics.ShadowLabel;
import com.speedrun.utilities.helper.CSVHelper;
import com.speedrun.utilities.toolkit.UtilitiesToolkit;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.nio.file.Paths;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountRngCount {
    private Map<String, Integer> pageValue = new HashMap<String, Integer>();
    private ShadowLabel jlabel;
    private String activePage = "";
    private ShadowLabel mashing;
    private ImagePanel panelMashing;
    Map<Integer,CardPatternOpponentDeck> indexFramesOpponnentDeck;
    private Boolean isQuistisPatern = true;
    private Options option;
    
    public CountRngCount(Options option){
    	try {
			this.indexFramesOpponnentDeck = CSVHelper.getAllListOpponentObjectFromCsvFile(Paths.get(GlobalValues.ACCESSIBLE_RESSOURCES_FILES + GlobalValues.OPPONENT_DECK_GAME_FILE));
		} catch (Exception e) {
			this.indexFramesOpponnentDeck = new HashMap<Integer,CardPatternOpponentDeck>();
			e.printStackTrace();
		}
        this.jlabel = new ShadowLabel("   ",GlobalValues.FONT_SIZE_BIG,-1);
        this.mashing = new ShadowLabel("  NONE  ",GlobalValues.FONT_SIZE,-1);
        this.option = option;
    }

    public CountRngCount(Options option, String pageLabel, int baseValue){
    	try {
			this.indexFramesOpponnentDeck = CSVHelper.getAllListOpponentObjectFromCsvFile(Paths.get(GlobalValues.ACCESSIBLE_RESSOURCES_FILES + GlobalValues.OPPONENT_DECK_GAME_FILE));
		} catch (Exception e) {
			this.indexFramesOpponnentDeck = new HashMap<Integer,CardPatternOpponentDeck>();
			e.printStackTrace();
		}
        pageValue.put(pageLabel, baseValue);
        this.jlabel = new ShadowLabel(String.valueOf(baseValue), GlobalValues.FONT_SIZE,-1);
        this.option = option;
    }

    public void increment(String pageLabel){
        this.pageValue.put(pageLabel, this.pageValue.get(pageLabel) + 1);
        reload(pageLabel);

    }

    public void addValue(String pageLabel, int value){
        this.pageValue.put(pageLabel, value);
    }

    public void incrementValue(String pageLabel, int value){
        this.pageValue.put(pageLabel, this.pageValue.get(pageLabel) + value);
        if(pageLabel.equals(this.activePage)) {
            reload(pageLabel);
        }
    }

    public int getHeight(){
        return 75;
    }
    

    public void addValueToActivePage(int value){
        incrementValue(this.activePage, value);
    }

    public void addValueToActivePage(String label, int value){
        incrementValue(label, value);
    }

    public void setValue(String pageLabel, int value){
        this.pageValue.put(pageLabel, value);
        reload(pageLabel);
    }

    public int getValue(String pageLabel){
        return this.pageValue.get(pageLabel);
    }
    
    public int getValueOfActivePage() {
    	return this.pageValue.get(this.activePage);
    }

    public boolean containPage(String pageLabel){
        return this.pageValue.containsKey(pageLabel);
    }

    public void showValue(String pageLabel){
        reload(pageLabel);
    }
    
    public JPanel getPanelWithMashing(String pageLabel) {
    	JPanel panel = new JPanel(new GridBagLayout());
    	GridBagConstraints gbc = new GridBagConstraints();
    	gbc.gridx = 0;
    	gbc.gridy = 0;
    	gbc.insets = new Insets(0, 0, 0, 1);
		setMashingValues();
		panel.add(getResultPanel(pageLabel), gbc);
		gbc.gridx++;
		panel.add(getMashingPanel(), gbc);
		panel.setOpaque(false);
		return panel;
    }

    public JPanel getResultPanel(String pageLabel){
        this.activePage = pageLabel;
        ImagePanel panel = new ImagePanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		panel.setBackground(new ImageIcon(this.getClass().getResource("/imageFenetre/bg.png")).getImage());
		panel.add(UtilitiesToolkit.getLabelBloc("RESULT", GlobalValues.FONT_SIZE, GlobalValues.WIDTH_LINE_BLOCS/5,-1,-1));
        panel.setPreferredSize(new Dimension(GlobalValues.WIDTH_LINE_BLOCS/5, GlobalValues.LINE_BASE_SIZE*2+10));
        panel.add(getPanelWithResult());
        panel.setOpaque(false);
        return panel;
    }
    
    public JPanel getMashingPanel(){
        panelMashing = new ImagePanel();
        panelMashing.setLayout(new BoxLayout(panelMashing,BoxLayout.PAGE_AXIS));
        panelMashing.setBackground(new ImageIcon(this.getClass().getResource("/imageFenetre/bg.png")).getImage());
        panelMashing.add(UtilitiesToolkit.getLabelBloc("Instant Mash?",GlobalValues.FONT_SIZE, GlobalValues.WIDTH_LINE_BLOCS/5, -1, -1));
        panelMashing.setPreferredSize(new Dimension(GlobalValues.WIDTH_LINE_BLOCS/5, GlobalValues.LINE_BASE_SIZE*2+10));
        panelMashing.setMinSize(GlobalValues.WIDTH_LINE_BLOCS/5,GlobalValues.LINE_BASE_SIZE*2+10);
        panelMashing.add(getMashing());
        panelMashing.setOpaque(false);
        return panelMashing;
    }
    
    public void setQuistisPatern(Boolean newState) {
    	this.isQuistisPatern = newState;
    }
    
    private JPanel getPanelWithResult() {
    	JPanel panel = new JPanel();
    	panel.add(jlabel);
    	jlabel.setShadowText(String.valueOf(this.pageValue.get(this.activePage)));
    	UtilitiesToolkit.setSizeOfComponent(panel, new Dimension(GlobalValues.WIDTH_LINE_BLOCS/5, GlobalValues.LINE_BASE_SIZE+10));
    	panel.setOpaque(false);
    	return panel;
    }
    
    private JPanel getMashing() {
    	JPanel panel = new JPanel();
    	panel.add(mashing);
    	UtilitiesToolkit.setSizeOfComponent(panel, new Dimension(GlobalValues.WIDTH_LINE_BLOCS/5, GlobalValues.LINE_BASE_SIZE));
   	    panel.setOpaque(false);
    	return panel;
    }
    
    private void setMashingValues(){
        if(indexFramesOpponnentDeck.containsKey(this.pageValue.get(this.activePage)) && isQuistisPatern) {
            String instantMash = this.indexFramesOpponnentDeck.get(this.pageValue.get(this.activePage)).getInstantMash();
            String frameGetted = "";
            int availableFrame = getSizeOfFrames(this.indexFramesOpponnentDeck.get(this.pageValue.get(this.activePage)));
            if (option.getBackgroundColorRngCount()) {
                int mashValue = Integer.valueOf(instantMash.replace(" ", "").split("=")[1]);
                if(mashValue < 4){
                    this.panelMashing.setColorBackground(Color.decode("#6f0000"));
                    this.panelMashing.getRootPane().setBackground(Color.decode("#6f0000"));
                }else if(mashValue < 46){
                    this.panelMashing.getRootPane().setBackground(Color.decode("#c47312"));
                    this.panelMashing.setColorBackground(Color.decode("#c47312"));
                }else{
                    this.panelMashing.getRootPane().setBackground(Color.decode("#9b9100"));
                    this.panelMashing.setColorBackground(Color.decode("#9b9100"));
                }
            } else {
                if (Integer.valueOf(instantMash.replace(" ", "").split("=")[1]) <= 2 && Integer.valueOf(instantMash.replace(" ", "").split("=")[1]) > 0) {
                    this.mashing.setColor(Color.decode("#5985FF"));
                } else if (availableFrame < 10) {
                    frameGetted = " (" + String.valueOf(availableFrame) + ")";
                    this.mashing.setColor(Color.red);
                } else {
                    this.mashing.setColor(Color.WHITE);
                }
            }
            this.mashing.setShadowText( instantMash + frameGetted);
        }else{
            if(panelMashing != null) {
                this.panelMashing.getRootPane().setBackground(null);
                this.panelMashing.setColorBackground(null);
            }
            this.mashing.setInvertColors(false);
            this.mashing.setInvertColors(false);
            this.mashing.setColor(Color.WHITE);
            this.mashing.setShadowText("     NONE     ");
        }
    }
    
    private void reload(String pageLabel){
        this.activePage = pageLabel;
        setMashingValues();
        jlabel.setShadowText(String.valueOf(this.pageValue.get(this.activePage)));
        
    }

    private int getSizeOfFrames(CardPatternOpponentDeck deck){
        int res = 0;
        for(String frame : deck.getFrames()){
            if(!frame.equals("XX")) {
                res++;
            }
        }
        return res;
    }
}
