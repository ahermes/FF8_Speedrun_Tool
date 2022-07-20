package com.speedrun.option.object;

import com.speedrun.utilities.GlobalValues;

public class Options {
	private Boolean rngPageFormat = true;
	private Boolean radioButton = true;
	private Boolean rngRubyExeChoice = true;
	private Boolean patternOrderByFrame = false;
	private String jsonPath = "";
	private String pathScriptExe = "";
	private String pathScriptTheEndExe = "";
	private String rubyZellPath = "";
	private String rubyQuistisPath = "";
	private String rngQuistis = "0" ;
	private double delayFrame = 0;
	private double acceptDelayFrame = 1;
	private double gameFps = 0;
	private boolean showPopUp = false;
	private boolean backgroundColorRngCount = false;

	private boolean isFromMenu = false;

	public Options() {
	}
	
	public Options(String pathScript, String jsonPath,String rubyZellPath, String rubyQuistisPath, String delayFrame) {
		this.pathScriptExe = pathScript;
		this.jsonPath = jsonPath;
		this.delayFrame = GlobalValues.DELAY_FRAME_PLATEFORM.get(delayFrame);
		this.rubyZellPath = rubyZellPath;
		this.rubyQuistisPath = rubyQuistisPath;
	}

	public Boolean getRngPageFormat() {
		return rngPageFormat;
	}

	public void setRngPageFormat(Boolean rngPageFormat) {
		this.rngPageFormat = rngPageFormat;
	}

	public String getPathScriptExe() {
		return pathScriptExe;
	}

	public void setPathScriptExe(String pathScript) {
		this.pathScriptExe = pathScript;
	}

	public String getPathScriptTheEndExe() {
		return pathScriptTheEndExe;
	}

	public void setPathScriptTheEndExe(String pathScript) {
		this.pathScriptTheEndExe = pathScript;
	}


	public String getJsonPath() {
		return jsonPath;
	}

	public void setJsonPath(String jsonPath) {
		this.jsonPath = jsonPath;
	}

	public String getRngQuistis() {
		return rngQuistis;
	}

	public void setRngQuistis(String rngQuistis) {
		this.rngQuistis = rngQuistis;
	}

	public double getDelayFrame() {
		return delayFrame;
	}

	public void setDelayFrame(double delayFrame) {
		this.delayFrame = delayFrame;
	}

	public String getRubyZellPath() {
		return rubyZellPath;
	}

	public void setRubyZellPath(String rubyZellPath) {
		this.rubyZellPath = rubyZellPath;
	}

	public String getRubyQuistisPath() {
		return rubyQuistisPath;
	}

	public void setRubyQuistisPath(String rubyQuistisPath) {
		this.rubyQuistisPath = rubyQuistisPath;
	}

	public Boolean getRngRubyExeChoice() {
		return rngRubyExeChoice;
	}

	public void setRngRubyExeChoice(Boolean rngRubyExeChoice) {
		this.rngRubyExeChoice = rngRubyExeChoice;
	}
	
	public Boolean getRadioButton() {
		return radioButton;
	}

	public void setRadioButton(Boolean radioButton) {
		this.radioButton = radioButton;
	}
	
	public Boolean getPatternOrderByFrame() {
		return this.patternOrderByFrame;
	}

	public void setPatternOrderByFrame(Boolean patternOrderByFrame) {
		this.patternOrderByFrame = patternOrderByFrame;
	}
	
	public double getAcceptDelayFrame() {
		return this.acceptDelayFrame;
	}
	
	public void setAcceptDelayFrame(double acceptDelayFrame) {
		this.acceptDelayFrame = acceptDelayFrame;
	}

	public boolean isFromMenu() {
		return isFromMenu;
	}

	public void setFromMenu(boolean isFromMenu) {
		this.isFromMenu = isFromMenu;
	}
	
	public void setShowPopUp(boolean showPopUp) {
		this.showPopUp = showPopUp;
	}

	public boolean getShowPopUp() {
		return this.showPopUp;
	}

	public void setGameFps(double gameFps){
		this.gameFps = gameFps;
	}

	public double getGameFps(){
		return this.gameFps;
	}

	public void setBackgroundColorRngCount(boolean backgroundColorRngCount){
		this.backgroundColorRngCount = backgroundColorRngCount;
	}

	public boolean getBackgroundColorRngCount(){
		return this.backgroundColorRngCount;
	}

	public void setAll(Options option){
		this.rngRubyExeChoice = option.getRngRubyExeChoice();
		this.jsonPath = option.getJsonPath();
		this.pathScriptExe = option.getPathScriptExe();
		this.pathScriptTheEndExe = option.getPathScriptTheEndExe();
		this.rngPageFormat = option.getRngPageFormat();
		this.rngQuistis = option.getRngQuistis();
		this.rubyQuistisPath = option.getRubyQuistisPath();
		this.rubyZellPath = option.getRubyZellPath();
		this.radioButton = option.getRadioButton();
		this.patternOrderByFrame = option.getPatternOrderByFrame();
		this.acceptDelayFrame = option.getAcceptDelayFrame();
		this.delayFrame = option.getDelayFrame();
		this.showPopUp = option.getShowPopUp();
		this.gameFps = option.getGameFps();
		this.backgroundColorRngCount = option.getBackgroundColorRngCount();
	}

	@Override
	public String toString() {
		return "Options{" +
				"rngPageFormat=" + rngPageFormat +
				", rngRubyExeChoice=" + rngRubyExeChoice +
				", radioButton=" + radioButton +
				", patternOrderByFrame=" + patternOrderByFrame + 
				", jsonPath='" + jsonPath + '\'' +
				", pathScriptExe='" + pathScriptExe + '\'' +
				", pathScriptTheEndExe='" + pathScriptTheEndExe + '\'' +
				", rubyZellPath='" + rubyZellPath + '\'' +
				", rubyQuistisPath='" + rubyQuistisPath + '\'' +
				", rngQuistis='" + rngQuistis + '\'' +
				", delayFrame=" + delayFrame +
				", acceptDelayFrame=" + acceptDelayFrame +
				", backgroundColorRngCount=" + backgroundColorRngCount +
				'}';
	}
}
