package com.speedrun.theEndTable.utilities;

import com.speedrun.theEndTable.engine.TheEndManipulationCore;
import com.speedrun.theEndTable.object.LimitsRes;
import com.speedrun.theEndTable.object.Spells;
import com.speedrun.theEndTable.object.TablesLB;
import com.speedrun.utilities.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TheEndManipulationEngine {
    static public Logger logger = new Logger(TheEndManipulationEngine.class,true);
    static public List<String> SPELL_LIST = Arrays.asList(new String[]{
            "arkange (1)", "anti-sort (2)", "aphasie (1)", "aphasie (2)", "aphasie (3)", "aura (1)", "aura (2)", "aura (3)", "blindage (1)", "blindage (2)", "booster (1)", "booster (2)", "booster (3)", "brasier (1)",
            "brasier (2)", "brasier (3)", "brasier + (1)", "brasier + (2)", "brasier x (1)", "brasier x (2)", "brasier x (3)", "carapace (1)", "carapace (2)", "carapace (3)", "cecite (1)", "cecite (2)", "cecite (3)",
            "cyanure (1)", "cyanure (2)", "cyanure (3)", "decubitus (1)", "decubitus (2)", "decubitus (3)", "double (2)", "esuna (1)", "esuna (2)", "esuna (3)", "folie (3)", "foudre (1)", "foudre (2)", "foudre (3)",
            "foudre + (1)", "foudre + (2)", "foudre + (3)", "foudre x (1)", "foudre x (2)", "foudre x (3)", "fournaise (1)", "glacier (1)", "glacier (2)", "glacier (3)", "glacier + (1)", "glacier + (2)", "glacier + (3)",
            "glacier x (1)", "glacier x (2)", "glacier x (3)", "h2o (1)", "h2o (2)", "h2o (3)", "joobu (1)", "megalith (2)", "megalith (3)", "meteore (2)", "meteore (3)", "morphee (1)", "morphee (2)", "morphee (3)",
            "quake (1)", "quart (1)", "quart (2)", "recup (2)", "saignee (1)", "saignee (2)", "saignee (3)", "sideral (1)", "soin (1)", "soin (2)", "soin + (1)", "soin + (2)", "soin + (3)", "soin max (1)", "the end (1)",
            "ultima (2)", "wall (1)"
    });
    static public String[] SPELL_LIST2 = new String[]{
            "arkange (1)", "anti-sort (2)", "aphasie (1)", "aphasie (2)", "aphasie (3)", "aura (1)", "aura (2)", "aura (3)", "blindage (1)", "blindage (2)", "booster (1)", "booster (2)", "booster (3)", "brasier (1)",
            "brasier (2)", "brasier (3)", "brasier + (1)", "brasier + (2)", "brasier x (1)", "brasier x (2)", "brasier x (3)", "carapace (1)", "carapace (2)", "carapace (3)", "cecite (1)", "cecite (2)", "cecite (3)",
            "cyanure (1)", "cyanure (2)", "cyanure (3)", "decubitus (1)", "decubitus (2)", "decubitus (3)", "double (2)", "esuna (1)", "esuna (2)", "esuna (3)", "folie (3)", "foudre (1)", "foudre (2)", "foudre (3)",
            "foudre + (1)", "foudre + (2)", "foudre + (3)", "foudre x (1)", "foudre x (2)", "foudre x (3)", "fournaise (1)", "glacier (1)", "glacier (2)", "glacier (3)", "glacier + (1)", "glacier + (2)", "glacier + (3)",
            "glacier x (1)", "glacier x (2)", "glacier x (3)", "h2o (1)", "h2o (2)", "h2o (3)", "joobu (1)", "megalith (2)", "megalith (3)", "meteore (2)", "meteore (3)", "morphee (1)", "morphee (2)", "morphee (3)",
            "quake (1)", "quart (1)", "quart (2)", "recup (2)", "saignee (1)", "saignee (2)", "saignee (3)", "sideral (1)", "soin (1)", "soin (2)", "soin + (1)", "soin + (2)", "soin + (3)", "soin max (1)", "the end (1)",
            "ultima (2)", "wall (1)"
    };

////////////////////////////////////////////////////UTILITIES///////////////////////////////////////////////////////////


    static public boolean isIncluded(String spell){
        return SPELL_LIST.contains(spell);
    }

    static public String checkSpellSpelling(String spell){
        String[] spellSplit = spell.split("\\(");
        if(spellSplit.length <= 1){
            while(spell.charAt(spell.length()-1) == ' '){
                spell = spell.substring(0,spell.length()-1);
            }
            return spell + " (1)";
        }
        return spell;
    }

    static public boolean spellFilter(String toTest, String str) {
        return toTest.toLowerCase().contains(str.toLowerCase());
    }

    static public Spells convertStringToSpell(String spell){
        spell = checkSpellSpelling(spell);
        String[] spellSplit = spell.split("\\(");
        return new Spells(spellSplit[0].substring(0, spellSplit[0].length() - 1), Integer.valueOf(spellSplit[1].replaceAll("\\)", "")));
    }

    static public String showActionString(int doOver, int skip, int doOverLimits){
        String res = "To reach your spell : \t\n {DoOver = " + doOver + ", SkipTurn = " + skip + ", DoOver = " + doOverLimits + "}";
        logger.showLog(res);
        return res;
    }

    static public void showList(List<LimitsRes> limitsResEntries){
        for(int i = 0; i < limitsResEntries.size(); i++){
            logger.showLog("entry " + (i+1) + " : " + limitsResEntries.get(i).toString());
        }
    }

////////////////////////////////////////////////////CALCULS/////////////////////////////////////////////////////////////

    static public double calculateLimitRng(int maxHp, int currentHp, int deadChar, int status, int limitLevel){
        double hpMod = Math.floor(2500*currentHp / maxHp);
        double deathBonus = Math.floor(deadChar * 200 + 1600);
        double statusBonus = Math.floor(status * 10);
        double randomMod = Math.floor(limitLevel + 160);
        return (statusBonus + deathBonus - hpMod) / randomMod;
    }

    static public double preCalculateLimitRng(int maxHp, int currentHp, int deadChar, int status){
        double hpMod = Math.floor(2500*currentHp / maxHp);
        double deathBonus = Math.floor(deadChar * 200 + 1600);
        double statusBonus = Math.floor(status * 10);
        return (statusBonus + deathBonus - hpMod);
    }

    static public int crisisLevelCalculator(int limitLevel){
        int res = limitLevel - 4;
        if(res <= 0){
            return 0;
        }else if (res >= 4){
            return 4;
        }
        return res;
    }


////////////////////////////////////////////////////TABLES_MANIPULATIONS////////////////////////////////////////////////

    static public List<TablesLB> listToTablesLBConvertor(List<List<List<String>>> dataTables) {
        List<TablesLB> res = new ArrayList<TablesLB>();
        int actualTable = 0;
        int actualTableCrisis = 0;
        int actualEntry = 0;
        for (List<List<String>> tableData : dataTables) {
            actualTable++;
            actualTableCrisis = 0;
            TablesLB tablesLB = new TablesLB();
            for (List<String> data : tableData) {
                actualTableCrisis++;
                actualEntry = 0;
                ArrayList<Spells> tableCrisis = new ArrayList<Spells>();
                for (String spell : data) {

                    Spells spellToAdd = convertStringToSpell(spell);
                    actualEntry++;
                    tableCrisis.add(spellToAdd);
                }
                tablesLB.addTable(tableCrisis);
            }

            res.add(tablesLB);
        }
        return res;
    }

    static public List<LimitsRes> getDotEntry(List<TablesLB> tableLimits,Spells spellWanted,Boolean preciseOnly){
        List<LimitsRes> res = new ArrayList<LimitsRes>();
        for(int i = 0; i < tableLimits.size(); i++) {
            for (int j = 1; j <= 4; j++) {
                List<Spells> tableCrisis = tableLimits.get(i).getTableCrisis(j);
                for (int a = 0; a < tableCrisis.size(); a++) {
                    if (isDotEntry(tableCrisis.get(a), spellWanted, preciseOnly)) {
                        res.add(new LimitsRes(i + 1, j, a));
                    }
                }
            }
        }
        return res;
    }

    static private Boolean isDotEntry(Spells spellToCheck, Spells spellWanted, Boolean preciseOnly){
        if(preciseOnly) {
            return spellFilter(spellToCheck.getFullName(),spellWanted.getFullName()) && spellWanted.getNumber() == spellToCheck.getNumber();
        }else{
            return spellFilter(spellToCheck.getName(),spellWanted.getName()) && spellWanted.getNumber() <= spellToCheck.getNumber();
        }
    }

    static public void getPossibleTable(){
    }










    static public String showActionToDo(LimitsRes entryToReach, LimitsRes actualLimit, int smallestDoOver){

        if(actualLimit.getTable() == entryToReach.getTable() && actualLimit.getCrisis() == entryToReach.getCrisis()){
            if(actualLimit.getCurrentEntry() == entryToReach.getCurrentEntry()){
                return TheEndManipulationEngine.showActionString(0,0,0);
            }
            int doOver = actualLimit.getCurrentEntry() - entryToReach.getCurrentEntry();
            if(doOver < 0){
                doOver += 64;
            }
            smallestDoOver += doOver;
            return TheEndManipulationEngine.showActionString(0,0,smallestDoOver);
        }else{
            int skipTurn = 1;
            int doOver = 0;
            int delta = entryToReach.getRng() - actualLimit.getCurrentRng() - skipTurn;
            if(delta < 0){
                delta += 256;
            }

            skipTurn += delta%4;
            doOver = (int)Math.floor(delta/4);
            return TheEndManipulationEngine.showActionString(doOver, skipTurn, smallestDoOver);
        }

    }

    //Calculate the number of action to take to reach the limit
    static public int dotSpellCalculator(LimitsRes spellToReach, LimitsRes actualLimit, int doOverToLimit, int stoppedChar){
        int doOver = 0;
        if (actualLimit.getTable() == spellToReach.getTable() && actualLimit.getCrisis() == spellToReach.getCrisis()) {
            doOver = spellToReach.getCurrentEntry() - actualLimit.getCurrentEntry();

            return doOver < 0? (doOver + 64 + doOverToLimit) : (doOver + doOverToLimit);
        }
        int delta = spellToReach.getCurrentRng() - actualLimit.getCurrentRng();
        if(delta < 0){
            delta += 256;
        }

        int skipTurn = delta % 4;
        if(skipTurn == 0){
            skipTurn = 1;
        }
        // if you have more than 1 character who can take action then, you have to skip x number of turn before having Selphie action
        if(skipTurn%(3-stoppedChar) != 0){
            while(!((delta-skipTurn) %  4 == 0 && skipTurn%(3-stoppedChar) == 0)) {
                if(skipTurn > 10){
                    return 999;
                }
                skipTurn += 1;
            }
        }

        doOver = (delta - skipTurn) /4;
        int res = skipTurn + doOver + doOverToLimit;

        return res;
    }
}
