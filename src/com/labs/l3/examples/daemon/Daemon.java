package com.labs.l3.examples.daemon;

import com.labs.l3.examples.Utils;
import java.util.Random;

public class Daemon {
    static int stock = 0;

    public static void main(String[] args) {
        System.out.println("Start");

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                while (true) {
                    stock = random.nextInt(1000);
                    Utils.pause(10000);
                }
            }
        });
        t.setDaemon(true); //t stops after stopping the main thread
        t.start(); //we should daemons use carefully

        for (int i = 0; i < 10; ++i) {
            Utils.pause(2000);
            System.out.println(" - Stock = " + stock);
        }

        System.out.print("Finish");
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(" and clear some resources of daemons");
            }
        }));
    }
}
