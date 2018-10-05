package com.jason.feelsbook;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Feelings {
    private String feels;
    private Date date;
    private String comment;
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MMM-dd");


    public Feelings(String feels, Date date, String comment) {
        this.feels = feels;
        this.date = date;this.comment = comment;
    }

    public String getFeels() {
        return feels;
    }

    public void setFeels(String name) {
        this.feels = feels;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getDateString(){
        return format.format(date);
    }
    @Override
    public String toString() {
        return "Name: " + feels + "\n"+
                "Date: " + format.format(date) + "\n"+
                "Comment: " + comment;
    }
}
