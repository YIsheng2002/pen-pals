package com.penpals.controller;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import com.penpals.model.*;

public class ShoppingCartController extends Controller{
    public void connectToDatabase()
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/penpalsoop", "root", "");
        } catch (ClassNotFoundException | SQLException err)
        {
            System.out.println(err.getMessage());
        }
    }

    public ShoppingCart getShoppingCartDetailbyCustomerId(int cusId)
    {
        ShoppingCart shoppingCart = new ShoppingCart();
        try{
            String query = "SELECT * FROM cart WHERE customer_id_fk = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, cusId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                shoppingCart.setShoppingCartId(resultSet.getInt("id"));
            }
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }
        return shoppingCart;
    }

   /*  public List <CartItem> getCartAllItem(){
        List <CartItem> cartItems = new ArrayList<CartItem>();
        try{
            String query = "SELECT * FROM cart_item";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next())
            {
                CartItem cartItem = new CartItem();
                //cartItem.setProduct());
                cartItem.setQuantity(resultSet.getInt("quantity"));
                cartItems.add(cartItem);
            }
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }
        return cartItems;
    }*/
}
