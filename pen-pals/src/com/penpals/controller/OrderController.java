package com.penpals.controller;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

import com.penpals.model.Order;
import com.penpals.model.CartItem;
import com.penpals.model.Product;

public class OrderController extends Controller{
    public void connectToDatabase(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/penpalsoop", "root", "");
        } catch (ClassNotFoundException | SQLException err){
            System.out.println(err.getMessage());
        }
    }

    public int insertOrder(int totalAmount, int cusId){
        
        try{
            String query = "INSERT INTO orderlist (total_amount, customer_id_fk, order_date) VALUES (?, ?, NOW());";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, totalAmount);
            ps.setInt(2, cusId);
            ps.executeUpdate();
            ResultSet rs = ps.executeQuery("SELECT LAST_INSERT_ID();");
            while(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException err){
            System.out.println(err.getMessage());
        }
        return 0;
    }

    public void insertOrderDetail(int orderId, int productId, int quantity){
        try{
            String query = "INSERT INTO order_item (orderlist_id_fk, product_id_fk, quantity) VALUES (?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, orderId);
            ps.setInt(2, productId);
            ps.setInt(3, quantity);
            ps.executeUpdate();
        } catch (SQLException err){
            System.out.println(err.getMessage());
        }
    }

    public List<Order> getAllOrdersbyCustomerId(int cusId){
        List<Order> orderList = new ArrayList<Order>();
        try{
            String query = "SELECT id, order_date, is_delivered, total_amount, isCompleted FROM orderlist WHERE customer_id_fk = ?;";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, cusId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Order order = new Order();
                order.setOrderId(rs.getInt("id"));
                order.setOrderDate(rs.getString("order_date"));
                order.setOrderIsDelivered(rs.getBoolean("is_delivered"));
                order.setOrderTotal(rs.getInt("total_amount"));
                order.setIsCompleted(rs.getBoolean("isCompleted"));
                orderList.add(order);
            }
        } catch (SQLException err){
            System.out.println(err.getMessage());
        }

        return orderList;
    }

    public List<CartItem> getAllItembyOrderId(int orderId){
        List<CartItem> cartItems = new ArrayList<CartItem>();
        try{
            String query = "SELECT p.id, p.name, p.price, p.description, p.image_url, p.stock, oi.quantity FROM order_item oi JOIN product p ON (oi.product_id_fk = p.id) WHERE oi.orderlist_id_fk = ?;";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                CartItem cartItem = new CartItem();
                Product product = new Product();
                product.setProductId(rs.getInt("p.id"));
                product.setProductName(rs.getString("p.name"));
                product.setProductPrice(rs.getInt("p.price"));
                product.setProductDescription(rs.getString("p.description"));
                product.setProductImageURL(rs.getString("p.image_url"));
                product.setProductStockQuantity(rs.getInt("p.stock"));
                cartItem.setCartItemProduct(product);
                cartItem.setCartItemQuantity(rs.getInt("oi.quantity"));
                cartItems.add(cartItem);
            }
        } catch (SQLException err){
            System.out.println(err.getMessage());
        }

        return cartItems;
    }

    public void updateOrderIsCompleted(int orderId){
        try{
            String query = "UPDATE orderlist SET isCompleted = 1 WHERE id = ?;";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, orderId);
            ps.executeUpdate();
        } catch (SQLException err){
            System.out.println(err.getMessage());
        }
    }
}
