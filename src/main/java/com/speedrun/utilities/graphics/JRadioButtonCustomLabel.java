//package com.speedrun.utilities.graphics;
//
//import java.awt.BorderLayout;
//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.font.FontRenderContext;
//import java.awt.geom.AffineTransform;
//
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JRadioButton;
//
//import com.speedrun.utilities.TheEndManipulationEngine.UtilitiesToolkit;
//
//public class JRadioButtonCustomLabel {
//	
//	private String label;
//	private JRadioButton button;
//	
//	public JRadioButtonCustomLabel() {
//		this.label = "";
//		this.button = new JRadioButton();
//	}
//	
//	public JRadioButtonCustomLabel(String label) {
//		this.label = label;
//		this.button = new JRadioButton();
//	}
//	
//	public void setLabel(String label) {
//		this.label = label;
//	}
//	
//	public void setButton(JRadioButton button) {
//		this.button = button;
//	}
//	
//	public String getLabel() {
//		return this.label;
//	}
//	
//	public JRadioButton getButton() {
//		return this.button;
//	}
//	
//	public JPanel getPanel() {
//		JPanel panel = new JPanel();
//		
//		ShadowLabel shadowLabel = new ShadowLabel(label,12);
////		JLabel shadowLabel = new JLabel(label);
//		UtilitiesToolkit.setSizeOfComponent(this.button,new Dimension(16,24));
//		panel.add(this.button);
//		panel.add(shadowLabel);
//		this.button.setOpaque(false);
////		UtilitiesToolkit.setSizeOfComponent(panel,new Dimension(16 + shadowLabel.getTextWidth(),24));
//
////		panel.setOpaque(false);
//		System.out.println(panel.getPreferredSize());
//		return panel;
//	}
//}
