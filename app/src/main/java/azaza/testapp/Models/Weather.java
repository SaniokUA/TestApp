package azaza.testapp.Models;

import android.graphics.Bitmap;

/**
 * Created by Alex on 25.11.2015.
 */
public class Weather {

    public static String wind;
    public static String icon;
    public static String country;
    public static String city;
    public static String weather;

    public static String getWind() {
        return wind;
    }

    public static void setWind(String wind) {
        Weather.wind = wind;
    }

    public static String getIcon() {
        return icon;
    }

    public static void setIcon(String icon) {
        Weather.icon = icon;
    }

    public static String getCountry() {
        return country;
    }

    public static void setCountry(String country) {
        Weather.country = country;
    }

    public static String getCity() {
        return city;
    }

    public static void setCity(String city) {
        Weather.city = city;
    }

    public static String getWeather() {
        return weather;
    }

    public static void setWeather(String weather) {
        Weather.weather = weather;
    }

    public static String getDescriptionWeather() {
        return descriptionWeather;
    }

    public static void setDescriptionWeather(String descriptionWeather) {
        Weather.descriptionWeather = descriptionWeather;
    }

    public static Bitmap getBitmap() {
        return bitmap;
    }

    public static void setBitmap(Bitmap bitmap) {
        Weather.bitmap = bitmap;
    }

    public static String descriptionWeather;
    public static Bitmap bitmap;

}
