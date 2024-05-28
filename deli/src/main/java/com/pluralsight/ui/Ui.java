package com.pluralsight.ui;

import com.pluralsight.models.Order;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ui {
    Scanner userInput = new Scanner(System.in);

    public void message(String message)
    {
        System.out.println(message);
    }

    public int displayHomeScreen() {
        int choice = 2;
        while (choice != 0) {
            try {
                System.out.println();
                System.out.println("      Welcome to the bay Area's deli     ");
                System.out.println("*****************************************");
                System.out.println("[1] - New order");
                System.out.println("[0] - Exit");
                System.out.print("Enter input:");
                choice = Integer.parseInt(userInput.nextLine().strip());
                switch (choice) {
                    case 1:
                        return choice;
                    case 0:
                        break;
                    default:
                        System.out.println();
                        message("Invalid input");
                }
            } catch (NumberFormatException e) {
                System.out.println();
                message("Invalid input, please enter numbers only");
            } catch (Exception e) {
                System.out.println();
                message("Invalid input, please try again");
            }
        }
        return 0;
    }

    public Order getOrderInformation()
    {
        System.out.println();
        System.out.print("Enter your name: ");
        String name = userInput.nextLine();

        LocalDateTime currentDateTime = LocalDateTime.now();
        int orderNumber = (int) Math.round(Math.random() * 100);

        return new Order(name, orderNumber, currentDateTime);
    }

    public int getNewOrder()
    {
        int choice = Integer.MAX_VALUE;
        while (choice != 0) {
            try {
                System.out.println();
                System.out.println("[1] - Add sandwich");
                System.out.println("[2] - Add drink");
                System.out.println("[3] - Add Chips");
                System.out.println("[4] - Check out");
                System.out.println("[0] - Cancel order");
                System.out.print("Enter input:");
                choice = Integer.parseInt(userInput.nextLine().strip());

                switch (choice) {
                    case 1, 2, 3, 4:
                        return choice;
                    case 0:
                        break;
                    default:
                        System.out.println();
                        message("Invalid input");
                }
            } catch (NumberFormatException e) {
                System.out.println();
                message("Invalid input, please enter numbers only");
            } catch (Exception e) {
                System.out.println();
                message("Invalid input, please try again");
            }
        }
        return 0;
    }

    public String getTypeOfBread()
    {
        System.out.println();
        System.out.println("What type of bread would you like? (white, wheat, rye, wrap) ");
        System.out.print("Enter input: ");
        return userInput.nextLine();
    }

    public int getSandWishSize()
    {
        int size = 0;
        try {
            System.out.println();
            System.out.println("what sandwich size would you like? (4inch, 8inch, 12inch) ");
            System.out.print("Enter input: ");
            size = Integer.parseInt(userInput.nextLine().strip());
        }
        catch (InputMismatchException | NumberFormatException e)
        {
            System.out.println();
            message("Invalid input, please enter your input in numbers only");
        }
        catch (Exception e)
        {
            System.out.println();
            message("Something went wrong, please try again");
        }
        return size;
    }

    public String[] getSandWishMeat()
    {
        System.out.println();
        System.out.println("Note: Any extra topping that's cheese or meat will be charged as extra");
        System.out.println("Enter your topping in this format -> Meat:Turkey, Chicken");
        System.out.println("(Turkey, chicken, roast beef, bacon, salami, steak, ham)");
        System.out.print("Meat:");
        return userInput.nextLine().strip().replace(" ", "").split(",");
    }

    public String[] getSandWishCheese()
    {
        System.out.println();
        System.out.println("(American, provolone, swiss, cheddar)");
        System.out.print("Cheese:");
        return userInput.nextLine().strip().replace(" ", "").split(",");
    }

    public String[] getRegularTopping()
    {
        System.out.println();
        System.out.println("(Lettuce, Tomatoes, Onions, jalapenos, cucumber, pickles, guacamole, mushrooms, peppers)");
        System.out.print("Veggies:");
        return userInput.nextLine().strip().replace(" ", "").split(",");
    }

    public String[] getSauces()
    {
        System.out.println();
        System.out.println("(Mayo, mustard, ketchup, ranch, thousand island, vinaigrette)");
        System.out.print("Sauces:");
        return userInput.nextLine().strip().replace(" ", "").split(",");
    }
}