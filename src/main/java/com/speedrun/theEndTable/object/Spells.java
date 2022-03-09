package com.speedrun.theEndTable.object;

public class Spells {
    String name;
    int number;

    public Spells(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getFullName(){
        return name + " (" + number + ")";
    }

    public boolean equals(Spells spell){
        return spell.getName().equals(name) && spell.getNumber() == number;
    }

    @Override
    public String toString() {
        return "Spells{" +
                "name=" + name +
                ", number=" + number +
                "}";
    }
}
