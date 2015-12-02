package com.esspl.hemendra.weatherapp.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by BAPI1 on 01-12-2015.
 */
public class Item implements JSONPopulator{
    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    @Override
    public void populate(JSONObject data) {
        condition = new Condition();
        try {
            condition.populate(data.getJSONObject("condition"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
