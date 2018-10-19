package com.example.asus.adminpanel;

public class Notice_Element {
    String date,details,headline;

    public Notice_Element() {
    }

    public Notice_Element(String date, String details,String headline) {
        this.date = date;
        this.details = details;
        this.headline=headline;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
