package com.esspl.hemendra.weatherapp.model;

import org.json.JSONObject;

/**
 * Created by BAPI1 on 01-12-2015.
 */
public class Condition implements JSONPopulator {
    private int tempaerature;
    private String description;
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getTempaerature() {
        return tempaerature;
    }

    public void setTempaerature(int tempaerature) {
        this.tempaerature = tempaerature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void populate(JSONObject data) {
        code = data.optInt("code");
        tempaerature = data.optInt("temp");
        description = data.optString("text");
    }
}
