package com.speedrun.utilities;

public class Logger {

    static private boolean logSystm;
    static private boolean logTrace;
    static private boolean logWarng;
    static private boolean logError;
    static private java.lang.Class nameClass;

    public Logger (java.lang.Class nameClass, Boolean log){
        this.logSystm = log;
        this.logTrace = false;
        this.logWarng = log;
        this.logError = log;
        this.nameClass = nameClass;
    }

    static public void showLog(String string){
        if(logSystm){
            System.out.println(nameClass.getName().substring(nameClass.getName().lastIndexOf(".")+1)+ " - SYSTM - " + string);
        }
    }

    static public void showTrace(String string){
        if(logTrace){
            System.out.println(nameClass.getName().substring(nameClass.getName().lastIndexOf(".")+1)+ " - TRACE - " + string);
        }
    }

    static public void showError(String string){
        if(logError){
            System.out.println(nameClass.getName().substring(nameClass.getName().lastIndexOf(".")+1)+ " - ERROR - " + string);
        }
    }

    static public void showWarning(String string){
        if(logWarng){
            System.out.println(nameClass.getName().substring(nameClass.getName().lastIndexOf(".")+1)+ " - WARNG - " + string);
        }
    }

    public static void setLogSystm(boolean logSystm) {
        Logger.logSystm = logSystm;
    }

    public static void setLogTrace(boolean logTrace) {
        Logger.logTrace = logTrace;
    }

    public static void setLogWarng(boolean logWarng) {
        Logger.logWarng = logWarng;
    }

    public static void setLogError(boolean logError) {
        Logger.logError = logError;
    }
}
