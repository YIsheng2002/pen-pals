package com.penpals.model;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Promotion extends Discount{
    private String name;
    private Date startDate;
    private Date endDate;
    private List<Product> products;

    public Promotion(int id, double discountPercentage, String description,String name, Date startDate, Date endDate, List<Product> products) {
        super(id, discountPercentage, description);
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.products = products;
    }

    public Promotion()
    {
        this.name = "";
        this.startDate = new Date();
        this.endDate = new Date();
        this.products = new ArrayList<Product>();
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

    public List<Product> getPromotionProducts() {
        return products;
    }

    public void setPromotionProducts(List<Product> products) 
    {
        this.products = products;
    }
}
