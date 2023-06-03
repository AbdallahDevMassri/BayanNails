package com.example.bayannails.Classes;

import java.io.Serializable;

public class Order implements Serializable {
    private int day;
    private int month;
    private int year;
    private int hour;


    public Order(int day, int month, int year, int hour) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;

    }

    public Order() {
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    @Override
    public String toString() {
        return "Order{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", hour=" + hour +
                '}';
    }
}
