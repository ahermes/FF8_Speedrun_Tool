package com.speedrun.cardrng.object.json;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class OptionExeJson {
    @SerializedName("Base")
    int base;
    @SerializedName("Width")
    int width;
    @SerializedName("RecoveryWidth")
    int recoveryWidth;
    @SerializedName("CountingWidth")
    int countingWidth;
    @SerializedName("CountingFrameWidth")
    int countingFrameWidth;
    @SerializedName("EarlyQuistis")
    String earlyQuistis;
    @SerializedName("AutofireSpeed")
    int autofireSpeed;
    @SerializedName("DelayFrame")
    double delayFrame;
    @SerializedName("RanksOrder")
    String ranksOrder;
    @SerializedName("StrongHighlightCards")
    int[] strongHighlightCards;
    @SerializedName("HighlightCards")
    int[] highlightCards;
    @SerializedName("Order")
    String order;
    @SerializedName("ConsoleFps")
    int consoleFps;
    @SerializedName("Player")
    String player;
    @SerializedName("Fuzzy")
    String[] fuzzy;
    @SerializedName("ForcedIncr")
    int forcedIncr;
    @SerializedName("AcceptDelayFrame")
    double acceptDelayFrame;
    @SerializedName("Prompt")
    String prompt;
    @SerializedName("Interactive")
    boolean interfact;
    @SerializedName("GameFps")
    double gameFps;

    public OptionExeJson(){
    }

    public OptionExeJson(int base, int width, int recoveryWidth, int countingWidth, int countingFrameWidth, String earlyQuistis, int autofireSpeed,
                         int delayFrame, String ranksOrder, int[] strongHighlightCards, int[] highlightCards, String order, int consoleFps, String player,
                         String[] fuzzy, int forcedIncr, int acceptDelayFrame, String prompt, boolean interfact, double gameFps) {
        this.base = base;
        this.width = width;
        this.recoveryWidth = recoveryWidth;
        this.countingWidth = countingWidth;
        this.countingFrameWidth = countingFrameWidth;
        this.earlyQuistis = earlyQuistis;
        this.autofireSpeed = autofireSpeed;
        this.delayFrame = delayFrame;
        this.ranksOrder = ranksOrder;
        this.strongHighlightCards = strongHighlightCards;
        this.highlightCards = highlightCards;
        this.order = order;
        this.consoleFps = consoleFps;
        this.player = player;
        this.fuzzy = fuzzy;
        this.forcedIncr = forcedIncr;
        this.acceptDelayFrame = acceptDelayFrame;
        this.prompt = prompt;
        this.interfact = interfact;
        this.consoleFps = consoleFps;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getRecoveryWidth() {
        return recoveryWidth;
    }

    public void setRecoveryWidth(int recoveryWidth) {
        this.recoveryWidth = recoveryWidth;
    }

    public int getCountingWidth() {
        return countingWidth;
    }

    public void setCountingWidth(int countingWidth) {
        this.countingWidth = countingWidth;
    }

    public int getCountingFrameWidth() {
        return countingFrameWidth;
    }

    public void setCountingFrameWidth(int countingFrameWidth) {
        this.countingFrameWidth = countingFrameWidth;
    }

    public String getEarlyQuistis() {
        return earlyQuistis;
    }

    public void setEarlyQuistis(String earlyQuistis) {
        this.earlyQuistis = earlyQuistis;
    }

    public int getAutofireSpeed() {
        return autofireSpeed;
    }

    public void setAutofireSpeed(int autofireSpeed) {
        this.autofireSpeed = autofireSpeed;
    }

    public double getDelayFrame() {
        return delayFrame;
    }

    public void setDelayFrame(double delayFrame) {
        this.delayFrame = delayFrame;
    }

    public String getRanksOrder() {
        return ranksOrder;
    }

    public void setRanksOrder(String ranksOrder) {
        this.ranksOrder = ranksOrder;
    }

    public int[] getStrongHighlightCards() {
        return strongHighlightCards;
    }

    public void setStrongHighlightCards(int[] strongHighlightCards) {
        this.strongHighlightCards = strongHighlightCards;
    }

    public int[] getHighlightCards() {
        return highlightCards;
    }

    public void setHighlightCards(int[] highlightCards) {
        this.highlightCards = highlightCards;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getConsoleFps() {
        return consoleFps;
    }

    public void setConsoleFps(int consoleFps) {
        this.consoleFps = consoleFps;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String[] getFuzzy() {
        return fuzzy;
    }

    public void setFuzzy(String[] fuzzy) {
        this.fuzzy = fuzzy;
    }

    public int getForcedIncr() {
        return forcedIncr;
    }

    public void setForcedIncr(int forcedIncr) {
        this.forcedIncr = forcedIncr;
    }

    public double getAcceptDelayFrame() {
        return acceptDelayFrame;
    }

    public void setAcceptDelayFrame(double acceptDelayFrame) {
        this.acceptDelayFrame = acceptDelayFrame;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public boolean isInterfact() {
        return interfact;
    }

    public void setInterfact(boolean interfact) {
        this.interfact = interfact;
    }

    public double getGameFps(){
        return gameFps;
    }

    public void setGameFps(double gameFps){
        this.gameFps = gameFps;
    }

    @Override
    public String toString() {
        return "OptionExeJson{" +
                "base=" + base +
                ", width=" + width +
                ", recoveryWidth=" + recoveryWidth +
                ", countingWidth=" + countingWidth +
                ", countingFrameWidth=" + countingFrameWidth +
                ", earlyQuistis='" + earlyQuistis + '\'' +
                ", autofireSpeed=" + autofireSpeed +
                ", delayFrame=" + delayFrame +
                ", ranksOrder='" + ranksOrder + '\'' +
                ", strongHighlightCards=" + Arrays.toString(strongHighlightCards) +
                ", highlightCards=" + Arrays.toString(highlightCards) +
                ", order='" + order + '\'' +
                ", consoleFps=" + consoleFps +
                ", player='" + player + '\'' +
                ", fuzzy=" + Arrays.toString(fuzzy) +
                ", forcedIncr=" + forcedIncr +
                ", acceptDelayFrame=" + acceptDelayFrame +
                ", prompt='" + prompt + '\'' +
                ", interfact=" + interfact +
                ", gameFps=" + gameFps +
                '}';
    }
}
