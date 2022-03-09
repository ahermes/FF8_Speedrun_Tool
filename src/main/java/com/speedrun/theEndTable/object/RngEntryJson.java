package com.speedrun.theEndTable.object;

import com.google.gson.annotations.SerializedName;

public class RngEntryJson {
    @SerializedName("rng")
    int rng;
    @SerializedName("table")
    int table;
    @SerializedName("entry")
    int entry;

    public RngEntryJson(int rng, int table, int entry) {
        this.rng = rng;
        this.table = table;
        this.entry = entry;
    }

    public int getRng() {
        return rng;
    }

    public void setRng(int rng) {
        this.rng = rng;
    }

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public int getEntry() {
        return entry;
    }

    public void setEntry(int entry) {
        this.entry = entry;
    }

    @Override
    public String toString() {
        return "RngEntryJson{"+
                "rng=" + rng +
                ", table=" + table +
                ", entry=" + entry +
                "}";
    }
}
