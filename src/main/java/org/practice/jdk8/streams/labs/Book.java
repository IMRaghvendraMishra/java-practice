package org.practice.jdk8.streams.labs;

class Book {
    private String title;
    private double price;

    Book(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return title + " " + price;
    }
}