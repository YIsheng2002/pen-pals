package com.penpals.model;

import java.util.List;

public class ShoppingCart {
    private List<CartItem> cartItems;

    public ShoppingCart(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public ShoppingCart()
    {
        this.cartItems = null;
    }

    public List<CartItem> getShoppingCartItems() {
        return cartItems;
    }

    public void setShoppingCartItems(List<CartItem> cartItems) 
    {
        this.cartItems = cartItems;
    }
}
