package lesson07.Task1;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        mySerialisation mySerialisation = new mySerialisation();
        Student student = new Student("Алексей", 19, 134);
        mySerialisation.serialize(student, "fileTask1");
        Object deSerialize = mySerialisation.deSerialize("fileTask1");
        System.out.println("Десериализованный студент: " + deSerialize);
    }

    static class mySerialisation {

        /* Сериализация*/
        public void serialize(Object object, String file) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
                System.out.println("Студент (сериализация): " + object);
                objectOutputStream.writeObject(object);
            } catch (IOException e) {
                System.out.println("Исключение (сериализации): " + e);
            }
        }

        /* Десериализация*/
        public Object deSerialize(String file) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
                Student student = (Student) objectInputStream.readObject();
                System.out.println("Студент (десериализация): " + student);
                return student;
            } catch (Exception e) {
                System.out.println("Исключение (десериализации): " + e);
                System.exit(0);
            }
            return null;
        }
    }
}