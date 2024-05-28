package com.pluralsight.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;

public class Order {
    String customerName;
    int orderNumber;
    String orderDate;

    ArrayList<Product> order = new ArrayList<>();
    public Order(String customerName, int orderNumber, String orderDate)
    {
        this.customerName = customerName;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String dateTime) {
        this.orderDate = dateTime;
    }

    public ArrayList<Product> getOrder() {
        return order;
    }

    public void setOrder(ArrayList<Product> order) {
        this.order = order;
    }

    public void addProduct(Product product)
    {
        order.add(product);
    }
}
