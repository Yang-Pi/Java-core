package udemyCourse.collections;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Sets {
    public static void main(String[] args) {
        Set<Integer> hashSet = new HashSet<>();
        Set<Integer> linkedHashSet = new LinkedHashSet<>();
        Set<Integer> treeSet = new TreeSet<>();

        for (int i = 0; i < 4; ++i) {
            hashSet.add(i % 7);
        }
        for (int i = 4; i < 9; ++i) {
            linkedHashSet.add(i % 7);
        }

        System.out.println(hashSet);
        System.out.println(linkedHashSet);

        Set<Integer> union = new HashSet<>(hashSet);
        union.addAll(linkedHashSet);
        System.out.println("Union: " + union);

        Set<Integer> intersection = new HashSet<>(hashSet);
        intersection.retainAll(linkedHashSet);
        System.out.println("Intersection: " + intersection);

        Set<Integer> difference = new HashSet<>(hashSet);
        difference.removeAll(linkedHashSet);
        System.out.println("Difference: " + difference);
    }
}
