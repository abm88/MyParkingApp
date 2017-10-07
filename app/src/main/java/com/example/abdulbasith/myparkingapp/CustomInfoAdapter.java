package com.example.abdulbasith.myparkingapp;

import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by AbdulBasit on 07/10/2017.
 */

public class CustomInfoAdapter implements GoogleMap.InfoWindowAdapter {
    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
