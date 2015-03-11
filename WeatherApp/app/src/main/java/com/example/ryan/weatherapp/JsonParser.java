package com.example.ryan.weatherapp;

import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ryan on 3/4/2015.
 */
public class JsonParser
{
    public static List<WeatherOnDate> buildWeatherOnDayObjects(InputStream json)
    {
        JsonReader reader = new JsonReader(new InputStreamReader(json));
        return readMessagesArray(reader);
    }

    private static List readMessagesArray(JsonReader reader){
        List messages = new ArrayList();

        try {
            reader.beginObject();
            while (reader.hasNext())
            {
                String name = reader.nextName();
                if(name.equals("list")) {
                    messages = readMessage(reader);
                }
                else
                    reader.skipValue();
            }
            reader.endObject();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return messages;
    }

    private static List readMessage(JsonReader reader) throws IOException {
        List messages = new ArrayList();

        reader.beginArray();
        while(reader.hasNext())
        {
            WeatherOnDate obj = new WeatherOnDate();

            reader.beginObject();
            while(reader.hasNext()) {
                String name = reader.nextName();
                if (name.equals("temp")) {
                    readTemp(reader, obj);
                }
                else if (name.equals("humidity"))
                    obj.setHumidity(reader.nextInt());
                else if (name.equals("dt"))
                    obj.setDate(reader.nextString());
                else if (name.equals("pressure"))
                    obj.setPressure(reader.nextDouble());
                else if (name.equals("weather"))
                    readWeather(reader, obj);
                else
                    reader.skipValue();
            }
            reader.endObject();
            long dv = Long.valueOf(obj.getDate())*1000;// its need to be in milisecond
            Date df = new java.util.Date(dv);
            String vv = new SimpleDateFormat("MM/dd/yyyy").format(df);
            obj.setDate(vv);
            messages.add(obj);
        }
        reader.endArray();
        return messages;
    }

    private static void readWeather(JsonReader reader, WeatherOnDate obj) throws IOException
    {
        reader.beginArray();
        while(reader.hasNext())
        {
            reader.beginObject();
            while(reader.hasNext())
            {
                String nextName = reader.nextName();
                if (nextName.equals("main"))
                    obj.setImg(reader.nextString());
                else
                    reader.skipValue();
            }
            reader.endObject();
        }
        reader.endArray();
    }

    private static void readTemp(JsonReader reader, WeatherOnDate obj) throws IOException
    {
        reader.beginObject();
        while(reader.hasNext())
        {
            String nextName = reader.nextName();
            if(nextName.equals("day"))
                obj.setDayTemp(reader.nextDouble());
            else if(nextName.equals("night"))
                obj.setNightTemp(reader.nextDouble());
            else if(nextName.equals("min"))
                obj.setMinTemp(reader.nextDouble());
            else if(nextName.equals("max"))
                obj.setMaxTemp(reader.nextDouble());
            else if(nextName.equals("eve"))
                obj.setEveTemp(reader.nextDouble());
            else if(nextName.equals("morn"))
                obj.setMornTemp(reader.nextDouble());
            else
                reader.skipValue();
        }
        reader.endObject();
    }
}
