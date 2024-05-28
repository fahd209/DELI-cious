package com.pluralsight.application;

import com.pluralsight.models.*;
import com.pluralsight.ui.Ui;

import java.util.ArrayList;

public class Application {
    Ui ui = new Ui();

    public void run() {
        manageHomeScreen();
    }

    public void manageHomeScreen() {

        int choice = 2;
        while (choice != 0) {
            choice = ui.displayHomeScreen();
            switch (choice) {
                case 1:
                    makeNewOrder();
                    break;
                case 0:
                    System.out.println();
                    System.out.println("Good bye :)");
                    break;
            }
        }
    }


    private void makeNewOrder() {
        Order newOrder = ui.getOrderInformation();
        int choice = 6;
        while(choice != 0) {
            choice = ui.getNewOrder();
            switch (choice) {
                case 1:
                    addSandWish(newOrder);
                    break;
                case 2:
                    System.out.println("adding drink");
                    break;
                case 3:
                    System.out.println("adding chips");
                    break;
                case 4:
                    System.out.println("Confirming order");
                    break;
                case 0:
                    System.out.println("Canceling order");
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
    }

    public void addSandWish(Order order) {

        // getting type of bread and sandwich size
        String typeOfBread = ui.getTypeOfBread();
        int sandWishSize = ui.getSandWishSize();

        // getting type of meat from ui
        String[] meat = ui.getSandWishMeat();
        Sandwich sandwish = new Sandwich(meat[0] + "Sandwich", sandWishSize, typeOfBread);
        addMeat(sandwish, meat);

        // getting sandwich Cheese from ui
        String[] cheese =  ui.getSandWishCheese();
        addCheese(sandwish, cheese);

        //getting regular topping from ui
        String[] regularToppings = ui.getRegularTopping();
        addRegularTopping(sandwish,regularToppings);

        //getting sauces for ui
        String[] sauces = ui.getSauces();
        addToppings(sandwish, sauces);

        // checking if the sandwich is toasted from ui
        sandwish.setToasted(ui.isSandwichToasted());

        ArrayList<Toppings> toppings = sandwish.getToppings();

        for (Toppings topping : toppings)
        {
            System.out.printf(" %s - %.2f \n", topping.getType(), topping.getPrice());
        }
    }

    public void addMeat(Sandwich sandwish, String[] meat)
    {
        // checking topping are extra and adding them to the sandwich
        for (int i = 0; i < meat.length; i++)
        {
            Toppings meatTopping;
            if (i < 1)
            {
                meatTopping = new PremiumTopping(meat[i], sandwish.getSize(), true, false, false, false);
            }
            else {
                meatTopping = new PremiumTopping(meat[i], sandwish.getSize(), false, false, false, true);
            }
            sandwish.addTopping(meatTopping);
        }
    }

    public void addCheese(Sandwich sandwich, String[] cheese)
    {
        // checking if she is extra and adding it to the sandwich
        for (int i = 0; i < cheese.length; i++)
        {
            Toppings meatTopping;
            if (i < 1)
            {
                meatTopping = new PremiumTopping(cheese[i], sandwich.getSize(), false, true, false, false);
            }
            else {
                meatTopping = new PremiumTopping(cheese[i], sandwich.getSize(), false, false, true, false);
            }
            sandwich.addTopping(meatTopping);
        }
    }

    public void addRegularTopping(Sandwich sandwish, String[] regularToppings)
    {
        for (int i = 0; i < regularToppings.length; i++)
        {
            Toppings toppings = new RegularToppings(regularToppings[i]);
            sandwish.addTopping(toppings);
        }
    }

    public void addToppings(Sandwich sandwish, String[] sauces)
    {
        for (int i = 0; i < sauces.length; i++)
        {
            Toppings toppings = new RegularToppings(sauces[i]);
            sandwish.addTopping(toppings);
        }
    }
}

