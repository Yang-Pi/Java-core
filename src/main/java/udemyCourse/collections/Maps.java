package udemyCourse.collections;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Maps {
    public static void main(String[] args) {
        java.util.Map<Integer, String> hashMap = new HashMap<>();
        java.util.Map<Integer, String> linkedHashMap = new LinkedHashMap<>();
        java.util.Map<Integer, String> treeMap = new TreeMap<>();

        System.out.println("---------HashMap order");
        testMap(hashMap);
        System.out.println("---------LinkedHashMap order");
        testMap(linkedHashMap);
        System.out.println("---------TreeMap order");
        testMap(treeMap);
    }

    public static void testMap(java.util.Map<Integer, String> map) {
        map.put(39, "Bob");
        map.put(12, "Moke");
        map.put(78, "Tom");
        map.put(0, "Tim");
        map.put(1500, "Lewis");
        map.put(7, "Jim");

        for(java.util.Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
