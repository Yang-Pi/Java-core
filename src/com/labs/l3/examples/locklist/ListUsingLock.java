package com.labs.l3.examples.locklist;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ListUsingLock {
    static class Node {
        Object item;
        Node next;
        Lock lock = new ReentrantLock();

        public Node(Object item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    Node _head;

    synchronized public Node getHead() {
        return _head;
    }

    public synchronized void add(Object item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        _head = new Node(item, _head);
    }

    public boolean search(Object x) {
        if (x != null) {
            Node p = getHead();
            p.lock.lock();
            while (p != null) {
                Node nextp = null;
                boolean found = false;

                try {
                    found = x.equals(p.item);
                    if (!found) {
                        nextp = p.next;
                        if (nextp != null) {
                            nextp.lock.lock();
                        }
                    }
                } finally {
                    p.lock.unlock();
                }
                if (found) {
                    return true;
                }
                p.next = nextp;
            }
        }
        return false;
    }
}
