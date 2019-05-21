package lesson02.Task2;

import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        System.out.print("Введите сколько чисел надо сгенерировать : ");
        Scanner scanner = new Scanner(System.in);
        int randonNumber = scanner.nextInt();
        int[] array = new int[randonNumber];

        for (int number = 0; number < array.length; number++) {
            array[number] = (int) Math.round(Math.random() * 100);
            System.out.println(array[number]);
            if (number < 0) {
                throw new ArithmeticException();
            } else {
                double sqr = Math.sqrt(array[number]);
                if (((int) sqr * (int) sqr) == number) {
                    System.out.println(number);
                }
            }
        }
    }
}
