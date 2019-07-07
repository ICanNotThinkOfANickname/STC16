package lesson11.Task1;

import java.util.concurrent.locks.LockSupport;

public class Main {
    private static final int COUNT = 100_000_000;

    public static void main(String[] args) {
        int value = 100;
        try {
            for (int i = 0; i < COUNT; i++) {
                System.out.println(Runtime.getRuntime().freeMemory());
                int[] intList = new int[value];
                value = value * value;
                LockSupport.parkNanos(700 * 1000000);
            }
        } catch (OutOfMemoryError e) {
            System.out.println("Java Heap Space");
            throw e;
        }
    }
}