package com.pluralsight.application;

import com.pluralsight.models.*;
import com.pluralsight.services.FileManager;
import com.pluralsight.ui.Colors;
import com.pluralsight.ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;

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
        Sandwich sandwich = new Sandwich("", sandWishSize, typeOfBread);

        // getting type of meat from ui
        String meatInput = ui.getSandWishMeat();
        if (!meatInput.equalsIgnoreCase("none")) {
            String[] meat = convertToppingInputToArray(meatInput);
            sandwich.setName(meat[0]+" sandwich");
            addMeat(sandwich, meat);
        }

        // getting sandwich Cheese from ui
        String cheeseInput =  ui.getSandWishCheese();
        if (!cheeseInput.equalsIgnoreCase("none")) {
            String[] cheese = convertToppingInputToArray(cheeseInput);
            addCheese(sandwich, cheese);
        }

        //getting regular topping from ui
        String regularToppingsInput = ui.getRegularTopping();
        if (!regularToppingsInput.equalsIgnoreCase("none"))
        {
            String[] regularToppings = convertToppingInputToArray(regularToppingsInput);
            addRegularTopping(sandwich,regularToppings);
        }


        //getting sauces for ui
        String saucesInput = ui.getSauces();
        if (!saucesInput.equalsIgnoreCase("none")) {
            String[] sauces = convertToppingInputToArray(saucesInput);
            addSauces(sandwich, sauces);
        }

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
                cancelOrder();
                break;
        }
    }

    public void printReceipt(Order order)
    {
        ArrayList<Product> products = order.getOrder();
        System.out.println();
        System.out.println(Colors.YELLOW + "  *********Bay Area's Deli*********  "+Colors.RESET);
        System.out.println(Colors.CYAN+"  *********(888)-888-8888*********  "+Colors.RESET);
        System.out.println("6700 Santa Rita Rd, Pleasanton, CA 94588");
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

                System.out.printf("%s %-25s: $%-20.2f\n", size, product.getName(), product.getPrice());
            }
            else
            {
                System.out.printf("%-25s: $%.2f\n", product.getName(), product.getPrice());
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
        System.out.println(Colors.GREEN + order.getOrderNumber() + " has been conformed, Enjoy :)"+Colors.RESET);
    }

    public void cancelOrder()
    {
        // canceling order
        System.out.println();
        System.out.println(Colors.RED+"Order has been canceled"+Colors.RESET);
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
        String removeToppingOrNot = ui.printSandwichToppings(Blt.getToppings()); //<== passing array list topping to the function, so I could get displayed
        ArrayList<Toppings> bltToppings = Blt.getToppings();

        // checking if user wants to remove any toppings
        while (removeToppingOrNot.equalsIgnoreCase("yes"))
        {
            // getting the toppings the user wants to remove
            String toppingName = ui.removeToppings();
            Toppings toppingToRemove = null;

            // looping through toppings
            for (Toppings topping : bltToppings)
            {
                // checking if user's topping picked is in the sandwich topping
                if (topping.getType().equalsIgnoreCase(toppingName))
                {
                    // removing topping
                    toppingToRemove = topping;
                    break;
                }
            }
            Blt.removeTopping(toppingToRemove);

            // asking user if they still want to remove more toppings to break out the loop
            removeToppingOrNot = ui.printSandwichToppings(Blt.getToppings());
        }

        // checking if the user wants extra toppings
        String addToppingsOrNot = ui.addToppingOrNot();
        if (addToppingsOrNot.equalsIgnoreCase("yes"))
        {
            String meatInput = ui.getSandWishMeat();
            if (!meatInput.equalsIgnoreCase("none"))
            {
                String[] meat = convertToppingInputToArray(meatInput);
                addMeatToSignatureSandwich(Blt, meat);
            }

            String cheeseInput = ui.getSandWishCheese();
            if (!cheeseInput.equalsIgnoreCase("none"))
            {
                String[] cheese = convertToppingInputToArray(cheeseInput);
                addCheeseToSignatureSandwich(Blt, cheese);
            }

            String regularToppingsInput = ui.getRegularTopping();
            if (!regularToppingsInput.equalsIgnoreCase("none"))
            {
                String[] regularToppings = convertToppingInputToArray(regularToppingsInput);
                addRegularToppingsToSignatureSandwich(Blt, regularToppings);
            }

            String saucesInput = ui.getSauces();
            if (saucesInput.equalsIgnoreCase("none"))
            {
                String[] sauces = convertToppingInputToArray(saucesInput);
                addRegularSaucesToSignatureSandwich(Blt, sauces);
            }
        }
        order.addProduct(Blt);
    }

    public void createPhilly(Order order)
    {
        Sandwich phillyCheeseSteak = new SignatureSandwich("Philly cheese steak", 8, "White");
        String removeToppingOrNot = ui.printSandwichToppings(phillyCheeseSteak.getToppings());
        ArrayList<Toppings> phillyCheeseSteakToppings = phillyCheeseSteak.getToppings();

        while (removeToppingOrNot.equalsIgnoreCase("yes")) {
            // getting the toppings the user wants to remove
            String toppingName = ui.removeToppings();
            Toppings toppingToRemove = null;

            // looping through toppings
            for (Toppings topping : phillyCheeseSteakToppings) {
                // checking if user's topping picked is in the sandwich topping
                if (topping.getType().equalsIgnoreCase(toppingName)) {
                    // removing topping
                    toppingToRemove = topping;
                    break;
                }
            }
            phillyCheeseSteak.removeTopping(toppingToRemove);

            // asking user if they still want to remove more toppings to break out the loop
            removeToppingOrNot = ui.printSandwichToppings(phillyCheeseSteak.getToppings());
        }
            // checking if the user wants extra toppings
            String addToppingsOrNot = ui.addToppingOrNot();
            if (addToppingsOrNot.equalsIgnoreCase("yes"))
            {
                // getting all the toppings
                String meatInput = ui.getSandWishMeat();
                if (!meatInput.equalsIgnoreCase("none"))
                {
                    String[] meat = convertToppingInputToArray(meatInput);
                    addMeatToSignatureSandwich(phillyCheeseSteak, meat);
                }

                String cheeseInput = ui.getSandWishCheese();
                if (!cheeseInput.equalsIgnoreCase("none"))
                {
                    String[] cheese = convertToppingInputToArray(cheeseInput);
                    addCheeseToSignatureSandwich(phillyCheeseSteak, cheese);
                }

                String regularToppingsInput = ui.getRegularTopping();
                if (!regularToppingsInput.equalsIgnoreCase("none"))
                {
                    String[] regularToppings = convertToppingInputToArray(regularToppingsInput);
                    addRegularToppingsToSignatureSandwich(phillyCheeseSteak, regularToppings);
                }

                String saucesInput = ui.getSauces();
                if (!saucesInput.equalsIgnoreCase("none"))
                {
                    String[] sauces = convertToppingInputToArray(saucesInput);
                    addRegularSaucesToSignatureSandwich(phillyCheeseSteak, sauces);
                }
            }
            order.addProduct(phillyCheeseSteak);

    }

    public void addMeatToSignatureSandwich(Sandwich sandwich, String[] meat)
    {
        // looping through the topping
        Arrays.stream(meat).forEach(m -> {
            // checking if sandwich already has meat
            boolean hasMeat = hasMeat(sandwich);
            // if it does then it will be extra
            if (hasMeat) {
                sandwich.addTopping(new PremiumTopping("Extra "+m, sandwich.getSize(), false, false, false, true));
            } else {
                sandwich.addTopping(new PremiumTopping(m, sandwich.getSize(), true, false, false, false));
            }
        });
    }

    public boolean hasMeat(Sandwich sandwich)
    {
        boolean hasMeat = false;
        ArrayList<Toppings> toppings = sandwich.getToppings();
        // looping through the sandwich topping
        for (Toppings topping : toppings)
        {
            // checking if it has meat
            if (topping instanceof PremiumTopping && ((PremiumTopping) topping).isMeat())
            {
                hasMeat = true;
                break;
            }
        }
        return hasMeat;
    }

    public void addCheeseToSignatureSandwich(Sandwich sandwich, String[] cheese)
    {
        Arrays.stream(cheese).forEach(c -> {
            // checking if sandwich already has cheese
            boolean hasCheese = hasCheese(sandwich);
            // if it does then it will be extra
            if (hasCheese) {
                sandwich.addTopping(new PremiumTopping("Extra "+c, sandwich.getSize(), false, false, true, false));
            } else {
                sandwich.addTopping(new PremiumTopping(c, sandwich.getSize(), false, true, false, false));
            }
        });
    }

    public boolean hasCheese(Sandwich sandwich)
    {
        boolean hasCheese = false;
        ArrayList<Toppings> toppings = sandwich.getToppings();
        // looping through the sandwich topping
        for (Toppings topping : toppings)
        {
            // checking if it has cheese
            if (topping instanceof PremiumTopping && ((PremiumTopping) topping).isCheese())
            {
                // setting has cheese to true
                hasCheese = true;
                break;
            }
        }
        return hasCheese;
    }

    public void addRegularToppingsToSignatureSandwich(Sandwich sandwich, String[] regularToppings)
    {
        for (int i = 0; i < regularToppings.length; i++)
        {
            Toppings topping = new RegularToppings(regularToppings[i]);
            sandwich.addTopping(topping);
        }
    }

    public void addRegularSaucesToSignatureSandwich(Sandwich sandwich, String[] sauces)
    {
        for (int i = 0; i < sauces.length; i++)
        {
            Toppings topping = new RegularToppings(sauces[i]);
            sandwich.addTopping(topping);
        }
    }

    public String[] convertToppingInputToArray(String toppings)
    {
        return toppings.split(",");
    }
}