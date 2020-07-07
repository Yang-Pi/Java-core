package spbstuWorks.work3.examples.blockingqueue;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    final private Queue<T> _items = new LinkedList<>();

    public void offer(T item) {
        synchronized (_items) {
            _items.offer(item);
            _items.notify();
        }
    }

    public T poll() {
        synchronized (_items) {
            while (_items.isEmpty()) {
                try {
                    _items.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return _items.poll();
        }
    }
}
