package com.labs.l3.examples.matrix;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorMatrix {
    static class RowTask implements Callable<Double> {
        private double[] _row;
        public RowTask(double[] row) {
            _row = row;
        }

        @Override
        public Double call() throws Exception {
            double sum = 0;
            for (int i = 0; i < _row.length; ++i) {
                sum += Utils.calculate(_row[i]);
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        int rows = 10000;
        int cols = rows;
        final double[][] matrix = Utils.generateMatrix(rows, cols);

        int nThreads = Runtime.getRuntime().availableProcessors();
        final ExecutorService service = Executors.newFixedThreadPool((nThreads / 2 != 0) ? nThreads : 1);

        Utils.evaluate(new Runnable() {
            @Override
            public void run() {
                List<Future<Double>> futures = new LinkedList<>();
                for (int i = 0; i < matrix.length; ++i) {
                    Future<Double> future = service.submit(new RowTask(matrix[i]));
                    futures.add(future);
                }
                double sum = 0.0;
                for (Future<Double> future: futures) {
                    try {
                        sum += future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Sum = " + sum);
                /*service.shutdown();
                try {
                    service.awaitTermination(1, TimeUnit.HOURS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        });
    }
}
