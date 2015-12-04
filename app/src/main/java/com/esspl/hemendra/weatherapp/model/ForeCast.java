package com.esspl.hemendra.weatherapp.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BAPI1 on 03-12-2015.
 */
public class ForeCast implements JSONPopulator {
    private List<ForecastData> foreCasts;
    private ForecastData forecastData;

    public ForeCast() {
        this.foreCasts = new ArrayList<ForecastData>();
    }

    public List<ForecastData> getForeCasts() {
        return foreCasts;
    }

    @Override
    public void populate(JSONObject data) {
        forecastData = new ForecastData();
        forecastData.setCode(data.optInt("code"));
        forecastData.setDate(data.optString("date"));
        forecastData.setDay(data.optString("day"));
        forecastData.setHigh(data.optInt("high"));
        forecastData.setLow(data.optInt("low"));
        forecastData.setDescription(data.optString("text"));

        foreCasts.add(forecastData);

    }
}
