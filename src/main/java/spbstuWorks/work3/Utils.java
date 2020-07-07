package spbstuWorks.work3;

import java.security.SecureRandom;

public class Utils {
    public static void pause(final int timeMillis) {
        try {
            Thread.sleep(timeMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int generateInt(final int maxBorder) {
        SecureRandom random = new SecureRandom();
        return random.nextInt(maxBorder);
    }
}
