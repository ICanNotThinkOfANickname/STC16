package lesson07.Task2;

import java.io.Serializable;
import java.util.List;

public class Student implements Serializable {
    String name;
    int age;
    int number;
    List students;

    public Student(String name, int age, int number, List students) {
        this.name = name;
        this.age = age;
        this.number = number;
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List getStudents() {
        return students;
    }

    public void setStudents(List students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", number=" + number +
                ", students=" + students +
                '}';
    }
}
