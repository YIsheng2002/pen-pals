package com.penpals.controller;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;
import com.penpals.model.Feedback;

public class FeedbackController extends Controller{
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

    public Feedback getFeedbackDetailbyId(int id)
    {
        Feedback feedback = new Feedback();
        try
        {
            String sql = "SELECT id, review, date, rating, product_id_fk FROM feedback WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                feedback.setFeedbackId(rs.getInt("id"));
                feedback.setFeedbackReview(rs.getString("review"));
                feedback.setFeedbackDate(rs.getDate("date"));
                feedback.setFeedbackRating(rs.getInt("rating"));
                feedback.setFeedbackProductId(rs.getInt("product_id_fk"));
            }
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }

        return feedback;
    }

    public List<Feedback> getAllFeedback(int productId)
    {
        List<Feedback> feedbacks = new ArrayList<Feedback>();
        try
        {
            String sql = "SELECT id, review, review_date, rating, product_id_fk FROM feedback WHERE product_id_fk = ? ORDER BY review_date DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                Feedback feedback = new Feedback();
                feedback.setFeedbackId(rs.getInt("id"));
                feedback.setFeedbackReview(rs.getString("review"));
                feedback.setFeedbackDate(rs.getDate("review_date"));
                feedback.setFeedbackRating(rs.getInt("rating"));
                feedback.setFeedbackProductId(rs.getInt("product_id_fk"));
                feedbacks.add(feedback);
            }
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }

        return feedbacks;
    }
}
