package com.speedrun.test;

import com.speedrun.theEndTable.front.ScreenTheEndManipulation;
import com.speedrun.utilities.*;
import com.speedrun.utilities.graphics.MainJFrame;
import com.speedrun.utilities.graphics.ShadowLabel;
import com.speedrun.theEndTable.engine.TheEndManipulationCore;
import com.speedrun.utilities.toolkit.UtilitiesToolkit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.rmi.CORBA.Util;
import javax.swing.*;

public class Testframe  extends JFrame implements ActionListener {

    public Testframe() {

    }

    public static void main(String[] args) throws Exception{

		UtilitiesToolkit.launchJsScript("C:\\Users\\rudy\\Desktop\\Rudy\\CardCounter\\resources\\scripts\\Script\\index.js",new ArrayList<>());

    	//ScreenTheEndManipulation screenTheEndManipulation = new ScreenTheEndManipulation();
		//screenTheEndManipulation.showFrame(new Point(0,0));

		//	ScreenTheEndManipulation screenTheEndManipulation = new ScreenTheEndManipulation(482,17,0,0,0,"Cyanure (1)");
	//	screenTheEndManipulation.showFrame(new Point());
	//	screenTheEndManipulation.addSpell("Brasier + (1)");
	//	screenTheEndManipulation.addSpell("Joobu (1)");
	//	screenTheEndManipulation.showThirdFrame();


	//	TheEndManipulationCore test = new TheEndManipulationCore();
	//	test.init("The End (1)", false);
	//	test.calculateNextSpell("joobu (1)");
	//	test.calculateNextSpell("foudre x (2)");
	//	test.calculateNextSpell("soin + (1)");
	//	test.tableDataLoader("Cyanure (1)", true);

	//	test.calculateAllLimitRng(482,17,0,0 , 0, "Brasier + (1)");
	//	test.calculateAllLimitRng(482,325,0,200 , "Brasier (1)");
	//	System.out.println("");
	//	test.nextSpell("Soin Max (1)");
	//	test.nextSpell("Joobu (1)");
	//	test.nextSpell("Joobu (1)");
	//	test.nextSpell("Brasier (2)");

    }



    private static JPanel frameStringTest() {

    	JPanel testText = new JPanel(new GridBagLayout());
    	int size = 19;
    	GridBagConstraints gbc = new GridBagConstraints();
    	gbc.gridx = 0;
    	gbc.gridy = 0;
    	Font font1 = new Font("Dialog", 1, size);
    	Font font2 = new Font("Dialog", 1, GlobalValues.FONT_SIZE);

    	MainJFrame frame = new MainJFrame("TEST",900,300, null) ;
    	ShadowLabel label1 = new ShadowLabel("Testy", size,-1);
    	ShadowLabel label2 = new ShadowLabel("TESTYtesty", GlobalValues.FONT_SIZE,-1);

    	JLabel label3 = new JLabel("Testy");
    	label3.setFont(font1);
    	JLabel label4 = new JLabel("TESTYtesty");
    	label4.setFont(font2);

    	ShadowLabel label5 = new ShadowLabel("TESTYtesty", GlobalValues.FONT_SIZE_BIG,-1);
    	ShadowLabel label6 = new ShadowLabel("TESTYtesty", GlobalValues.FONT_SIZE,-1);
    	System.out.println(label1.getFont());
    	System.out.println(label3.getFont());
    	JLabel label7 = new JLabel("TESTYtesty");
    	label7.setFont(font1);
    	JLabel label8 = new JLabel("TESTYtesty");
    	label8.setFont(font2);

    	JPanel panel1 = new JPanel();
    	panel1.add(label1);
    	JPanel panel2 = new JPanel();
    	panel2.add(label2);
    	JPanel panel3 = new JPanel();
    	panel3.add(label3);
    	JPanel panel4 = new JPanel();
    	panel4.add(label4);
    	JPanel panel5 = new JPanel();
//    	panel5.add(label5);
    	JPanel panel6 = new JPanel();
//    	panel6.add(label6);
    	JPanel panel7 = new JPanel();
//    	panel7.add(label7);
    	JPanel panel8 = new JPanel();
//    	panel8.add(label8);

    	testText.add(panel1,gbc);
//    	panel1.setOpaque(false);
    	gbc.gridx++;
    	testText.add(panel2,gbc);
//    	panel2.setOpaque(false);
    	gbc.gridx++;
    	testText.add(panel3,gbc);
//    	panel3.setOpaque(false);
    	gbc.gridx++;
    	testText.add(panel4,gbc);
//    	panel4.setOpaque(false);
    	gbc.gridx = 0;
    	gbc.gridy++;
    	testText.add(panel5,gbc);
//    	panel5.setOpaque(false);
    	gbc.gridx++;
    	testText.add(panel6,gbc);
    	panel6.setOpaque(false);
    	gbc.gridx++;
    	testText.add(panel7,gbc);
//    	panel7.setOpaque(false);
    	gbc.gridx++;
    	testText.add(panel8,gbc);
    	panel8.setOpaque(false);



    	testText.setOpaque(false);

    	return testText;
    }

    private static JPanel frameToTest() {
    	final JPanel jpanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        jpanel.setOpaque(false);
        jpanel.setPreferredSize(new Dimension(300,300));
        jpanel.setMinimumSize(new Dimension(300,300));
        jpanel.setMaximumSize(new Dimension(300,300));
    	return jpanel;
    }

    public static String getFrameCalculator(String baseValue, int count){
        Long valueModulo =  Long.parseLong("1000000000",16);
        Long staticValue = Long.valueOf("10dcd",16);
        for(int i = 0 ; i < count; i++){
           baseValue =  Long.toHexString((Long.valueOf(baseValue, 16) * staticValue + 1) % valueModulo);
        }
        return baseValue;
    }

    private static JFrame getFrame() {
    	JFrame frame = new JFrame();
    	frame.setTitle("TEST");
    	frame.setLocationRelativeTo((Component)null);
    	frame.setDefaultCloseOperation(2);

    	frame.setResizable(true);
    	frame.setFocusable(true);
    	frame.setVisible(true);
    	frame.setSize(new Dimension(460,460));
    	frame.setPreferredSize(new Dimension(460,460));
    	return frame;
    }

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}




}
