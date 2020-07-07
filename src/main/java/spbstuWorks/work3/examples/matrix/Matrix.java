package spbstuWorks.work3.examples.matrix;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;

public class Matrix {
    public static void main(String[] args) {
        int rows = 25;
        int cf = 100000;
        int cols = rows * cf;
        final double[][] matrix = Utils.generateMatrix(rows, cols);

        System.out.println("Start");
        //one general thread
        Utils.evaluate(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < matrix.length; ++i) {
                    for (int j = 0; j < matrix[i].length; ++j) {
                        double result = Utils.calculate(matrix[i][j]);
                    }
                }
            }
        });
        //a thread for every row
        Utils.evaluate(new Runnable() {
            @Override
            public void run() {
                List<Thread> threads = new LinkedList<>();

                for (int i = 0; i < matrix.length; ++i) {
                    Thread t = new Thread(new RowTask(matrix[i]));
                    threads.add(t);
                    t.start();
                }
                for (Thread t : threads) {
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        System.out.println("Finish");
    }

    static class RowTask implements Runnable {
        private double[] _row;
        public RowTask(double[] row) {
            _row = row;
        }
        @Override
        public void run() {
            for (int i = 0; i < _row.length; ++i) {
                double result = Utils.calculate(_row[i]);
            }
        }
    }
}
