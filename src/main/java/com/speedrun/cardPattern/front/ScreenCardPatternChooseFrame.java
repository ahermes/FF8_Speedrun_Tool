package com.speedrun.cardPattern.front;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
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
import com.speedrun.utilities.helper.CSVHelper;
import com.speedrun.utilities.helper.JsonExtractorHelper;
import com.speedrun.utilities.helper.JsonWriterHelper;
import com.speedrun.utilities.toolkit.UtilitiesToolkit;

public class ScreenCardPatternChooseFrame implements PrincipalFrames {

	private final JTextField jtf = new JTextField("");
	private MainJFrame mainFrame;
	private ScreenCardPattern screenCardPattern;
	private Options option;

	public ScreenCardPatternChooseFrame(ScreenCardPattern screenCardPattern, Options option) {
		this.screenCardPattern = screenCardPattern;
		this.option = option;
	}

	public void showFrame(Point position) {
		mainFrame = null;
		mainFrame = new MainJFrame(GlobalValuesTitle.QUISTIS_CARD_PATTERNS, GlobalValues.WIDTH_FRAME+20,GlobalValues.LINE_BASE_SIZE*7,position);
		mainFrame.addPanelWithScroll(getMainPanel());
	}

	private JPanel getMainPanel() {
		JPanel jpanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		jtf.setPreferredSize(new Dimension(50, 20));

		
		jpanel.add(UtilitiesToolkit.getLabelBloc("which RNG you want?", GlobalValues.FONT_SIZE_BIG, GlobalValues.WIDTH_BLOCS, -1, GlobalValues.FONT_SIZE_BIG+10),gbc);
		gbc.gridy++;
		jpanel.add(UtilitiesToolkit.getLabelBloc("(enter 0 for early Quistis or a superior number)", GlobalValues.FONT_SIZE, GlobalValues.WIDTH_BLOCS, -1, -1),gbc);
		gbc.gridy++;
		jpanel.add(jtf,gbc);
		gbc.gridy++;
		jpanel.add(new JLabel(" "),gbc);
		gbc.gridy++;
		jpanel.add(getButtonPanel(),gbc);

		jpanel.setOpaque(false);
		return jpanel;
	}



	private JPanel getButtonPanel() {
		JPanel res = new JPanel(new BorderLayout(50,0));
		JButton patern = new JButton("Get Pattern");
		patern.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(jtf.getText() != null && !jtf.getText().equals("") && UtilitiesToolkit.isInt(jtf.getText()) && (CSVHelper.isRngExistfromCsvFile((Integer.parseInt(jtf.getText()))) ||(Integer.parseInt(jtf.getText())) == 0 || (Integer.parseInt(jtf.getText())) == 999)) {
						mainFrame.dispose();
						option.setFromMenu(true);
						screenCardPattern.setFrames(Integer.parseInt(jtf.getText()));
						screenCardPattern.showFrame(mainFrame.getLocation());
						mainFrame = null;
					}else {
						JOptionPane.showMessageDialog(mainFrame, "The rng isn't a number or doesn't have patterns yet", "Input error", 0);
					}
				} catch (Exception err) {
					err.printStackTrace();
					JOptionPane.showMessageDialog(mainFrame, "The rng isn't a number or doesn't have patterns yet", "Input error", 0);
				}
			}
		});
		patern.setBackground(new Color(200,200,200));
		JButton script = new JButton("Get Pattern (/w script)");
		script.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (jtf.getText() != null && !jtf.getText().equals("") && UtilitiesToolkit.isInt(jtf.getText()) && (CSVHelper.isRngExistfromCsvFile((Integer.parseInt(jtf.getText()))) || (Integer.parseInt(jtf.getText())) == 0 || (Integer.parseInt(jtf.getText())) == 999)) {
						mainFrame.dispose();
						option.setFromMenu(true);
						openPatternScreen(Integer.parseInt(jtf.getText()));
						mainFrame = null;
					} else {
						JOptionPane.showMessageDialog(mainFrame, "The rng isn't a number or doesn't have patterns yet", "Input error", 0);
					}
				}catch (Exception err){
					err.printStackTrace();
					JOptionPane.showMessageDialog(mainFrame, "The rng isn't a number or doesn't have patterns yet", "Input error", 0);
				}
			}
		});
		
		script.setBackground(new Color(200,200,200));
		
		res.add(patern,BorderLayout.WEST);
		res.add(script,BorderLayout.EAST);
		res.setOpaque(false);
		return res;
	}

	private void openPatternScreen(int rngValue) {
		this.option.setFromMenu(true);
		screenCardPattern.setFrames(rngValue);
		screenCardPattern.showFrame(mainFrame.getLocation());
		if (option.getRngRubyExeChoice()) {
			String exePath = option.getRubyQuistisPath();
			UtilitiesToolkit.launchScript(exePath, new ArrayList<String>((Arrays.asList("0",String.valueOf(rngValue)))));
		} else {
			String path = option.getPathScriptExe() + "\\settings.json";
			String exePath = option.getPathScriptExe() + "\\ff8-card-manip.exe";
			OptionExeJson optionsExecutableScript = (OptionExeJson) JsonExtractorHelper.getJson(path, OptionExeJson.class);
			optionsExecutableScript.setPlayer(GlobalValues.QUISTIS_OPTION_EXE);
			optionsExecutableScript.setDelayFrame(option.getDelayFrame());
			optionsExecutableScript.setAcceptDelayFrame(option.getAcceptDelayFrame());
			optionsExecutableScript.setGameFps(option.getGameFps());
			JsonWriterHelper.writeJsonIntoFile(path, optionsExecutableScript);
			UtilitiesToolkit.launchScript(exePath, new ArrayList<String>((Arrays.asList("0",String.valueOf(rngValue)))));
		}
	}


}
