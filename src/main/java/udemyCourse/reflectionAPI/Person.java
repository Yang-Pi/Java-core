package udemyCourse.reflectionAPI;

public class Person {
    private int id;
    private String name;

    public Person() {
        id = -1;
        name = "No name";
    }

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
