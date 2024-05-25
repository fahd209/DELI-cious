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

    public String getTypeOfBread() {
        return typeOfBread;
    }

    public void setTypeOfBread(String typeOfBread) {
        this.typeOfBread = typeOfBread;
    }

    public boolean isToasted() {
        return isToasted;
    }

    public void setToasted(boolean toasted) {
        isToasted = toasted;
    }

    public ArrayList<Toppings> getToppings() {
        return toppings;
    }

    public void setToppings(ArrayList<Toppings> toppings) {
        this.toppings = toppings;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public double getMeatPrice()
    {
        double meatPrice = 0;
        switch (getSize())
        {
            case 4:
                meatPrice += 1.00;
                break;
            case 8:
                meatPrice += 2.00;
                break;
            case 12:
                meatPrice += 3.00;
                break;
        }

        return meatPrice;
    }

    @Override
    public double getPrice()
    {
        return 0;
    }
}