package lesson02.Task1;

import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        System.out.println("1.NullPointerException; 2.ArrayIndexOutOfBoundsException; 3.RuntimeException;");
        Scanner scanner = new Scanner(System.in);
        if (scanner.nextInt() == 1) {
            String s = null;
            s.toString();
            System.out.println(s);
        } else if (scanner.nextInt() == 2) {
            String word = "Hello world";
            String[] strings =
                    {"Hello world", "World hello"};
            strings[2] = "Hello";
            System.out.print(strings);
        } else if (scanner.nextInt() == 3) {
            throw new RuntimeException("Ошибка RuntimeException");
        }
    }
}