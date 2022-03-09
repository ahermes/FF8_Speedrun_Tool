package com.speedrun.utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.speedrun.utilities.GlobalValues.ACCESSIBLE_RESSOURCES_SCRIPTS;

public class ScriptLauncher {

    public static void launchScript(String scriptPath, ArrayList<String> arg){
        try
        {
            ArrayList<String> listArg = new ArrayList<String>(Arrays.asList("cmd", "/c", "start", "cmd.exe", "/K" ));
            listArg.add("\"" + scriptPath + "\"");
            listArg.addAll(arg);
            System.out.println(listArg);
            Runtime.getRuntime().exec(listArg.toArray(new String[0]));

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
