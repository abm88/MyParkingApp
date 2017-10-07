package com.example.abdulbasith.myparkingapp.AppServices;

import com.example.abdulbasith.myparkingapp.API_Constants;
import com.example.abdulbasith.myparkingapp.ParkingListModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by AbdulBasit on 06/10/2017.
 */

public interface IRequestInterface {

    @GET(API_Constants.BASE_URL)
    Observable <List<ParkingListModel>> getParkingListModel(@Query(API_Constants.QUERY) String location);
}
