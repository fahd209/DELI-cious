package com.pluralsight.models;

import java.util.ArrayList;

public class SignatureSandwich extends Sandwich{
    ArrayList<Toppings> toppings = new ArrayList<>();
    public SignatureSandwich(String name, int size, String typeOfBread) {
        super(name, size, typeOfBread);
        this.toppings = loadToppings();
    }

    public ArrayList<Toppings> BltToppings()
    {
        ArrayList<Toppings> toppings = new ArrayList<>();
        toppings.add(new PremiumTopping("Bacon", getSize(), true, false, false, false));
        toppings.add(new PremiumTopping("Cheddar cheese", getSize(), false, true, false, false));
        toppings.add(new RegularToppings("Lettuce"));
        toppings.add(new RegularToppings("Tomato"));
        toppings.add(new RegularToppings("ranch"));
        return toppings;
    }

    public ArrayList<Toppings> phillyToppings()
    {
        ArrayList<Toppings> toppings = new ArrayList<>();
        toppings.add(new PremiumTopping("Steak", getSize(), true, false, false, false));
        toppings.add(new PremiumTopping("American cheese", getSize(), false, true, false, false));
        toppings.add(new RegularToppings("Peppers"));
        toppings.add(new RegularToppings("Mayo"));
        return toppings;
    }

    public ArrayList<Toppings> loadToppings()
    {
        if (getName().equals("BLT"))
        {
            return BltToppings();
        }
        else
        {
            return phillyToppings();
        }
    }

    @Override
    public ArrayList<Toppings> getToppings() {
        return toppings;
    }

    @Override
    public void setToppings(ArrayList<Toppings> toppings) {
        this.toppings = toppings;
    }

    public void addTopping(Toppings topping)
    {
        toppings.add(topping);
    }

    public void removeTopping(Toppings topping)
    {
        toppings.remove(topping);
    }
}
