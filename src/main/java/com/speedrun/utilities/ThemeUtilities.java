package com.speedrun.utilities;

import java.awt.Color;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;

public class ThemeUtilities extends DefaultMetalTheme {

	@Override
	public ColorUIResource getMenuBackground() {
		return new ColorUIResource(Color.BLACK);
	}
	public ColorUIResource getWindowTitleBackground() {
		return new ColorUIResource(Color.BLACK);
	}
}

