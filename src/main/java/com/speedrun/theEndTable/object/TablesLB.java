package com.speedrun.theEndTable.object;


import java.util.ArrayList;

public class TablesLB {


    ArrayList<Spells> tableCrisis1;
    ArrayList<Spells> tableCrisis2;
    ArrayList<Spells> tableCrisis3;
    ArrayList<Spells> tableCrisis4;


    public TablesLB(){

    }

    public TablesLB(ArrayList<Spells> tableCrisis1, ArrayList<Spells> tableCrisis2, ArrayList<Spells> tableCrisis3, ArrayList<Spells> tableCrisis4){
        this.tableCrisis1 = tableCrisis1;
        this.tableCrisis2 = tableCrisis2;
        this.tableCrisis3 = tableCrisis3;
        this.tableCrisis4 = tableCrisis4;
    }

    public void addTable(ArrayList<Spells> table){
        if(tableCrisis1 == null){
            this.tableCrisis1 = table;
        }
        else if (tableCrisis2 == null){
            this.tableCrisis2 = table;
        }
        else if (tableCrisis3 == null){
            this.tableCrisis3 = table;
        }
        else {
            this.tableCrisis4 = table;
        }
    }

    public ArrayList<Spells> getTableCrisis1() {
        return tableCrisis1;
    }

    public void setTableCrisis1(ArrayList<Spells> tableCrisis1) {
        this.tableCrisis1 = tableCrisis1;
    }

    public ArrayList<Spells> getTableCrisis2() {
        return tableCrisis2;
    }

    public void setTableCrisis2(ArrayList<Spells> tableCrisis2) {
        this.tableCrisis2 = tableCrisis2;
    }

    public ArrayList<Spells> getTableCrisis3() {
        return tableCrisis3;
    }

    public void setTableCrisis3(ArrayList<Spells> tableCrisis3) {
        this.tableCrisis3 = tableCrisis3;
    }

    public ArrayList<Spells> getTableCrisis4() {
        return tableCrisis4;
    }

    public void setTableCrisis4(ArrayList<Spells> tableCrisis4) {
        this.tableCrisis4 = tableCrisis4;
    }

    public ArrayList<Spells> getTableCrisis(int i) {
        switch (i) {
            case 1:
                return this.tableCrisis1;
            case 2:
                return this.tableCrisis2;
            case 3:
                return this.tableCrisis3;
            default:
                return this.tableCrisis4;
        }
    }

    @Override
    public String toString(){
        return "TablesLB{" +
                "\ntableCrisis1=" + tableCrisis1 +
                "\n, tableCrisis2=" + tableCrisis2 +
                "\n, tableCrisis3=" + tableCrisis3 +
                "\n, tableCrisis4=" + tableCrisis4 +
                "} \n\n";
    }
}
