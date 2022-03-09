package com.speedrun.cardrng.object.json;

import java.util.Arrays;
import java.util.List;

public class CountRngJsonCategories {
    private String label;
    private CountRngJsonSubCategories[] subCategories;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public CountRngJsonSubCategories[] getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(CountRngJsonSubCategories[] subCategories) {
        this.subCategories = subCategories;
    }

	@Override
	public String toString() {
		return "CountRngJsonCategories [label=" + label + ", subCategories=" + Arrays.toString(subCategories) + "]";
	}

}
