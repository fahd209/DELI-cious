package com.pluralsight.models;

public class Chips extends Product{
    String typeOfChips;
    public Chips(String name, int size, String typeOfSandWish) {
        super(name, size);
        this.typeOfChips = typeOfChips;
    }

    @Override
    public double getPrice()
    {
        return 1.50;
    }
}
