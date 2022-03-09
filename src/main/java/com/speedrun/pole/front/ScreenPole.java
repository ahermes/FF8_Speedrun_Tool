package com.speedrun.pole.front;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.speedrun.PrincipalFrames;
import com.speedrun.pole.engine.Constants;
import com.speedrun.pole.engine.Poles;
import com.speedrun.utilities.GlobalValues;
import com.speedrun.utilities.GlobalValuesTitle;
import com.speedrun.utilities.graphics.MainJFrame;
import com.speedrun.utilities.graphics.ShadowLabel;
import com.speedrun.utilities.toolkit.UtilitiesToolkit;

public class ScreenPole implements PrincipalFrames{
	private ArrayList<JComboBox<String>> listComboBox = new ArrayList<JComboBox<String>>();
	private MainJFrame mainFrame;
	private ScreenPoleRes screenPoleRes;

	
	public ScreenPole() {
		this.screenPoleRes = new ScreenPoleRes();
	}
	
	public void showFrame(Point position) {
		mainFrame = null;
		this.listComboBox = new ArrayList<JComboBox<String>>();
		mainFrame = new MainJFrame(GlobalValuesTitle.POLES, -1,-1,position);
		mainFrame.addPanelWithScroll(mainPanel());
	}

	private JPanel mainPanel() {

		JPanel frame = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.insets = new Insets(10,0,0,0);
		frame.add(getNorthPanel(), gbc);
		gbc.gridy++;
		frame.add(getCentralPanel(), gbc);
		JButton boutonCalcul = new JButton("Submit");
		boutonCalcul.setBackground(new Color(200,200,200));
		boutonCalcul.setPreferredSize(new Dimension(GlobalValues.WIDTH_FRAME/6, GlobalValues.LINE_BASE_SIZE));
		boutonCalcul.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				getPoleResult();
			}
		});
		gbc.gridy++;
		frame.add(boutonCalcul, gbc);
		frame.setOpaque(false);
		return frame;
	}

	private JPanel getNorthPanel() {
		JPanel selection = new JPanel();
		selection.add(UtilitiesToolkit.getLabelBloc("Enter the Posts's series then submit them", GlobalValues.FONT_SIZE, GlobalValues.WIDTH_FRAME/2, GlobalValues.WIDTH_FRAME/2, -1));
		selection.setOpaque(false);
		return selection;
	}

	private JPanel getCentralPanel() {
		JPanel selection = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 0, 0, 0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		for(int i = 0; i < 6; ++i) {
			selection.add(getLine(),gbc);
			gbc.gridy++;
		}
		selection.setOpaque(false);
		return selection;
	}
	
	private JPanel getLine() {
		JPanel res = new JPanel(new BorderLayout());
		JComboBox<String> comboBox = new JComboBox<String>(Constants.getItems());
		this.listComboBox.add(comboBox);
		res.add(UtilitiesToolkit.getLabelBloc("Serie number " + listComboBox.size() + ": ",GlobalValues.FONT_SIZE, GlobalValues.WIDTH_FRAME/4,-1, -1), BorderLayout.WEST);
		res.add(comboBox, BorderLayout.EAST);
		res.setOpaque(false);
		return res;
	}


	public void getPoleResult() {
		ArrayList<String> choix = new ArrayList<String>();
		for(JComboBox<String> item : this.listComboBox) {
			choix.add((String) item.getSelectedItem());
		}
		Poles calcule = new Poles((String)listComboBox.get(0).getSelectedItem(), (String)listComboBox.get(1).getSelectedItem(), (String)listComboBox.get(2).getSelectedItem(), 
				(String)listComboBox.get(3).getSelectedItem(), (String)listComboBox.get(4).getSelectedItem(), (String)listComboBox.get(5).getSelectedItem());
        if (!calcule.getRes().get(0).equals("nothing")) {
            try {
                if (calcule.getRes().size() > 10) {
                    JOptionPane.showMessageDialog(mainFrame, "This serie isn't complete or have too many results", "Serie error", 2);
                }else {
                	this.screenPoleRes.setResult(calcule);
                	this.screenPoleRes.showFrame(mainFrame.getLocation());
                		mainFrame.dispose();
                }
            } catch (ArrayIndexOutOfBoundsException var5) {
                JOptionPane.showMessageDialog(mainFrame, "This serie doesn't exist. Try again", "Serie error", 2);
            }
        }else {
        	JOptionPane.showMessageDialog(mainFrame, "This serie doesn't exist. Try again", "Serie error", 2);
        }
	}
}
