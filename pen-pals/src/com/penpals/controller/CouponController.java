package com.penpals.controller;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.penpals.model.Coupon;

public class CouponController extends Controller {
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

    public Coupon getCouponDetailbyCode(String code)
    {
        Coupon coupon = new Coupon();
        try{
            String query = "SELECT id, couponCode, expirationDate, percentage, minSpent FROM coupon WHERE couponCode = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                coupon.setDiscountId(resultSet.getInt("id"));
                coupon.setCouponCode(resultSet.getString("couponCode"));
                coupon.setDiscountPercentage(resultSet.getInt("percentage"));
                coupon.setCouponExpirationDate(resultSet.getString("expirationDate"));
                coupon.setCouponMinSpent(resultSet.getInt("minSpent"));
            }
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }
        return coupon;
    }

    public int checkCouponCode(int cusId, String couponCode, double totalSpent)
    {
        try{
            int couponQuantity = 0;
            String query = "SELECT cc.quantity, c.minSpent FROM customer_coupon cc JOIN coupon c ON (cc.coupon_id_fk = c.id) WHERE cc.customer_id_fk = ? AND c.couponCode = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, cusId);
            preparedStatement.setString(2, couponCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                if (resultSet.getDouble("c.minSpent") <= totalSpent)
                {
                    couponQuantity = resultSet.getInt("cc.quantity");
                    return couponQuantity;
                } else
                {
                    JOptionPane.showMessageDialog(null, "The coupon cant be used in this order.","Coupon Error",JOptionPane.INFORMATION_MESSAGE);
                    return 0;
                }

            } else
            {
                JOptionPane.showMessageDialog(null, "Invalid coupon code","Coupon",JOptionPane.INFORMATION_MESSAGE);
                return 0;
            }
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }
        return 0;
    }

    // 1 delete, >1 minus 1 
    public void deductCoupon(int cusId, String couponCode, int quantity){
        try{
            if (quantity - 1>0)
            {
                String query = "UPDATE customer_coupon cc JOIN coupon c ON (cc.coupon_id_fk = c.id) SET cc.quantity = cc.quantity - 1 WHERE cc.customer_id_fk = ? AND c.couponCode = ?;";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, cusId);
                preparedStatement.setString(2, couponCode);
                preparedStatement.executeUpdate();
            } else
            {
                String query = "DELETE cc FROM customer_coupon cc JOIN coupon c ON (cc.coupon_id_fk = c.id) WHERE cc.customer_id_fk = ? AND c.couponCode = ?;";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, cusId);
                preparedStatement.setString(2, couponCode);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        } 
    }

    public List<Coupon> getAllCouponsbyCustomerId(int cusId)
    {
        List<Coupon> coupons = new ArrayList<Coupon>();
        try{
            String query = "SELECT c.id, c.couponCode, c.expirationDate, c.percentage, c.minSpent, cc.quantity FROM customer_coupon cc JOIN coupon c ON (cc.coupon_id_fk = c.id) WHERE cc.customer_id_fk = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, cusId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                Coupon coupon = new Coupon();
                coupon.setDiscountId(resultSet.getInt("c.id"));
                coupon.setCouponCode(resultSet.getString("c.couponCode"));
                coupon.setDiscountPercentage(resultSet.getInt("c.percentage"));
                coupon.setCouponExpirationDate(resultSet.getString("c.expirationDate"));
                coupon.setCouponMinSpent(resultSet.getInt("c.minSpent"));
                coupon.setCouponQuantity(resultSet.getInt("cc.quantity"));
                coupons.add(coupon);
            }
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }
        return coupons;
    }
}
