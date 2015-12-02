package com.esspl.hemendra.weatherapp;

import android.accounts.NetworkErrorException;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
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
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading.....");
        dialog.show();
        DetectNetworkConnectivity networkConnectivity = new DetectNetworkConnectivity(this);
        if (networkConnectivity.isInternetConnected())
            service.refreshWeather("Balasore,India");
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
}
