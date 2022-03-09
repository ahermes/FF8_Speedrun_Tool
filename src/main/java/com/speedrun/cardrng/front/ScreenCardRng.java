package com.speedrun.cardrng.front;

import com.speedrun.PrincipalFrames;
import com.speedrun.cardPattern.front.ScreenCardPattern;
import com.speedrun.cardPattern.front.ScreenCardPatternZell;
import com.speedrun.cardPattern.front.ScreenCardPatternZellExec;
import com.speedrun.cardrng.engine.CountRngCount;
import com.speedrun.cardrng.object.json.CountRngJsonPage;
import com.speedrun.cardrng.object.json.OptionExeJson;
import com.speedrun.option.object.Options;
import com.speedrun.utilities.*;
import com.speedrun.utilities.graphics.ImagePanel;
import com.speedrun.utilities.graphics.MainJFrame;
import com.speedrun.utilities.helper.CSVHelper;
import com.speedrun.utilities.helper.JsonExtractorHelper;
import com.speedrun.utilities.helper.JsonWriterHelper;
import com.speedrun.utilities.toolkit.UtilitiesToolkit;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScreenCardRng implements PrincipalFrames{ 
	private int height = 0;
	private List<CountRngCountPages> listPages;
	private Options option;
	private MainJFrame mainFrame;
	private ScreenCardPattern screenCardPattern;
	private ScreenCardPatternZell screenCardPatternZell;

	public ScreenCardRng(ScreenCardPattern screenCardPattern, ScreenCardPatternZell screenCardPatternZell, Options option) {
		this.listPages = new ArrayList<CountRngCountPages>();
		this.option = option;
		this.screenCardPattern = screenCardPattern;
		this.screenCardPatternZell = screenCardPatternZell;

	}

	public void showFrame(Point position) {
		mainFrame = null;
		CountRngCount countRngCount = new CountRngCount(this.option);
		CountRngJsonPage[] jsonPages = (CountRngJsonPage[])JsonExtractorHelper.getJson(option.getJsonPath(), CountRngJsonPage[].class);
		JPanel panel = getTabbedPane(jsonPages, countRngCount);
		mainFrame = new MainJFrame(GlobalValuesTitle.CARD_RNG_MANIPULATION,GlobalValues.WIDTH_FRAME+20, this.height + countRngCount.getHeight() + 87,position);
		mainFrame.setNewBackground(null);
		mainFrame.addPanelWithScroll(panel);
	}
	
	private JPanel getTabbedPane(final CountRngJsonPage[] pages,final CountRngCount countRngCount) {
		JPanel res = new JPanel(new BorderLayout());
		final JTabbedPane tabs = new JTabbedPane();
		tabs.setUI(new BasicTabbedPaneUI(){
			  protected void paintContentBorder(Graphics g,int tabPlacement,int selectedIndex){}
		});
		String pageName = "";
		for(int i =0 ; i < pages.length; i++) {

			CountRngCountPages page = new CountRngCountPages(
					pages[i],
					countRngCount,
					this.option
					);
			listPages.add(page);
			if(pageName.equals("")) {
				pageName = page.getLabel();
			}
			tabs.add(page.getLabel(),page.getJPanel());
			tabs.setBackgroundAt(i, new Color(100,100,100));
			if(this.height < page.getHeight()) {
				this.height = page.getHeight();
			}
		}
		tabs.setForeground(Color.WHITE);
		res.add(tabs, BorderLayout.NORTH);
		res.add(getSouthPanel(tabs, countRngCount, pages, pageName),BorderLayout.SOUTH);
		res.setOpaque(false);
		return res;
	}


	public JPanel getSouthPanel(JTabbedPane tabs, CountRngCount countRngCount, CountRngJsonPage[] pages, String pageName) {

		final JButton resetAll = new JButton("Reset All");
		resetAll.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				resetAll();
			}
		});

		final JButton resetPage = new JButton("Reset Page");
		resetPage.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				reset(tabs.getTitleAt(tabs.getSelectedIndex()));
			}
		});

		final JButton button = new JButton("Get Q Patterns");
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				openPatternScreen(countRngCount.getValueOfActivePage());
			}
		});
		final JButton buttonChrono = new JButton("Start Z Chrono");
		buttonChrono.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				openChronoScreen(countRngCount.getValueOfActivePage());
			}
		});
		resetPage.setPreferredSize(new Dimension(GlobalValues.WIDTH_FRAME/5, GlobalValues.LINE_BASE_SIZE));
		resetAll.setPreferredSize(new Dimension(GlobalValues.WIDTH_FRAME/5, GlobalValues.LINE_BASE_SIZE));
		button.setPreferredSize(new Dimension(GlobalValues.WIDTH_FRAME/4, GlobalValues.LINE_BASE_SIZE));
		buttonChrono.setPreferredSize(new Dimension(GlobalValues.WIDTH_FRAME/4, GlobalValues.LINE_BASE_SIZE));

		resetPage.setBackground(new Color(200,200,200));
		resetAll.setBackground(new Color(200,200,200));
		button.setBackground(new Color(200,200,200));
		buttonChrono.setBackground(new Color(200,200,200));
		
		button.setVisible(pages[tabs.getSelectedIndex()].getButton());
		buttonChrono.setVisible(!pages[tabs.getSelectedIndex()].getButton());

		tabs.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				String pageJsonName = tabs.getTitleAt(tabs.getSelectedIndex());
				if(pages[tabs.getSelectedIndex()].getBaseCountPage() != null) {
					pageJsonName = pages[tabs.getSelectedIndex()].getBaseCountPage();
				}
				countRngCount.setQuistisPatern(pages[tabs.getSelectedIndex()].getButton());
				countRngCount.setValue(pageJsonName, countRngCount.getValue(pageJsonName));
				button.setVisible(pages[tabs.getSelectedIndex()].getButton());
				buttonChrono.setVisible(!pages[tabs.getSelectedIndex()].getButton());
				

			}
		});

		ImagePanel buttons = new ImagePanel(new GridBagLayout());
		buttons.setBackground(new ImageIcon(this.getClass().getResource("/imageFenetre/bg.png")).getImage());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx=0;
		gbc.gridy=0;
		buttons.add(button,gbc);
		buttons.add(buttonChrono,gbc);
		gbc.insets = new Insets(0,20,0,0);
		gbc.gridy=0;
		gbc.gridx++;
		buttons.add(resetPage,gbc);
		gbc.gridy++;
		buttons.add(resetAll,gbc);
		
		UtilitiesToolkit.setSizeOfComponent(buttons, new Dimension((GlobalValues.WIDTH_FRAME/5)*3,GlobalValues.LINE_BASE_SIZE*2+10));
		
		JPanel southPanel = new JPanel(new BorderLayout());
		southPanel.add(buttons,BorderLayout.EAST);
		southPanel.add(countRngCount.getPanelWithMashing(pageName),BorderLayout.WEST);
		UtilitiesToolkit.setSizeOfComponent(southPanel, new Dimension(GlobalValues.WIDTH_BLOCS, GlobalValues.LINE_BASE_SIZE*2+10));
		southPanel.setOpaque(false);
		return southPanel;
	}

	private void openPatternScreen(int rngValue) {
		try {
			if (!CSVHelper.isRngExistfromCsvFile(rngValue)) {
				JOptionPane.showMessageDialog(mainFrame, "this RNG doesn't have patterns yet", "Input error", 0);
				return;
			}
		}catch (Exception err){
			err.printStackTrace();
			JOptionPane.showMessageDialog(mainFrame, "this RNG doesn't have patterns yet", "Input error", 0);
			return;
		}
		this.option.setFromMenu(false);
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
	
	private void openChronoScreen(int rngValue) {
		this.option.setFromMenu(false);
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
			optionsExecutableScript.setAcceptDelayFrame(option.getAcceptDelayFrame());
			optionsExecutableScript.setGameFps(option.getGameFps());
			JsonWriterHelper.writeJsonIntoFile(path, optionsExecutableScript);
			UtilitiesToolkit.launchScript(exePath, new ArrayList<String>((Arrays.asList(option.getRngQuistis(),String.valueOf(rngValue)))));

		}
	}


	private void resetAll(){
		for(CountRngCountPages page : listPages){
			page.reset();
		}
	}

	private void reset(String label){
		for(CountRngCountPages page : listPages){
			if(page.getLabel().equals(label)){
				page.reset();
			}
		}
	}
}
