package com.labs.l3.examples.cafe;

import com.labs.l3.examples.Utils;

import java.util.Random;

public class Cafe {
    static int nMeals = 5;
    static String[] meals = {"Chicken", "IceCream", "Pasta", "Soup", "Steak"};

    static String meal;
    static Object bell = new Object(); //monitor

    static class Cooker implements Runnable{
        @Override
        public void run() {
            Random random = new Random();
            while (true) {
                String cookingMeal = meals[random.nextInt(nMeals)];
                int cookingTime = 1000 + random.nextInt(2000);
                Utils.pause(cookingTime);
                synchronized (bell) {
                    meal = cookingMeal;
                    System.out.println("Cooker made: " + meal);
                    bell.notify();
                }
            }
        }
    }

    static class Waiter implements Runnable{
        @Override
        public void run() {
            while (true) {
                synchronized (bell) {
                    while (meal == null) {
                        try {
                            bell.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    String readyMeal = meal;
                    System.out.println("Waiter has caught: " + readyMeal);
                    meal = null;
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread cookerTread = new Thread(new Cooker());
        cookerTread.start();
        Thread waiterThread = new Thread(new Waiter());
        waiterThread.start();
    }
}
