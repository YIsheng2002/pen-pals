package com.penpals.controller;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import javax.swing.JOptionPane;

import com.penpals.model.Address;

public class AddressController extends Controller{
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

    public Address getAddressDetailbyCustomerId(int cusId)
    {
        Address address = new Address();
        try
        {
            String sql = "SELECT number, road, postcode, state FROM address WHERE customer_id_fk = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cusId);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                address.setNumber(rs.getInt("number"));
                address.setRoad(rs.getString("road"));
                address.setPostcode(rs.getInt("postcode"));
                address.setState(rs.getString("state"));
            }
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }

        return address;
    }

    public void updateAddressDetail(Address address, int cusId)
    {
        try
        {
            String sql = "UPDATE address SET number = ?, road = ?, postcode = ?, state = ? WHERE customer_id_fk = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, address.getNumber());
            ps.setString(2, address.getRoad());
            ps.setInt(3, address.getPostcode());
            ps.setString(4, address.getState());
            ps.setInt(5, cusId);
            ps.executeUpdate();
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }
    }

    public void insertAddressDetail(Address address, int cusId)
    {
        try
        {
            String sql = "INSERT INTO address (number, road, postcode, state, customer_id_fk) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, address.getNumber());
            ps.setString(2, address.getRoad());
            ps.setInt(3, address.getPostcode());
            ps.setString(4, address.getState());
            ps.setInt(5, cusId);
            ps.executeUpdate();
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }
    }
}
