package com.speedrun.cardPattern.front;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.speedrun.cardPattern.object.CardPatternCardObject;
import com.speedrun.utilities.GlobalValues;

public class FrontCardObject {
	
	public static JPanel getPanel(CardPatternCardObject object) throws IOException {
		 JPanel panel  = new JPanel();
		BufferedImage card = ImageIO.read(FrontCardObject.class.getResource( "/images/cards/" + object.getLabel().toLowerCase() + ".png"));
		if(object.getOrder() != 0 && !(object.getLabel().equals("A") || object.getLabel().equals("a"))) {
			
			String frameName = "Cadre_" + object.getOrder() + "_P.png";
			if(object.isOpponent()) {
				frameName = "Cadre_" + object.getOrder() + "_O.png";
			}
			
			BufferedImage imgFG = ImageIO.read(FrontCardObject.class.getResource( "/images/cards/" +  frameName));
	        final BufferedImage combinedImage = new BufferedImage(
	                card.getWidth(),
	                card.getHeight(),
	                BufferedImage.TYPE_INT_ARGB );
	        Graphics2D g = combinedImage.createGraphics();
	        g.drawImage(card,0,0,null);
	        g.drawImage(imgFG,0,0,null);
	        g.dispose();

	        panel.add(new JLabel(new ImageIcon(combinedImage)));
		}else {
			panel.add(new JLabel(new ImageIcon(card)));
		}
		panel.setOpaque(false);
        return panel;
	}

}
