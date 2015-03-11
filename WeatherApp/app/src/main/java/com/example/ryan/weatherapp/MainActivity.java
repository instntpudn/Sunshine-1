package com.example.ryan.weatherapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.JsonToken;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText searchField = (EditText) findViewById(R.id.searchText);
        searchField.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                searchField.setText("");
            }
        });

        final Button searchButton = (Button)findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                GetWeather(searchField.getText().toString());
            }
        });
    }

    private void populateListView(List<WeatherOnDate> wod)
    {
//        ArrayAdapter<WeatherOnDate> adapter = new
//                ArrayAdapter<WeatherOnDate>(
//                this,
//                R.layout.layout,
//                R.id.WeatherInfo,
//                wod
//        );

        WeatherOnDate[] displayText = new WeatherOnDate[wod.size()];
        int [] prgmImages = new int[wod.size()];
        for(int i = 0; i < wod.size(); i++)
        {
            WeatherOnDate obj = wod.get(i);
            displayText[i] = obj;
            if(obj.getImg().equals("Clouds"))
                prgmImages[i] = R.drawable.clouds;
            else if(obj.getImg().equals("Clear"))
                prgmImages[i] = R.drawable.clear;
            else if(obj.getImg().equals("Rain"))
                prgmImages[i] = R.drawable.rain;
            else if(obj.getImg().equals("Snow"))
                prgmImages[i] = R.drawable.snow;
        }

        ListView list = (ListView)findViewById(R.id.listView);
        list.setAdapter(new CustomAdapter(this, displayText, prgmImages));
    }

    private void GetWeather(String searchText)
    {
        AsyncTask<String, Void, List<WeatherOnDate>> as = new ForecastHit().execute(searchText);
        try
        {
            populateListView(as.get());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
