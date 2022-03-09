package com.speedrun.cardrng.front;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.speedrun.cardrng.engine.CountRngCount;
import com.speedrun.cardrng.object.lines.CountRngButtonsLine;
import com.speedrun.option.object.Options;
import com.speedrun.utilities.GlobalValues;
import com.speedrun.utilities.graphics.ShadowLabel;
import com.speedrun.utilities.toolkit.UtilitiesToolkit;

public class FrontBlocCheckBoxEngine implements IFrontAbstractLine{
	private CountRngCount countRngCount;
	private Map<JCheckBox, Integer> checkBoxList;
	private CountRngButtonsLine line;
	private String parentLabel;
	private Options option;

	public FrontBlocCheckBoxEngine(CountRngButtonsLine line, CountRngCount countRngCount, String parentLabel, Options option){
		this.line = line;
		this.countRngCount = countRngCount;
		this.checkBoxList = new HashMap<JCheckBox,Integer>();
		this.parentLabel = parentLabel;
		this.option = option;
	}

	public JPanel getPanel() {
		JPanel panel = new JPanel();
		UtilitiesToolkit.setSizeOfComponent(panel,new Dimension(GlobalValues.WIDTH_LINE_BLOCS, line.getHeight()));
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
			buttonPanel.add(getCheckBox(entry),gbc);
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
		name.add(new ShadowLabel (line.getLabel(), GlobalValues.FONT_SIZE,-1));
		if(option.getRngPageFormat()) {
			name.setLayout(new FlowLayout(FlowLayout.LEFT));
			UtilitiesToolkit.setSizeOfComponent(name,new Dimension(GlobalValues.WIDTH_LINE_BLOCS/3, line.getHeight()));
		}
		name.setOpaque(false);
		return name;
	}

	private JPanel getVoidPanel() {
		JPanel name = new JPanel();
		name.add(new JLabel(" "));
		if(option.getRngPageFormat()) {
			UtilitiesToolkit.setSizeOfComponent(name,new Dimension(GlobalValues.WIDTH_LINE_BLOCS/5, line.getHeight()));
		}
		name.setOpaque(false);
		return name;
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
			UtilitiesToolkit.setSizeOfComponent(rb,new Dimension((int)(GlobalValues.WIDTH_LINE_BLOCS/5), GlobalValues.LINE_BASE_SIZE));
		}
		rb.setOpaque(false);
		UtilitiesToolkit.setFont(rb, GlobalValues.FONT_SIZE, Color.WHITE);
		return rb;
	}

	private void addToCount(int value){
		countRngCount.addValueToActivePage(value);
	}

	public void reset(){
		for(Map.Entry<JCheckBox,Integer> button : checkBoxList.entrySet()){
			if(button.getKey().isSelected()) {
				button.getKey().setSelected(false);
				countRngCount.addValueToActivePage(this.parentLabel, button.getValue() * -1);
			}
		}
	}
}
