package org.example;

import java.util.Random;

public class Worker extends Thread {

    private long iterations;
    private long insideCircle = 0;

    public Worker(long iterations) {
        this.iterations = iterations;
    }

    public long getInsideCircle() {
        return insideCircle;
    }

    @Override
    public void run() {
        Random rand = new Random();

        long count = 0;
        for (long i = 0; i < iterations; i++) {
            double x = rand.nextDouble();
            double y = rand.nextDouble();
            if (Math.sqrt(x * x + y * y) <= 1.0) {
                count++;
            }
        }

        insideCircle = count;
    }
}


