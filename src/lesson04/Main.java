package lesson04;

public class Main {
    public static void main(String[] args) {
        Person person1 = new Person("Ivan", 30, "Man");
        Person person2 = new Person("Elena", 25, "Woman");
        Person person3 = new Person("Timur", 40, "Man");
        Person person4 = new Person("Anna", 33, "Woman");

        Animal animal1 = new Animal(1, "Tirion", person1, 10);
        Animal animal2 = new Animal(2, "John", person3, 30);
        Animal animal3 = new Animal(3, "Cercei", person2, 15);
        Animal animal4 = new Animal(4, "Ed", person1, 20);
        Animal animal5 = new Animal(5, "Sansa", person4, 5);

        MethodAnimal methodAnimal = new MethodAnimal();
        methodAnimal.addAnimal(animal1);
        methodAnimal.addAnimal(animal2);
        methodAnimal.addAnimal(animal3);
        methodAnimal.addAnimal(animal4);
        methodAnimal.addAnimal(animal5);

        System.out.println("Список животных:");
        methodAnimal.printAnimal();
        System.out.println("_________________");
        System.out.println("\"Поиск животного\"");
        Animal findAnimal = methodAnimal.findAnimal("Cercei");
        if (findAnimal != null) {
            System.out.println("\"Животное найдено \" : " + findAnimal.getAnimalName() + findAnimal.toString());
        }
        System.out.println("_________________");
        System.out.println("\"Изменение хозяина животного под номером 3\"");
        methodAnimal.modificationNameMaster(3, person1);
        methodAnimal.printAnimal();
        System.out.println("_________________");
        System.out.println("\"Изменение имени животного\"");
        methodAnimal.modificatioNameAnimal(5, "Arya");
        methodAnimal.printAnimal();
        System.out.println("_________________");
        System.out.println("\"Отсортированный список животных\"");
        methodAnimal.printSort();
        System.out.println("_________________");
    }
}
