package com.speedrun.pole.front;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.speedrun.pole.engine.Poles;
import com.speedrun.utilities.GlobalValues;
import com.speedrun.utilities.GlobalValuesTitle;
import com.speedrun.utilities.graphics.ImagePanel;
import com.speedrun.utilities.graphics.MainJFrame;
import com.speedrun.utilities.toolkit.UtilitiesToolkit;

public class ScreenPoleRes extends JFrame{
	private ArrayList<String> result;
	private MainJFrame mainFrame;

	public ScreenPoleRes() {
	}
	
	public void setResult(Poles calcule) {
		this.result = calcule.getRes();
	}
	
	public void showFrame(Point position) {
		mainFrame = null;
		mainFrame = new MainJFrame(GlobalValuesTitle.POLES,-1, -1,position);
		mainFrame.setNewBackground(null);
		mainFrame.addPanelWithScroll(mainPanel());
	}

	private JPanel mainPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		for(int k = 0; k < this.result.size(); ++k) {
			panel.add(getBloc(this.result.get(k).split(" ")));
		}
		panel.setOpaque(false);
		return panel;
	}

	private JPanel getBloc(String[] resValue) {
		ImagePanel res = new ImagePanel();
		res.setLayout(new BoxLayout(res,BoxLayout.PAGE_AXIS));
		res.setBackground(new ImageIcon(this.getClass().getResource("/imageFenetre/bg.png")).getImage());
		res.add(UtilitiesToolkit.getLabelBloc( "INDEX " + resValue[4], GlobalValues.FONT_SIZE, GlobalValues.POLE_BLOC_WIDTH-20, GlobalValues.POLE_BLOC_WIDTH-20, -1));
		res.add(getLine("If you are lucky ",resValue[0]));
		res.add(getLine("If you are Unlucky ",resValue[1]));
		UtilitiesToolkit.setSizeOfComponent(res,new Dimension(GlobalValues.POLE_BLOC_WIDTH,GlobalValues.LINE_BASE_SIZE*4+10) );
		return res;
	}

	private JPanel getLine(String label,String value1) {
		JPanel res = new JPanel();
		res.add(UtilitiesToolkit.getLabelBloc(label, GlobalValues.FONT_SIZE, GlobalValues.POLE_BLOC_WIDTH/2, GlobalValues.POLE_BLOC_WIDTH/2, -1));
		res.add(UtilitiesToolkit.getLabelBloc(value1, GlobalValues.FONT_SIZE_BIG, GlobalValues.POLE_BLOC_WIDTH/4 + 10, -1, GlobalValues.LINE_BASE_SIZE+10));
		UtilitiesToolkit.setSizeOfComponent(res, new Dimension(GlobalValues.POLE_BLOC_WIDTH,GlobalValues.LINE_BASE_SIZE+10));
		res.setOpaque(false);
		return res;
	}
}
