package com.speedrun.utilities;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.HashMap;
import java.util.Map;

import static javax.swing.UIManager.put;

public class GlobalValues {
	
	public final static String ACCESSIBLE_RESSOURCES = System.getProperty("user.dir") + "\\resources\\";
	
	public final static String ACCESSIBLE_RESSOURCES_FILES = System.getProperty("user.dir") + "\\resources\\files\\";

	public final static String ACCESSIBLE_RESSOURCES_SCRIPTS = System.getProperty("user.dir") + "\\resources\\scripts\\";
	
	public final static String INTERNAL_RESSOURCES = System.getProperty("user.dir") + "\\src\\main\\resources\\";
	
	public final static String INTERNAL_RESSOURCES_IMAGES = System.getProperty("user.dir") + "\\src\\main\\resources\\images\\";
	
	public final static String INTERNAL_RESSOURCES_CARDS = System.getProperty("user.dir") + "\\src\\main\\resources\\images\\cards\\";
	
	public final static String FULL_GAME_PATTERNS_FILE = "Q card Late - Full Game Scenario.csv";

	public final static String RNG_RESULT_GAME_FILE = "Q card Late - RNG result.csv";

	public final static String OPPONENT_DECK_GAME_FILE = "Q card Late - Opponent Deck.csv";

	public final static String RNG_ENTRIES_FILE = "RNGMap.json";

	public final static String LIMITS_TABLES_FILE = "table.json";
	
	public final static int LINE_BASE_SIZE = 25;
	
	public final static int FONT_SIZE_BIG = 18;
	
	public final static int FONT_SIZE = 12;
	
	public final static int WIDTH_FRAME = 500;
	
	public final static int WIDTH_BLOCS = 475;
	
	public final static int WIDTH_LINE_BLOCS = 455;
	
	public final static int WIDTH_BLOCS_SMALL = 450; 
	
	public final static int PATTERN_HEIGHT = 475;
	
	public final static int POLE_BLOC_WIDTH = 380;

	public final static String QUISTIS_OPTION_EXE = "fc01";

	public final static String ZELL_OPTION_EXE = "zellmama";

	public final static BiMap<String,Double> DELAY_FRAME_PLATEFORM = HashBiMap.create(new HashMap<String,Double>(){{
		put("PS2", 285.0);
		put("PC", 75.0);
		put("Bettle PSX", 215.0);
	}});


}
