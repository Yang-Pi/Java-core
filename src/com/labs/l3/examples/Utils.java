package com.labs.l3.examples;

public class Utils {
    public static void pause(int time) {
        try {
            Thread.sleep(time);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
