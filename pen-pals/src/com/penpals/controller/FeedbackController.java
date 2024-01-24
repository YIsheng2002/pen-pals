package com.penpals.controller;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

import com.penpals.model.Feedback;

public class FeedbackController extends Controller{
    public void connectToDatabase(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/penpalsoop", "root", "");
        } catch (ClassNotFoundException | SQLException err){
            System.out.println(err.getMessage());
        }
    }

    public List<Feedback> getAllFeedback(int productId){
        try{
            List<Feedback> feedbacks = new ArrayList<Feedback>();
            String query = "SELECT * FROM feedback WHERE product_id_fk = ?;";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Feedback feedback = new Feedback();
                feedback.setFeedbackId(rs.getInt("id"));
                feedback.setFeedbackProductId(rs.getInt("product_id_fk"));
                feedback.setFeedbackReview(rs.getString("review"));
                feedback.setFeedbackDate(rs.getDate("review_date"));
                feedback.setFeedbackRating(rs.getInt("rating"));
                feedbacks.add(feedback);
            }
            return feedbacks;
        } catch (SQLException err){
            System.out.println(err.getMessage());
        }

        return null;
    }

    public void addproductReview(int productId, int customerId, int rating, String review)
    {
        try{
            String query = "INSERT INTO feedback (product_id_fk, customer_id_fk, rating, review, review_date) VALUES (?, ?, ?, ?, NOW());";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, productId);
            ps.setInt(2, customerId);
            ps.setInt(3, rating);
            ps.setString(4, review);
            ps.executeUpdate();
        } catch (SQLException err){
            System.out.println(err.getMessage());
        }
    }
}
