package com.speedrun.cardrng.front;

import com.speedrun.cardrng.engine.CountRngCount;
import com.speedrun.cardrng.object.CountRngBlocs;
import com.speedrun.cardrng.object.lines.CountRngAbstractLine;
import com.speedrun.cardrng.object.lines.CountRngButtonsLine;
import com.speedrun.cardrng.object.lines.CountRngScrollsLine;
import com.speedrun.option.object.Options;
import com.speedrun.utilities.GlobalValues;
import com.speedrun.utilities.graphics.ImagePanel;
import com.speedrun.utilities.graphics.ShadowLabel;
import com.speedrun.utilities.toolkit.UtilitiesToolkit;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FrontBlocEngine {
    private CountRngCount countRngCount;
    private CountRngBlocs bloc;
    private List<IFrontAbstractLine> linesList;
    private String parentLabel;
    private Options option;

    public FrontBlocEngine(CountRngBlocs bloc, CountRngCount countRngCount, String parentLabel, Options option){
        this.countRngCount = countRngCount;
        this.bloc = bloc;
        this.linesList = new ArrayList<IFrontAbstractLine>();
        this.parentLabel = parentLabel;
        this.option = option;
    }
 
    public JPanel getJPanel(){
		ImagePanel panel = new ImagePanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		panel.setBackground(new ImageIcon(this.getClass().getResource("/imageFenetre/bg.png")).getImage());
        panel.add(UtilitiesToolkit.getLabelBloc(bloc.getLabel(),GlobalValues.FONT_SIZE, GlobalValues.WIDTH_LINE_BLOCS,GlobalValues.WIDTH_LINE_BLOCS, -1));
        for(CountRngAbstractLine line : bloc.getListLine()) {
            IFrontAbstractLine convertedLine = generateLine(line);
            linesList.add(convertedLine);
            panel.add(convertedLine.getPanel());
        }
        UtilitiesToolkit.setSizeOfComponent(panel, new Dimension(GlobalValues.WIDTH_BLOCS, bloc.getHeight()+10));
        panel.setOpaque(false);
        return panel;
    }
 
    private IFrontAbstractLine generateLine(CountRngAbstractLine line){
        if(line.getClass() == CountRngButtonsLine.class){
        	switch(((CountRngButtonsLine)line).getTypeButton()) {
        		case CHECKBOX:
        			return new FrontBlocCheckBoxEngine((CountRngButtonsLine)line,countRngCount,parentLabel,option);
        		case RADIOBUTTON:
        			return new FrontBlocRadioButtonEngine((CountRngButtonsLine)line,countRngCount,parentLabel,option);
        		default:
        			return new FrontBlocButtonsEngine((CountRngButtonsLine)line,countRngCount,parentLabel,option);
        	}
        }else{
            return new FrontBlocScrollsEngine((CountRngScrollsLine)line,countRngCount,parentLabel,option);
        }
    }

    public void reset(){
        for(IFrontAbstractLine line : linesList){
            line.reset();
        }
    }


}
