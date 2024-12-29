package org.practice.jdk8.lambdas.lab2;

import java.util.*;
import java.util.function.*;

public class LambdasAndMethodReferences {

    public static void main(String[] args) {
        LambdasAndMethodReferences obj = new LambdasAndMethodReferences();

        obj.staticMR();
//        obj.boundMR();
//        obj.unboundMR();
//        obj.constructorMR();
    }

    /**
     * Static method references:
     * a. in staticMR(), declare a List of integers with 1, 2, 7, 4, and 5 as values.
     * b. using a Consumer typed for List<Integer> and the Collections.sort static method, code a lambda that sorts the list passed  in.
     * c. invoke the lambda.
     * d. prove that the sort worked.
     * e. re-initialise the list (so it is unsorted again).
     * f. code the method reference version.
     *     i. sort() is overloaded : sort(List) and sort(List, Comparator)
     *     ii. how does Java know which version to call?
     * g. invoke the method reference version.
     * h. prove that the sort worked.
     */
    void staticMR() {
        // a. Declare a List of integers with 1, 2, 7, 4, and 5 as values
        List<Integer> numbers = List.of(1, 2, 7, 4, 5);

        // Convert immutable List to mutable List
        numbers = new java.util.ArrayList<>(numbers);

        // b. Using a Consumer and Collections.sort, code a lambda that sorts the list passed in
        Consumer<List<Integer>> sortLambda = list -> Collections.sort(list);

        // c. Invoke the lambda
        sortLambda.accept(numbers);

        // d. Prove that the sort worked
        System.out.println("Sorted using lambda: " + numbers);

        // e. Reinitialize the list (unsorted again)
        numbers = new java.util.ArrayList<>(List.of(1, 2, 7, 4, 5));
        System.out.println("Reinitialized list: " + numbers);

        // f. Code the method reference version
        Consumer<List<Integer>> sortMethodRef = Collections::sort;

        // g. Invoke the method reference version
        sortMethodRef.accept(numbers);

        // h. Prove that the sort worked
        System.out.println("Sorted using method reference: " + numbers);

        /*
         * f.ii. How does Java know which version of sort() to call?
         * Java determines the version to call based on the method signature.
         * - Collections.sort(List): Requires only a list as a parameter.
         * - Collections.sort(List, Comparator): Requires both a list and a comparator.
         *
         * In this case, since we are only passing a list to the sort() method, Java resolves
         * the call to the first overloaded method, i.e., Collections.sort(List).
         */
    }

    /**
     * Bound method references (calling instance methods on a particular object):
     * a. in boundMR(), declare a String variable called name and initialise it to “Mr. Joe Bloggs”.
     * b. using a Predicate typed for String, code a lambda that checks to see if name starts with the prefix passed in.
     * c. invoke the lambda passing in “Mr.” which should return true.
     * d. invoke the lambda passing in “Ms.” which should return false.
     * e. code the method reference version.
     * f. repeat c and d above except using the method reference version.
     */
    void boundMR() {
        // a. Declare a String variable called name and initialise it to “Mr. Joe Bloggs”
        String name = "Mr. Joe Bloggs";

        // b. Using a Predicate typed for String, code a lambda that checks if name starts with the prefix
        Predicate<String> startsWithLambda = prefix -> name.startsWith(prefix);

        // c. Invoke the lambda passing in “Mr.” which should return true
        System.out.println("Using Lambda - Does name start with 'Mr.': " + startsWithLambda.test("Mr.")); // true

        // d. Invoke the lambda passing in “Ms.” which should return false
        System.out.println("Using Lambda - Does name start with 'Ms.': " + startsWithLambda.test("Ms.")); // false

        // e. Code the method reference version
        Predicate<String> startsWithMethodRef = name::startsWith;

        // f. Repeat c and d above except using the method reference version
        System.out.println("Using Method Reference - Does name start with 'Mr.': " + startsWithMethodRef.test("Mr.")); // true
        System.out.println("Using Method Reference - Does name start with 'Ms.': " + startsWithMethodRef.test("Ms.")); // false
    }

    /**
     * Unbound method references (calling instance methods on a parameter):
     * a. in unboundMR(), code a Predicate lambda typed for String that checks to see if the string passed in is empty.
     * b. invoke the lambda passing in “” (returns true).
     * c. invoke the lambda passing in “xyz” (returns false).
     * d. code the method reference version of the lambda from (a).
     * e. repeat b and c above except using the method reference version.
     * f. code a BiPredicate lambda typed for String and String:
     *     i. the lambda takes in two parameters (hence “Bi”)
     *     ii. check if the first parameter starts with the second parameter
     *     iii. invoke the lambda twice:
     *         1. passing in “Mr. Joe Bloggs” and “Mr.” (returns true)
     *         2. passing in “Mr. Joe Bloggs” and “Ms.” (returns false)
     * g. code the method reference version of the lambda from (f).
     * h. test it as per above in (f.iii)
     */
    void unboundMR() {
        // a. Code a Predicate lambda typed for String that checks to see if the string passed in is empty
        Predicate<String> isEmptyLambda = s -> s.isEmpty();

        // b. Invoke the lambda passing in "" (returns true)
        System.out.println("Using Lambda - Is empty string: " + isEmptyLambda.test("")); // true

        // c. Invoke the lambda passing in "xyz" (returns false)
        System.out.println("Using Lambda - Is empty string: " + isEmptyLambda.test("xyz")); // false

        // d. Code the method reference version of the lambda from (a)
        Predicate<String> isEmptyMethodRef = String::isEmpty;

        // e. Repeat b and c above except using the method reference version
        System.out.println("Using Method Reference - Is empty string: " + isEmptyMethodRef.test("")); // true
        System.out.println("Using Method Reference - Is empty string: " + isEmptyMethodRef.test("xyz")); // false

        // f. Code a BiPredicate lambda typed for String and String
        BiPredicate<String, String> startsWithLambda = (str, prefix) -> str.startsWith(prefix);

        // f.iii. Invoke the lambda twice
        System.out.println("Using Lambda - 'Mr. Joe Bloggs' starts with 'Mr.': " + startsWithLambda.test("Mr. Joe Bloggs", "Mr.")); // true
        System.out.println("Using Lambda - 'Mr. Joe Bloggs' starts with 'Ms.': " + startsWithLambda.test("Mr. Joe Bloggs", "Ms.")); // false

        // g. Code the method reference version of the lambda from (f)
        BiPredicate<String, String> startsWithMethodRef = String::startsWith;

        // h. Test it as per above in (f.iii)
        System.out.println("Using Method Reference - 'Mr. Joe Bloggs' starts with 'Mr.': " + startsWithMethodRef.test("Mr. Joe Bloggs", "Mr.")); // true
        System.out.println("Using Method Reference - 'Mr. Joe Bloggs' starts with 'Ms.': " + startsWithMethodRef.test("Mr. Joe Bloggs", "Ms.")); // false
    }


    /**
     * Constructor method references:
     * a. in constructorMR(), code a Supplier typed for List<String> that returns a new ArrayList.
     * b. invoke the lambda to create a new List<String> named list.
     * c. add “Lambda” to the list.
     * d. output the list to show it worked.
     * e. code the method reference version of the lambda:
     *     i. re-initialise list by invoking the method reference version.
     *     ii. add “Method Reference” to the list.
     *     iii. output the list to show it worked.
     * f. next, we want to use the overloaded ArrayList constructor passing in 10 as the initial capacity (note: the default
     *     constructor assumes a capacity of 10).
     *     i. thus, we need to pass IN something and get back OUT something:
     *         1. IN: 10 OUT: ArrayList
     *     ii. we need a Function typed for Integer and List<String> for this.
     *     iii. code the lambda.
     *     iv. re-initialise the list by invoking the lambda passing in 10 as the capacity.
     *     v. add “Lambda” to the list.
     *     vi. output the list to show it worked.
     */
    void constructorMR() {
        // a. Code a Supplier typed for List<String> that returns a new ArrayList
        Supplier<List<String>> listSupplierLambda = () -> new ArrayList<>();

        // b. Invoke the lambda to create a new List<String> named list
        List<String> list = listSupplierLambda.get();

        // c. Add "Lambda" to the list
        list.add("Lambda");

        // d. Output the list to show it worked
        System.out.println("Using Lambda Supplier: " + list);

        // e. Code the method reference version of the lambda
        Supplier<List<String>> listSupplierMethodRef = ArrayList::new;

        // e.i. Re-initialise list by invoking the method reference version
        list = listSupplierMethodRef.get();

        // e.ii. Add "Method Reference" to the list
        list.add("Method Reference");

        // e.iii. Output the list to show it worked
        System.out.println("Using Method Reference Supplier: " + list);

        // f. Overloaded ArrayList constructor with initial capacity
        // f.i. We need a Function typed for Integer and List<String>
        Function<Integer, List<String>> listFunctionLambda = capacity -> new ArrayList<>(capacity);

        // f.iv. Re-initialise the list by invoking the lambda, passing in 10 as the capacity
        list = listFunctionLambda.apply(10);

        // f.v. Add "Lambda" to the list
        list.add("Lambda");

        // f.vi. Output the list to show it worked
        System.out.println("Using Lambda with initial capacity: " + list);

        // g. Method reference version for the overloaded constructor
        Function<Integer, List<String>> listFunctionMethodRef = ArrayList::new;

        // g.i. Re-initialise the list by invoking the method reference, passing in 10 as the capacity
        list = listFunctionMethodRef.apply(10);

        // g.ii. Add "Method Reference" to the list
        list.add("Method Reference");

        // g.iii. Output the list to show it worked
        System.out.println("Using Method Reference with initial capacity: " + list);
    }
}
