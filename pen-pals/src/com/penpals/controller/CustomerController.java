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
        Customer customer = new Customer();
        try
        {
            String sql = "SELECT * FROM customer WHERE Username = ? AND Password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                customer.setCustomerId(rs.getInt("ID"));
                customer.setCustomerName(rs.getString("Name"));
                customer.setCustomerEmail(rs.getString("email"));
                customer.setCustomerTelNumber(rs.getString("telNumber"));
                customer.setCustomerAddress(new AddressController().getAddressDetailbyId(rs.getInt("AddressID")));
                customer.setCustomerUsername(rs.getString("Username"));
                customer.setCustomerPassword(rs.getString("Password"));
            }
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }

        return customer;
    }
     
}
