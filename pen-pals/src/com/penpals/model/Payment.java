package com.penpals.model;

import java.sql.Date;

public class Payment {
    private int id;
    private double amount;
    private Date date;
    private String paymentMethod;

    public Payment(int id, double amount, Date date, String paymentMethod) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.paymentMethod = paymentMethod;
    }

    public Payment()
    {
        this.id = 0;
        this.amount = 0.0;
        this.paymentMethod = "";
    }

    public int getPaymentId() {
        return id;
    }

    public void setPaymentId(int id) 
    {
        this.id = id;
    }

    public double getPaymentAmount() {
        return amount;
    }

    public void setPaymentAmount(double amount) 
    {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return date;
    }

    public void setPaymentDate(Date date) 
    {
        this.date = date;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) 
    {
        this.paymentMethod = paymentMethod;
    }
}
