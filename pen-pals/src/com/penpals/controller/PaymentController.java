package com.penpals.controller;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class PaymentController extends Controller{
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

    public void insertPayment(int orderId, String paymentMethod, double amount)
    {
        try{
            String query = "INSERT INTO payment (order_id_fk, method, payment_date, amount) VALUES (?, ?, NOW(), ?);";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, orderId);
            ps.setString(2, paymentMethod);
            ps.setDouble(3, amount);
            ps.executeUpdate();
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }
    }
}
