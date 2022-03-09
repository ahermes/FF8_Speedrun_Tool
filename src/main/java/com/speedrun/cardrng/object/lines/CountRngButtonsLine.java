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

public class CountRngButtonsLine implements CountRngAbstractLine{
	private String label;
	private int height = GlobalValues.LINE_BASE_SIZE;
	private ButtonTypeEnum buttonType;
	private Map<String, Integer> value;

	public CountRngButtonsLine(){
		this.height = GlobalValues.LINE_BASE_SIZE;
		this.buttonType = ButtonTypeEnum.BUTTON;
		this.value = new LinkedHashMap<String, Integer>();
	};
	
	public CountRngButtonsLine(CountRngJsonSubCategories value, ButtonTypeEnum buttonType){
		this.height = GlobalValues.LINE_BASE_SIZE;
		this.buttonType = buttonType;
		this.label = value.getLabel();
		this.value = new LinkedHashMap<String, Integer>();
		injectData(value);
	}

	public CountRngButtonsLine(String label, Map<String, Integer> value){
		this.height = GlobalValues.LINE_BASE_SIZE;
		this.buttonType = ButtonTypeEnum.BUTTON;
		this.value = new LinkedHashMap<String, Integer>();
		this.label = label;
		this.value = value;
		this.height = (GlobalValues.LINE_BASE_SIZE * ((this.value.size() / 4) + 1));
	}
	
	public CountRngButtonsLine(String label, Map<String, Integer> value, ButtonTypeEnum buttonType){
		this.height = GlobalValues.LINE_BASE_SIZE;
		this.buttonType = buttonType;
		this.value = new LinkedHashMap<String, Integer>();
		this.label = label;
		this.value = value;
		this.height = (GlobalValues.LINE_BASE_SIZE * ((this.value.size() / 4) + 1));
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

	public void setHeight(int height) {
		this.height = height;
	}


	public void setValue(Map<String, Integer> value){
		this.value = value;
		if(value == null || value.size() == 0){
			this.height = 20;
		}else {
			this.height = (GlobalValues.LINE_BASE_SIZE * ((this.value.size() / 4) + 1));
		}
	}

	public Map<String, Integer> getValue(){
		return this.value;
	}

	public void setTypeButton(ButtonTypeEnum buttonType) {
		this.buttonType = buttonType;
	}
	
	public ButtonTypeEnum getTypeButton() {
		return this.buttonType;
	}
	
	public void injectData(CountRngJsonSubCategories values) {
		if(values != null) {
			for(int i = 0; i < values.getValue().length ; i++){
				this.value.put(values.getValue()[i].getLabel(), values.getValue()[i].getValue());
			}
			if(value == null || value.size() == 0){
				this.height = GlobalValues.LINE_BASE_SIZE;
			}else {
				this.height = (GlobalValues.LINE_BASE_SIZE * ((this.value.size() / 4) + 1));
			}
		}
	}
	
	@Override
	public String toString() {
		return "CountRngButtonsLine [buttonType=" + this.buttonType + "label=" + label + ", height=" + height + ", value=" + value + "]";
	}
	
	public static enum ButtonTypeEnum {
		BUTTON,
		CHECKBOX,
		RADIOBUTTON

	}

}
