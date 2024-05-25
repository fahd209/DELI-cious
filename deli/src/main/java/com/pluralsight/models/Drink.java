package com.pluralsight.models;

public class Drink extends Product{
    public Drink(String name, int size) {
        super(name, size);
    }

    @Override
    public double getPrice()
    {
        return 0;
    }
}
