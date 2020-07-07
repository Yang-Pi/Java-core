package spbstuWorks.work3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class EduObject implements Runnable{
    private String _subject;
    private Integer _nTasks;
    Lock lock = new ReentrantLock();

    public EduObject() {
    }

    public EduObject(final String subject, final Integer nTasks) {
        _subject = subject;
        _nTasks = nTasks;
    }

    public void setSubject(final String subject) {
        lock.lock();
        try {
            _subject = subject;
        } finally {
            lock.unlock();
        }
    }
    public String getSubject() {
        lock.lock();
        try {
            return _subject;
        } finally {
            lock.unlock();
        }
    }

    public void setTasksCount(final Integer nTasks) {
        lock.lock();
        try {
            _nTasks = nTasks;
        } finally {
            lock.unlock();
        }
    }
    public Integer getTasksCount() {
        lock.lock();
        try {
            return _nTasks;
        } finally {
            lock.unlock();
        }
    }
}
