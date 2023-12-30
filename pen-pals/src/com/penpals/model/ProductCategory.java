package com.penpals.model;

public class ProductCategory {
    private int id;
    private String name;

    public ProductCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProductCategory()
    {
        this.id = 0;
        this.name = "";
    }

    public int getProductCategoryId() {
        return id;
    }

    public void setProductCategoryId(int id) 
    {
        this.id = id;
    }

    public String getProductCategoryName() {
        return name;
    }

    public void setProductCategoryName(String name) 
    {
        this.name = name;
    }
}
