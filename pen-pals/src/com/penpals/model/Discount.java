package com.penpals.model;

public class Discount {
    private int id;
    private double discountPercentage;
    private String description;
    
    public Discount(int id, double discountPercentage, String description) {
        this.id = id;
        this.discountPercentage = discountPercentage;
        this.description = description;
    }

    public Discount()
    {
        this.id = 0;
        this.discountPercentage = 0.0;
        this.description = "";
    }

    public int getDiscountId() {
        return id;
    }

    public void setDiscountId(int id) 
    {
        this.id = id;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) 
    {
        this.discountPercentage = discountPercentage;
    }

    public String getDiscountDescription() {
        return description;
    }

    public void setDiscountDescription(String description) 
    {
        this.description = description;
    }
}
