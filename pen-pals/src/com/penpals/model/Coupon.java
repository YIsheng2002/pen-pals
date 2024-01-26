package com.penpals.model;

public class Coupon extends Discount{
    private String code;
    private int quantity;
    private String expirationDate;
    private double minSpent;

    public Coupon(int id, double discountPercentage, String description,String code, int quantity, String expirationDate, double minSpent) {
        super(id, discountPercentage, description);        
        this.code = code;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.minSpent = minSpent;
    }

    public Coupon()
    {
        super();
        this.code = "";
        this.expirationDate = "";
        this.minSpent = 0.0;
    }

    public String getCouponCode() {
        return code;
    }

    public void setCouponCode(String code) 
    {
        this.code = code;
    }

    public int getCouponQuantity() {
        return quantity;
    }

    public void setCouponQuantity(int quantity) 
    {
        this.quantity = quantity;
    }

    public String getCouponExpirationDate() {
        return expirationDate;
    }

    public void setCouponExpirationDate(String expirationDate) 
    {
        this.expirationDate = expirationDate;
    }

    public double getCouponMinSpent() {
        return minSpent;
    }

    public void setCouponMinSpent(double minSpent) 
    {
        this.minSpent = minSpent;
    }
}
