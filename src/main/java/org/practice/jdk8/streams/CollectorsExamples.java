package org.practice.jdk8.streams;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsExamples {
    public static void main(String[] args) {
//        doPartitioning();

//        doGroupingBy();

        doCollectToMap();

        doJoining();
        doAveragingInt();
    }

    public static void doPartitioning() {
        Stream<String> names = Stream.of("Thomas", "Teresa", "Mike", "Alan", "Peter");

        System.out.println(names.collect(
                // pass in a Predicate
                Collectors.partitioningBy(s -> s.startsWith("T"))
        )); // {false=[Mike, Alan, Peter], true=[Thomas, Teresa]}

        names = Stream.of("Thomas", "Teresa", "Mike", "Alan", "Peter");
        System.out.println(names.collect(
                // pass in a Predicate
                Collectors.partitioningBy(s -> s.length() > 4)
        ));// {false=[Mike, Alan], true=[Thomas, Teresa, Peter]}

        names = Stream.of("Thomas", "Teresa", "Mike", "Alan", "Peter");
        System.out.println(names.collect(
                // forcing an empty list
                Collectors.partitioningBy(s -> s.length() > 10)
        ));// {false=[Thomas, Teresa, Mike, Alan, Peter], true=[]}

        names = Stream.of("Thomas", "Teresa", "Mike", "Alan", "Peter");
        System.out.println(names.collect(
                Collectors.partitioningBy(
                        s -> s.length() > 4,// predicate
                        Collectors.toSet())
        ));// {false=[Mike, Alan], true=[Teresa, Peter]}
    }

    public static void doGroupingBy() {

        Stream<String> names = Stream.of("Martin", "Peter", "Joe", "Tom", "Tom", "Ann", "Alan");
        Map<Integer, List<String>> map1 = names.collect(
                // passing in a Function that determines the key in the Map
                Collectors.groupingBy(String::length) // s -> s.length()
        );
        System.out.println(map1);// {3=[Joe, Tom, Tom, Ann], 4=[Alan], 5=[Peter], 6=[Martin]}

        names = Stream.of("Joe", "Tom", "Tom", "Alan", "Peter");
        Map<Integer, List<String>> map2 = names.collect(
                // passing in a Function that determines the key in the Map
                Collectors.groupingBy(String::length) // s -> s.length()
        );
        System.out.println(map2);// {3=[Joe, Tom, Tom], 4=[Alan], 5=[Peter]}

        names = Stream.of("Martin", "Peter", "Joe", "Tom", "Tom", "Ann", "Alan");
        Map<Integer, Set<String>> map3 = names.collect(
                Collectors.groupingBy(
                        String::length,     // key Function
                        Collectors.toSet()) // what to do with the values
        );
        System.out.println(map3);// {3=[Ann, Joe, Tom], 4=[Alan], 5=[Peter], 6=[Martin]}

        names = Stream.of("Alan", "Joe", "Tom", "Tom", "Peter");
        Map<Integer, Set<String>> map4 = names.collect(
                Collectors.groupingBy(
                        String::length,     // key Function
                        Collectors.toSet()) // what to do with the values
        );
        System.out.println(map4);// {3=[Joe, Tom], 4=[Alan], 5=[Peter]}

        names = Stream.of("Martin", "Peter", "Joe", "Tom", "Tom", "Ann", "Alan");
        Map<Integer, List<String>> map5 = names.collect(
                Collectors.groupingBy(
                        String::length,
                        TreeMap::new,       // map type Supplier
                        Collectors.toList())// downstream collector
        );
        System.out.println(map5);// {3=[Joe, Tom, Tom, Ann], 4=[Alan], 5=[Peter], 6=[Martin]}
        System.out.println(map5.getClass());// class java.util.TreeMap

        names = Stream.of("Joe", "Tom", "Tom", "Alan", "Peter");
        TreeMap<Integer, List<String>> map6 = names.collect(
                Collectors.groupingBy(
                        String::length,
                        TreeMap::new,       // map type Supplier
                        Collectors.toList())// downstream collector
        );
        System.out.println(map6);// {3=[Joe, Tom, Tom], 4=[Alan], 5=[Peter]}
        System.out.println(map6.getClass());// class java.util.TreeMap
    }

    public static void doCollectToMap() {

        // We want a map: dessert name -> number of characters in dessert name
        // Output: {biscuits=8, cake=4, apple tart=10}
        Map<String, Integer> map = Stream.of("cake", "biscuits", "apple tart")
                .collect(
                        Collectors.toMap(s -> s,         // Function for the key
                                s -> s.length())// Function for the value
                );
        System.out.println(map);

        // We want a map: number of characters in dessert name -> dessert name
        // However, 2 of the desserts have the same length (cake and tart) and as
        // length is our key and we can't have duplicate keys, this leads to an
        // exception as Java does not know what to do...
        //    IllegalStateException: Duplicate key 4 (attempted merging values cake and tart)
        // To get around this, we can supply a merge function, whereby we append the
        // colliding keys values together.
        Map<Integer, String> map2 = Stream.of("cake", "biscuits", "tart")
                .collect(
                        Collectors.toMap(s -> s.length(),// key is the length
                                s -> s,         // value is the String
                                (s1, s2) -> s1 + "," + s2)// Merge function - what to
                        // do if we have duplicate keys
                        //   - append the values
                );
        System.out.println(map2);// {4=cake,tart, 8=biscuits}

        // The maps returned are HashMaps but this is not guaranteed. What if we wanted
        // a TreeMap implementation so our keys would be sorted. The last argument
        // caters for this.
        TreeMap<String, Integer> map3 = Stream.of("cake", "biscuits", "apple tart", "cake")
                .collect(
                        Collectors.toMap(s -> s,            // key is the String
                                s -> s.length(),   // value is the length of the String
                                (len1, len2) -> len1 + len2, // what to do if we have
                                // duplicate keys
                                //    - add the *values*
                                () -> new TreeMap<>()));// TreeMap::new works
        System.out.println(map3);// {apple tart=10, biscuits=8, cake=8} Note: cake maps to 8
        System.out.println(map3.getClass());// class java.util.TreeMap
    }

    public static void doJoining() {
        String s = Stream.of("cake", "biscuits", "apple tart")
                .collect(Collectors.joining(", "));
        System.out.println(s);  // cake, biscuits, apple tart
    }

    public static void doAveragingInt() {
        Double avg = Stream.of("cake", "biscuits", "apple tart")
                // averagingInt(ToIntFunction) functional method is:
                //      int applyAsInt(T value);
                .collect(Collectors.averagingInt(s -> s.length()));
        System.out.println(avg);  // 7.333333333333333
    }
}