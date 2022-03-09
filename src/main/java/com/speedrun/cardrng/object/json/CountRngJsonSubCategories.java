package com.speedrun.cardrng.object.json;

import java.util.Arrays;
import java.util.List;

public class CountRngJsonSubCategories {
    private String label;
    private int times;
    private String type;
    private CountRngJsonValues[] value;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CountRngJsonValues[] getValue() {
        return value;
    }

    public void setValue(CountRngJsonValues[] value) {
        this.value = value;
    }

	@Override
	public String toString() {
		return "CountRngJsonSubCategories [label=" + label + ", times=" + times + ", type=" + type + ", value="
				+ Arrays.toString(value) + "]";
	}


}
