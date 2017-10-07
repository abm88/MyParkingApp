package com.example.abdulbasith.myparkingapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.abdulbasith.myparkingapp.AppServices.ConnectionService;
import com.example.abdulbasith.myparkingapp.AppServices.IRequestInterface;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private IRequestInterface iRequestInterface;
    private ConnectionService connectionService;
    private List<ParkingListModel> parkingListModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        iRequestInterface = ConnectionService.getConnectionService();
        iRequestInterface.getParkingListModel(API_Constants.VALUE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(this::Success, this::onError);

    }

    private void Success(List<ParkingListModel> parkingListModel) {
      for (int i=0; i<parkingListModel.size(); i++) {
            double myLatitude =  Double.parseDouble(((List<ParkingListModel>) parkingListModel).get(i).getLat());
            double myLangtitude = Double.parseDouble(((List<ParkingListModel>) parkingListModel).get(i).getLng());
            LatLng myParkingMarker = new LatLng(myLatitude,myLangtitude);

              // Add a marker and move the camera
            mMap.addMarker(new MarkerOptions().position(myParkingMarker).title(((List<ParkingListModel>) parkingListModel).get(i).getName()));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myParkingMarker, 13));
        }
    }

    private void onError(Throwable throwable) {
        Log.i("This error --> ", throwable.getMessage());
    }


}
