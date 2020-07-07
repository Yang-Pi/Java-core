package spbstuWorks.work3.examples.worker;

import java.util.LinkedList;
import java.util.Queue;

public class PrimitiveWorkerTask {
    static final Runnable POISON_PILL = new Runnable() {
        @Override
        public void run() {
        }
    };
    final private Queue<Runnable> _tasks = new LinkedList<>();

    private class WorkerRun implements Runnable {
        @Override
        public void run() {
            Runnable task;
            while (true) {
                synchronized (_tasks) {
                    while (!hasNewTask()) {
                        try {
                            _tasks.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    task = _tasks.poll();
                }
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

    public PrimitiveWorkerTask() {
        new Thread(this.new WorkerRun()).start();
    }

    private boolean hasNewTask() {
        return !_tasks.isEmpty();
    }

    public void execute(Runnable task) {
        synchronized (_tasks) {
            _tasks.offer(task);
            _tasks.notify();
        }
    }

    public void shutDown() {
        this.execute(POISON_PILL);
    }
}
