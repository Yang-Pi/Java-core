package com.labs.l3.examples.worker;

import com.labs.l3.examples.Utils;

public class Worker {
    public static void main(String[] args) {
        //PrimitiveWorkerTask worker = new PrimitiveWorkerTask();
        SmartWorkerTask worker = new SmartWorkerTask();

        worker.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from " + Thread.currentThread().getName());
            }
        });
        worker.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Another hello from " + Thread.currentThread().getName());
            }
        });

        worker.shutDown(); //poison pill
    }
}
