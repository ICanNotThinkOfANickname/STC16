package lesson06;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int temp = random.nextInt(10);
            arrayList.add(temp);
        }
        System.out.println("Числа: " + arrayList);
        List<NewThreads> arrayMyThread = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            arrayMyThread.add(new NewThreads(arrayList.get(i)));
        }
        List<Thread> arrayThread = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            arrayThread.add(new Thread(arrayMyThread.get(i)));
        }
        for (int i = 0; i < arrayList.size(); i++) {
            arrayThread.get(i).start();
        }
        for (int i = 0; i < arrayList.size(); i++) {
            try {
                arrayThread.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        List<BigInteger> randArrayBigInt = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            randArrayBigInt.add(BigInteger.valueOf(arrayMyThread.get(i).getIteration()));
        }
        System.out.println("Факториал чисел:");
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.print(randArrayBigInt.get(i) + ", ");
        }
    }
}