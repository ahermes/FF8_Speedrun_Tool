package com.speedrun.cardrng.object.lines;

import com.speedrun.cardrng.engine.CountRngCount;
import com.speedrun.cardrng.object.json.CountRngJsonSubCategories;
import com.speedrun.utilities.GlobalValues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CountRngScrollsLine implements CountRngAbstractLine{
	private String label;
	private int height;
	private Map<String, Integer> value;
	private int times = 1;

	public CountRngScrollsLine(){
		this.value = new LinkedHashMap<String, Integer>();
	};

	public CountRngScrollsLine(int times){
		this.times = times;
		this.value = new LinkedHashMap<String, Integer>();
	}

	public CountRngScrollsLine(CountRngJsonSubCategories value){
		this.value = new LinkedHashMap<String, Integer>();
		this.label = value.getLabel();
		this.times = value.getTimes();
		injectData(value);
	} 


	public CountRngScrollsLine(String label, Map<String, Integer> value, int times){
		this.label = label;
		this.value = value;
		this.times = times;
		this.height = (GlobalValues.LINE_BASE_SIZE * (((times-1)/3) + 1));
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getHeight() {
		return height;
	}

	public int getArragedHeight() {
		this.height = (GlobalValues.LINE_BASE_SIZE * ((times-1)/2 + 1));
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getTimes(){
		return this.times;
	}

	public void setTimes(int times){
		this.times = times;
	}

	public void setValue(Map<String, Integer> value){
		this.value = value;
		this.height = (GlobalValues.LINE_BASE_SIZE * ((times-1)/3 + 1));
	}

	public Map<String,Integer> getValue(){
		return this.value;
	}

	public void injectData(CountRngJsonSubCategories values) {
		if(values != null) {
			for(int i = 0; i < values.getValue().length ; i++){
				this.value.put(values.getValue()[i].getLabel(), values.getValue()[i].getValue());
			}
			this.height = (GlobalValues.LINE_BASE_SIZE * ((times-1)/3 + 1));
		}
	}

	@Override
	public String toString() {
		return "CountRngScrollsLine [label=" + label + ", height=" + height + ", value=" + value + ", times=" + times
				+ "]";
	}
	


}
