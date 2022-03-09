package com.speedrun.utilities.graphics;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

public class CustomRbButton extends JRadioButton{
	
	public CustomRbButton(){	
	super();
	this.setIcon(new ImageIcon(this.getClass().getResource("/imageFenetre/Select.png")));
	}
	
	public CustomRbButton(Icon icon) {
		super();
		this.setSelectedIcon(icon);
	}
	
}
