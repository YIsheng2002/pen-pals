package com.penpals.model;

import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private Date date;
    private boolean isDelivered;
    private double total;
    private Customer customer;
    private List<CartItem> cartItems;

    public Order(int id, Date date, boolean isDelivered, double total, Customer customer, List<CartItem> cartItems) {
        this.id = id;
        this.date = date;
        this.isDelivered = false;
        this.total = total;
        this.customer = customer;
        this.cartItems = cartItems;
    }

    public Order()
    {
        this.id = 0;
        this.date = new Date();
        this.isDelivered = false;
        this.total = 0.0;
        this.cartItems = null;
    }

    public int getOrderId() {
        return id;
    }

    public void setOrderId(int id) 
    {
        this.id = id;
    }

    public Date getOrderDate() {
        return date;
    }

    public void setOrderDate(Date date) 
    {
        this.date = date;
    }

    public boolean getOrderIsDelivered() {
        return isDelivered;
    }

    public void setOrderIsDelivered(boolean isDelivered) 
    {
        this.isDelivered = isDelivered;
    }

    public double getOrderTotal() {
        return total;
    }


    public void setOrderTotal(double total) 
    {
        this.total = total;
    }

    public Customer getOrderCustomer() {
        return customer;
    }

    public void setOrderCustomer(Customer customer) 
    {
        this.customer = customer;
    }

    public List<CartItem> getOrderCartItems() {
        return cartItems;
    }

    public void setOrderCartItems(List<CartItem> cartItems) 
    {
        this.cartItems = cartItems;
    }

    public void addCartItem(CartItem cartItem)
    {
        this.cartItems.add(cartItem);
    }

    public void removeCartItem(CartItem cartItem)
    {
        this.cartItems.remove(cartItem);
    }

    public void clearCartItems()
    {
        this.cartItems.clear();
    }
}
