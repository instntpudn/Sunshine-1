package com.example.ryan.weatherapp;

/**
 * Created by Ryan on 2/26/2015.
 */
public class WeatherOnDate
{
    private double dayTemp;
    private double nightTemp;
    private double minTemp;
    private double maxTemp;
    private double eveTemp;
    private double mornTemp;
    private String date;
    private int humidity;
    private String img;
    private double pressure;

    @Override
    public String toString()
    {
        return "Day Temp:  " + dayTemp + "\nNightTemp:  " +
                nightTemp + "\nDate:  " + date + "\nHumidity:  " +
                humidity;
    }

    public WeatherOnDate()
    {

    }

    public WeatherOnDate(double dayTemp, double nightTemp, double minTemp, double maxTemp,
                         double eveTemp, double mornTemp, String date, int humidity, String img, double pressure) {
        this.dayTemp = dayTemp;
        this.nightTemp = nightTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.eveTemp = eveTemp;
        this.mornTemp = mornTemp;
        this.date = date;
        this.humidity = humidity;
        this.img = img;
        this.pressure = pressure;
    }

    public double getDayTemp() {
        return dayTemp;
    }

    public void setDayTemp(double dayTemp) {
        this.dayTemp = dayTemp;
    }

    public double getNightTemp() {
        return nightTemp;
    }

    public void setNightTemp(double nightTemp) {
        this.nightTemp = nightTemp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public double getEveTemp() {
        return eveTemp;
    }

    public void setEveTemp(double eveTemp) {
        this.eveTemp = eveTemp;
    }

    public double getMornTemp() {
        return mornTemp;
    }

    public void setMornTemp(double mornTemp) {
        this.mornTemp = mornTemp;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }
}
