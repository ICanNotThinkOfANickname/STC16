package lesson02.Task3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Person {
    int age;
    String sex;
    String name;

    int z;
    ArrayList<String> manNames = new ArrayList<>(Arrays.asList("Иван Иванов", "Павел Павлов", "Илья Сергеев",
            "Михаил Медведев", "Кузьма Соколов", "Сергей Михалков", "Фредди Меркьюри", "Джастин Бибер"));
    ArrayList<String> womanNames = new ArrayList<>(Arrays.asList("Алиса Селезнева", "Кристина Агилера", "Ирина Шейк",
            "Бритни Спирс", "Анна Михалкова", "Мария Шукшина", "Анастасия Иванова", "Светлана Петрова"));

    public Person() {
        this.age = setAge();
        this.sex = setSex();
        this.name = setName();
    }

    public static int setAge() {
        return new Random().nextInt(100);
    }

    public static double arrayDistrib() {
        double x = new Random().nextGaussian();
        return x;
    }

    public static int getElement() {
        double y = arrayDistrib();
        if (y > 0) {
            return (int) y;
        }
        return 0;
    }

    public String setSex() {
        z = getElement();
        if (z == 0) {
            return "MAN";
        }
        z = 1;
        return "WOMAN";
    }

    public String setName() {
        if (z == 0) {
            int w = new Random().nextInt(manNames.size());
            return manNames.get(w);
        }
        int w = new Random().nextInt(womanNames.size());
        return womanNames.get(w);
    }
}