package com.labs.l3;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.labs.l3.Main.*;

public class ExamRoom implements Runnable{
    static final int ROBOT_SPEED = 5;
    private List<Robot> _robots = new LinkedList<Robot>();
    private Student _student;
    private ExecutorService service = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    static private Integer _nStudentsInRoom = 0;

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
            while (!hasPlace()) {
                Utils.pause(500);
            }
            try {
                _student = ClassRoom.getStudent();
                for (Robot robot : _robots) {
                    if (_student.getSubject().equals(robot.getSubject())) {
                        if (!_student.getSubject().equals("No subject")) {
                            robot.setCountRobotWorkTasks(_student.getTasksCount());
                            service.execute(robot);
                            synchronized (_nStudentsInRoom) {
                                ++_nStudentsInRoom;
                            }
                        }
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }

    static public void dicStudentsInRoom() {
        synchronized (_nStudentsInRoom) {
            --_nStudentsInRoom;
        }
    }
    public boolean hasPlace() {
        synchronized (_nStudentsInRoom) {
            if (_nStudentsInRoom < ARR_SIZE) {
                return true;
            }
            else {
                return false;
            }
        }
    }
}
