package com.labs.l3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Robot extends EduObject {
    private Lock _lock = new ReentrantLock();
    private Integer _robotSpeed;
    private Integer _nRobotWorkTasks = 0;

    public Robot() {
        super();
    }

    public Robot(final String subject, final Integer nTasks) {
        super(subject, nTasks);
        _robotSpeed = super.getTasksCount();
    }

    @Override
    public void run() {
        synchronized (_nRobotWorkTasks) {
            while (_nRobotWorkTasks > 0) {
                _nRobotWorkTasks -= _robotSpeed;
                System.out.println(this.getSubject() + ": " +  _nRobotWorkTasks);
                Utils.pause(500);
            }
        }
        ExamRoom.dicStudentsInRoom();
    }

    synchronized public void setCountRobotWorkTasks(Integer nTasks) {
        if (_nRobotWorkTasks == 0) {
                _nRobotWorkTasks = nTasks;
        }
    }
}
