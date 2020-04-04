package com.labs.l3;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.labs.l3.Main.*;

public class ExamRoom implements Runnable{
    static final int ROBOT_SPEED = 5;
    static private List<Robot> _robots = new LinkedList<Robot>();
    static private Student _student;

    static private Lock lock = new ReentrantLock();

    public ExamRoom() {
        for (int i = 0; i < ARR_SIZE; ++i) {
            _robots.add(new Robot(SUBJECTS[i], ROBOT_SPEED));
        }
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                _student = ClassRoom.getStudent();
                if (!_student.getSubject().equals("No subject")) {
                    _student.run();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public static List<Robot> getRobotsList() {
        return _robots;
    }

    public static Student getStudent() {
        lock.lock();
        if (_student != null) {
            Student tmpStudent = _student;
            lock.unlock();
            return tmpStudent;
        }
        return new Student("No subject", 0);
    }
}
