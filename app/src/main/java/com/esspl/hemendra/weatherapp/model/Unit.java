package com.esspl.hemendra.weatherapp.model;

import org.json.JSONObject;

/**
 * Created by BAPI1 on 01-12-2015.
 */
public class Unit implements JSONPopulator {

    private String tempUnit;

    public String getTempUnit() {
        return tempUnit;
    }

    public void setTempUnit(String tempUnit) {
        this.tempUnit = tempUnit;
    }

    @Override
    public void populate(JSONObject data) {
        tempUnit = data.optString("temperature");
    }
}
