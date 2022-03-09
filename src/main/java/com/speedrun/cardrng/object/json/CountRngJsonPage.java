package com.speedrun.cardrng.object.json;

import java.util.Arrays;
import java.util.List;

public class CountRngJsonPage {

    private String label;
    private int baseValue;
    private String baseCountPage;
    private boolean button;
    private CountRngJsonCategories[] categories;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(int baseValue) {
        this.baseValue = baseValue;
    }

    public String getBaseCountPage() {
        return baseCountPage;
    }

    public void setBaseCountPage(String baseCountPage) {
        this.baseCountPage = baseCountPage;
    }

    public CountRngJsonCategories[] getCategories() {
        return categories;
    }

    public void setCategories(CountRngJsonCategories[] categories) {
        this.categories = categories;
    }
    
    public boolean getButton() {
    	return this.button;
    }
    
    public void setButton(Boolean button) {
    	this.button = button;
    }

	@Override
	public String toString() {
		return "CountRngJsonPage [label=" + label + ", baseValue=" + baseValue + ", baseCountPage=" + baseCountPage
				+ ", button=" + button + ", categories=" + Arrays.toString(categories) + "]";
	}

}
