package lesson03.task1;

import lesson03.task2.ObjectBox;

import java.util.*;

public class MathBox extends ObjectBox<Number> {
    private TreeSet<Integer> newSet;

    public MathBox() {
        List<Number> newList = Arrays.asList(new Array().randomArray());
        newSet = new TreeSet<Integer>((Comparator<? super Integer>) newList);
    }

    public int summator() {
        int amount = 0;
        for (Integer number : newSet) {
            amount = amount + number;
        }
        return amount;
    }

    public TreeSet<Integer> splitter(int del) {
        for (Integer number : newSet) {
            double newNumber = (double) number / del;
            System.out.println("Делимое " + number + " Делитель " + del + " Частное " + newNumber);
        }
        return newSet;
    }

    public boolean deleteNumber(int deleteNumber) {
        return newSet.remove(deleteNumber);
    }

    @Override
    public String toString() {
        return "mathBox.MathBox{" +
                "newSet=" + newSet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MathBox mathBox = (MathBox) o;
        return Objects.equals(newSet, mathBox.newSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newSet);
    }
}
