package org.practice.jdk8.lambdas.lab1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BasicLamdas {
    public static void main(String[] args) {
        BasicLamdas obj = new BasicLamdas();

        obj.consumer("Printable lambda");
//        obj.supplier();
//        obj.predicate();
//        obj.function();
//        obj.sortPersonList();

        List<Person> peopleList = getPeople();
//        obj.sortAge(peopleList);
//        obj.sortName(peopleList);
//        obj.sortHeight(peopleList);
    }

    void consumer(String str) {
        Printable<String> printableImpl = new Printable<String>() {
            @Override
            public void retrieve(String s) {
                System.out.println(s);
            }
        };
        Printable<String> printableLambda = System.out::println;

        printableImpl.retrieve(str);
        printableLambda.retrieve(str);

        // Using Consumer provided in Java Out of the box (OOTB)
        Consumer<String> consumerLambda = System.out::println;
        consumerLambda.accept(str);
    }

    void supplier() {
        Retrievable<Integer> retrievableImpl = new Retrievable<Integer>() {
            @Override
            public Integer retrieve() {
                return 77;
            }
        };
        Retrievable<Integer> retrievableLambda = () -> 77;

        System.out.println(retrievableImpl.retrieve());
        System.out.println(retrievableLambda.retrieve());

        // Supplier in Java OOTB
        Supplier<Integer> supplierLambda = () -> 77;
        System.out.println(supplierLambda.get());
    }

    void predicate() {
        Evaluate<Integer> evaluateImpl = new Evaluate<Integer>() {
            @Override
            public Boolean isNegative(Integer value) {
                if (value < 0) {
                    return true;
                }
                return false;
            }
        };
        Evaluate<Integer> evaluateLambda = value -> value < 0;

        System.out.println("evaluateImpl.isNegative(-1):: " + evaluateImpl.isNegative(-1)); // true
        System.out.println("evaluateImpl.isNegative(1):: " + evaluateImpl.isNegative(1)); // false
        System.out.println("evaluateLambda.isNegative(-1):: " + evaluateLambda.isNegative(-1)); // true
        System.out.println("evaluateLambda.isNegative(1):: " + evaluateLambda.isNegative(1)); // false

        Predicate<Integer> predicateLambda = value -> value < 0;
        System.out.println("predicateLambda.test(-1):: " + predicateLambda.test(-1)); // true
        System.out.println("predicateLambda.test(1):: " + predicateLambda.test(1)); // false

        // test generic check method
        Predicate<Integer> evenPredicate = num -> num % 2 == 0;
        CheckUtil<Integer> intCheckObj = new CheckUtil<>();
        System.out.println("evenPredicate.test(4):: " + intCheckObj.check(4, evenPredicate)); // even -> true
        System.out.println("evenPredicate.test(7):: " + intCheckObj.check(7, evenPredicate)); // odd -> false

        Predicate<String> startWithPredicate = str -> str.startsWith("Mr.");
        CheckUtil<String> strCheckObj = new CheckUtil<>();
        System.out.println("Mr. Joe Bloggs startsWith Mr.?:: " + strCheckObj.check("Mr. Joe Bloggs", startWithPredicate)); // even -> true
        System.out.println("Ms. Ann Bloggs startsWith Mr.?:: " + strCheckObj.check("Ms. Ann Bloggs", startWithPredicate)); // odd -> false

        // test generic check method with Persons

        Predicate<Person> adultPredicate = p -> p.getAge() > 18;
        CheckUtil<Person> personCheckUtil = new CheckUtil<>();
        Person person = new Person("Mike", 33, 1.8);
        System.out.println(person.getName() + " is adult?:: " + personCheckUtil.check(person, adultPredicate));

        person = new Person("Ann", 13, 1.4);
        System.out.println(person.getName() + " is adult?:: " + personCheckUtil.check(person, adultPredicate));
    }

    void function() {
        Functionable<Integer, String> functionableImpl = new Functionable<Integer, String>() {
            @Override
            public String applyThis(Integer value) {
                return "Number is: " + value;
            }
        };
        Functionable<Integer, String> functionableLambda = value -> "Number is: " + value;
        System.out.println(functionableImpl.applyThis(25));
        System.out.println(functionableLambda.applyThis(25));

        // Using Function<T, R> from OOTB
        Function<Integer, String> functionLambda = value -> "Number is: " + value;
        System.out.println(functionLambda.apply(25));
    }

    void sortName(List<Person> personList) {
        personList.sort(Comparator.comparing(Person::getName));
        System.out.println("After sorting list by name:");
        printPersonList(personList);
    }

    void sortAge(List<Person> personList) {
        personList.sort(Comparator.comparingInt(Person::getAge));
        System.out.println("After sorting list by age:");
        printPersonList(personList);
    }

    void sortHeight(List<Person> personList) {
        personList.sort(Comparator.comparing(Person::getHeight));
        System.out.println("After sorting list by height:");
        printPersonList(personList);
    }

    private static List<Person> getPeople() {
        List<Person> result = new ArrayList<>();
        result.add(new Person("Ram", 33, 1.8));
        result.add(new Person("Krishna", 25, 1.4));
        result.add(new Person("Ashok", 34, 1.7));
        result.add(new Person("Shiva", 30, 1.5));
        return result;
    }

    private static void printPersonList(List<Person> personList) {
//        personList.forEach(p -> System.out.println("name: " + p.getName() + ", age: " + p.getAge()
//                + ", height: " + p.getHeight()));
        personList.forEach(System.out::println);
    }

    class CheckUtil<T> {
        Boolean check(T t, Predicate<T> predicate) {
            return predicate.test(t);
        }
    }
}
