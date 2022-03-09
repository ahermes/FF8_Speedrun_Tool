package com.speedrun.cardrng.engine;

import com.speedrun.cardrng.object.CountRngBlocs;
import com.speedrun.cardrng.object.json.CountRngJsonCategories;
import com.speedrun.cardrng.object.json.CountRngJsonSubCategories;
import com.speedrun.cardrng.object.lines.CountRngAbstractLine;
import com.speedrun.cardrng.object.lines.CountRngButtonsLine;
import com.speedrun.cardrng.object.lines.CountRngScrollsLine;
import com.speedrun.cardrng.object.lines.CountRngButtonsLine.ButtonTypeEnum;

import java.util.Arrays;

public class CountRngBlocCreator {


    public static CountRngBlocs createBlocs(CountRngJsonCategories categorie){
        CountRngBlocs res = new CountRngBlocs();
        res.setLabel(categorie.getLabel());
        for(CountRngJsonSubCategories subCategories : Arrays.asList(categorie.getSubCategories())){
        	switch(subCategories.getType().toLowerCase()) {
        	case "list":
        		res.addListLine(new CountRngScrollsLine(subCategories));
        		break;
        	case "checkbox":
        		res.addListLine(new CountRngButtonsLine(subCategories, ButtonTypeEnum.CHECKBOX));
        		break;
        	case "radiobutton":
        		res.addListLine(new CountRngButtonsLine(subCategories, ButtonTypeEnum.RADIOBUTTON));
        		break;
        	default:
        		res.addListLine(new CountRngButtonsLine(subCategories, ButtonTypeEnum.BUTTON));
        		break;
        	}
        }
        return res;
    }
 
}
