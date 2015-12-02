package com.esspl.hemendra.weatherapp;

import android.accounts.NetworkErrorException;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.esspl.hemendra.weatherapp.model.Channel;
import com.esspl.hemendra.weatherapp.model.Item;
import com.esspl.hemendra.weatherapp.service.DetectNetworkConnectivity;
import com.esspl.hemendra.weatherapp.service.WeatherServiceCallback;
import com.esspl.hemendra.weatherapp.service.YAHOOWeatherService;

public class MainActivity extends AppCompatActivity implements WeatherServiceCallback {

    private ImageView weatherIconImageView;
    private TextView temperatureTextView;
    private TextView conditionTextView;
    private TextView locationTextView;

    private String searchCity;

    private SearchView searchView;

    private YAHOOWeatherService service;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherIconImageView = (ImageView) findViewById(R.id.weatherIconImageView);
        temperatureTextView = (TextView) findViewById(R.id.temperatureTextView);
        conditionTextView = (TextView) findViewById(R.id.conditionTextView);
        locationTextView = (TextView) findViewById(R.id.locationTextView);

        service = new YAHOOWeatherService(this);
        searchWeather("Bhubaneswar,India");
        searchView = (SearchView) findViewById(R.id.search_city);
        /*
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //Toast.makeText(MainActivity.this, "Yahooo Search active.....", Toast.LENGTH_SHORT).show();
            }
        });*/
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Toast.makeText(MainActivity.this, "Yahooo sadhasdasjdhasjdhjasd active.....", Toast.LENGTH_SHORT).show();
                searchCity = query.toString();
                searchWeather(searchCity);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });






    }

    private void searchWeather(String searchCity) {
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading.....");
        dialog.show();
        DetectNetworkConnectivity networkConnectivity = new DetectNetworkConnectivity(this);
        if (networkConnectivity.isInternetConnected())
            service.refreshWeather(searchCity);
        else
            serviceFailure(new NetworkErrorException("Your device is not connected to the internet"));
    }

    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();

        Item item = channel.getItem();
        int resourceId = getResources().getIdentifier("drawable/icon_" + String.valueOf(item.getCondition().getCode()).trim(), null, getPackageName());

        Log.d("Icon Id==============", String.valueOf(resourceId));

        Drawable weatherIconDrawable = getResources().getDrawable(resourceId);

        weatherIconImageView.setImageDrawable(weatherIconDrawable);

        temperatureTextView.setText(item.getCondition().getTempaerature() + "\u00B0" + channel.getUnit().getTempUnit());
        conditionTextView.setText(item.getCondition().getDescription());
        locationTextView.setText(service.getLocation().toString());



    }

    @Override
    public void serviceFailure(Exception error) {
        dialog.hide();
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_weather_menu,menu);
/*
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
*/
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search:
                Toast.makeText(MainActivity.this, "Clicked on Search", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
