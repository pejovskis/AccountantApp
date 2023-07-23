package com.example.myapp10;

import android.text.format.DateFormat;

import java.util.Date;

public class Expense {

    //fields
    private String id;
    private String where;
    private String category;
    private String essentials;
    private String date;
    private String price;

    public Expense(String where, String category, String essentials, String date, String price) {
        this.where = where;
        this.category = category;
        this.essentials = essentials;
        this.date = date;
        this.price = price;
        setId();
    }

    public String getId() {
        return id;
    }

    public void setId() {
        String idGenerated;
        String dateFiltered = getDate().replace(".", "");

        idGenerated = getWhere().substring(0, 1) + getCategory().substring(0, 1) + getEssentials() + dateFiltered + getPrice();

        this.id = idGenerated;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
