package com.speedrun.utilities;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

public class ScrollPaneAdder {

	public static JScrollPane addJScrollPane(Component panel) {
		JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setViewportView(panel);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(50);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        return scrollPane;
	}
}
