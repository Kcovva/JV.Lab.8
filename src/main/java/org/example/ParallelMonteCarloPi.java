package org.example;

import java.util.Scanner;

public class ParallelMonteCarloPi {

    private static final long ITERATIONS = 1_000_000_000L;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of threads: ");
        int threadsCount = 0;
        try {
            threadsCount = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number!");
            System.exit(1);
        }

        Worker[] workers = new Worker[threadsCount];
        long iterationsPerThread = ITERATIONS / threadsCount;

        long start = System.nanoTime();

        for (int i = 0; i < threadsCount; i++) {
            workers[i] = new Worker(iterationsPerThread);
            workers[i].start();
        }

        long totalInsideCircle = 0;

        try {
            for (Worker w : workers) {
                w.join();
                totalInsideCircle += w.getInsideCircle();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        double pi = 4.0 * totalInsideCircle / ITERATIONS;
        long end = System.nanoTime();
        double timeMs = (end - start) / 1_000_000.0;

        System.out.printf("PI is %.5f%n", pi);
        System.out.printf("THREADS %d%n", threadsCount);
        System.out.printf("ITERATIONS %,d%n", ITERATIONS);
        System.out.printf("TIME %.2fms%n", timeMs);
    }
}

