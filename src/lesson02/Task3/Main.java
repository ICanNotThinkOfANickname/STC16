package lesson02.Task3;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int arrayNum = 100;
        ArrayList<Person> personList = new ArrayList<Person>();
        for (int i = 0; i < arrayNum; ++i) {
            personList.add(new Person());
        }

        System.out.println("До сортировки");
        for (Person person : personList) {
            System.out.println(person.age + " " + person.sex + " " + person.name);
        }

        // Вызов пузырьковой сортировки
        final long startTime = System.nanoTime();
        Bubble.bubbleName(personList);
        Bubble.bubbleAge(personList);
        Bubble.bubbleSex(personList);
        final long duration = System.nanoTime() - startTime;

        System.out.println("");
        System.out.println("После пузырьковой сортировки");

        for (int i = 0; i < arrayNum; ++i) {
            System.out.println(personList.get(i).age + " " + personList.get(i).sex + " " + personList.get(i).name);
            if (i == 0) {
                continue;
            }
            if (personList.get(i).name.equals(personList.get(i - 1).name)) {
                try {
                    int x = personList.get(i).age / (personList.get(i).age - personList.get(i - 1).age);
                } catch (ArithmeticException e) {
                    System.out.println("Имена людей и возраст одинаковые");
                }
            }
        }

        ArrayList<Person> personListNext = new ArrayList<Person>();
        for (int i = 0; i < arrayNum; ++i) {
            personListNext.add(new Person());
        }

        System.out.println("");
        System.out.println("До сортировки вставкой");
        for (Person person : personListNext) {
            System.out.println(person.age + " " + person.sex + " " + person.name);
        }

        // Вызов методов сортировки вставкой
        final long startTimeNext = System.nanoTime();
        Insert.insertName(personListNext);
        Insert.insertAge(personListNext);
        Insert.insertSex(personListNext);
        final long durationNext = System.nanoTime() - startTime;

        System.out.println("");
        System.out.println("После сортировки вставкой");

        for (int i = 0; i < arrayNum; ++i) {
            System.out.println(personListNext.get(i).age + " " + personListNext.get(i).sex + " " + personListNext.get(i).name);
            if (i == 0) {
                continue;
            }
            if (personList.get(i).name.equals(personList.get(i - 1).name)) {
                try {
                    int x = personList.get(i).age / (personList.get(i).age - personList.get(i - 1).age);
                } catch (ArithmeticException e) {
                    System.out.println("Имена людей и возраст одинаковые");
                }
            }
        }
    }
}