package com.esspl.hemendra.weatherapp.service;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.esspl.hemendra.weatherapp.model.Channel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by BAPI1 on 01-12-2015.
 */
public class YAHOOWeatherService {
    private WeatherServiceCallback callback;
    private String location;
    private Exception error;

    public String getLocation() {
        return location;
    }

    public YAHOOWeatherService(WeatherServiceCallback callback) {
        this.callback = callback;
    }

    public void refreshWeather(String l){
        this.location=l;
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\") and u='c'", params[0]);

                //String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")", params[0]);
                String endPoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));

                Log.d("Endpoint=============",endPoint);

                try {
                    URL url = new URL(endPoint);
                    URLConnection connection = url.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line= reader.readLine())!=null ){
                        result.append(line);
                    }
                    return result.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {

                if(s==null && error != null){
                    callback.serviceFailure(error);
                    return;
                }

                try {
                    if (s==null){
                        callback.serviceFailure(new LocationWetherException("Location Bodhe Miluni taku.."+s));
                        return;
                    }
                    JSONObject data = new JSONObject(s);
                    JSONObject queryResults = data.getJSONObject("query");

                    int count = queryResults.getInt("count");
                    if(count == 0){
                        callback.serviceFailure(new LocationWetherException("No Weather Information Found For "+location));
                        return;
                    }

                    Channel channel = new Channel();
                    channel.populate(queryResults.optJSONObject("results").optJSONObject("channel"));
                    callback.serviceSuccess(channel);
                } catch (JSONException e) {
                    callback.serviceFailure(e);

                }
            }
        }.execute(location);
    }

    public class LocationWetherException extends Exception{
        public LocationWetherException(String detailMessage) {
            super(detailMessage);
        }
    }
}
