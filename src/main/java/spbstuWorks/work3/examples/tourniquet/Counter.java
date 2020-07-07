package spbstuWorks.work3.examples.tourniquet;

public class Counter {
    private int counter;
    static public int iExample;

    public void inc() {
        //critical section
        synchronized (this) { //mutex = mutual exclusion
            ++counter;
            SynchronizedTourniquet.printInfo();
        }
    }
    //==
    synchronized public void inc2() {
        synchronized (this) {
            counter *= 2;
            SynchronizedTourniquet.printInfo();
        }
    }

    public int get() {
        return counter;
    }

    //---Example of synchronized static
    synchronized static public void incExample() {
        ++iExample;
    }
    //==
    static public void incExample2() {
        synchronized (SynchronizedTourniquet.class) {
            ++iExample;
        }
    }
}
