package com.pluralsight.ui;

import com.pluralsight.models.Order;
import com.pluralsight.models.Toppings;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
                System.out.println(Colors.CYAN+"      Welcome to the bay Area's deli     "+Colors.RESET);
                System.out.println("*****************************************");
                System.out.println(Colors.CYAN+"[1]" + Colors.BLACK + " - New order"+ Colors.RESET);
                System.out.println(Colors.RED +"[0]"+Colors.BLACK +" - Exit"+Colors.RESET);
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
                message(Colors.RED+"Invalid input, please enter numbers only"+Colors.RESET);
            } catch (Exception e) {
                System.out.println();
                message(Colors.RED+"Invalid input, please try again"+Colors.RESET);
            }
        }
        return 0;
    }

    public Order getOrderInformation()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd - hh:mm:ss a");
        System.out.println();
        System.out.print("Enter your name: ");
        String name = userInput.nextLine();

        LocalDateTime currentDateTime = LocalDateTime.now();
        int orderNumber = (int) Math.round(Math.random() * 100);

        return new Order(name, orderNumber, currentDateTime.format(formatter));
    }

    public int getNewOrder()
    {
        int choice = Integer.MAX_VALUE;
        while (choice != 0) {
            try {
                System.out.println();
                System.out.println(Colors.CYAN+"[1]"+Colors.BLACK+" - Add sandwich"+Colors.RESET);
                System.out.println(Colors.CYAN+"[2]"+Colors.BLACK+" - Add drink"+Colors.RESET);
                System.out.println(Colors.CYAN+"[3]"+Colors.BLACK+" - Add Chips"+Colors.RESET);
                System.out.println(Colors.CYAN+"[4]"+Colors.BLACK+" - Signature sandwiches"+Colors.RESET);
                System.out.println(Colors.CYAN+"[5]"+Colors.BLACK+" - Check out"+Colors.RESET);
                System.out.println(Colors.RED+"[0]"+Colors.BLACK+" - Cancel order and go back to home screen"+Colors.RESET);
                System.out.print("Enter input:");
                choice = Integer.parseInt(userInput.nextLine().strip());

                switch (choice) {
                    case 1, 2, 3, 4, 5:
                        return choice;
                    case 0:
                        break;
                    default:
                        System.out.println();
                        message("Invalid input");
                }
            } catch (NumberFormatException e) {
                System.out.println();
                message(Colors.RED+"Invalid input, please enter numbers only"+Colors.RESET);
            } catch (Exception e) {
                System.out.println();
                message(Colors.RED+"Invalid input, please try again"+Colors.RESET);
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
            size = Integer.parseInt(userInput.nextLine().strip().replace("inch", ""));
        }
        catch (InputMismatchException | NumberFormatException e)
        {
            System.out.println();
            message(Colors.RED + "Invalid input, please enter your input in numbers only"+ Colors.RESET);
        }
        catch (Exception e)
        {
            System.out.println();
            message(Colors.RED +"Something went wrong, please try again"+Colors.RESET);
        }
        return size;
    }

    public String getSandWishMeat()
    {
        System.out.println();
        System.out.println(Colors.YELLOW+"Note: Any extra topping that's cheese or meat will be charged as extra"+Colors.RESET);
        System.out.println("Enter your topping in this format ->"+Colors.GREEN+" Meat:Turkey, Chicken"+Colors.RESET);
        System.out.println("(Turkey, chicken, roast beef, bacon, salami, steak, ham)");
        System.out.println("Enter "+Colors.RED+"'none'"+Colors.RESET+" if you don't want any of those toppings");
        System.out.print("Meat:");
        return userInput.nextLine().strip().replace(" ", "");
    }

    public String getSandWishCheese()
    {
        System.out.println();
        System.out.println("(American, provolone, swiss, cheddar)");
        System.out.println("Enter "+Colors.RED+"'none'"+Colors.RESET+" if you don't want any of those toppings");
        System.out.print("Cheese:");
        return userInput.nextLine().strip().replace(" ", "");
    }

    public String getRegularTopping()
    {
        System.out.println();
        System.out.println("(Lettuce, Tomatoes, Onions, jalapenos, cucumber, pickles, guacamole, mushrooms, peppers)");
        System.out.println("Enter "+Colors.RED+"'none'"+Colors.RESET+" if you don't want any of those toppings");
        System.out.print("Veggies:");
        return userInput.nextLine().strip().replace(" ", "");
    }

    public String getSauces()
    {
        System.out.println();
        System.out.println("(Mayo, mustard, ketchup, ranch, thousand island, vinaigrette)");
        System.out.println("Enter "+Colors.RED+"'none'"+Colors.RESET+" if you don't want any of those toppings");
        System.out.print("Sauces:");
        return userInput.nextLine().strip().replace(" ", "");
    }

    public boolean isSandwichToasted()
    {
        System.out.println();
        System.out.println("Do you want to toast your sandwich? (Yes, no)");
        System.out.print("Enter input:");
        return userInput.nextLine().equalsIgnoreCase("yes");
    }

    public int getDrinkSize()
    {
            System.out.println();
            System.out.println("What size drink would you like? (S, M, L)");
            System.out.print("Enter input: ");
            String size = userInput.nextLine().strip();
            return switch (size.toLowerCase()) {
                case "s" -> 4;
                case "m" -> 8;
                case "l" -> 12;
                default -> throw new IllegalStateException("Unexpected value: " + size.toLowerCase());
            };
    }

    public String getDrinkType()
    {
        System.out.println();
        System.out.println("What flavor drink would you like? ");
        System.out.print("Enter input:");
        return userInput.nextLine().strip();
    }

    public String getChipsType()
    {
        System.out.println();
        System.out.println("What type of chips would you like? ");
        System.out.print("Enter input:");
        return userInput.nextLine().strip();
    }

    public int checkOut()
    {
        int choice = 0;
        try {
            System.out.println();
            System.out.println(Colors.GREEN+"[1]"+Colors.BLACK+" - Confirm order"+Colors.RESET);
            System.out.println(Colors.RED+"[2]"+Colors.BLACK+" - Cancel order"+Colors.RESET);
            System.out.print("Enter input: ");
            choice = Integer.parseInt(userInput.nextLine().strip());
        }
        catch (NumberFormatException e)
        {
            message(Colors.RED +"Invalid input, please enter numbers only"+ Colors.RESET);
        }
        catch (Exception e)
        {
            System.out.println(Colors.RED +"Invalid input, please try again"+ Colors.RESET);
        }
        return choice;
    }

    public int getSignatureSandwich()
    {
        int choice = Integer.MAX_VALUE;
        try {
            System.out.println();
            System.out.println(Colors.CYAN+"[1]"+Colors.BLACK+" - BLT"+Colors.RESET);
            System.out.println(Colors.CYAN+"[2]"+Colors.BLACK+" - Philly cheese steak"+Colors.RESET);
            System.out.println(Colors.CYAN+"[0]"+Colors.BLACK+" - Go Back to home screen"+Colors.RESET);
            System.out.print("Enter input:");
            choice = Integer.parseInt(userInput.nextLine());
        }
        catch (NumberFormatException e)
        {
            message(Colors.RED + " Invalid, input please enter numbers only."+ Colors.RESET);
        }
        catch (Exception e)
        {
            message(Colors.RED + "Invalid input, try again." + Colors.RESET);
        }
        return choice;
    }

    public String printSandwichToppings(ArrayList<Toppings> toppings)
    {
        for (int i = 0; i < toppings.size(); i++)
        {
            Toppings topping = toppings.get(i);
            System.out.println(i+". " + topping.getType());
        }
        System.out.println();
        System.out.println("Would you like to remove any toppings? (Yes, No)");
        System.out.print("Enter input: ");
        return userInput.nextLine();
    }

    public String removeToppings()
    {
        System.out.println();
        System.out.println("Enter the toppings name you would like to remove");
        System.out.print("Enter input:");
        return userInput.nextLine().strip();
    }

    public String addToppingOrNot()
    {
        System.out.println();
        System.out.println("Would you like to add toppings? (Yes, No)");
        System.out.print("Enter input: ");
        return userInput.nextLine();
    }
}
