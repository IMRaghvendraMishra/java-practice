package org.practice.jdk8.streams.labs;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Q2_1841 {

    public static void main(String[] args) {
//        AtomicInteger ai1 = new AtomicInteger(); // initial value of 0
//        Stream.of(11, 11, 22, 33)
//                .parallel()
//                .filter(n -> {
//                    ai1.incrementAndGet();
//                    return n % 2 == 0;
//                })
//                .forEach(System.out::println);//22
//        System.out.println(ai1);//4

// 1 of 2
//        AtomicInteger ai2 = new AtomicInteger(); // initial value of 0
//        Stream<Integer> stream1 = Stream.of(11, 11, 22, 33).parallel();
//        stream1.filter(e -> {
//            ai2.incrementAndGet();
//            return e % 2 == 0;
//        });
//        stream1.forEach(System.out::println);// java.lang.IllegalStateException
//        System.out.println(ai2);

// 2 of 2
//        AtomicInteger ai = new AtomicInteger(); // initial value of 0
//        Stream<Integer> stream2 = Stream.of(11, 11, 22, 33, 34).parallel();
//        Stream<Integer> stream3 = stream2.filter(e -> {
//            ai.incrementAndGet();
//            return e % 2 == 0;
//        });
//        stream3.forEach(System.out::println);// 22, 34
//        System.out.println(ai);
    }

}
