package com.penpals.model;

import java.util.Date;

public class Promotion extends Discount{
    private String name;
    private Date startDate;
    private Date endDate;
    private Product product;

    public Promotion(int id, double discountPercentage, String description,String name, Date startDate, Date endDate, Product product) {
        super(id, discountPercentage, description);
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.product = product;
    }

    public Promotion()
    {
        this.name = "";
        this.startDate = new Date();
        this.endDate = new Date();
        this.product = new Product();
    }

    public String getPromotionName() {
        return name;
    }

    public void setPromotionName(String name) 
    {
        this.name = name;
    }

    public Date getPromotionStartDate() {
        return startDate;
    }

    public void setPromotionStartDate(Date startDate) 
    {
        this.startDate = startDate;
    }

    public Date getPromotionEndDate() {
        return endDate;
    }

    public void setPromotionEndDate(Date endDate) 
    {
        this.endDate = endDate;
    }

    public Product getPromotionProduct() {
        return product;
    }

    public void setPromotionProduct(Product product) 
    {
        this.product = product;
    }
}
