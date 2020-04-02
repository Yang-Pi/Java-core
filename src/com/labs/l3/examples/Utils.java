package com.labs.l3.examples.daemon;

public class Utils {
    public static void pause(final int timeMillis) {
        try {
            Thread.sleep(timeMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
