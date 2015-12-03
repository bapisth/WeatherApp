package com.esspl.hemendra.weatherapp.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by BAPI1 on 01-12-2015.
 */
public class Item implements JSONPopulator{
    private Condition condition;
    private ForeCast foreCasts;

    public ForeCast getForeCasts() {
        return foreCasts;
    }

    public Condition getCondition() {
        return condition;
    }


    @Override
    public void populate(JSONObject data) {
        condition = new Condition();
        foreCasts = new ForeCast();
        try {
            condition.populate(data.getJSONObject("condition"));
            JSONArray forecastArray = data.getJSONArray("forecast");
            for (int i=0; i<forecastArray.length(); i++)
                foreCasts.populate(forecastArray.getJSONObject(i));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
