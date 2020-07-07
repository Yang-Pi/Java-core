package spbstuWorks.work3.examples.matrix;

import java.security.SecureRandom;

public class Utils {
    //Fill matrix with random counts
    public static double[][] generateMatrix(final int rows, final int cols) {
        double[][] matrix = new double[rows][cols];
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                matrix[i][j] = random.nextDouble();
            }
        }
        return matrix;
    }

    //"big" operation
    public static double calculate(double dMatrixEl) {
        //use some heavy operations for double
        return Math.pow(Math.sin(Math.pow(dMatrixEl, Math.E)) / Math.cos(Math.pow(dMatrixEl, Math.E)), Math.PI);
    }

    //evaluate and print time of working
    public static void evaluate(Runnable task) {
        long start = System.currentTimeMillis();
        task.run();
        long stop = System.currentTimeMillis();

        long elapsed = stop - start;
        System.out.println("Elapsed = " + elapsed);
    }
}
