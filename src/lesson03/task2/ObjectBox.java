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

    public void removeObject(T object) {
        this.newList.remove(object);
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

