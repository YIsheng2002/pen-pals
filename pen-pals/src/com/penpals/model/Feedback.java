package com.penpals.model;

import java.util.Date;

public class Feedback {
    private int id;
    private String review;
    private Date date;
    private int rating;
    private int productId;

    public Feedback(int id, String review, Date date, int rating, int productId) {
        this.id = id;
        this.review = review;
        this.date = date;
        this.rating = rating;
        this.productId = productId;
    }

    public Feedback()
    {
        this.id = 0;
        this.review = "";
        this.date = new Date();
        this.rating = 0;
        this.productId = 0;
    }

    public int getFeedbackId() {
        return id;
    }

    public void setFeedbackId(int id) 
    {
        this.id = id;
    }

    public String getFeedbackReview() {
        return review;
    }

    public void setFeedbackReview(String review) 
    {
        this.review = review;
    }

    public Date getFeedbackDate() {
        return date;
    }

    public void setFeedbackDate(Date date) 
    {
        this.date = date;
    }

    public int getFeedbackRating() {
        return rating;
    }

    public void setFeedbackRating(int rating) 
    {
        this.rating = rating;
    }

    public int getFeedbackProductId() {
        return productId;
    }

    public void setFeedbackProductId(int productId) 
    {
        this.productId = productId;
    }
}
