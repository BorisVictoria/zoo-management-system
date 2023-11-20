package com.example.zoo_management_system;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Sales {


    private String monthYear;
    private String enclosure;
    private int ticketsSold;
    private double totalSold;

    public String getMonth_year() {
        return month_year;
    }

    public int getChild() {
        return child;
    }

    public int getAdult() {
        return adult;
    }

    private String month_year;
    private int child;
    private int adult;




    public Sales(int year, int month, String enclosure, int child, int adult, int ticketsSold, double totalSold) {
        month_year = month + "/" + year;
        this.enclosure = enclosure;
        this.ticketsSold = ticketsSold;
        this.totalSold = totalSold;
        this.adult = adult;
        this.child = child;
    }

    public String getMonthYear() {
        return monthYear;
    }

    public String getEnclosure() {
        return enclosure;
    }

    public int getTicketsSold() {
        return ticketsSold;
    }

    public double getTotalSold() {
        return totalSold;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }

    public void setEnclosure(String enclosure) {
        this.enclosure = enclosure;
    }

    public void setTicketsSold(int ticketsSold) {
        this.ticketsSold = ticketsSold;
    }

    public void setTotalSold(double totalSold) {
        this.totalSold = totalSold;
    }
}
