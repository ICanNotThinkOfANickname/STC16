package lesson03.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ObjectBox<T extends Object> {
    private List<Object> newList;

    public ObjectBox() {
        this.newList = new ArrayList<>(10);
    }

    public void add(T object) {
        newList.add(object);
    }

    public String dump() {
        return "ObjectBox{" +
                "newList=" + newList +
                '}';
    }

    public void find(T object) {
        for (Object o : newList) {
            if (o.equals(object)) {
                System.out.println("Object found " + o);
                break;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectBox objectBox = (ObjectBox) o;
        return Objects.equals(newList, objectBox.newList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newList);
    }
}

