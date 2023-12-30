package com.penpals.model;

public class Customer {
    private String name;
    private String telNumber;
    private String email;
    private String password;
    private Address address;
    private int id;
    private ShoppingCart shoppingCart;

    public Customer(String name, String telNumber, String email, String password, Address address, int id, ShoppingCart shoppingCart) {
        this.name = name;
        this.telNumber = telNumber;
        this.email = email;
        this.password = password;
        this.address = address;
        this.id = id;
        this.shoppingCart = shoppingCart;
    }

    public int getCustomerId() {
        return id;
    }

    public void setCustomerId(int id) 
    {
        this.id = id;
    }

    public String getCustomerName() {
        return name;
    }

    public void setCustomerName(String name) 
    {
        this.name = name;
    }

    public String getCustomerTelNumber() {
        return telNumber;
    }

    public void setCustomerTelNumber(String telNumber) 
    {
        this.telNumber = telNumber;
    }

    public String getCustomerEmail() {
        return email;
    }

    public void setCustomerEmail(String email) 
    {
        this.email = email;
    }

    public String getCustomerPassword() {
        return password;
    }

    public void setCustomerPassword(String password) 
    {
        this.password = password;
    }


    public Address getCustomerAddress() {
        return address;
    }


    public void setCustomerAddress(Address address) 
    {
        this.address = address;
    }

    public ShoppingCart getCustomerShoppingCart() {
        return shoppingCart;
    }

    public void setCustomerShoppingCart(ShoppingCart shoppingCart) 
    {
        this.shoppingCart = shoppingCart;
    }
}
