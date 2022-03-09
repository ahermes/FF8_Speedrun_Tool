package com.speedrun;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.speedrun.option.engine.OptionHelper;
import com.speedrun.option.object.Options;
import com.speedrun.utilities.toolkit.UtilitiesToolkit;

public class PopUpFrame extends JFrame{
	private Options option;
	private JLabel text;
	
	
	public PopUpFrame(Options option,String title, Point point) {
		this.option = option;
		text = new JLabel();
		setPanel(title,point);
	}
	
	private void setPanel(String title, Point point) {
		JPanel panel = new JPanel(new GridBagLayout());
		text.setOpaque(false);
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(text,gbc);
        gbc.gridy++;
		JCheckBox checkBox = new JCheckBox(title);
		checkBox.setText("Don't show this popup again");
		checkBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(checkBox.isSelected()) {
					option.setShowPopUp(false);
					OptionHelper.optionWriter(option);
				}else{
					option.setShowPopUp(true);
					OptionHelper.optionWriter(option);
				}
			}
		});
		panel.add(checkBox,gbc);
		gbc.gridy++;
		JButton button = new JButton("OK");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeThisFrame();
			}
		});
		panel.add(button,gbc);
		this.add(UtilitiesToolkit.addJScrollPane(panel));
		this.setTitle(title);
		this.pack();
		this.setDefaultCloseOperation(2);
		this.setResizable(true);
		
		this.setLocation(point);
	}
	
	public void setText(String text) {
		this.text.setText(text);
		this.pack();
	}
	
	private void closeThisFrame() {
		 this.dispose();
	}
}
