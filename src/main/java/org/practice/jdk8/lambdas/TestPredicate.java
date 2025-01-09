package org.practice.jdk8.lambdas;

import java.util.function.Predicate;  // pre-defined in the API


public class TestPredicate {

    // my own custom Functional Interface
    interface Evaluate<T> {
        boolean isNegative(T t);  // similar to Predicate
    }


    public static void main(String[] args) {
        // Evaluate<T> is a functional interface i.e. one abstract method:
        //      boolean isNegative(T t);  // similar to java.util.function.Predicate
        Evaluate<Integer> lambda = i -> i < 0;
        System.out.println("Evaluate: " + lambda.isNegative(-1));  // true
        System.out.println("Evaluate: " + lambda.isNegative(+1));  // false

        // Predicate<T> is a functional interface i.e. one abstract method:
        //      boolean test(T t)
        Predicate<Integer> predicate = i -> i < 0;
        System.out.println("Predicate: " + predicate.test(-1));  // true
        System.out.println("Predicate: " + predicate.test(+1));  // false

        int x = 4;
        Predicate<Integer> evenPredicate = n -> n % 2 == 0;
        System.out.println("Is " + x + " even? " + check(4, evenPredicate));  // true
        x = 7;
        System.out.println("Is " + x + " even? " + check(7, evenPredicate));  // false

        String name = "Mr. Joe Bloggs";
        Predicate<String> startWithMrPredicate = str -> str.startsWith("Mr.");
        System.out.println("Does " + name + " start with Mr. ? "
                + check("Mr. Joe Bloggs", startWithMrPredicate));  // true
        name = "Ms. Ann Bloggs";
        System.out.println("Does " + name + " start with Mr. ? "
                + check("Ms. Ann Bloggs", s -> s.startsWith("Mr.")));  // false
    }

    // Use of util method that use Predicate functional interface
    public static <T> boolean check(T t, Predicate<T> lambda) {
        return lambda.test(t);
    }
}
