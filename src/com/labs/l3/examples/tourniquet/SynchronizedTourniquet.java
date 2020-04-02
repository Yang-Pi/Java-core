package com.labs.l3.examples.tourniquet;

import com.labs.l3.examples.Utils;

import java.util.Random;

public class SynchronizedTourniquet {
    static Counter counter = new Counter();

    static class TourniquetRun implements Runnable {
        @Override
        public void run() {
            Random random = new Random();
            while (true) {
                Utils.pause(2000 /*+ random.nextInt(3000)*/);
                counter.inc();
            }
        }
    }

    public static void main(String[] args) {
        final int nTourniquets = 8;
        for (int i = 0; i < nTourniquets; ++i) {
            new Thread(new TourniquetRun()).start();
        }
    }

    public static void printInfo() {
        System.out.println(Thread.currentThread().getName() + ": " + counter.get());

    }
}
