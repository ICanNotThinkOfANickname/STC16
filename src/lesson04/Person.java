package lesson04;

public final class Person implements Comparable {
    private String name;
    private Integer age;
    private String sex;

    public Person(String name, Integer age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    @Override
    public int compareTo(Object o) {
        Person person = (Person) o;
        int result;
        result = this.getName().compareTo(person.getName());
        if (result == 0) {
            result = this.getAge() - person.getAge();
        }
        return result;
    }
}