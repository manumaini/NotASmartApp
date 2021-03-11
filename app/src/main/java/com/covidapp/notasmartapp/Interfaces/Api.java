package com.covidapp.notasmartapp.Interfaces;

import com.covidapp.notasmartapp.Data.Models.LatLng;
import com.covidapp.notasmartapp.POJO.CovidStateData;
import com.covidapp.notasmartapp.POJO.HealthNewsResponse;
import com.covidapp.notasmartapp.POJO.LocationResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("/data.json")
    Call<List<CovidStateData>> getAllCovidData();

    @GET("http://newsapi.org/v2/top-headlines?country=in&category=health&apiKey=8a6ac3a1466e47afaff19ef069cc02e9")
    Call<HealthNewsResponse> getNews();

    @GET("https://maps.googleapis.com/maps/api/place/nearbysearch/json?radius=5000&types=hospital&key=AIzaSyAg6E9sTXVJDv7RMpO3CU6LNsKTm-UqOMs")
    Call<LocationResponse> getLocation(@Query(value = "location", encoded = true) LatLng location);

}
