package com.example.ryan.weatherapp;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Created by Ryan on 2/25/2015.
 */
public class ForecastHit extends AsyncTask<String, Void, List<WeatherOnDate>>
{
    public ForecastHit(){}
    protected List<WeatherOnDate> doInBackground(String... city)
    {
        try
        {
            String url = "http://api.openweathermap.org/data/2.5/forecast/daily?q="+city[0]+"&mode=json&units=metric&cnt=7";

            HttpClient httpClient = new DefaultHttpClient();
            HttpContext localContext = new BasicHttpContext();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet, localContext);

            return JsonParser.buildWeatherOnDayObjects(response.getEntity().getContent());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}