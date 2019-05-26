package lesson06;

import java.math.BigInteger;

public final class Factorial {
    public static BigInteger factorial(int number) {
        if (number < 0) {
            throw new IllegalArgumentException();
        }
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= number; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
