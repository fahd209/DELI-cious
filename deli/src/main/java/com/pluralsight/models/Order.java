package com.pluralsight.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;

public class Order {
    String customerName;
    int orderNumber;
    LocalDateTime orderDate;

    ArrayList<Product> order = new ArrayList<>();
    public Order(String customerName, int orderNumber, LocalDateTime orderDate)
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

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime dateTime) {
        this.orderDate = dateTime;
    }

    public ArrayList<Product> getOrder() {
        return order;
    }

    public void setOrder(ArrayList<Product> order) {
        this.order = order;
    }
}
