package com.speedrun.utilities.toolkit;

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;

import com.speedrun.utilities.GlobalValues;
import com.speedrun.utilities.graphics.ImagePanel;
import com.speedrun.utilities.graphics.ShadowLabel;

public class UtilitiesToolkit {

	
	public static void setSizeOfComponent(Component jObject, Dimension dimension) {
		jObject.setPreferredSize(dimension);
		jObject.setMaximumSize(dimension);
		jObject.setMinimumSize(dimension);
	}
	
	public static JScrollPane addJScrollPane(Component panel) {
		JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setViewportView(panel);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(50);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        return scrollPane;
	}
	
    public static void launchScript(String scriptPath, ArrayList<String> arg){
        try
        {
            ArrayList<String> listArg = new ArrayList<String>(Arrays.asList("cmd", "/c", "start", "cmd.exe", "/K" ));
			listArg.add("\"\"" + scriptPath.replaceAll("\\\\", "\"\\\\\"") + "\"\"");
            listArg.addAll(arg);
			listArg.forEach(System.out::println);
            Runtime.getRuntime().exec(listArg.toArray(new String[0]));

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void launchJsScript(String scriptPath, ArrayList<String> arg) throws ScriptException, IOException {
		try
		{
			ArrayList<String> listArg = new ArrayList<String>(Arrays.asList("cmd", "/c", "start", "cmd.exe", "/K" ));
			listArg.add("\"" + scriptPath + "\"");
			listArg.addAll(arg);
			Runtime.getRuntime().exec(listArg.toArray(new String[0]));

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void launchScriptScript(String scriptPath, ArrayList<String> arg) throws ScriptException, IOException {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("Rhino");
		engine.eval(Files.newBufferedReader(Paths.get(scriptPath), StandardCharsets.UTF_8));

	}

    public static void setFont(Component component,int size, Color color) {
    	component.setFont(new Font("Arial", 1, size));
    	component.setForeground(color);
    }
    
    public static void setRadioButton(JRadioButton button) {
    	if(button.isSelected()) {
    		button.setIcon(new ImageIcon(UtilitiesToolkit.class.getResource("/imageFenetre/Select.png")));
    	}else {
    		button.setIcon(new ImageIcon(UtilitiesToolkit.class.getResource("/imageFenetre/unSelect.png")));
    	}
    }
    
    public static JPanel getVoidPanel(int width, int height) {
    	JPanel res = new JPanel();
    	Dimension dimension = new Dimension(width, height);
    	res.setPreferredSize(dimension);
    	res.setMaximumSize(dimension);
    	res.setMinimumSize(dimension);
    	res.setOpaque(false);
    	return res;
    }

    public static JPanel getBlocWithBackground(String name, Component panel,Image bgImage){
		ImagePanel panelRes = new ImagePanel();
		panelRes.setLayout(new BoxLayout(panelRes,BoxLayout.PAGE_AXIS));
		panelRes.setBackground(bgImage);
		panelRes.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		int height = (int)panel.getMaximumSize().getHeight();
		int width = (int)panel.getMaximumSize().getWidth();
		UtilitiesToolkit.setSizeOfComponent(panelRes, new Dimension(width, height+GlobalValues.LINE_BASE_SIZE));
		panelRes.add(UtilitiesToolkit.getLabelBloc(name, GlobalValues.FONT_SIZE,width-10,width-10,-1));
		panelRes.add(panel);
		panelRes.setOpaque(false);
		return panelRes;
	}

	public static JPanel getBlocWithBackground(String name, Component panel,int width, int height,Image bgImage){
		ImagePanel panelRes = new ImagePanel();
		panelRes.setLayout(new BoxLayout(panelRes,BoxLayout.PAGE_AXIS));
		panelRes.setBackground(bgImage);
		panelRes.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		UtilitiesToolkit.setSizeOfComponent(panelRes, new Dimension(width, height+GlobalValues.LINE_BASE_SIZE));
		panelRes.add(UtilitiesToolkit.getLabelBloc(name, GlobalValues.FONT_SIZE,width-10,width-10,-1));
		panelRes.add(panel);
		panelRes.setOpaque(false);
		return panelRes;
	}

	public static JPanel getDecorateComponenent(String name, Component component,int width){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		panel.add(UtilitiesToolkit.getLabelBloc(name, GlobalValues.FONT_SIZE, width, width, -1),BorderLayout.WEST);
		panel.add(component,BorderLayout.EAST);
		panel.setOpaque(false);
		return panel;
	}
    
    public static JPanel getLabelBloc(String title, int fontSize, int width, int labelwidth,int lineSize) {
    	JPanel panel = new JPanel();
    	if(lineSize < 0) {
    		lineSize = GlobalValues.LINE_BASE_SIZE;
    	}
    	ShadowLabel label = new ShadowLabel(title, fontSize,labelwidth);
    	panel.add(label);
    	UtilitiesToolkit.setSizeOfComponent(panel, new Dimension(width, lineSize));
    	panel.setOpaque(false);
    	return panel;
    }

	public static boolean isInt(String s)
	{
		try
		{ int i = Integer.parseInt(s); return true; }

		catch(NumberFormatException er)
		{ return false; }
	}

	public static boolean isDouble(String toCheck){
		try
		{ double i = Double.parseDouble(toCheck); return true; }
		catch (NumberFormatException er)
		{return false;}
	}
}
