package lesson03.Task1;

import java.util.Random;

public class Array {
    Random random = new Random();

    public Number[] randomArray() {
        Number[] numbers = new Number[10];
        boolean numberAlreadyExists;
        for (int i = 0; i < numbers.length; ) {
            numberAlreadyExists = false;
            int newRandomValue = random.nextInt();
            for (int j = 0; j < i; j++) {
                if (numbers[j] == (Integer) newRandomValue) {
                    numberAlreadyExists = true;
                    break;
                }
            }
            if (!numberAlreadyExists) {
                numbers[i] = newRandomValue;
                i++;
            }
        }
        return numbers;
    }
}
