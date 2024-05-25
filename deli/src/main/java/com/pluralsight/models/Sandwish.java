package com.pluralsight.models;

import java.util.ArrayList;

public class Sandwish extends Product{
    String typeOfBread;
    boolean isToasted;
    ArrayList<Toppings> toppings = new ArrayList<>();
    double price;

    public Sandwish(String name, int size, String typeOfBread) {
        super(name, size);
    }

    public double getBreadPrice()
    {
        double breadPrice = 0;
        switch (getSize())
        {
            case 4:
                breadPrice += 5.50;
                break;
            case 8:
                breadPrice += 7.00;
                break;
            case 12:
                breadPrice += 8.50;
                break;
        }

        return breadPrice;
    }

    @Override
    public double getPrice()
    {
        return 0;
    }
}