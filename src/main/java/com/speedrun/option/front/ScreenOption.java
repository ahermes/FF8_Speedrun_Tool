package com.speedrun.option.front;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.speedrun.PrincipalFrames;
import com.speedrun.option.engine.OptionHelper;
import com.speedrun.option.object.Options;
import com.speedrun.utilities.GlobalValues;
import com.speedrun.utilities.GlobalValuesTitle;
import com.speedrun.utilities.graphics.MainJFrame;
import com.speedrun.utilities.graphics.ShadowLabel;
import com.speedrun.utilities.toolkit.UtilitiesToolkit;

public class ScreenOption implements PrincipalFrames{
	
	private Options option;
	private Options newerOption;
	private MainJFrame mainFrame;
	private Map<String,JTextField> textFieldMap;

	public ScreenOption(Options option) {
		this.option = option;
		this.newerOption = new Options();
		this.newerOption.setAll(option);
		this.textFieldMap = new HashMap<String,JTextField>();
		initTextFieldMap();
	}

	private void initTextFieldMap(){
		for(ChoiceOption value : ChoiceOption.values()) {
			this.textFieldMap.put(value.name(), new JTextField());
		}
	}

	public void showFrame(Point position) {
		this.mainFrame = null;
		this.mainFrame = new MainJFrame(GlobalValuesTitle.OPTIONS,-1,-1,position);
		mainFrame.addPanelWithScroll(getPanel());
	}

	public JPanel getPanel() {
		JPanel res= new JPanel();
		res.setLayout(new BoxLayout(res,BoxLayout.PAGE_AXIS));
		res.add(getDelayFrameOption());
		res.add(writeValueLine("Delay Frame",ChoiceOption.DELAY));
		res.add(writeValueLine("Game FPS",ChoiceOption.FPS));
		res.add(writeValueLine("Accept Delay Frame",ChoiceOption.ACCEPTDELAY));
		res.add(setButtonLine("RNG Page format","Arranged format", "Speedrun format", option.getRngPageFormat(), OptionsButton.FORMAT));
		res.add(setButtonLine("Instant Mash Color","Background", "Font", option.getBackgroundColorRngCount(), OptionsButton.BACKGROUND));
		res.add(setButtonLine("Patterns order","by Frame", "by Name", option.getPatternOrderByFrame(), OptionsButton.ORDER));
		res.add(setButtonLine("Buttons format","RadioButton format", "CheckBox format", option.getRadioButton(), OptionsButton.BUTTON));
		//	res.add(setButtonLine("Script's type choice","Ruby scripts", "Exe scripts", option.getRngRubyExeChoice(), OptionsButton.EXECUTION));
		res.add(getPathOption(ChoicePathEnum.JSON));
		res.add(UtilitiesToolkit.getVoidPanel(GlobalValues.WIDTH_BLOCS, 5));
		res.add(getPathOption(ChoicePathEnum.EXE));
		res.add(UtilitiesToolkit.getVoidPanel(GlobalValues.WIDTH_BLOCS, 5));
		res.add(getPathOption(ChoicePathEnum.THE_END_EXE));
		res.add(UtilitiesToolkit.getVoidPanel(GlobalValues.WIDTH_BLOCS, 5));
//		res.add(getPathOption(ChoicePathEnum.QUISTISRB));
//		res.add(UtilitiesToolkit.getVoidPanel(GlobalValues.WIDTH_BLOCS, 5));
//		res.add(getPathOption(ChoicePathEnum.ZELLRB));
//		res.add(UtilitiesToolkit.getVoidPanel(GlobalValues.WIDTH_BLOCS, 5));
		res.add(getSavedButtons());
		res.setOpaque(false);
		return res;
	}
	
	private JPanel getSavedButtons() {
		JPanel buttons = new JPanel();
		
		JButton buttonSave = new JButton("Save");
		buttonSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveOption();
			}
		});
		JButton buttonSaveQuit = new JButton("Save & Quit");
		buttonSaveQuit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveOption();
				mainFrame.dispose();
			}
		});
		buttonSave.setBackground(new Color(200,200,200));
		buttonSaveQuit.setBackground(new Color(200,200,200));
		buttons.add(buttonSave);
		buttons.add(buttonSaveQuit);

		UtilitiesToolkit.setSizeOfComponent(buttons, new Dimension(GlobalValues.WIDTH_BLOCS, GlobalValues.LINE_BASE_SIZE*2));
		buttons.setOpaque(false);
		return buttons;
	}
	
	private JPanel setButtonLine(String title, String btn1, String btn2, Boolean status, OptionsButton option) {
		JPanel line = new JPanel(new BorderLayout());
		line.add(UtilitiesToolkit.getLabelBloc(title, GlobalValues.FONT_SIZE, GlobalValues.WIDTH_FRAME/3, GlobalValues.WIDTH_FRAME/3, -1), BorderLayout.WEST);
		line.add(getButtonLine(btn1,btn2,status,option));
		UtilitiesToolkit.setSizeOfComponent(line, new Dimension(GlobalValues.WIDTH_BLOCS, GlobalValues.LINE_BASE_SIZE));
		line.setOpaque(false);
		return line;
	}

	private JPanel getPathOption(ChoicePathEnum optionnal){
		JPanel line = new JPanel(new BorderLayout());
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
		JTextField textField =  new JTextField();
		textField.setEnabled(true);
		textField.setEditable(false);
		JButton chooseFile = new JButton();
		chooseFile.setBackground(new Color(200,200,200));
		switch (optionnal){
			case EXE:
				line.add(new ShadowLabel("Exe directory path",GlobalValues.FONT_SIZE,-1),BorderLayout.WEST);
				chooseFile.setText("Choose path");
				textField.setText(option.getPathScriptExe());
				chooseFile.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String pathRes = chooseFile("Select exe directory", null,option.getPathScriptExe());
						if(pathRes != null){
							newerOption.setPathScriptExe(pathRes);
						}
						textField.setText(newerOption.getPathScriptExe());
					}
				});
				break;
			case THE_END_EXE:
				line.add(new ShadowLabel("The End Exe directory path",GlobalValues.FONT_SIZE,-1),BorderLayout.WEST);
				chooseFile.setText("Choose path");
				textField.setText(option.getPathScriptTheEndExe());
				chooseFile.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String pathRes = chooseFile("Select the end exe directory", null,option.getPathScriptTheEndExe());
						if(pathRes != null){
							newerOption.setPathScriptTheEndExe(pathRes);
						}
						textField.setText(newerOption.getPathScriptTheEndExe());
					}
				});
				break;
			case JSON:
				line.add(new ShadowLabel("Json file path",GlobalValues.FONT_SIZE,-1),BorderLayout.WEST);
				chooseFile.setText("choose file");
				textField.setText(option.getJsonPath());
				chooseFile.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String pathRes = chooseFile("Select json file", "json", option.getJsonPath());
						if(pathRes != null){
							newerOption.setJsonPath(pathRes);
						}
						textField.setText(newerOption.getJsonPath());
					}
				});
				break;
			case ZELLRB:
				line.add(new ShadowLabel(GlobalValuesTitle.ZELL + " Ruby file path",GlobalValues.FONT_SIZE,-1),BorderLayout.WEST);
				chooseFile.setText("choose file");
				textField.setText(option.getRubyZellPath());
				chooseFile.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String pathRes = chooseFile("Choose Ruby file", "rb",option.getRubyZellPath());
						if(pathRes != null){
							newerOption.setRubyZellPath(pathRes);
						}
						textField.setText(newerOption.getRubyZellPath());
					}
				});
				break;
			case QUISTISRB:
				line.add(new ShadowLabel(GlobalValuesTitle.QUISTIS + " Ruby file path",GlobalValues.FONT_SIZE,-1),BorderLayout.WEST);
				chooseFile.setText("choose file");
				textField.setText(option.getRubyQuistisPath());
				chooseFile.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String pathRes = chooseFile("Choose Ruby file", "rb", option.getRubyQuistisPath());
						if(pathRes != null){
							newerOption.setRubyQuistisPath(pathRes);
						}
						textField.setText(newerOption.getRubyQuistisPath());
					}
				});
				break;
			default:
				break;
		}
		
		UtilitiesToolkit.setSizeOfComponent(rightPanel, new Dimension(GlobalValues.WIDTH_FRAME - (GlobalValues.WIDTH_FRAME/3), GlobalValues.LINE_BASE_SIZE));
		UtilitiesToolkit.setSizeOfComponent(textField, new Dimension(GlobalValues.WIDTH_FRAME - (GlobalValues.WIDTH_FRAME/3), GlobalValues.LINE_BASE_SIZE));
		UtilitiesToolkit.setSizeOfComponent(chooseFile, new Dimension(GlobalValues.WIDTH_BLOCS_SMALL/4, GlobalValues.LINE_BASE_SIZE));
		textField.setAlignmentX(Component.RIGHT_ALIGNMENT);
		chooseFile.setAlignmentX(Component.RIGHT_ALIGNMENT);
		rightPanel.add(textField);
		rightPanel.add(chooseFile);

		rightPanel.setOpaque(false);
		line.add(rightPanel, BorderLayout.EAST);
		UtilitiesToolkit.setSizeOfComponent(line, new Dimension(GlobalValues.WIDTH_BLOCS, GlobalValues.LINE_BASE_SIZE*2));
		line.setOpaque(false);
		return line;
	}

	private JPanel getDelayFrameOption() {
		JPanel line = new JPanel(new BorderLayout());
		JComboBox<Object> plateform = new JComboBox<Object>(GlobalValues.DELAY_FRAME_PLATEFORM.keySet().toArray());
		plateform.addItem("Custom");
		if(GlobalValues.DELAY_FRAME_PLATEFORM.containsValue(option.getDelayFrame()) && option.getGameFps() == 60 && option.getAcceptDelayFrame() == 2 ){
			textFieldMap.get(ChoiceOption.DELAY.name()).setEditable(false);
			textFieldMap.get(ChoiceOption.FPS.name()).setEditable(false);
			textFieldMap.get(ChoiceOption.ACCEPTDELAY.name()).setEditable(false);
			plateform.setSelectedItem(GlobalValues.DELAY_FRAME_PLATEFORM.inverse().get(option.getDelayFrame()));

		}else{
			textFieldMap.get(ChoiceOption.DELAY.name()).setEditable(true);
			textFieldMap.get(ChoiceOption.FPS.name()).setEditable(true);
			textFieldMap.get(ChoiceOption.ACCEPTDELAY.name()).setEditable(true);
			plateform.setSelectedItem("Custom");
		}
		plateform.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(plateform.getSelectedItem() != null) {
					if(plateform.getSelectedItem().equals("Custom")){
						textFieldMap.get(ChoiceOption.DELAY.name()).setEditable(true);
						textFieldMap.get(ChoiceOption.DELAY.name()).setText(String.valueOf(option.getDelayFrame()));
						textFieldMap.get(ChoiceOption.FPS.name()).setEditable(true);
						textFieldMap.get(ChoiceOption.FPS.name()).setText(String.valueOf(option.getGameFps()));
						textFieldMap.get(ChoiceOption.ACCEPTDELAY.name()).setEditable(true);
						textFieldMap.get(ChoiceOption.ACCEPTDELAY.name()).setText(String.valueOf(option.getAcceptDelayFrame()));
					}else {
						textFieldMap.get(ChoiceOption.DELAY.name()).setEditable(false);
						textFieldMap.get(ChoiceOption.DELAY.name()).setText(String.valueOf(GlobalValues.DELAY_FRAME_PLATEFORM.get(plateform.getSelectedItem())));
						textFieldMap.get(ChoiceOption.FPS.name()).setEditable(false);
						textFieldMap.get(ChoiceOption.FPS.name()).setText("60.0");
						textFieldMap.get(ChoiceOption.ACCEPTDELAY.name()).setEditable(false);
						textFieldMap.get(ChoiceOption.ACCEPTDELAY.name()).setText("2.0");
					}
				}
			}
		});

		UtilitiesToolkit.setSizeOfComponent(plateform, new Dimension(GlobalValues.WIDTH_BLOCS/3, GlobalValues.LINE_BASE_SIZE));

		line.add(plateform, BorderLayout.CENTER);
		ShadowLabel label =  new ShadowLabel("Platform", GlobalValues.FONT_SIZE,-1);
		UtilitiesToolkit.setSizeOfComponent(label, new Dimension(GlobalValues.WIDTH_BLOCS/2, GlobalValues.LINE_BASE_SIZE));
		line.add(label, BorderLayout.WEST);
		UtilitiesToolkit.setSizeOfComponent(line, new Dimension(GlobalValues.WIDTH_BLOCS, GlobalValues.LINE_BASE_SIZE));
		line.setOpaque(false);
		return line;
	}
	
	private JPanel getButtonLine(String name1, String name2, Boolean status, OptionsButton optionButton) {
		JPanel buttonBloc = new JPanel(new BorderLayout());
		JRadioButton rbAF = new JRadioButton();
		JRadioButton rbSF = new JRadioButton();
		rbAF.setText(name1);
		rbAF.setSelected(status);
		UtilitiesToolkit.setRadioButton(rbAF);
		rbAF.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				rbSF.setSelected(!rbAF.isSelected());
				setOption(optionButton, rbAF);
				UtilitiesToolkit.setRadioButton(rbSF);
				UtilitiesToolkit.setRadioButton(rbAF);
			}
		});

		rbSF.setText(name2);
		rbSF.setSelected(!status);
		UtilitiesToolkit.setRadioButton(rbSF);
		rbSF.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				rbAF.setSelected(!rbSF.isSelected());
				setOption(optionButton, rbAF);
				UtilitiesToolkit.setRadioButton(rbSF);
				UtilitiesToolkit.setRadioButton(rbAF);
			}
		});
		buttonBloc.add(rbSF, BorderLayout.EAST);
		buttonBloc.add(rbAF, BorderLayout.WEST);
		
		UtilitiesToolkit.setSizeOfComponent(rbAF, new Dimension(GlobalValues.WIDTH_BLOCS_SMALL/3, GlobalValues.LINE_BASE_SIZE));
		UtilitiesToolkit.setSizeOfComponent(rbSF, new Dimension(GlobalValues.WIDTH_BLOCS_SMALL/3, GlobalValues.LINE_BASE_SIZE));
		UtilitiesToolkit.setSizeOfComponent(buttonBloc, new Dimension(2*(GlobalValues.WIDTH_FRAME/3), GlobalValues.LINE_BASE_SIZE));
		UtilitiesToolkit.setFont(rbAF, GlobalValues.FONT_SIZE, Color.WHITE);
		UtilitiesToolkit.setFont(rbSF, GlobalValues.FONT_SIZE, Color.WHITE);
		rbAF.setOpaque(false);
		rbSF.setOpaque(false);
		buttonBloc.setOpaque(false);
		return buttonBloc;
	}
	
	private void setOption(OptionsButton optionButton, JRadioButton button) {
		switch (optionButton) {
			case EXECUTION:
				newerOption.setRngRubyExeChoice(button.isSelected());
				break;
		    case ORDER:
			    newerOption.setPatternOrderByFrame(button.isSelected());
			    break;
		    case FORMAT:
			    newerOption.setRngPageFormat(button.isSelected());
			    break;
			case BACKGROUND:
				newerOption.setBackgroundColorRngCount(button.isSelected());
				break;
		default:
			newerOption.setRadioButton(button.isSelected());
			break;
		}
	}

	private JPanel writeValueLine(String title, ChoiceOption optionChoice){
		JPanel line = new JPanel(new BorderLayout());
		line.add(UtilitiesToolkit.getLabelBloc(title, GlobalValues.FONT_SIZE, GlobalValues.WIDTH_BLOCS/2, GlobalValues.WIDTH_BLOCS/2, -1), BorderLayout.WEST);
		JTextField textField = new JTextField();
		switch(optionChoice) {
			case FPS:
				textField = this.textFieldMap.get(ChoiceOption.FPS.name());
				textField.setText(String.valueOf(this.option.getGameFps()));
				break;
			case ACCEPTDELAY:
				textField = this.textFieldMap.get(ChoiceOption.ACCEPTDELAY.name());
				textField.setText(String.valueOf(this.option.getAcceptDelayFrame()));
				break;
			case DELAY:
				textField = this.textFieldMap.get(ChoiceOption.DELAY.name());
				textField.setText(String.valueOf(this.option.getDelayFrame()));
				break;
		}
		UtilitiesToolkit.setSizeOfComponent(textField, new Dimension(GlobalValues.WIDTH_BLOCS/4, GlobalValues.LINE_BASE_SIZE));
		UtilitiesToolkit.setSizeOfComponent(line, new Dimension(GlobalValues.WIDTH_BLOCS, GlobalValues.LINE_BASE_SIZE));
		line.add(textField);
		line.setOpaque(false);
		return line;
	}

	private String chooseFile(String description, String extension,String path) {
		  JFileChooser chooser = new JFileChooser();
		  if(extension == null){
		  	chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		  }else {
			  FileNameExtensionFilter filter = new FileNameExtensionFilter(
					  description, extension);
			  chooser.setFileFilter(filter);
			  }
		  if (path == null) {
			  path = ".";
		  }
		  File tmpDir = new File(path);
		  if(path.equals("") || !tmpDir.exists()){
			  chooser.setCurrentDirectory(new File("."));
		  }else {
			  chooser.setCurrentDirectory(new File(path));
		  }

	        int returnVal = chooser.showOpenDialog(null);
	        if(returnVal == JFileChooser.APPROVE_OPTION) {
	        	if(extension != null
	        		&&	!chooser.getSelectedFile().getAbsolutePath().substring(chooser.getSelectedFile().getAbsolutePath().lastIndexOf(".") + 1, chooser.getSelectedFile().getAbsolutePath().length())
	        			.equals(extension)) {
	        		JOptionPane.showMessageDialog(this.mainFrame, "The choosen file has a wrong extention", "Input error", 0);
	        		return null;
	        	}
	            return chooser.getSelectedFile().getAbsolutePath();
	        }
	        return null;
	}

	private void saveOption() {
		if(UtilitiesToolkit.isDouble(this.textFieldMap.get(ChoiceOption.ACCEPTDELAY.name()).getText())){
			newerOption.setAcceptDelayFrame(Double.valueOf(this.textFieldMap.get(ChoiceOption.ACCEPTDELAY.name()).getText()));
		}
		if(UtilitiesToolkit.isDouble(this.textFieldMap.get(ChoiceOption.DELAY.name()).getText())){
			newerOption.setDelayFrame(Double.valueOf(this.textFieldMap.get(ChoiceOption.DELAY.name()).getText()));
		}
		if(UtilitiesToolkit.isDouble(this.textFieldMap.get(ChoiceOption.FPS.name()).getText())){
			newerOption.setGameFps(Double.valueOf(this.textFieldMap.get(ChoiceOption.FPS.name()).getText()));
		}
		option.setAll(newerOption);
		OptionHelper.optionWriter(option);
		JOptionPane.showMessageDialog(mainFrame, "Your options have been saved!");
	}



	public static enum OptionsButton {
		FORMAT,
		ORDER,
		EXECUTION,
		BUTTON,
		BACKGROUND
	}
	
	public static enum ChoicePathEnum{
		JSON,
		EXE,
		THE_END_EXE,
		QUISTISRB,
		ZELLRB
	}

	public static enum ChoiceOption{
		DELAY,
		FPS,
		ACCEPTDELAY
	}
}
