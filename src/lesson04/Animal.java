package lesson04;

public class Animal implements Comparable {
    private int id;
    private String animalName;
    private Person masterName;
    private double weight;

    public Animal(int id, String animalName, Person masterName, double weight) {
        this.id = id;
        this.animalName = animalName;
        this.masterName = masterName;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public Person getMasterName() {
        return masterName;
    }

    public void setMasterName(Person masterName) {
        this.masterName = masterName;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", animalName='" + animalName + '\'' +
                ", masterName='" + masterName + '\'' +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
