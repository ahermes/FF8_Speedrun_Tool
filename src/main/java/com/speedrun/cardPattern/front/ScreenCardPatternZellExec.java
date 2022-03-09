package com.speedrun.cardPattern.front;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.speedrun.PrincipalFrames;
import com.speedrun.cardrng.object.json.OptionExeJson;
import com.speedrun.option.object.Options;
import com.speedrun.utilities.GlobalValues;
import com.speedrun.utilities.GlobalValuesTitle;
import com.speedrun.utilities.graphics.MainJFrame;
import com.speedrun.utilities.helper.JsonExtractorHelper;
import com.speedrun.utilities.helper.JsonWriterHelper;
import com.speedrun.utilities.toolkit.UtilitiesToolkit;

public class ScreenCardPatternZellExec implements PrincipalFrames {

	private final JTextField jtf = new JTextField("");
	private MainJFrame mainFrame;
	private Options option;
	private ScreenCardPatternZell screenCardPatternZell;

	public ScreenCardPatternZellExec(Options option, ScreenCardPatternZell screenCardPatternZell) {
		this.option = option;
		this.screenCardPatternZell = screenCardPatternZell;

	}

	public void showFrame(Point position) {
		mainFrame = null;
		mainFrame = new MainJFrame(GlobalValuesTitle.ZELL_CARD_PATTERNS, GlobalValues.WIDTH_FRAME+20,GlobalValues.LINE_BASE_SIZE*7,position);
		mainFrame.addPanelWithScroll(getMainPanel());
	}
	
	private JPanel getMainPanel() {
		JPanel jpanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        jtf.setPreferredSize(new Dimension(50, 20));
        JButton accept = new JButton("execute");
        accept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(UtilitiesToolkit.isInt(jtf.getText()) && !(Integer.parseInt(jtf.getText()) < 0)) {
		    		mainFrame.dispose();
		    		openChronoScreen(Integer.parseInt(jtf.getText()));
					mainFrame = null;
				}else {
					JOptionPane.showMessageDialog(mainFrame, "The rng isn't a number or doesn't have patterns yet", "Input error", 0);
				}
			}
		});
        accept.setBackground(new Color(200,200,200));
        jpanel.add(UtilitiesToolkit.getLabelBloc("which RNG you want? (Rng saved : " + option.getRngQuistis() + ")", GlobalValues.FONT_SIZE_BIG, GlobalValues.WIDTH_BLOCS, -1, GlobalValues.FONT_SIZE_BIG+10),gbc);
        gbc.gridy++;
        jpanel.add(UtilitiesToolkit.getLabelBloc("(enter a number for Zell program)", GlobalValues.FONT_SIZE, GlobalValues.WIDTH_BLOCS, -1, -1),gbc);
        gbc.gridy++;
        jpanel.add(jtf,gbc);
        gbc.gridy++;
        jpanel.add(new JLabel(" "),gbc);
        gbc.gridy++;
        jpanel.add(accept,gbc);
        jpanel.setOpaque(false);
        return jpanel;
	}
	
	private void openChronoScreen(int rngValue) {
		screenCardPatternZell.showFrame(mainFrame.getLocation());
		if (option.getRngRubyExeChoice()) {
			String exePath = option.getRubyZellPath();
			UtilitiesToolkit.launchScript(exePath, new ArrayList<String>((Arrays.asList(option.getRngQuistis(),String.valueOf(rngValue)))));
		} else {
			String path = option.getPathScriptExe() + "\\settings.json";
			String exePath = option.getPathScriptExe() + "\\ff8-card-manip.exe";
			OptionExeJson optionsExecutableScript = (OptionExeJson) JsonExtractorHelper.getJson(path, OptionExeJson.class);
			optionsExecutableScript.setPlayer(GlobalValues.ZELL_OPTION_EXE);
			optionsExecutableScript.setDelayFrame(option.getDelayFrame());
			JsonWriterHelper.writeJsonIntoFile(path, optionsExecutableScript);
			UtilitiesToolkit.launchScript(exePath, new ArrayList<String>((Arrays.asList(option.getRngQuistis(),String.valueOf(rngValue)))));
		}
	}

}
