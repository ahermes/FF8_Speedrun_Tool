package com.speedrun.theEndTable.front;

import com.speedrun.theEndTable.engine.TheEndManipulationCore;
import com.speedrun.theEndTable.utilities.TheEndManipulationEngine;
import com.speedrun.utilities.GlobalValues;
import com.speedrun.utilities.GlobalValuesTitle;
import com.speedrun.utilities.graphics.MainJFrame;
import com.speedrun.utilities.graphics.ShadowLabel;
import com.speedrun.utilities.toolkit.UtilitiesToolkit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ScreenTheEndManipulation {

    private MainJFrame mainFrame;
    private final JTextField jtf = new JTextField("");
    private MainJFrame secondFrame;
    private MainJFrame thirdFrame;
    private Map<String,JTextField> textFieldMap;
    private boolean flag = false;
    private Map<String,String> dataMap;
    private boolean precise = false;
    private int statusStats = 0;

    public ScreenTheEndManipulation(){
        this.textFieldMap = new HashMap<String,JTextField>();
        this.dataMap = new HashMap<String,String>();
        this.textFieldMap.put("MaxHp", new JTextField());
        this.dataMap.put("MaxHp", "Enter value");
        this.textFieldMap.put("CurrentHp", new JTextField());
        this.dataMap.put("CurrentHp", "Enter value");
        this.textFieldMap.put("TargetSpell", new JTextField());
        this.dataMap.put("TargetSpell", "Enter value");
    }

    public void showFrame(Point position) {
        this.mainFrame = null;
        this.mainFrame = new MainJFrame(GlobalValuesTitle.THE_END_MANIPULATION,-1,-1,position);
        mainFrame.setNewBackground(null);
        mainFrame.addPanelWithScroll(getPanel());
    }

    public JPanel getPanel() {
        JPanel res = new JPanel();
        res.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        res.add(getColumn("CurrentHp"),gbc);
        gbc.gridx++;
        res.add(getRadioButton("SelphieLvl",3,initDataRadio()),gbc);
        gbc.gridx++;
        res.add(getColumn("MaxHp"),gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        res.add(getRadioButton("Dead Characters",3,initDataRadio()),gbc);
        gbc.gridx++;
        res.add(getCheckBox("Status",3, initDataBox()),gbc);
        gbc.gridx++;
        res.add(getRadioButton("Available ATB?",3,initDataRadio()),gbc);
        gbc.gridy++;
        gbc.gridx=0;
        res.add(getTheEndBlock(),gbc);
        gbc.gridx++;
        gbc.gridwidth = 2;
        res.add(getButtonBlock(),gbc);
        res.setOpaque(false);
        return res;
    }

    private Map<String,Integer> initDataBox(){
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("Aura", 300);
        map.put("Fossile", 100);
        map.put("Poison", 200);
        map.put("Blind", 300);
        map.put("Silent",200);
        map.put("test",100);
        return map;
    }

    private Map<String,Integer> initDataRadio(){
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("1", 300);
        map.put("2", 100);
        map.put("3", 200);
        return map;
    }


    private JPanel getCheckBox(String name, int limitLine, Map<String,Integer> data){
        JPanel blockPanel = new JPanel(new BorderLayout());
        blockPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        for(String boxName : data.keySet()){
            JCheckBox checkBox = new JCheckBox("", false);
            checkBox.addItemListener(
                    new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                            if(e.getStateChange() == 1){
                                statusStats += data.get(boxName);
                            }else{
                                statusStats -= data.get(boxName);
                            }
                        }
                    });
            checkBox.setOpaque(false);
            UtilitiesToolkit.setSizeOfComponent(checkBox, new Dimension(50, GlobalValues.LINE_BASE_SIZE));
            blockPanel.add(UtilitiesToolkit.getDecorateComponenent(boxName,checkBox,45),gbc);
            if(gbc.gridx >= limitLine-1){
                gbc.gridx = 0;
                gbc.gridy ++;
            }else {
                gbc.gridx++;
            }
    }
        UtilitiesToolkit.setSizeOfComponent(blockPanel, new Dimension(300, GlobalValues.LINE_BASE_SIZE*data.size()/limitLine));
        blockPanel.setOpaque(false);
        return UtilitiesToolkit.getBlocWithBackground(name,blockPanel,new ImageIcon(this.getClass().getResource("/imageFenetre/bg.png")).getImage());

    }

    private JPanel getRadioButton(String name, int limitLine, Map<String,Integer> data){
        JPanel blockPanel = new JPanel(new BorderLayout());
        blockPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        for(String boxName : data.keySet()){

            JRadioButton radioButton = new JRadioButton("", false);
            radioButton.addItemListener(
                    new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                            if(e.getStateChange() == 1){
                                statusStats += data.get(boxName);
                            }else{
                                statusStats -= data.get(boxName);
                            }
                        }
                    });
            radioButton.setOpaque(false);
            UtilitiesToolkit.setSizeOfComponent(radioButton, new Dimension(40, GlobalValues.LINE_BASE_SIZE));

            blockPanel.add(UtilitiesToolkit.getDecorateComponenent(boxName,radioButton,10),gbc);
            if(gbc.gridx >= limitLine-1){
                gbc.gridx = 0;
                gbc.gridy ++;
            }else{
                gbc.gridx++;
            }

        }
        UtilitiesToolkit.setSizeOfComponent(blockPanel, new Dimension(300, GlobalValues.LINE_BASE_SIZE*2));
        blockPanel.setOpaque(false);
        return UtilitiesToolkit.getBlocWithBackground(name,blockPanel,new ImageIcon(this.getClass().getResource("/imageFenetre/bg.png")).getImage());

    }

    private JPanel getColumn(String name){
            JTextField textField = this.textFieldMap.get(name);
            textField.setText(dataMap.get(name));
            textField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    textField.setText("");
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (textField.getText().equals("")) {
                        textField.setText(dataMap.get(name));
                    }
                }
            });
            UtilitiesToolkit.setSizeOfComponent(textField, new Dimension(100, GlobalValues.LINE_BASE_SIZE));
          return UtilitiesToolkit.getBlocWithBackground(name,textField,300,GlobalValues.LINE_BASE_SIZE*2,new ImageIcon(this.getClass().getResource("/imageFenetre/bg.png")).getImage());

    }

    private JPanel getTheEndBlock(){
        JPanel panelTheEnd = new JPanel();
        panelTheEnd.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JTextField textField = this.textFieldMap.get("TargetSpell");
        textField.setText(dataMap.get("TargetSpell"));
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().equals("")) {
                    textField.setText(dataMap.get("TargetSpell"));
                }
            }
        });
        UtilitiesToolkit.setSizeOfComponent(textField, new Dimension(100, GlobalValues.LINE_BASE_SIZE));
        JCheckBox checkBox = new JCheckBox("", false);
        checkBox.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                    }
                });
        checkBox.setOpaque(false);
        UtilitiesToolkit.setSizeOfComponent(checkBox, new Dimension(50, GlobalValues.LINE_BASE_SIZE));

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelTheEnd.add(UtilitiesToolkit.getDecorateComponenent("The end?",checkBox,45),gbc);
        gbc.gridy++;
        panelTheEnd.add(textField,gbc);
        panelTheEnd.setOpaque(false);
        UtilitiesToolkit.setSizeOfComponent(panelTheEnd, new Dimension(300, 60));
        return UtilitiesToolkit.getBlocWithBackground("Target Spell",panelTheEnd,new ImageIcon(this.getClass().getResource("/imageFenetre/bg.png")).getImage());
    }

    private JPanel getButtonBlock(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        JPanel spaceBefore = new JPanel();
        UtilitiesToolkit.setSizeOfComponent(spaceBefore, new Dimension(280, 20));
        spaceBefore.setOpaque(false);
        panel.add(spaceBefore,gbc);
        gbc.gridx++;
        panel.add(getResetButtonPanel(),gbc);
        gbc.gridx++;
        JPanel spaceBetween = new JPanel();
        UtilitiesToolkit.setSizeOfComponent(spaceBetween, new Dimension(50, 20));
        spaceBetween.setOpaque(false);
        panel.add(spaceBetween,gbc);
        gbc.gridx++;
        panel.add(getLaunchButtons(),gbc);
        panel.setOpaque(false);
        return UtilitiesToolkit.getBlocWithBackground("",panel,600,60,new ImageIcon(this.getClass().getResource("/imageFenetre/bg.png")).getImage());
    }

    private JPanel getLaunchButtons() {
        JPanel buttons =  new JPanel(new BorderLayout(50,0));

        JButton buttonLaunch = new JButton("Launch!");
        buttonLaunch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        buttonLaunch.setBackground(new Color(200,200,200));
        buttons.add(buttonLaunch);

        UtilitiesToolkit.setSizeOfComponent(buttons, new Dimension(100, GlobalValues.LINE_BASE_SIZE));
        buttons.setOpaque(false);
        return buttons;
    }

    private JPanel getResetButtonPanel() {
        JPanel res = new JPanel(new BorderLayout(50,0));
        JButton patern = new JButton("Reset");
        patern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetAll();
            }
        });
        patern.setBackground(new Color(200,200,200));

        res.add(patern,BorderLayout.CENTER);
        res.setOpaque(false);
        return res;
    }

    private void resetAll(){
        for(String key : this.textFieldMap.keySet()){
            this.textFieldMap.get(key).setText("");
        }
    }
}
