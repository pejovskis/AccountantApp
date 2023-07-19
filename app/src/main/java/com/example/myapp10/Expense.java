package com.example.myapp10;

import android.text.format.DateFormat;

import java.util.Date;

public class Expense {

    //fields
    private int id;
    private String where;
    private String category;
    private String essentials;
    private Date date;
    private  int price;

    public Expense(int id, String where, String category, String essentials, Date date, int price) {
        this.id = id;
        this.where = where;
        this.category = category;
        this.essentials = essentials;
        this.date = date;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEssentials() {
        return essentials;
    }

    public void setEssentials(String essentials) {
        this.essentials = essentials;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
