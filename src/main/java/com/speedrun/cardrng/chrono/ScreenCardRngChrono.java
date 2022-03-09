package com.speedrun.cardrng.chrono;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

import com.speedrun.cardrng.engine.CountRngCount;

public class ScreenCardRngChrono extends JFrame{
	private JLabel lineShowed = new JLabel();
	private String line = "";
	private int index = 0;
	private Timer time;

	public ScreenCardRngChrono(Point location) {
		this.setLocationRelativeTo((Component)null);
		this.setDefaultCloseOperation(2);
		this.setVisible(true);
		Font font = new Font("Serif", 2, 12);
		this.add(frameToTest());
		this.setSize(500, 500);
		this.setTitle("CHRONO");
		this.setLocation(location);
		this.setVisible(true);
	}

	private static String createRandomString() {
		String res = "";
		String exp = "";
		while(res.length() < 1000) {
			if(((int)(Math.random() * ((1 - 0) + 1)) + 0) != 1) {
				exp = "*";
			}else {
				exp = "-";
			}
			for(int i = 0; i < 10; i++) {
				res += exp;
			}
		}
		return res;
	}

	private void reload() {
		if(line.length() > index+20) {
			lineShowed.setText(line.substring(index, index+20));
			index++;
		}else {
			time.stop();
		}
	}

	private JPanel frameToTest() {
		final JPanel jpanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		int DELAY = 1000/30;
		String pattern = createRandomString();
		line = createRandomString();
		jpanel.add(lineShowed);

		time = new Timer(DELAY,new ActionListener() {
			public void actionPerformed(ActionEvent event) 
			{
				reload();
			}
		});
		time.setRepeats(true);
		time.start();
		return jpanel;
	}
}
