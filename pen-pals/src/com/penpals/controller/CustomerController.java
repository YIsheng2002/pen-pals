package com.penpals.controller;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import javax.swing.JOptionPane;
import com.penpals.model.Customer;

public class CustomerController extends Controller{
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

    public Customer getCustomerDetailbyUsernamePassword(String username, String password)
    {
    	Customer customer = null;
        try
        {
        	customer = new Customer();
            String sql = "SELECT id, name, email, telNumber, username, password FROM customer WHERE username = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                customer.setCustomerId(rs.getInt("id"));
                customer.setCustomerName(rs.getString("name"));
                customer.setCustomerEmail(rs.getString("email"));
                customer.setCustomerTelNumber(rs.getString("telNumber"));
                customer.setCustomerAddress(new AddressController().getAddressDetailbyCustomerId(rs.getInt("id")));
                customer.setCustomerUsername(rs.getString("username"));
                customer.setCustomerPassword(rs.getString("password"));
            }
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }
        
        return customer;
    }

    public void updateCustomerDetail(Customer customer)
    {
        try
        {
            String sql = "UPDATE customer SET name = ?, email = ?, telNumber = ?, username = ?, password = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getCustomerEmail());
            ps.setString(3, customer.getCustomerTelNumber());
            ps.setString(4, customer.getCustomerUsername());
            ps.setString(5, customer.getCustomerPassword());
            ps.setInt(6, customer.getCustomerId());
            ps.executeUpdate();
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }
    }
     
    public void addCustomer(Customer customer)
    {
        try
        {
            String sql = "INSERT INTO customer (name, email, telNumber, username, password) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getCustomerEmail());
            ps.setString(3, customer.getCustomerTelNumber());
            ps.setString(4, customer.getCustomerUsername());
            ps.setString(5, customer.getCustomerPassword());
            ps.executeUpdate();
            ResultSet rs = ps.executeQuery("SELECT LAST_INSERT_ID();");
            if(rs.next()){
                new AddressController().insertAddressDetail(customer.getCustomerAddress(), rs.getInt(1));
            }
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }
    }
}
