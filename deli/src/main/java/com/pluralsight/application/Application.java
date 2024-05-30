package com.pluralsight.application;

import com.pluralsight.models.*;
import com.pluralsight.services.FileManager;
import com.pluralsight.ui.Colors;
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
                    addDrink(newOrder);
                    break;
                case 3:
                    addChips(newOrder);
                    break;
                case 4:
                    signatureSandwich(newOrder);
                    break;
                case 5:
                    checkOut(newOrder);
                    return;
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
        Sandwich sandwich = new Sandwich(meat[0] + " Sandwich", sandWishSize, typeOfBread);
        addMeat(sandwich, meat);

        // getting sandwich Cheese from ui
        String[] cheese =  ui.getSandWishCheese();
        addCheese(sandwich, cheese);

        //getting regular topping from ui
        String[] regularToppings = ui.getRegularTopping();
        addRegularTopping(sandwich,regularToppings);

        //getting sauces for ui
        String[] sauces = ui.getSauces();
        addSauces(sandwich, sauces);

        // checking if the sandwich is toasted from ui
        sandwich.setToasted(ui.isSandwichToasted());
        order.addProduct(sandwich);

    }

    public void addMeat(Sandwich sandwich, String[] meat)
    {
        // checking topping are extra and adding them to the sandwich
        for (int i = 0; i < meat.length; i++)
        {
            Toppings meatTopping;
            if (i < 1)
            {
                meatTopping = new PremiumTopping(meat[i], sandwich.getSize(), true, false, false, false);
            }
            else {
                meatTopping = new PremiumTopping("Extra "+meat[i], sandwich.getSize(), false, false, false, true);
            }
            sandwich.addTopping(meatTopping);
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
                meatTopping = new PremiumTopping(cheese[i]+" cheese", sandwich.getSize(), false, true, false, false);
            }
            else {
                meatTopping = new PremiumTopping("Extra "+cheese[i]+" cheese", sandwich.getSize(), false, false, true, false);
            }
            sandwich.addTopping(meatTopping);
        }
    }

    public void addRegularTopping(Sandwich sandwich, String[] regularToppings)
    {
        // adding regular toppings
        for (int i = 0; i < regularToppings.length; i++)
        {
            Toppings toppings = new RegularToppings(regularToppings[i]);
            sandwich.addTopping(toppings);
        }
    }

    public void addSauces(Sandwich sandwich, String[] sauces)
    {
        // adding sauces to sandwich
        for (int i = 0; i < sauces.length; i++)
        {
            Toppings toppings = new RegularToppings(sauces[i]);
            sandwich.addTopping(toppings);
        }
    }

    public void addDrink(Order order)
    {
        // adding drink
        int size = ui.getDrinkSize();
        String typeOfDrink = ui.getDrinkType();

        Product drink = new Drink(typeOfDrink, size);
        order.addProduct(drink);
    }

    public void addChips(Order order)
    {
        // adding chips
        String typeOfChips = ui.getChipsType();
        Product chips = new Chips(typeOfChips, 0);
        order.addProduct(chips);
    }

    public void checkOut(Order order)
    {
        // printing receipt and prompting user to confirm or cancel
        printReceipt(order);
        int choice = ui.checkOut();
        switch (choice)
        {
            case 1:
                confirmOrder(order);
                break;
            case 2:
                cancelOrder(order);
                break;
        }
    }

    public void printReceipt(Order order)
    {
        ArrayList<Product> products = order.getOrder();
        System.out.println();
        System.out.println(Colors.YELLOW + "***Bay Area's Deli***"+Colors.RESET);
        System.out.println("-----------------------------------------");
        System.out.printf("Customer: %-10s\n", order.getCustomerName());
        System.out.printf("Date: %-10s\n", order.getOrderDate());
        System.out.printf("Order number: %d \n", order.getOrderNumber());
        System.out.println("------------------------------------------");

        // looping through all the products
        for (Product product : products)
        {
            if (product instanceof Sandwich)
            {
                String toasted = ((Sandwich) product).isToasted() ? "Toasted" : "Not toasted";
                System.out.printf("%d inch %s bread, %s %s : $%.2f  \n", product.getSize(), ((Sandwich) product).getTypeOfBread(), product.getName(),toasted , ((Sandwich) product).getBreadPrice());
                ArrayList<Toppings> toppings = ((Sandwich) product).getToppings();
                for (Toppings topping : toppings)
                {
                    System.out.printf("%-25s: $%-10.2f\n", topping.getType(), topping.getPrice());
                }
            }
            else if (product instanceof Drink)
            {
                // getting drinks size
                String size = convertDrinkSize(product.getSize());

                System.out.printf("%s %-25s %-20.2f\n", size, product.getName(), product.getPrice());
            }
            else
            {
                System.out.printf("%-25s %-20.2f\n", product.getName(), product.getPrice());
            }
        }
        System.out.printf("Total: $%.2f", order.getTotal());
        System.out.println();
    }

    public void confirmOrder(Order order)
    {
        // saving order to file
        System.out.println();
        FileManager.saveReceipt(order);
        System.out.println("Order number " + order.getOrderNumber() + " has been conformed, Enjoy :)");
    }

    public void cancelOrder(Order order)
    {
        // canceling order
        System.out.println();
        System.out.println("Order has been canceled");
    }

    public static String convertDrinkSize(int size)
    {
        return switch (size)
        {
            case 4 -> "Small";
            case 8 -> "Medium";
            case 12 -> "Large";
            default -> "";
        };
    }

    public void signatureSandwich(Order order)
    {
        int choice = ui.getSignatureSandwich();
        switch (choice)
        {
            case 1:
                createBlt(order);
                break;
            case 2:
                createPhilly(order);
                break;
            case 0:
                break;
        }
    }

    private void createBlt(Order order)
    {
        // creating signature sandwich
        Sandwich Blt = new SignatureSandwich("BLT", 8, "White");
        String removeToppingOrNot = ui.printSandwichToppings(Blt.getToppings()); //<== array list topping to the function so i could get displayed

        if (removeToppingOrNot.equalsIgnoreCase("yes"))
        {
            ArrayList<Integer> toppingsToRemove = ui.removeToppings();
            for (int i = 0; i < toppingsToRemove.size(); i++)
            {
                for (int j = 0; j < Blt.getToppings().size(); j++)
                {
                    if (toppingsToRemove.get(i) == j)
                    {
                        Blt.getToppings().remove(Blt.getToppings().get(j));
                    }
                }
            }
        }

        // checking if the user wants extra toppings
        String addToppingsOrNot = ui.addToppingOrNot();
        if (addToppingsOrNot.equalsIgnoreCase("yes"))
        {
            // getting all the toppings
            String[] meat = ui.getSandWishMeat();
            String[] cheese = ui.getSandWishCheese();
            String[] regularToppings = ui.getRegularTopping();
            String[] sauces = ui.getSauces();
        }
        order.addProduct(Blt);
    }

    public void createPhilly(Order order)
    {
        Sandwich phillyCheeseSteak = new SignatureSandwich("Philly cheese steak", 8, "White");
        String removeToppingOrNot = ui.printSandwichToppings(phillyCheeseSteak.getToppings());
    }
}