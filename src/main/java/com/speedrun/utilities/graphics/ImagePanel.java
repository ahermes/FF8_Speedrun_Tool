package com.speedrun.utilities.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {

    Image image;
    Color color;
    boolean toErease;
    int minWidth;
    int minHeight;

    public ImagePanel() {
    	super();
    }

    public ImagePanel(LayoutManager2 layout) {
    	super(layout);
    }

    public void setBackground(Image image) {
        this.image = image;
    }

    public void setColorBackground(Color color){
        this.color = color;
    }

    public void setMinWidth(int minWidth) {
    	this.minWidth = minWidth;
    }

    public void setMinHeight(int minHeight) {
    	this.minHeight = minHeight;
    }

    public void setMinSize(int minWidth, int minHeight) {
    	this.minWidth = minWidth;
    	this.minHeight = minHeight;
    }

    public void paintComponent(Graphics g) {
    	if(getWidth() < minWidth && getHeight() < minHeight) {
            if (this.color != null) {
                g.setColor(this.color);
                g.fillRect(0, 0, minWidth, minHeight);
            }else{
    	        g.drawImage(image, 0, 0, minWidth, minHeight, this);
    	    }
    	}
    	else if(getWidth() < minWidth) {
            if (this.color != null) {
                g.setColor(this.color);
                g.fillRect(0, 0,minWidth, getHeight());
            }else {
                g.drawImage(image, 0, 0, minWidth, getHeight(), this);
            }
    	}
    	else if(getHeight() < minHeight) {
            if (this.color != null) {
                g.setColor(this.color);
                g.fillRect(0, 0,  getWidth(), minHeight);
            }else {
    	        g.drawImage(image, 0, 0, getWidth(), minHeight, this);
    	    }
    	}
    	else {
    	    if (this.color != null) {
                g.setColor(this.color);
                g.fillRect(0, 0, getWidth(), getHeight());
    	    }else {
    	        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
    	}
        toErease = false;
    }

}