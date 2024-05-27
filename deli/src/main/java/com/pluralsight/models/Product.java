package com.pluralsight.models;

public abstract class Product {
    String name;
    int size;

    public Product(String name, int size)
    {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getPrice()
    {
        return 0;
    }
}
