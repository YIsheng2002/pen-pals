package com.penpals.model;

public class Coupon extends Discount{
    private String code;
    private String expirationDate;
    private double minSpent;

    public Coupon(int id, double discountPercentage, String description,String code, String expirationDate, double minSpent) {
        super(id, discountPercentage, description);        
        this.code = code;
        this.expirationDate = expirationDate;
        this.minSpent = minSpent;
    }

    public String getCouponCode() {
        return code;
    }

    public void setCouponCode(String code) 
    {
        this.code = code;
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
