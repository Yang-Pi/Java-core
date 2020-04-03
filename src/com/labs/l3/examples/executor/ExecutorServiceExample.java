package com.labs.l3.examples.executor;

import com.labs.l3.examples.Utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExample {
    private static final class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello from " + Thread.currentThread().getName());
            Utils.pause(3000);
            System.out.println("Bye from " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();

        service.execute(new Task());
        service.execute(new Task());

        service.shutdown();
    }
}
