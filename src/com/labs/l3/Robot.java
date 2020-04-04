package com.labs.l3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Robot extends EduObject {
    private Lock _lock = new ReentrantLock();
    private Integer _robotSpeed;

    public Robot() {
        super();
    }

    public Robot(final String subject, final Integer nTasks) {
        super(subject, nTasks);
        _robotSpeed = super.getTasksCount();
    }

    @Override
    public void run() {
        lock.lock();
        try {
            Student student = ExamRoom.getStudent();
            System.out.println("Student passes " + student.getSubject()
                    + " with " + student.getTasksCount() + " tasks");
            if (!student.getSubject().equals("No subject")) {
                Integer nAllTasks = student.getTasksCount();
                while (nAllTasks > 0) {
                    nAllTasks -= _robotSpeed;
                    Utils.pause(500);
                }
            }
        } finally {
            lock.unlock();
        }
    }
}
