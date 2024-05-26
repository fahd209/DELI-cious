package com.pluralsight.models;

public abstract class Toppings {
    String type;

    public Toppings(String type)
    {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice()
    {

        return 0;
    }
}
