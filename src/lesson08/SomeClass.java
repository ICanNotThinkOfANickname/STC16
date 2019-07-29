package lesson08;

public class SomeClass implements Worker {
    static {
        System.out.println("Егор");
    }

    public SomeClass() {
    }

    public void doWork() {
        System.out.println("Пишу код");
    }
}