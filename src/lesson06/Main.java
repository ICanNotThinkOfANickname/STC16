package lesson06;

import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static lesson06.Factorial.factorial;

public class Main {
    private static final int NUMBER_K = 1000;
    private static final int COUNT = 100;

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        doSingleThreadCalculation();

        long end = System.currentTimeMillis();

        System.out.println("Результат однопоточно: " + (end - start));


        start = System.currentTimeMillis();

        doMultiThreadCalculation();

        end = System.currentTimeMillis();

        System.out.println("Результат многопоточно: " + (end - start));


    }

    private static void doMultiThreadCalculation() {
        ExecutorService exec = Executors.newFixedThreadPool(COUNT);
        for (int i = 1; i <= COUNT; i++) {
            final int value = i * NUMBER_K;

            exec.execute(new Runnable() {
                @Override
                public void run() {
                    doStep(value);
                }
            });
        }
        exec.shutdown();

        try {
            exec.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void doSingleThreadCalculation() {
        for (int i = 1; i <= COUNT; i++) {
            int value = i * NUMBER_K;
            doStep(value);
        }
    }

    public static void doStep(int i) {
        BigInteger result = factorial(i);
    }
}
