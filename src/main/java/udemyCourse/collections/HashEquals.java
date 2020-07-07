package udemyCourse.collections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HashEquals {
    public static void main(String[] args) {
        Person person1 = new Person(1, "Mike");
        Person person2 = new Person(2, "Kate");

        Map<Person, Integer> map = new HashMap<>();
        map.put(person1, 1);
        map.put(person2, 2);
        System.out.println(map);

        Person person3 = new Person(1, "Mike");
        map.put(person3, 3);
        System.out.println(map);
    }

    public static class Person {
        private Integer id;
        private String name;

        public Person(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Person person = (Person) o;

            if (!id.equals(person.id)) return false;
            return name.equals(person.name);
        }

        @Override
        public int hashCode() {
            int result = id.hashCode();
            result = 31 * result + name.hashCode();
            return result;
        }
    }
}
