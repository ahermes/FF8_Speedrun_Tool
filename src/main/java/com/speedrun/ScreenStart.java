package com.speedrun;
import com.speedrun.cardPattern.front.ScreenCardPattern;
import com.speedrun.cardPattern.front.ScreenCardPatternChooseFrame;
import com.speedrun.cardPattern.front.ScreenCardPatternZell;
import com.speedrun.cardPattern.front.ScreenCardPatternZellExec;
import com.speedrun.cardrng.front.ScreenCardRng;
import com.speedrun.option.engine.OptionHelper;
import com.speedrun.option.front.ScreenOption;
import com.speedrun.option.object.Options;
import com.speedrun.pole.front.ScreenPole;
import com.speedrun.theEndTable.front.ScreenTheEndManipulation;
import com.speedrun.utilities.GlobalValues;
import com.speedrun.utilities.GlobalValuesTitle;
import com.speedrun.utilities.toolkit.UtilitiesToolkit;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class ScreenStart extends JFrame implements ActionListener {
    public GridBagConstraints gbc = new GridBagConstraints();
    private Options option;
    private ScreenCardPattern screenCardPattern;
    private ScreenPole screenPole;
    private ScreenCardPatternChooseFrame screenCardPatternChooseFrame;
    private ScreenOption screenOption;
    private ScreenCardRng screenCardRng;
    private ScreenCardPatternZellExec screenCardPatternZellExec;
    private ScreenCardPatternZell screenCardPatternZell;
    private ScreenTheEndManipulation screenTheEndManipulation;
    
    public ScreenStart() {
        this.option = OptionHelper.optionLoader();
        initAllScreen();
        UIManager.put("TabbedPane.highlight",Color.GRAY);
		UIManager.put("TabbedPane.shadow",Color.BLACK);
		UIManager.put("TabbedPane.selected",  new Color(150,150,150));
		UIManager.put("TabbedPane.background", new Color(0,0,0,0));
		
		this.setIconImage(new ImageIcon(GlobalValues.ACCESSIBLE_RESSOURCES + "icon.png").getImage());
        this.setLocation(this.getLocation());
        this.setTitle("FFVIII Tool Manip");
        this.setFocusable(true);
        this.setSize(790, 510);
        this.getContentPane().add(UtilitiesToolkit.addJScrollPane(getPanel()));
        this.setDefaultCloseOperation(2);
        this.setLocationRelativeTo((Component)null);
        this.setResizable(true);
        this.setVisible(true);
        this.setBackground(Color.BLACK);
        
        if(option.getShowPopUp()) {
        	PopUpFrame popUp = new PopUpFrame(option, "Warning", this.getLocation());
        	popUp.setText("Don't forget to setup your script path in option");
        	popUp.setVisible(true);
        }

    }

    private void initAllScreen() {
    	
    	this.screenPole = new ScreenPole();
    	this.screenOption = new ScreenOption(option);
    	this.screenCardPatternZell = new ScreenCardPatternZell(option);
    	this.screenCardPatternZellExec = new ScreenCardPatternZellExec(option, this.screenCardPatternZell);
    	this.screenCardPattern = new ScreenCardPattern(0,option, this.screenCardPatternZellExec);
    	this.screenCardPatternChooseFrame = new ScreenCardPatternChooseFrame(screenCardPattern,this.option);
    	this.screenCardRng = new ScreenCardRng(screenCardPattern, screenCardPatternZell, option);
    	this.screenTheEndManipulation = new ScreenTheEndManipulation(option);
    }
    
    public JPanel getPanel() {
    	JPanel panel = new JPanel();
        JPanel center = new JPanel(new GridBagLayout());
        panel.setBackground(Color.black);
        
        
        this.gbc.insets = new Insets(5, 0, 0, 0);
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        this.gbc.anchor = 23;
        this.gbc.anchor = 10;
        this.gbc.gridwidth = 3;
        center.add(new JLabel(new ImageIcon(this.getClass().getResource("/imageFenetre/Start.png"))), this.gbc);
        this.gbc.gridx = 0;
        this.gbc.gridy = 1;
       // center.add(UtilitiesToolkit.getLabelBloc("V1", GlobalValues.FONT_SIZE, 100, 100, -1));
        for(JButton button : setAllButton()) {
        	center.add(button, gbc);
        	gbc.gridy++;
        }
        center.setOpaque(false);
        panel.add(center);
        return panel;
    }
    
    private ArrayList<JButton> setAllButton(){
    	ArrayList<JButton> buttonList = new ArrayList<JButton>();
    	buttonList.add(createButton(GlobalValuesTitle.CARD_RNG_MANIPULATION));
    	buttonList.add(createButton(GlobalValuesTitle.QUISTIS_CARD_PATTERNS));
    	buttonList.add(createButton(GlobalValuesTitle.POLES));
    	buttonList.add(createButton(GlobalValuesTitle.THE_END_MANIPULATION));
    	buttonList.add(createButton(GlobalValuesTitle.OPTIONS));
    	return buttonList;
    }
    
    private JButton createButton(String name) {
    	JButton button = new JButton(name);
    	Font font = new Font("Calibri", 1, 15);
    	button.setOpaque(false);
    	button.setContentAreaFilled(false);
    	button.setBorderPainted(false);
    	button.setFont(font);
    	button.setForeground(Color.WHITE);
    	button.addActionListener(this);
    	return button;
    }

    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getActionCommand().equals(GlobalValuesTitle.OPTIONS)) {
        	System.out.println(this.getLocation());
            screenOption.showFrame(this.getLocation());
        } else if (arg0.getActionCommand().equals(GlobalValuesTitle.POLES)) {
            screenPole.showFrame(this.getLocation());
        } else if (arg0.getActionCommand().equals(GlobalValuesTitle.QUISTIS_CARD_PATTERNS)) {
            screenCardPatternChooseFrame.showFrame(this.getLocation());
        } else if (arg0.getActionCommand().equals(GlobalValuesTitle.CARD_RNG_MANIPULATION)) {
        	getRNGPage();
        } else if (arg0.getActionCommand().equals(GlobalValuesTitle.THE_END_MANIPULATION)) {
            screenTheEndManipulation.showFrame(this.getLocation());
        } else {
            this.dispose();
        }

    }
    
    private void getRNGPage() {
    	File jsonFile = new File(option.getJsonPath());
    	File executableFile = new File(option.getPathScriptExe());
    	File executableTheEndFile = new File(option.getPathScriptTheEndExe());
    	File rubyZFile = new File(option.getRubyZellPath());
    	File rubyQFile = new File(option.getRubyQuistisPath());
    	boolean canItShowed = true;
    	if(!jsonFile.exists()) {
    		JOptionPane.showMessageDialog(this, "Json file don't exist or wrong path", "Input error", 0);
    		canItShowed = false;
    	}else if(option.getRngRubyExeChoice()) {
    		if(!rubyZFile.exists()) {
    			JOptionPane.showMessageDialog(this, "Zell ruby file don't exist or wrong path", "Input error", 0);
    			canItShowed = false;
    		}
    		if(!rubyQFile.exists()) {
    			JOptionPane.showMessageDialog(this, "Quistis ruby file don't exist or wrong path", "Input error", 0);
    			canItShowed = false;
    		}
    	}else if(!executableFile.exists()) {
    		JOptionPane.showMessageDialog(this, "The exe file don't exist or wrong path", "Input error", 0);
    		canItShowed = false;
    	}else if(!executableTheEndFile.exists()) {
            JOptionPane.showMessageDialog(this, "The exe file for The END don't exist or wrong path", "Input error", 0);
            canItShowed = false;
        }
    	if(canItShowed) {
    		screenCardRng.showFrame(this.getLocation());
    	}
    }
}
