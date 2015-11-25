package azaza.testapp.Managers;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.google.android.gms.maps.GoogleMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import azaza.testapp.Models.Weather;
import azaza.testapp.Settings.Config;

/**
 * Created by Alex on 25.11.2015.
 */
public class LoadJsonManager extends AsyncTask<JSONObject, Void, JSONObject> {


    GoogleMap mMap;
    Context context;

    public LoadJsonManager(GoogleMap mMap, Context context){
        this.mMap = mMap;
        this.context = context;
    }

    HttpURLConnection urlConnection = null;
    URL url = null;
    InputStream inputStream;
    BufferedReader reader = null;
    String resultJson;
    String line;
    JSONObject jsonObject;
    Weather weatherData = new Weather();

    @Override
    protected JSONObject doInBackground(JSONObject... params) {
        try {
            url = new URL("http://" + Config.BASE_URL + "?lat=" + Config.LATITUDE + "&lon=" + Config.LONGITUDE + "&appid=" + Config.API_KEY_WEATHER);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            resultJson = buffer.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            jsonObject = new JSONObject(resultJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);

        if (jsonObject != null) {
            try {
                JSONArray weatherGLobalInfo = jsonObject.getJSONArray("weather");
                JSONObject weatherInfo = weatherGLobalInfo.getJSONObject(0);

                weatherData.setWeather(weatherInfo.getString("main"));
                weatherData.setDescriptionWeather(weatherInfo.getString("description"));
                weatherData.setIcon(weatherInfo.getString("icon"));

                JSONObject windInfo = jsonObject.getJSONObject("wind");
                weatherData.setWind(windInfo.getString("speed"));

                JSONObject countryInfo = jsonObject.getJSONObject("sys");
                weatherData.setCountry(countryInfo.getString("country"));
                weatherData.setCity(jsonObject.getString("name"));

                if (weatherData.getIcon()!=null){
                    Bitmap bitmap = new LoadImageManager(weatherData.getIcon(), mMap, context).execute().get();
                  weatherData.setBitmap(bitmap);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
    }
}


