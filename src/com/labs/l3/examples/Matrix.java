package com.labs.l3.examples;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;

public class Matrix {
    public static void main(String[] args) {
        int rows = 25;
        int cf = 100000;
        int cols = rows * cf;
        final double[][] matrix = generateMatrix(rows, cols);

        System.out.println("Start");
        //one general thread
        evaluate(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < matrix.length; ++i) {
                    for (int j = 0; j < matrix[i].length; ++j) {
                        double result = calculate(matrix[i][j]);
                    }
                }
            }
        });
        //a thread for every row
        evaluate(new Runnable() {
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
                double result = calculate(_row[i]);
            }
        }
    }
    //"big" operation
    private static double calculate(double dMatrixEl) {
        //use some heavy operations for double
        return Math.pow(Math.sin(Math.pow(dMatrixEl, Math.E)) / Math.cos(Math.pow(dMatrixEl, Math.E)), Math.PI);
    }
    //evaluate and print time of working
    private static void evaluate(Runnable task) {
        long start = System.currentTimeMillis();
        task.run();
        long stop = System.currentTimeMillis();

        long elapsed = stop - start;
        System.out.println("Elapsed = " + elapsed);
    }
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
}
