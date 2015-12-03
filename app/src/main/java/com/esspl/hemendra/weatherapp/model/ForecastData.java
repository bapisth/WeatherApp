package com.esspl.hemendra.weatherapp.model;

/**
 * Created by BAPI1 on 03-12-2015.
 */
public class ForecastData {

    private int code;
    private String date;
    private String day;
    private int high;
    private int low;
    private String description;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
