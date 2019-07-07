package lesson11.Task2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

public class Main {
    private static final int COUNT = 100_000_000;

    //-XX:MaxMetaspaceSize=64m
    public static void main(String[] args) {
        Integer[] intList = new Integer[COUNT];
        List<Integer[]> arrayList = new ArrayList<>();
        try {
            for (int i = 0; i < COUNT; i++) {
                System.out.println(Runtime.getRuntime().freeMemory());
                arrayList.add(intList);
                LockSupport.parkNanos(700 * 1000000);
            }
        } catch (OutOfMemoryError e) {
            System.out.println("OutOfMemoryError: Metaspace");
            throw e;
        }
    }
}