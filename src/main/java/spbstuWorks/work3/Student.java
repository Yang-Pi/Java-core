package spbstuWorks.work3;

public class Student extends EduObject{
    public Student(){
        super();
    };

    public Student(String subject, Integer nTasks) {
        super(subject, nTasks);
    }

    @Override
    public void run() {
    }
}
