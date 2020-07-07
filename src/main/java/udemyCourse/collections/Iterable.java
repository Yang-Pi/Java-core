package udemyCourse.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Iterable {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; ++i) {
            list.add(i * 10);
        }

        //I
        for (Integer element : list) {
            System.out.print(element + " ");
        }
        System.out.println();

        //II
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.hasNext()) {
                iterator.next();
                iterator.remove();
            }
        }
        System.out.println(list);

    }
}
