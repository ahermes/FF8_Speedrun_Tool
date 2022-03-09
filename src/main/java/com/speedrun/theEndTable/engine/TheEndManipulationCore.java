package com.speedrun.theEndTable.engine;

import com.google.gson.reflect.TypeToken;
import com.speedrun.theEndTable.object.LimitsRes;
import com.speedrun.theEndTable.object.RngEntryJson;
import com.speedrun.theEndTable.object.Spells;
import com.speedrun.theEndTable.object.TablesLB;
import com.speedrun.theEndTable.utilities.TheEndManipulationEngine;
import com.speedrun.utilities.GlobalValues;
import com.speedrun.utilities.Logger;
import com.speedrun.utilities.helper.JsonExtractorHelper;

import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TheEndManipulationCore {
    private Logger logger = new Logger(TheEndManipulationCore.class,true);

    private List<TablesLB> tableLimits= new ArrayList<TablesLB>();
    private List<RngEntryJson> rngEntries;
    private List<LimitsRes> rngEntriesRes = new ArrayList<LimitsRes>();
    private List<LimitsRes> spellDotEntry = new ArrayList<LimitsRes>();
    private List<LimitsRes> possibleEntry = new ArrayList<LimitsRes>();
    private  Boolean crisisToCalculFlag = false;
    private double crisisLimit = -1;
    private int stoppedChar = 1;
    private int deadChar = 0;
    private int[] rngArray = {
           252, 171, 181, 166, 141, 70, 192, 5, 60, 227, 223, 232, 74, 100, 78, 3, 111, 20, 247, 220, 194, 224, 148, 236,
           94, 72, 6, 217, 196, 183, 168, 186, 28, 75, 85, 15, 45, 102, 173, 37, 121, 81, 23, 71, 215, 126, 118, 35,
           143, 208, 49, 191, 73, 0, 113, 66, 254, 169, 230, 209, 161, 234, 9, 33, 127, 107, 117, 93, 77, 140, 159, 174,
           139, 163, 184, 190, 206, 83, 14, 112, 47, 10, 82, 95, 204, 63, 84, 7, 162, 226, 198, 153, 65, 119, 104, 132,
           218, 250, 21, 219, 237, 38, 122, 229, 57, 212, 88, 211, 170, 68, 54, 197, 189, 29, 241, 188, 172, 248, 116, 167,
           103, 213, 123, 145, 97, 151, 201, 154, 11, 205, 19, 144, 13, 27, 90, 152, 182, 99, 120, 115, 202, 53, 214, 48,
           106, 61, 18, 31, 76, 255, 176, 199, 135, 245, 155, 178, 1, 56, 105, 193, 156, 109, 40, 253, 96, 108, 58, 22,
           86, 131, 24, 30, 239, 243, 110, 133, 125, 221, 50, 124, 44, 128, 52, 12, 39, 62, 59, 249, 36, 87, 137, 225,
           203, 235, 200, 80, 165, 134, 26, 69, 180, 238, 210, 51, 138, 147, 67, 142, 177, 240, 55, 146, 233, 216, 195, 98,
           158, 136, 91, 25, 4, 17, 41, 129, 92, 246, 149, 79, 32, 187, 160, 101, 43, 150, 114, 222, 175, 179, 46, 244,
           207, 157, 242, 89, 34, 64, 16, 130, 231, 2, 251, 185, 228, 42, 8, 164
 };


    public TheEndManipulationCore() {
        rngDataLoader();
        tableDataLoader();
//        logger.setLogTrace(true);
    }

    public void init(String spell, Boolean preciseOnly){
        spellDotEntry = TheEndManipulationEngine.getDotEntry(tableLimits,TheEndManipulationEngine.convertStringToSpell(spell), preciseOnly);
        initSearchingTable();
        logger.showTrace("list : " + spellDotEntry);
    }

    public void calculateCrisisLimit(int maxHp, int currentHp, int deadChar, int status, int stoppedChar){
        crisisToCalculFlag = true;
        this.stoppedChar = stoppedChar;
        crisisLimit = TheEndManipulationEngine.preCalculateLimitRng(maxHp,currentHp,deadChar,status);
    }

    private void initSearchingTable(){
        for(int i = 255 ; i >= 0; i--){
            LimitsRes result = new LimitsRes();
            result.setRng(rngArray[255-i]);
            getTableEntry(result);
            if(crisisToCalculFlag){
                int res = (int)Math.floor(crisisLimit/i);
                result.setLimitLevel(res);
                result.setCrisis( TheEndManipulationEngine.crisisLevelCalculator(res));
                isAnEntry(result);
                result.decreaseCurrentEntry();
                result.decreaseCurrentRNG();
                rngEntriesRes.add(result);
            }else{
                for(int lvl = 1 ; lvl <= 4; lvl++) {
                    LimitsRes noCrisisRes = new LimitsRes();
                    noCrisisRes.setFromLimitsRes(result);
                    noCrisisRes.setCrisis(lvl);
                    isAnEntry(noCrisisRes);
                    noCrisisRes.decreaseCurrentEntry();
                    noCrisisRes.decreaseCurrentRNG();
                    rngEntriesRes.add(noCrisisRes);
                }
            }
        }
        Collections.sort(rngEntriesRes, LimitsRes.ComparatorLimits);
    }

    public void calculateNextSpell(String spell){
        spell = TheEndManipulationEngine.checkSpellSpelling(spell);
        logger.showTrace("NEXT SPELL : " + spell.toLowerCase());
        List<LimitsRes> newRngEntriesRes = new ArrayList<LimitsRes>();
        for(LimitsRes res : rngEntriesRes){
            res.addCurrentRNG();
            res.addCurrentEntry();
            LimitsRes newRes = new LimitsRes(res.getTable(), res.getCrisis(), res.getCurrentEntry());
            newRes.setRng(res.getRng());
            if(checkSpell(spell.toLowerCase(),newRes)){
                logger.showLog("Keept Entry : " + newRes);
                newRngEntriesRes.add(res);
            }
        }
        rngEntriesRes = newRngEntriesRes;
        logger.showLog("///////////NEXT   :"+rngEntriesRes.size()+"///////////");
        TheEndManipulationEngine.showList(rngEntriesRes);
        if(rngEntriesRes.size() == 1){
            TheEndManipulationEngine.showList(rngEntriesRes);
            LimitsRes result = getNearestEntry(rngEntriesRes.get(0));
            logger.showLog("Entry to get : " + result.toString());
            TheEndManipulationEngine.showActionToDo(result,rngEntriesRes.get(0),3-this.deadChar);
        }
    }

    private void getTableEntry (LimitsRes limitRes){
        for(int i = 0 ; i < rngEntries.size() ; i ++){
            if(rngEntries.get(i).getRng() == limitRes.getRng()){
                limitRes.setEntry(rngEntries.get(i).getEntry());
                limitRes.setTable(rngEntries.get(i).getTable());
                break;
            }
        }
    }

    private void isAnEntry(LimitsRes result){
        for(LimitsRes entry : this.spellDotEntry){
            if(entry.getTable() == result.getTable() && entry.getCrisis() == result.getCrisis() && !possibleEntry.contains(result)){
                possibleEntry.add(result);
            }
        }
    }

    public Boolean isResultAvailable(){
        logger.showTrace("size : " +  rngEntriesRes.size());
        return rngEntriesRes.size() <= 1;
    }

    public String getResult(){
        if(rngEntriesRes.size() == 1){
            logger.showTrace("///////////ENTRIES:" + possibleEntry.size() + "///////////");
            //       showList(possibleEntry);
            logger.showTrace("///////////FINAL  :"+rngEntriesRes.size()+"///////////");
            TheEndManipulationEngine.showList(rngEntriesRes);
            LimitsRes result = getNearestEntry(rngEntriesRes.get(0));
            logger.showTrace("Entry to get : " + result.toString());
            return TheEndManipulationEngine.showActionToDo(result,rngEntriesRes.get(0),checkOverToLimit(result));
        }
        else{
            return "NO RESULT";
        }
    }

    private boolean checkSpell(String spell, LimitsRes limitRes){
        if(limitRes.getCrisis() == 0){
            return false;
        }
        return tableLimits.get(limitRes.getTable()-1).getTableCrisis(limitRes.getCrisis()).get(limitRes.getEntry()).getFullName().equals(spell);

    }

    private LimitsRes getNearestEntry(LimitsRes actualLimit){
        LimitsRes res = actualLimit;
        int nearestRng = 999;
        for(LimitsRes spellFound : possibleEntry) {
            //System.out.println("Possible Entry : " + spellFound);
            int rngToTest = TheEndManipulationEngine.dotSpellCalculator(spellFound,actualLimit,checkOverToLimit(spellFound),stoppedChar);
            logger.showTrace("Spell Found : " + spellFound.toString());
            logger.showTrace("Rng Delta : " + rngToTest + ", rng Took : " + nearestRng);
            if (actualLimit.getTable() == spellFound.getTable() && actualLimit.getCrisis() == spellFound.getCrisis() && nearestRng > rngToTest){
                res = actualLimit;
                nearestRng = rngToTest;
            }

            if(res == null || rngToTest < nearestRng){
                res = spellFound;
                nearestRng = rngToTest;
            }
        }
        return res;
    }


    //Calculate the number of over to do to reach the limit when a the enter of the table
    private int checkOverToLimit(LimitsRes spellEntry){
        int res = 999;
        int toTest = 0;
        for(LimitsRes limit : spellDotEntry){
            if(limit.getTable() == spellEntry.getTable() && limit.getCrisis() == spellEntry.getCrisis()){
                toTest = limit.getCurrentEntry() - spellEntry.getCurrentEntry();
                if(toTest < 0){
                    toTest += 64;
                }
                if(toTest < res){
                    res = toTest;
                }
            }
        }
        return res;
    }



    private void rngDataLoader(){
        Type listRngEntry = new TypeToken<List<RngEntryJson>>() {}.getType();
        this.rngEntries = (List<RngEntryJson>) JsonExtractorHelper.getJson(Paths.get(GlobalValues.ACCESSIBLE_RESSOURCES_FILES + GlobalValues.RNG_ENTRIES_FILE).toString() , listRngEntry);

    }

    private void tableDataLoader(){
        Type listTable = new TypeToken<List<List<List<String>>>>() {}.getType();
        List<List<List<String>>> tableEntry = (List<List<List<String>>>) JsonExtractorHelper.getJson(Paths.get(GlobalValues.ACCESSIBLE_RESSOURCES_FILES + GlobalValues.LIMITS_TABLES_FILE).toString(), listTable);
        this.tableLimits = TheEndManipulationEngine.listToTablesLBConvertor(tableEntry);
    }

    public void reset(){
        int stoppedChar = 1;
        rngEntriesRes.clear();
        spellDotEntry.clear();
        possibleEntry.clear();
    }
}
