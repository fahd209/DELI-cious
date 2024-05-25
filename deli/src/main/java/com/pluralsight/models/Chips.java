package com.pluralsight.models;

public class Chips extends Product{
    public Chips(String name, int size) {
        super(name, size);
    }

    @Override
    public double getPrice()
    {
        return 0;
    }
}
