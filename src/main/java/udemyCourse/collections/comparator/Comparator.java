package udemyCourse.collections.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Comparator {
    public static void main(String[] args) {
        List<String> animals = new ArrayList<>();
        animals.add("dog");
        animals.add("cat");
        animals.add("frog");
        animals.add("bird");

        Collections.sort(animals, new StringLengthComparator());
        System.out.println(animals);

        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            numbers.add(i % 5 * i);
        }

        Collections.sort(numbers, new java.util.Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return -1;
                }
                else if (o1 < o2) {
                    return 1;
                }
                else {
                    return 0;
                }
            }
        });
        System.out.println(numbers);
    }

    public static class StringLengthComparator implements java.util.Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            if (o1.length() > o2.length()) {
                return 1;
            }
            else if (o1.length() < o2.length()) {
                return -1;
            }
            else {
                return 0;
            }
        }
    }
}
