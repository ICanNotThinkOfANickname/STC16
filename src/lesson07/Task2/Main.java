package lesson07.Task2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        mySerialisation mySerialisation = new mySerialisation();
        List<Student> students = new ArrayList<>();
        Student studentFirst = new Student("Сапар", 18, 251, null);
        Student studentSecond = new Student("Рустам", 19, 274, null);
        students.add(studentFirst);
        students.add(studentSecond);

        Student student = new Student("Тамби", 18, 268, students);
        /*Сериализация и десериализация*/
        mySerialisation.serialize(student, "fileTask2");
        Object deSerialize = mySerialisation.deSerialize("fileTask2");
        System.out.println("Десериализованный студент: " + deSerialize);
    }

    /* Класс, реализующий методы сериализации и десериализации */
    static class mySerialisation {

        /* Сериализация объекта */
        public void serialize(Object object, String file) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
                System.out.println("Студент (сериализация): " + object);
                objectOutputStream.writeObject(object);
            } catch (IOException e) {
                System.out.println("Исключение (сериализации): " + e);
            }
        }

        /* Десериализация объекта */
        public Object deSerialize(String file) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
                Student student = (Student) objectInputStream.readObject();
                System.out.println("Студент (десериализация) " + student);
                return student;
            } catch (Exception e) {
                System.out.println("Исключение (десериализации) " + e);
                System.exit(0);
            }
            return null;
        }
    }
}
