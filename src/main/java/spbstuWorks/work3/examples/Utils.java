package spbstuWorks.work3.examples;

public class Utils {
    public static void pause(final int timeMillis) {
        try {
            Thread.sleep(timeMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
