package com.esspl.hemendra.weatherapp.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by BAPI1 on 01-12-2015.
 */
public class Channel implements JSONPopulator {
    private Item item;
    private Unit unit;

    public Item getItem() {
        return item;
    }

    public Unit getUnit() {
        return unit;
    }

    public Channel(Item item, Unit unit) {
        this.item = item;
        this.unit = unit;
    }

    public Channel() {
    }

    @Override
    public void populate(JSONObject data) {

        try {
            unit = new Unit();
            unit.populate(data.getJSONObject("units"));

            item = new Item();
            item.populate(data.getJSONObject("item"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
