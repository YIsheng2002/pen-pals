package com.penpals.model;

public class Address {
    private int number;
    private String road;
    private int postcode;
    private String state;

    public Address(int number, String road, int postcode, String state) {
        this.number = number;
        this.road = road;
        this.postcode = postcode;
        this.state = state;
    }

    public Address()
    {
        this.number = 0;
        this.road = "";
        this.postcode = 0;
        this.state = "";
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) 
    {
        this.number = number;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) 
    {
        this.road = road;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) 
    {
        this.postcode = postcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) 
    {
        this.state = state;
    }
}
