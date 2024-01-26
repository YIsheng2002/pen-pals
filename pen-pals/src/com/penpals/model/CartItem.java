package com.penpals.model;

public class CartItem {
    private int quantity;
    private Product product;

    public CartItem(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public CartItem()
    {
        this.quantity = 0;
        this.product = null;
    }

    public int getCartItemQuantity() {
        return quantity;
    }

    public void setCartItemQuantity(int quantity) 
    {
        this.quantity = quantity;
    }

    public Product getCartItemProduct() {
        return product;
    }

    public void setCartItemProduct(Product product) 
    {
        this.product = product;
    }
}
