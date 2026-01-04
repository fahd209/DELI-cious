package com.pluralsight.services;

import com.pluralsight.application.Application;
import com.pluralsight.models.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileManager {
    public static void saveReceipt(Order order)
    {
        System.out.println("Creating a reciept for your order");
        String receiptDate = order.getOrderDate().replace(" ", "").replace(":", "-");
        File file = new File("receipts\\"+receiptDate+".txt");

        try(
                FileWriter fileWriter = new FileWriter(file);
                PrintWriter writer = new PrintWriter(fileWriter);
                )
        {
            ArrayList<Product> products = order.getOrder();
            writer.printf("Customer: %-10s\n", order.getCustomerName());
            writer.printf("Date: %-10s\n", order.getOrderDate());
            writer.printf("Order number: %d \n", order.getOrderNumber());
            writer.println("------------------------------------------");

            for (Product product : products)
            {
                if (product instanceof Sandwich)
                {
                    String toasted = ((Sandwich) product).isToasted() ? "Toasted" : "Not toasted";
                    writer.printf("%d inch %s bread, %s %s : $%.2f  \n", product.getSize(), ((Sandwich) product).getTypeOfBread(), product.getName(),toasted , ((Sandwich) product).getBreadPrice());
                    ArrayList<Toppings> toppings = ((Sandwich) product).getToppings();
                    for (Toppings topping : toppings)
                    {
                        writer.printf("%-25s: $%-10.2f\n", topping.getType(), topping.getPrice());
                    }
                }
                else if (product instanceof Drink)
                {
                    // getting drinks size
                    String size = Application.convertDrinkSize(product.getSize());
                    writer.printf("%s %-25s %-20.2f\n", size, product.getName(), product.getPrice());
                }
                else
                {
                    writer.printf("%-25s %-20.2f\n", product.getName(), product.getPrice());
                }
            }
            
            writer.printf("Total: $%.2f", order.getTotal());
            System.out.println("✅ Receipt saved for order #" + order.getOrderNumber());
        }
        catch (IOException e)
        {
        System.out.println("Error while saving receipt error= : {{{{ " + e.getMessage() + "}}}}");
        }
        catch (Exception e)
        {
            System.out.println("Sorry, something went wrong.");
        }
    }
}
