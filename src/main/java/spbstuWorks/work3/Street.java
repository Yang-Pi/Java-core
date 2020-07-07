package spbstuWorks.work3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static spbstuWorks.work3.Main.*;

public class Street implements Runnable{
    static private BlockingQueue<Student> _smokingStudents = new LinkedBlockingQueue<Student>();

    @Override
    public void run() {
        while (true) {
            Student genStudent = new Student();
            genStudent.setSubject(SUBJECTS[Utils.generateInt(ARR_SIZE)]);
            genStudent.setTasksCount(N_TASKS[Utils.generateInt(ARR_SIZE)]);
            try {
                _smokingStudents.put(genStudent);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("New smoking student learn " + genStudent.getSubject()
                    + " and has " + genStudent.getTasksCount() + " tasks");
            Utils.pause(Utils.generateInt(1000));
        }
    }

    static public Student getStudent() {
        try {
            return _smokingStudents.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Student("No subject", 0);
    }
}
