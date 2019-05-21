package lesson04;

import java.util.*;

public class MethodAnimal {
    private Set<Animal> animalSet = new TreeSet<>();
    private Map<String, Animal> animalMap = new HashMap<>();

    /*Map - для поиска, Set - сортировка.*/
    /*Добавление животного*/
    public void addAnimal(Animal animal) {
        if (animalMap.containsKey(animal.getAnimalName())) {
            System.out.println("Животное " + animal.getAnimalName() + " добавлено");
        }
        animalSet.add(animal);
        animalMap.put(animal.getAnimalName(), animal);
    }

    /*Поиск животного*/
    public Animal findAnimal(String animalName) {
        Animal animal = null;
        if (animalMap.containsKey(animalName)) {
            animal = animalMap.get(animalName);
        }
        return animal;
    }

    /*Изменение хозяина по id животного*/
    public void modificationNameMaster(int id, Person person) {
        Iterator<Animal> animalIterator = animalSet.iterator();
        Animal newNameAnimal;
        Animal oldNameAnimal;
        while (animalIterator.hasNext()) {
            oldNameAnimal = animalIterator.next();
            if (oldNameAnimal.getId() == id) {
                newNameAnimal = new Animal(oldNameAnimal.getId(), oldNameAnimal.getAnimalName(), person, oldNameAnimal.getWeight());
                animalMap.replace(oldNameAnimal.getAnimalName(), newNameAnimal);
                animalSet.add(newNameAnimal);
                animalSet.remove(oldNameAnimal);
                break;
            }
        }
    }

    /*Меняем имя животного*/
    public void modificatioNameAnimal(int id, String animal) {
        Iterator<Animal> animalIterator = animalSet.iterator();
        Animal newNameAnimal;
        Animal oldNameAnimal;
        while (animalIterator.hasNext()) {
            oldNameAnimal = animalIterator.next();
            if (oldNameAnimal.getId() == id) {
                newNameAnimal = new Animal(oldNameAnimal.getId(), animal, oldNameAnimal.getMasterName(), oldNameAnimal.getWeight());
                animalMap.remove(oldNameAnimal.getAnimalName(), oldNameAnimal);
                animalMap.put(newNameAnimal.getAnimalName(), newNameAnimal);
                animalSet.add(newNameAnimal);
                animalSet.remove(oldNameAnimal);
                break;
            }
        }
    }

    public void printAnimal() {
        for (Map.Entry entry : animalMap.entrySet()) {
            System.out.println(entry.getKey() + " , " + entry.getValue());
        }
    }

    public void printSort() {
        for (Animal animal : animalSet) {
            System.out.println(animal.toString());
        }
    }
}
