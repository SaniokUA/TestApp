package azaza.testapp.Managers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.net.URL;

import azaza.testapp.Models.Weather;
import azaza.testapp.Settings.Config;

/**
 * Created by Alex on 25.11.2015.
 */
public class LoadImageManager extends AsyncTask<Bitmap, Void, Bitmap> {

    String icon;
    URL url = null;
    Bitmap bmp;
    GoogleMap mMap;
    Context context;

    public LoadImageManager(String icon, GoogleMap mMap, Context context){
        this.icon = icon;
        this.mMap = mMap;
        this.context = context;
    }


    @Override
    protected Bitmap doInBackground(Bitmap... params) {
        try {
            url = new URL("http://" + Config.LOAD_IMG_URL + icon);
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());

        } catch (java.io.IOException e) {
            e.printStackTrace();
            bmp = null;
        }
        return bmp;
    }

    @Override
    protected void onPostExecute(Bitmap bmp) {
        super.onPostExecute(bmp);
        Weather.setBitmap(bmp);

        LatLng country = new LatLng(Double.parseDouble(Config.LATITUDE), Double.parseDouble(Config.LONGITUDE));

        MarkerOptions marker = new MarkerOptions().position(country);

        mMap.addMarker(marker).showInfoWindow();
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(country, 10.0f));
        mMap.setInfoWindowAdapter(new MapsInterfaceManager(context));
    }

}
