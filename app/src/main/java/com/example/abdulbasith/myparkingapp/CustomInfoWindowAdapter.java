package com.example.abdulbasith.myparkingapp;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.List;

/**
 * Created by AbdulBasit on 08/10/2017.
 */

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

   List <ParkingListModel> parkingListModel;

    private Activity context;

    public CustomInfoWindowAdapter(Activity context){
        this.context = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = context.getLayoutInflater().inflate(R.layout.custom_info_contents, null);

        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView tvSubTitle = (TextView) view.findViewById(R.id.tv_subtitle);
        TextView tvDetails = (TextView) view.findViewById(R.id.tv_details);

        tvTitle.setText(marker.getTitle());
        tvSubTitle.setText(marker.getSnippet());


        return view;
    }
}