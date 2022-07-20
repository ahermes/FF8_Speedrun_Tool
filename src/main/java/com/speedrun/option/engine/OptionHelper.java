package com.speedrun.option.engine;

import com.speedrun.option.object.Options;
import com.speedrun.utilities.GlobalValues;

import java.io.*;
import java.util.Properties;

public class OptionHelper {


    public static Options optionLoader() {
        Options res = new Options();
        try {
            InputStream input = new FileInputStream(GlobalValues.ACCESSIBLE_RESSOURCES + "config.properties");

            Properties prop = new Properties();

            prop.load(input);

            res.setJsonPath(prop.getProperty("speedrun.jsonPath"));
            res.setPathScriptExe(prop.getProperty("speedrun.pathScriptExe"));
            res.setPathScriptTheEndExe(prop.getProperty("speedrun.pathScriptTheEndExe"));
            res.setBackgroundColorRngCount(Boolean.valueOf(prop.getProperty("speedrun.rngCount.backgroundColor")));
            res.setAcceptDelayFrame(Double.valueOf(prop.getProperty("speedrun.acceptDelayFrame")));
            res.setRngPageFormat(Boolean.valueOf(prop.getProperty("speedrun.arrangedFormat")));
            res.setDelayFrame(Double.valueOf(prop.getProperty("speedrun.delayFrame")));
            res.setGameFps(Double.valueOf(prop.getProperty("speedrun.gameFps")));
            res.setRubyQuistisPath(prop.getProperty("speedrun.ruby.quistis"));
            res.setRubyZellPath(prop.getProperty("speedrun.ruby.zell"));
            res.setRngRubyExeChoice(Boolean.valueOf(prop.getProperty("speedrun.script.choice")));
            res.setRadioButton(Boolean.valueOf(prop.getProperty("speedrun.buttons")));
            res.setPatternOrderByFrame(Boolean.valueOf(prop.getProperty("speedrun.patterns.order")));
            res.setShowPopUp(Boolean.valueOf(prop.getProperty("speedrun.popUp")));
        } catch (IOException io) {
            io.printStackTrace();
        }
        return res;
    }

    public static void optionWriter(Options option){
        try {
            OutputStream output = new FileOutputStream( GlobalValues.ACCESSIBLE_RESSOURCES + "config.properties");

            Properties prop = new Properties();

            prop.setProperty("speedrun.jsonPath", option.getJsonPath());
            prop.setProperty("speedrun.pathScriptExe", option.getPathScriptExe());
            prop.setProperty("speedrun.pathScriptTheEndExe", option.getPathScriptTheEndExe());
            prop.setProperty("speedrun.arrangedFormat", String.valueOf(option.getRngPageFormat()));
            prop.setProperty("speedrun.acceptDelayFrame",String.valueOf(option.getAcceptDelayFrame()));
            prop.setProperty("speedrun.delayFrame", String.valueOf(option.getDelayFrame()));
            prop.setProperty("speedrun.gameFps", String.valueOf(option.getGameFps()));
            prop.setProperty("speedrun.ruby.quistis", option.getRubyQuistisPath());
            prop.setProperty("speedrun.ruby.zell", option.getRubyZellPath());
            prop.setProperty("speedrun.script.choice", String.valueOf(option.getRngRubyExeChoice()));
            prop.setProperty("speedrun.buttons", String.valueOf(option.getRadioButton()));
            prop.setProperty("speedrun.patterns.order", String.valueOf(option.getPatternOrderByFrame()));
            prop.setProperty("speedrun.popUp", String.valueOf(option.getShowPopUp()));
            prop.setProperty("speedrun.rngCount.backgroundColor", String.valueOf(option.getBackgroundColorRngCount()));
            prop.store(output,null);


        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
