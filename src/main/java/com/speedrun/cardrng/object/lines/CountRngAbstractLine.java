package com.speedrun.cardrng.object.lines;

import com.speedrun.cardrng.engine.CountRngCount;
import com.speedrun.cardrng.object.json.CountRngJsonSubCategories;
import com.speedrun.cardrng.object.json.CountRngJsonValues;

import javax.swing.*;

public interface CountRngAbstractLine {

    public int getHeight();
    public String getLabel();

    public void injectData(CountRngJsonSubCategories values);

}
