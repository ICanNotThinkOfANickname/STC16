package lesson04;

public class Animal implements Comparable {
    private int id;
    private String name;
    private Person person;
    private int weight;

    public Animal(int id, String name, Person person, int weight) {
        this.id = id;
        this.name = name;
        this.person = person;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", person=" + person +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Animal animal = (Animal) o;
        int result = 0;
        int resultPerson = this.getPerson().compareTo(animal.getPerson());
        int resultWeight = this.getWeight() - animal.getWeight(); //0 - равный, <0 this меньше,
        int resultName = this.getName().compareTo(animal.getName()); //0 - равный, <0 this меньше,

        if (resultPerson == 0) {
            if (resultName == 0) {
                result = resultWeight;
            } else
                result = resultName;
        } else {
            result = resultPerson;
        }
        return result;
    }
}