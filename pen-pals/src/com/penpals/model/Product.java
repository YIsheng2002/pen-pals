package com.penpals.model;

public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;
    private ProductCategory category;
    private String imageURL;
    private boolean hasPromotion;

    public Product(int id, String name, String description, double price, int stockQuantity, ProductCategory category, String imageURL, boolean hasPromotion) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
        this.imageURL = imageURL;
        this.hasPromotion = hasPromotion;
    }

    public Product()
    {
        this.id = 0;
        this.name = "";
        this.description = "";
        this.price = 0.0;
        this.stockQuantity = 0;
        this.category = new ProductCategory();
        this.imageURL = "";
        this.hasPromotion = false;
    }

    public int getProductId() {
        return id;
    }

    public void setProductId(int id) 
    {
        this.id = id;
    }

    public String getProductName() {
        return name;
    }

    public void setProductName(String name) 
    {
        this.name = name;
    }

    public String getProductDescription() {
        return description;
    }

    public void setProductDescription(String description) 
    {
        this.description = description;
    }

    public double getProductPrice() {
        return price;
    }

    public void setProductPrice(double price) 
    {
        this.price = price;
    }

    public int getProductStockQuantity() {
        return stockQuantity;
    }

    public void setProductStockQuantity(int stockQuantity) 
    {
        this.stockQuantity = stockQuantity;
    }

    public ProductCategory getProductCategory() {
        return category;
    }

    public void setProductCategory(ProductCategory category) 
    {
        this.category = category;
    }

    public String getProductImageURL() {
        return imageURL;
    }

    public void setProductImageURL(String imageURL) 
    {
        this.imageURL = imageURL;
    }

    public boolean getProductHasPromotion() {
        return hasPromotion;
    }

    public void setProductHasPromotion(boolean hasPromotion) 
    {
        this.hasPromotion = hasPromotion;
    }
}
