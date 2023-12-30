package com.penpals.model;

import java.util.Date;

public class Feedback {
    private int id;
    private String review;
    private Date date;
    private int rating;
    private Product product;

    public Feedback(int id, String review, Date date, int rating, Product product) {
        this.id = id;
        this.review = review;
        this.date = date;
        this.rating = rating;
        this.product = product;
    }

    public Feedback()
    {
        this.id = 0;
        this.review = "";
        this.date = new Date();
        this.rating = 0;
        this.product = new Product();
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

    public Product getFeedbackProduct() {
        return product;
    }

    public void setFeedbackProduct(Product product) 
    {
        this.product = product;
    }
}
