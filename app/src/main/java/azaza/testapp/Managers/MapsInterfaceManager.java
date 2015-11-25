package azaza.testapp.Managers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import azaza.testapp.Models.Weather;
import azaza.testapp.R;

/**
 * Created by Alex on 25.11.2015.
 */
public class MapsInterfaceManager implements GoogleMap.InfoWindowAdapter {

    private LayoutInflater mInflater;
    Context context;

    public MapsInterfaceManager(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View getInfoWindow(Marker marker) {
        View popup = mInflater.inflate(R.layout.map_weather_info, null);
        TextView title = (TextView) popup.findViewById(R.id.title);
        TextView desc = (TextView) popup.findViewById(R.id.desc);
        title.setText(Weather.country + ", " + Weather.city);
        desc.setText(" Weather: " + Weather.getWeather() + " , " + Weather.getDescriptionWeather() + ".\n Wind speed: " + Weather.getWind());
        ImageView imageView = (ImageView) popup.findViewById(R.id.imageIcon);
        imageView.setImageBitmap(Weather.getBitmap());
        return popup;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View popup = mInflater.inflate(R.layout.map_weather_info, null);
        TextView title = (TextView) popup.findViewById(R.id.title);
        TextView desc = (TextView) popup.findViewById(R.id.desc);
        title.setText(Weather.country + ", " + Weather.city);
        desc.setText(" Weather: " + Weather.getWeather() + " , " + Weather.getDescriptionWeather() + ".\n Wind speed: " + Weather.getWind());
        ImageView imageView = (ImageView) popup.findViewById(R.id.imageIcon);
        imageView.setImageBitmap(Weather.getBitmap());
        return popup;
    }
}
