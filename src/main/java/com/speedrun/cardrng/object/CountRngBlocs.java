package com.speedrun.cardrng.object;

import com.speedrun.cardrng.object.lines.CountRngAbstractLine;
import com.speedrun.utilities.GlobalValues;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CountRngBlocs {
    private String label ="";
    private List<CountRngAbstractLine> listLine;
    private int height;

    public CountRngBlocs(){
        this.listLine = new ArrayList<CountRngAbstractLine>();
        this.height = GlobalValues.LINE_BASE_SIZE;
    }

    public CountRngBlocs(String label){
        this.label = label;
        this.listLine = new ArrayList<CountRngAbstractLine>();
        this.height = GlobalValues.LINE_BASE_SIZE;
    }

    public CountRngBlocs(String label, List<CountRngAbstractLine> listLine){
        this.label = label;
        this.listLine = listLine;
        this.height = GlobalValues.LINE_BASE_SIZE;
        for(CountRngAbstractLine line : listLine){
            this.height += line.getHeight();
        }
    }

    public String getLabel() {
        return this.label;
    }

    public List<CountRngAbstractLine> getListLine(){
        return this.listLine;
    }

    public void setLabel(String label){
        this.label = label;
    }

    public void setListLine(List<CountRngAbstractLine> listLine){
        this.height = GlobalValues.LINE_BASE_SIZE;
        this.listLine = listLine;
        for(CountRngAbstractLine line : listLine){
            this.height += line.getHeight();
        }
    }

    public int getHeight(){
        this.height = GlobalValues.LINE_BASE_SIZE;
        for(CountRngAbstractLine line : listLine){
            height += line.getHeight();
        }
        return this.height;
    }

    public void addListLine(CountRngAbstractLine line){
        this.listLine.add(line);
        this.height += line.getHeight();
    }

	@Override
	public String toString() {
		return "CountRngBlocs [label=" + label + ", listLine=" + listLine + ", height=" + height + "]";
	}
    


}
