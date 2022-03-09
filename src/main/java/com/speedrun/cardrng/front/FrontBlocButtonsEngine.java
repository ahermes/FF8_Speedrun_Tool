package com.speedrun.cardrng.front;

import com.speedrun.cardrng.engine.CountRngCount;
import com.speedrun.cardrng.object.lines.CountRngButtonsLine;
import com.speedrun.option.object.Options;
import com.speedrun.utilities.GlobalValues;
import com.speedrun.utilities.graphics.ShadowLabel;
import com.speedrun.utilities.toolkit.UtilitiesToolkit;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class FrontBlocButtonsEngine implements IFrontAbstractLine{
	private CountRngCount countRngCount;
	private Map<JCheckBox, Integer> checkBoxList;
	private Map<JRadioButton, Integer> radioButtonList;
	private CountRngButtonsLine line;
	private String parentLabel;
	private Options option;

	public FrontBlocButtonsEngine(CountRngButtonsLine line, CountRngCount countRngCount, String parentLabel, Options option){
		this.line = line;
		this.countRngCount = countRngCount;
		this.radioButtonList = new HashMap<JRadioButton,Integer>();
		this.checkBoxList = new HashMap<JCheckBox,Integer>();
		this.parentLabel = parentLabel;
		this.option = option;
	}

	public JPanel getPanel() {

		JPanel panel = new JPanel();
		UtilitiesToolkit.setSizeOfComponent(panel, new Dimension(GlobalValues.WIDTH_LINE_BLOCS, line.getHeight()));
		if(option.getRngPageFormat()) {
			panel.setLayout(new BorderLayout());
			panel.add(getLabelPanel(),BorderLayout.WEST);
			panel.add(getValuePanel(),BorderLayout.EAST);
		}else {
			panel.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			panel.add(getLabelPanel(),gbc);
			gbc.gridx++;
			panel.add(getValuePanel(),gbc);
		}
		panel.setOpaque(false);
		return panel;
	} 




	private JPanel getValuePanel(){
		JPanel buttonPanel= new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		if(option.getRngPageFormat()) {
			gbc.anchor = GridBagConstraints.EAST;
		}
		for (final Map.Entry<String, Integer> entry : line.getValue().entrySet()) {
			if(option.getRadioButton()) {
				buttonPanel.add(getRadioButton(entry),gbc);
			}else {
				buttonPanel.add(getCheckBox(entry),gbc);
			}
			if(gbc.gridx == 3){
				gbc.gridx = 0;
				gbc.gridy++;
			}else{
				gbc.gridx++;
			}
		}
		while(gbc.gridx < 3) {
			buttonPanel.add(getVoidPanel(), gbc);
			gbc.gridx++;
		}
		buttonPanel.setOpaque(false);
		return buttonPanel;
	}

	private JPanel getLabelPanel(){
		JPanel name = new JPanel();
		ShadowLabel labelPanel = new ShadowLabel (line.getLabel(), GlobalValues.FONT_SIZE,-1);
		name.add(labelPanel);
		if(option.getRngPageFormat()) {
			name.setLayout(new FlowLayout(FlowLayout.LEFT));
			UtilitiesToolkit.setSizeOfComponent(name, new Dimension(GlobalValues.WIDTH_LINE_BLOCS/3, line.getHeight()));
		}else {
			UtilitiesToolkit.setSizeOfComponent(name, new Dimension(labelPanel.getTextWidth(), line.getHeight()));
		}
		name.setOpaque(false);
		return name;
	}

	private JPanel getVoidPanel() {
		JPanel name = new JPanel();
		name.add(new JLabel(" "));
		if(option.getRngPageFormat()) {
			UtilitiesToolkit.setSizeOfComponent(name, new Dimension((int)GlobalValues.WIDTH_LINE_BLOCS/5, line.getHeight()));
		}
		name.setOpaque(false);
		return name;
	}


	private JRadioButton getRadioButton(Map.Entry<String, Integer> entry) {
		JRadioButton rb = new JRadioButton();
		radioButtonList.put(rb,entry.getValue());
		rb.setText(entry.getKey());
		rb.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(rb.isSelected()) {
					addToCount(entry.getValue());
					unselectOthers(rb);
				}else{
					addToCount(entry.getValue()*-1);
				}
			}
		});
		if(option.getRngPageFormat()) {
			UtilitiesToolkit.setSizeOfComponent(rb, new Dimension((int)GlobalValues.WIDTH_LINE_BLOCS/5, line.getHeight()));
		}
		rb.setOpaque(false);
		UtilitiesToolkit.setFont(rb, GlobalValues.FONT_SIZE, Color.WHITE);
		return rb;
	}

	private JCheckBox getCheckBox(Map.Entry<String, Integer> entry) {
		JCheckBox rb = new JCheckBox();
		checkBoxList.put(rb,entry.getValue());
		rb.setText(entry.getKey());
		rb.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(rb.isSelected()) {
					addToCount(entry.getValue());
				}else{
					addToCount(entry.getValue()*-1);
				}
			}
		});
		if(option.getRngPageFormat()) {
			UtilitiesToolkit.setSizeOfComponent(rb, new Dimension((int)GlobalValues.WIDTH_LINE_BLOCS/5, line.getHeight()));
		}
		rb.setOpaque(false);
		UtilitiesToolkit.setFont(rb, GlobalValues.FONT_SIZE, Color.WHITE);
		return rb;
	}

	private void addToCount(int value){
		countRngCount.addValueToActivePage(value);
	}
	
	private void unselectOthers(JRadioButton rb) {
		for(Map.Entry<JRadioButton, Integer> entry : radioButtonList.entrySet()) {
			if(entry.getKey() != rb && entry.getKey().isSelected()) {
				entry.getKey().setSelected(false);
				addToCount(entry.getValue()*-1);
			}
		}
	}

	public void reset(){
		for(Map.Entry<JCheckBox,Integer> button : checkBoxList.entrySet()){
			if(button.getKey().isSelected()) {
				button.getKey().setSelected(false);
				countRngCount.addValueToActivePage(this.parentLabel, button.getValue() * -1);
			}
		}
		for(Map.Entry<JRadioButton,Integer> button : radioButtonList.entrySet()){
			if(button.getKey().isSelected()) {
				button.getKey().setSelected(false);
				countRngCount.addValueToActivePage(this.parentLabel, button.getValue() * -1);
			}
		}
	}
}
