package com.penpals.model;

import java.util.List;

public class ShoppingCart {
    private int id;
    private List<CartItem> cartItems;

    public ShoppingCart(int id,List<CartItem> cartItems) {
        this.id = id;
        this.cartItems = cartItems;
    }

    public ShoppingCart()
    {
        this.id = 0;
        this.cartItems = null;
    }

    public List<CartItem> getShoppingCartItems() {
        return cartItems;
    }

    public void setShoppingCartItems(List<CartItem> cartItems) 
    {
        this.cartItems = cartItems;
    }

    public int getShoppingCartId() {
        return id;
    }

    public void setShoppingCartId(int id) 
    {
        this.id = id;
    }
}
