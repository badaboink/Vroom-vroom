package com.example.myapplication;

public class Price {
    private String price1;
    private String price2;
    private String price3;
    public Price(String price1, String price2, String price3)
    {
        this.price1 = price1;
        this.price2 = price2;
        this.price3 = price3;
    }

    public String getPrice()
    {
        return price1;
    }
    public void setPrice(String price)
    {
        this.price1 = price;
    }
}
