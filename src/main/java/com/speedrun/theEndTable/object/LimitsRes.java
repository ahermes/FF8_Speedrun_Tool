package com.speedrun.theEndTable.object;

import java.util.Comparator;

public class LimitsRes {
    int table = 0;
    int crisis = -1;
    int entry = 0;
    int rng = 0;
    int limitLevel = 0;
    int currentRng = 0;
    int currentEntry = 0;

    public LimitsRes() {
    }

    public LimitsRes(int table, int crisis, int entry) {
        this.table = table;
        this.crisis = crisis;
        this.entry = entry;
        this.currentEntry = entry;
    }

    public LimitsRes(int table, int crisis, int entry, int rng, int limitLevel) {
        this.table = table;
        this.crisis = crisis;
        this.entry = entry;
        this.rng = rng;
        this.limitLevel = limitLevel;
        this.currentEntry = entry;
        this.currentRng = rng;
    }

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public int getCrisis() {
        return crisis;
    }

    public void setCrisis(int crisis) {
        this.crisis = crisis;
    }

    public int getEntry() {
        return entry;
    }

    public void setEntry(int entry) {
        this.entry = entry;
        this.currentEntry = entry;
    }

    public int getRng() {
        return rng;
    }

    public void setRng(int rng) {
        this.rng = rng;
        this.currentRng = rng;
    }

    public int getLimitLevel() {
        return limitLevel;
    }

    public void setLimitLevel(int limitLevel) {
        this.limitLevel = limitLevel;
    }

    public void addCurrentRNG(){
        this.currentRng = (this.currentRng + 4) %256;
    }

    public void addCurrentEntry(){
        this.currentEntry = (this.currentEntry + 1) % 64;
    }

    public void decreaseCurrentEntry(){
        this.currentEntry = (this.currentEntry - 1) % 64;
    }

    public void decreaseCurrentRNG(){
        this.currentRng = (this.currentRng - 4) %256;
    }

    public int getCurrentRng(){
        return this.currentRng;
    }

    public int getCurrentEntry(){
        return this.currentEntry;
    }

    public void setFromLimitsRes(LimitsRes limitsRes){
        this.table = limitsRes.getTable();
        this.crisis = limitsRes.getCrisis();
        this.entry = limitsRes.getEntry();
        this.rng = limitsRes.getRng();
        this.limitLevel = limitsRes.getLimitLevel();
        this.currentEntry = limitsRes.getCurrentEntry();
        this.currentRng = limitsRes.getCurrentRng();
    }

    @Override
    public String toString() {
        return "LimitsRes{" +
                "table=" + table +
                ", crisis=" + crisis +
                ", entry=" + entry +
                ", rng=" + rng +
                ", limitLevel=" + limitLevel +
                ", currentEntry =" + currentEntry +
                ", currentRng =" + currentRng +
                '}';
    }

    public static Comparator<LimitsRes> ComparatorLimits = new Comparator<LimitsRes>() {

        @Override
        public int compare(LimitsRes e1, LimitsRes e2) {
            return (int) (e1.getRng() - e2.getRng());
        }
    };
}
