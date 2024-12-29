package org.practice.lambdas.lab1;

class Person {
    private String name;
    private Integer age;
    private Double height;

    Person(String name, Integer age, Double height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "name: " + getName() + ", age: " + getAge()
                + ", height: " + getHeight();
    }
}
