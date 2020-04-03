package com.labs.l3.examples.worker;

import java.util.LinkedList;
import java.util.Queue;

public class SmartWorkerTask {
    static final Runnable POISON_PILL = new Runnable() {
        @Override
        public void run() {
        }
    };
    final private BlockingQueue<Runnable> _tasks = new BlockingQueue<>();

    private class WorkerRun implements Runnable {
        @Override
        public void run() {
            Runnable task;
            while (true) {
                task = _tasks.poll();
                if (task == POISON_PILL) {
                    break;
                }
                try {
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public SmartWorkerTask() {
        new Thread(this.new WorkerRun()).start();
    }

    public void execute(Runnable task) {
        _tasks.offer(task);
    }

    public void shutDown() {
        this.execute(POISON_PILL);
    }
}
