package spbstuWorks.work3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ClassRoom implements Runnable {
    static final int CLASSROOM_SIZE = 10;
    static private BlockingQueue<Student> _students = new ArrayBlockingQueue<Student>(CLASSROOM_SIZE);

    private Lock _lockFirstReady = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            try {
                Student student = Street.getStudent();
                if (!student.getSubject().equals("No subject")) {
                    _students.put(student);
                }
                System.out.println("New student in classroom learn " + student.getSubject()
                            + " and has " + student.getTasksCount() + " tasks");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static public Student getStudent() {
        try {
            return _students.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Student("No subject", 0);
    }
}
