package com.speedrun.test;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

public class Test_Robot {
    public Test_Robot() {
    }

    public static void main(String[] args) throws Exception {
        Thread.sleep(5000L);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        System.out.print("Where this come from?");
        Robot robot = new Robot();
        robot.keyPress(17);
        robot.keyPress(86);
        robot.keyRelease(17);
        robot.keyRelease(86);
        robot.keyPress(71);
        robot.keyRelease(71);
        robot.mouseMove(0, 0);
    }
}
