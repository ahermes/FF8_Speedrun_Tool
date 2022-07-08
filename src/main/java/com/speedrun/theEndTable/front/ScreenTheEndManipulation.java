package com.speedrun.theEndTable.front;

import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.speedrun.theEndTable.utilities.TheEndManipulationEngine;
import com.speedrun.utilities.GlobalValues;
import com.speedrun.utilities.GlobalValuesTitle;
import com.speedrun.utilities.graphics.MainJFrame;
import com.speedrun.utilities.toolkit.UtilitiesToolkit;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class ScreenTheEndManipulation {

    private final static String DEFAULT_ENTER_VALUE = "Enter value";
    private final static String DEFAULT_MAX_HP = "482";
    private final static String DEFAULT_TARGET_SPELL = "The End";

    private MainJFrame mainFrame;
    private final JTextField jtf = new JTextField("");
    private MainJFrame secondFrame;
    private MainJFrame thirdFrame;
    private Map<String,JTextField> textFieldMap;
    private boolean flag = false;
    private Map<String,String> dataMap;
    private boolean precise = false;
    private int statusStats = 0;
//    private int deadCharacters = 0;
    private int selphieLvl = 8;
//    private int availableATB = 1;


    public ScreenTheEndManipulation(){
        this.textFieldMap = new HashMap<String,JTextField>();
        this.dataMap = new HashMap<String,String>();
        this.textFieldMap.put("MaxHp", new JTextField());
        this.dataMap.put("MaxHp", DEFAULT_MAX_HP);
        this.textFieldMap.put("Current Hp", new JTextField());
        this.dataMap.put("Current Hp", DEFAULT_ENTER_VALUE);
        this.textFieldMap.put("Target Spell", new JTextField());
//        this.textFieldMap.put("TargetSpell", new AutoCompleteTextField(10));
        this.dataMap.put("Target Spell", DEFAULT_TARGET_SPELL);
        // Ajout des mort
        this.dataMap.put("Dead Characters", String.valueOf(0));
        // Ajout des ATB Dispo
        this.dataMap.put("Available ATB?", String.valueOf(1));
        // Ajout des Status
        this.dataMap.put("Status Sum", String.valueOf(statusStats));

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
        res.add(getColumn("Current Hp"),gbc);
        gbc.gridx++;
//        res.add(getRadioButton("SelphieLvl",3,initDataRadio()),gbc);
        gbc.gridx++;
//        res.add(getColumn("MaxHp"),gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        res.add(getRadioButton("Dead Characters",3,initDataDeadCharacterRadio(), true),gbc);
        gbc.gridx++;
        res.add(getCheckBox("Status Sum",3, initDataBox()),gbc);
        gbc.gridx++;
        res.add(getRadioButton("Available ATB?",3,initDataAvailableATBRadio(), true),gbc);
        gbc.gridy++;
        gbc.gridx=0;
//        res.add(getTheEndBlock(),gbc);
        gbc.gridx++;
        gbc.gridwidth = 2;
        res.add(getButtonBlock(res),gbc);
        res.setOpaque(false);
        return res;
    }

    private Map<String,Integer> initDataBox(){
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("Aura", 200);
        map.put("Doom",45);
        map.put("Sub Fossil", 30);
        map.put("Poison", 30);
        map.put("Blind", 30);
        map.put("Silence",30);
        map.put("Slow",15);
        return map;
    }

    private Map<String,Integer> initDataAvailableATBRadio(){
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("1", 1);
        map.put("2", 2);
        return map;
    }

    private Map<String,Integer> initDataDeadCharacterRadio(){
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("0", 0);
        map.put("1", 1);
        map.put("2", 2);
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
                            dataMap.put(name, String.valueOf(statusStats));
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


    private JPanel getRadioButton(String name, int limitLine, Map<String,Integer> data, boolean firstSelected){
        JPanel blockPanel = new JPanel(new BorderLayout());
        blockPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        ButtonGroup buttonGroup = new ButtonGroup();
        for(String boxName : data.keySet()){

            JRadioButton radioButton = new JRadioButton("", false);
            radioButton.setActionCommand(String.valueOf(data.get(boxName)));
            radioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dataMap.put(name, buttonGroup.getSelection().getActionCommand());
                }
            });

            radioButton.setOpaque(false);
            if (firstSelected) {
                radioButton.setSelected(true);
                firstSelected = false;
            }
            UtilitiesToolkit.setSizeOfComponent(radioButton, new Dimension(40, GlobalValues.LINE_BASE_SIZE));
            buttonGroup.add(radioButton);
            blockPanel.add(UtilitiesToolkit.getDecorateComponenent(boxName,radioButton,10),gbc);
            if(gbc.gridx >= limitLine-1){
                gbc.gridx = 0;
                gbc.gridy ++;
            }else{
                gbc.gridx++;
            }

        }
//        UtilitiesToolkit.setSizeOfComponent(buttonGroup, new Dimension(40, GlobalValues.LINE_BASE_SIZE));
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
                    System.out.println(e);
                    if (textField.getText().equals(DEFAULT_ENTER_VALUE)) {
                        textField.setText("");
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    System.out.println("FOCUS LOST");
                    if (textField.getText().equals("")) {
                        textField.setText(DEFAULT_ENTER_VALUE);
                    } else {
                        textField.setText(textField.getText());
                        dataMap.put(name, textField.getText());
                    }
                    System.out.println(dataMap.get(name));
                    System.out.println(textField.getText());
                }
            });
            UtilitiesToolkit.setSizeOfComponent(textField, new Dimension(100, GlobalValues.LINE_BASE_SIZE));
          return UtilitiesToolkit.getBlocWithBackground(name,textField,300,GlobalValues.LINE_BASE_SIZE*2,new ImageIcon(this.getClass().getResource("/imageFenetre/bg.png")).getImage());

    }

    private JPanel getTheEndBlock(){
        JPanel panelTheEnd = new JPanel();
        panelTheEnd.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

//        AutoCompleteTextField textField = (AutoCompleteTextField) this.textFieldMap.get("TargetSpell");
        JComboBox<Object> jComboBox = new JComboBox<>();
        AutoCompleteSupport.install(jComboBox, GlazedLists.eventListOf(TheEndManipulationEngine.SPELL_LIST2));
        JTextField textField =  this.textFieldMap.get("TargetSpell");
        AutoCompleteDecorator.decorate(textField, TheEndManipulationEngine.SPELL_LIST, false);
//        TheEndManipulationEngine.SPELL_LIST.forEach(textField::addPossibility);
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
        panelTheEnd.add(jComboBox,gbc);
        panelTheEnd.setOpaque(false);
        UtilitiesToolkit.setSizeOfComponent(panelTheEnd, new Dimension(300, 60));
        return UtilitiesToolkit.getBlocWithBackground("Target Spell",panelTheEnd,new ImageIcon(this.getClass().getResource("/imageFenetre/bg.png")).getImage());
    }

    private JPanel getButtonBlock(JPanel res){
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
        panel.add(getLaunchButtons(res),gbc);
        panel.setOpaque(false);
        return UtilitiesToolkit.getBlocWithBackground("",panel,600,60,new ImageIcon(this.getClass().getResource("/imageFenetre/bg.png")).getImage());
    }

    private JPanel getLaunchButtons(JPanel res) {
        JPanel buttons =  new JPanel(new BorderLayout(50,0));
        for(String key : this.dataMap.keySet()){
            System.out.println(key + " : " + this.dataMap.get(key));
        }
        JButton buttonLaunch = new JButton("Launch!");
        buttonLaunch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(String key : dataMap.keySet()){
                    System.out.println(key + " : " + dataMap.get(key));
                }
                //@TODO ajouter dans les options le dossier des Scripts JS
//                final String jsPath = "C:\\Users\\cladd\\IdeaProjects\\FF8_Speedrun_Tool\\resources\\scripts\\Script";
                final String jsPath = "C:\\Users\\Marie KIKI\\Documents\\code\\FF8_Speedrun_Tool\\resources\\scripts\\Script\\dist\\slot-manip-project.exe";
//                UtilitiesToolkit.launchScript("npm start", new ArrayList<String>(Arrays.asList("--prefix", jsPath, "toto", "4")));
                ArrayList<String> test = new ArrayList<>();
                dataMap.forEach((key, value) -> {
                    if (value.contains(" ")) {
                        test.add("'" + value + "'");
                    } else {
                        test.add(value);
                    }
                });
                Gson gson = new GsonBuilder().disableHtmlEscaping().create();
                Type gsonType = new TypeToken<HashMap>(){}.getType();
                String gsonString = gson.toJson(dataMap, gsonType);
                System.out.println(gsonString);

                UtilitiesToolkit.launchScript(jsPath, new ArrayList<String>(Arrays.asList(gsonString.replace("\"", "'").replace(" ", ""))));
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
