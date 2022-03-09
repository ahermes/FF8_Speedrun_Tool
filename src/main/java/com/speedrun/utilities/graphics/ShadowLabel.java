package com.speedrun.utilities.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

public class ShadowLabel extends JLabel {

	private String text;

	private Font f;

	private int fontSize;

	private int textWidth;
	
	boolean forced;

	Color color;
	
	private boolean invertColors = false;

	public ShadowLabel() {
		super();
	}

	public ShadowLabel(int fontSize) {
		super();
		this.fontSize = fontSize;
	}

	public ShadowLabel(String text, int fontSize,int width) {
		super();
		this.text = text;
		this.fontSize = fontSize;
		this.color = new Color(255, 255, 255);
		
		if(width < 0) {
			setSize();
		}else {
			forced = true;
			this.setPreferredSize(new Dimension(width,10 + fontSize));
		}
	}

	private void setSize() {
		f = new Font("Dialog", 1, fontSize);
		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		FontMetrics fm = img.getGraphics().getFontMetrics(f);
		textWidth = fm.stringWidth(text);
		this.setFont(f);
		forced = false;
		this.setPreferredSize(new Dimension(textWidth,10 + fontSize));
	}
	
	public int getTextWidth() {
		return this.textWidth;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		// ////////////////////////////////////////////////////////////////
		// antialiasing
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		// ////////////////////////////////////////////////////////////////

		/**
		 * draw text
		 */
		int shadowing = fontSize/10;
		int xToDraw = (this.getParent().getHeight()/2) - fontSize/10;
		if (!invertColors) {
			g2D.setFont(f);
			g2D.setColor(new Color(0, 0, 0));
			g2D.drawString(this.text, 1, xToDraw+shadowing);
			g2D.setColor(color);
			g2D.drawString(this.text, 0, xToDraw);
		} else {
			g2D.setFont(f);
			g2D.setColor(color);
			g2D.drawString(this.text, 1, xToDraw+shadowing);
			g2D.setColor(new Color(0, 0, 0));
			g2D.drawString(this.text, 0, xToDraw);
		}
		g2D.dispose();

	}

	public void setInvertColors(boolean invertColors) {
		this.invertColors = invertColors;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

	public void setShadowText(String text) {
		this.text = text;
		if(!forced) {
			setSize();
		}
		repaint();
	}

	/**
	 * Default UID
	 */
	private static final long serialVersionUID = 1L;

}