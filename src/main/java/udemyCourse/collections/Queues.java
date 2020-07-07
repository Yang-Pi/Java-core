package udemyCourse.collections;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Queues {
    public static void main(String[] args) {
        Queue<Person> peopleQueue = new ArrayBlockingQueue<>(5);
        for (int i = 0; i < 10; ++i) {
            System.out.print(peopleQueue.offer(new Person(i)) + " ");
        }
        System.out.println("\npeek: " + peopleQueue.peek());
        System.out.println("poll: " + peopleQueue.poll());

        //also there are add() & remove() & element()
    }

    public static class Person {
        private Integer id;

        public Person(Integer id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    '}';
        }
    }
}
