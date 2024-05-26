package com.pluralsight.models;

public class Drink extends Product{
    String drinkType;
    public Drink(String name, int size, String drinkType) {
        super(name, size);
        this.drinkType = drinkType;
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
            default -> 0.00;
        };
    }
}
