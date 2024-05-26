package com.pluralsight.models;

public class RegularToppings extends Toppings{
    public RegularToppings(String type) {
        super(type);
    }

    @Override
    public double getPrice() {
        return 0.00;
    }
}
