package com.esspl.hemendra.weatherapp.model;

import org.json.JSONObject;

/**
 * Created by BAPI1 on 01-12-2015.
 */
public class Location implements JSONPopulator {

    private String city;
    private String country;
    private String region;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public void populate(JSONObject data) {

    }
}
