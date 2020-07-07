package udemyCourse.collections.comparator;

import java.util.Set;
import java.util.TreeSet;

public class ComparableObjects {
    public static void main(String[] args) {
        Set<Person> peopleSet = new TreeSet<>();
        peopleSet.add(new Person(0, "Michael"));
        peopleSet.add(new Person(1, "Anton"));
        peopleSet.add(new Person(2, "Robert"));
        peopleSet.add(new Person(3, "Jane"));
        peopleSet.add(new Person(4, "Ann"));

        System.out.println(peopleSet);
    }

    public static class Person implements Comparable<Person> {
        private int id;
        private String name;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public int compareTo(Person o) {
            if (this.name.length() > o.getName().length()) {
                return 1;
            }
            else if (this.name.length() < o.getName().length()) {
                return -1;
            }
            else {
                return 0;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Person person = (Person) o;

            if (id != person.id) return false;
            return name != null ? name.equals(person.name) : person.name == null;
        }

        @Override
        public int hashCode() {
            int result = id;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
