package com.speedrun.cardrng.front;

import com.speedrun.cardrng.engine.CountRngCount;
import com.speedrun.cardrng.object.lines.CountRngScrollsLine;
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

public class FrontBlocScrollsEngine implements IFrontAbstractLine{
	private CountRngCount countRngCount;
	private Map<JComboBox<Object>,String> oldValue = new HashMap<JComboBox<Object>,String>();
	private CountRngScrollsLine line;
	private String parentLabel;
	private Options option;


	public FrontBlocScrollsEngine(CountRngScrollsLine line, CountRngCount countRngCount, String parentLabel, Options option){
		this.line = line;
		this.countRngCount = countRngCount;
		this.parentLabel = parentLabel;
		this.option = option;
	}

	public JPanel getPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		if(line.getLabel() != null && !line.getLabel().equals("") && option.getRngPageFormat()){
			panel.add(setLabelPanel(),BorderLayout.WEST);
			UtilitiesToolkit.setSizeOfComponent(panel, new Dimension(GlobalValues.WIDTH_LINE_BLOCS, line.getArragedHeight()));
			panel.add(setValuePanel(),BorderLayout.EAST);
		}else {
			UtilitiesToolkit.setSizeOfComponent(panel, new Dimension(GlobalValues.WIDTH_LINE_BLOCS, line.getHeight()));
			panel.add(setValuePanel());
		}
		panel.setOpaque(false);
		return panel;
	}

	private JPanel setLabelPanel(){
		JPanel name = new JPanel();
		if(option.getRngPageFormat()) {
			name.setLayout(new FlowLayout(FlowLayout.LEFT));
		}
		name.add(new ShadowLabel (line.getLabel(), GlobalValues.FONT_SIZE,-1));
		UtilitiesToolkit.setSizeOfComponent(name, new Dimension(GlobalValues.WIDTH_LINE_BLOCS/3,GlobalValues.LINE_BASE_SIZE));
		name.setOpaque(false);
		return name;
	}

	private JPanel setValuePanel(){
		JPanel comboBoxPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;

		boolean withLabel = false;
		comboBoxPanel.setSize(GlobalValues.WIDTH_BLOCS, line.getHeight());
		if(line.getLabel() != null && !line.getLabel().equals("")){
			if(option.getRngPageFormat()) {
				withLabel = true;
				comboBoxPanel.setSize(GlobalValues.WIDTH_BLOCS, line.getArragedHeight());
			}else {
				comboBoxPanel.add(setLabelPanel(),gbc);
				gbc.gridx++;
			}
		}

		if(option.getRngPageFormat()) {
			gbc.anchor = GridBagConstraints.WEST;
			gbc.fill = GridBagConstraints.HORIZONTAL;
		}

		final Map<String,Integer> lines = line.getValue();
		for(int i = 0; i < line.getTimes(); i++){
			JComboBox<Object> comboBox = new JComboBox<Object>(lines.keySet().toArray());
			
			UtilitiesToolkit.setSizeOfComponent(comboBox, new Dimension(GlobalValues.WIDTH_LINE_BLOCS/3,GlobalValues.LINE_BASE_SIZE));
			oldValue.put(comboBox, (String) lines.keySet().toArray()[0]);
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addToCount(lines.get(oldValue.get(comboBox)) *-1);
					oldValue.put(comboBox, comboBox.getSelectedItem().toString());
					addToCount(lines.get(comboBox.getSelectedItem().toString()));
				}
			});
			comboBoxPanel.add(comboBox,gbc);

			if(withLabel && option.getRngPageFormat()) {
				if (gbc.gridx >= 1) {
					gbc.gridx = 0;
					gbc.gridy++;
				} else {
					gbc.gridx++;
				}
			}else{
				if(gbc.gridx >= 2) {
					gbc.gridx = 0;
					gbc.gridy++;
				}else{
					gbc.gridx++;
				}
			}
		}
		comboBoxPanel.setOpaque(false);
		return comboBoxPanel;
	}

	private void addToCount(int value){
		countRngCount.addValueToActivePage(parentLabel, value);
	}

	public void reset(){
		for(Map.Entry<JComboBox<Object>,String> value : oldValue.entrySet()){
			if(value.getKey().getSelectedIndex() != 0) {
				value.getKey().setSelectedIndex(0);
			}
		}
	}

}
