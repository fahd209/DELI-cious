package com.pluralsight.models;

import java.util.ArrayList;

public class Sandwich extends Product{
    String typeOfBread;
    boolean isToasted;
    ArrayList<Toppings> toppings = new ArrayList<>();
    double price;

    public Sandwich(String name, int size, String typeOfBread) {
        super(name, size);
        this.typeOfBread = typeOfBread;
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


    public void addTopping(Toppings topping)
    {
        toppings.add(topping);
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
        double toppingTotal = 0;
        for (Toppings topping : getToppings())
        {
            toppingTotal += topping.getPrice();
        }
        return toppingTotal + getBreadPrice();
    }
}