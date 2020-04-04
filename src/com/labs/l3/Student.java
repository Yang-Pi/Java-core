package com.labs.l3;

public class Student extends EduObject{
    public Student(){
        super();
    };

    public Student(String subject, Integer nTasks) {
        super(subject, nTasks);
    }

    @Override
    public void run() {
        for (Robot robot : ExamRoom.getRobotsList()) {
            if (getSubject().equals(robot.getSubject())) {
                robot.run();
                break;
            }
        }
    }
}
