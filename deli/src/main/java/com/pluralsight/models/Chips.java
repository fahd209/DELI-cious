package com.pluralsight.models;

public class Chips extends Product{
    String typeOfChips;
    public Chips(String name, int size) {
        super(name, size);
    }

    @Override
    public double getPrice()
    {
        return 1.50;
    }
}
