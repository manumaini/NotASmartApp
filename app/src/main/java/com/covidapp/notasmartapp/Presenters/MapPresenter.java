package com.covidapp.notasmartapp.Presenters;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.covidapp.notasmartapp.Clients.RetrofitClient;
import com.covidapp.notasmartapp.Data.Models.LatLng;
import com.covidapp.notasmartapp.Interfaces.MainContract;
import com.covidapp.notasmartapp.POJO.LocationResponse;
import com.covidapp.notasmartapp.POJO.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapPresenter implements MainContract.MapPresenter {


    private MainContract.MapView mapView;
    private Context context;
    private String TAG = "MapPresenter";
    private Activity activity;


    public MapPresenter(MainContract.MapView mapView, Context context ,Activity activity) {
        this.mapView = mapView;
        this.context = context;
        this.activity = activity;

    }


    @Override
    public void loadLocation(Location location) {
        mapView.showLoading();
        Log.d(TAG, "loadLocation: "+location.getLatitude()+","+location.getLongitude());
        LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());

        RetrofitClient.getInstance().getApi().getLocation(latLng)
                .enqueue(new Callback<LocationResponse>() {
                    @Override
                    public void onResponse(Call<LocationResponse> call, Response<LocationResponse> response) {
                        List<Result> loclist = response.body().getResults();
                        mapView.onSuccess(loclist);
                        mapView.hideLoading();

                    }

                    @Override
                    public void onFailure(Call<LocationResponse> call, Throwable t) {
                        mapView.onFailed(t.getLocalizedMessage());
                        mapView.hideLoading();
                    }
                });
    }
}
