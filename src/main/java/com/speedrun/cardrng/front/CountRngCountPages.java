package com.speedrun.cardrng.front;


import com.speedrun.cardrng.engine.CountRngBlocCreator;
import com.speedrun.cardrng.engine.CountRngCount;
import com.speedrun.cardrng.object.CountRngBlocs;
import com.speedrun.cardrng.object.json.CountRngJsonPage;
import com.speedrun.option.object.Options;
import com.speedrun.utilities.GlobalValues;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CountRngCountPages {
    String label;
    String baseCountPageLabel;
    JPanel jPanel;
    int height = GlobalValues.LINE_BASE_SIZE;
    List<FrontBlocEngine> frontBlocList;


    public CountRngCountPages(CountRngJsonPage jsonPage, CountRngCount countRngCount, Options option){
        this.frontBlocList = new ArrayList<FrontBlocEngine>();
        this.jPanel = new JPanel();
        this.jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        this.label = jsonPage.getLabel();
        String nameToUse = this.label;
        if (jsonPage.getBaseValue() != -1) {
            countRngCount.addValue(this.label , jsonPage.getBaseValue());
            this.baseCountPageLabel = this.label; 
        }else{
        	
            this.baseCountPageLabel = jsonPage.getBaseCountPage();
            nameToUse= jsonPage.getBaseCountPage();
        }

        for (int a = 0; a < jsonPage.getCategories().length; a++) {
        	CountRngBlocs bloc = CountRngBlocCreator.createBlocs(jsonPage.getCategories()[a]);
        	FrontBlocEngine blocs = new FrontBlocEngine(bloc,countRngCount,nameToUse, option);
        	frontBlocList.add(blocs);
            this.jPanel.add(blocs.getJPanel());
            height+= bloc.getHeight();
        }
        this.jPanel.setOpaque(false);
    }

    public int getHeight(){
        return this.height;
    }

    public String getLabel(){
        return this.label;
    }

    public String getBaseCountPageLabel(){
        return this.baseCountPageLabel;
    }

    public JPanel getJPanel(){
        return this.jPanel;
    }

    public void reset(){
        for(FrontBlocEngine bloc : frontBlocList){
            bloc.reset();
        }
    }


}
