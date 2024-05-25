package com.pluralsight.models;

public class Drink extends Product{
    public Drink(String name, int size) {
        super(name, size);
    }

    @Override
    public double getPrice()
    {
        // Drink size 4: S, 8: M, 12: L
        return switch (getSize())
        {
            case 4 -> 2.00;
            case 8 -> 2.50;
            case 12 -> 3.00;
            default -> 0;
        };
    }
}
