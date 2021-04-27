package com.covidapp.notasmartapp.Interfaces;

import com.covidapp.notasmartapp.Data.Models.LatLng;
import com.covidapp.notasmartapp.POJO.CovidStateData;
import com.covidapp.notasmartapp.POJO.HealthNewsResponse;
import com.covidapp.notasmartapp.POJO.Hospital;
import com.covidapp.notasmartapp.POJO.LocationResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    //data
    //spreadsheets.google.com/feeds/worksheets/spreadsheetID/public/basic?alt=json
    //https://sheets.googleapis.com/v4/spreadsheets/{Sheet ID}/values/{Range}?key={API Key}

    @GET("/data.json")
    Call<CovidStateData> getAllCovidData();

    @GET("http://newsapi.org/v2/top-headlines?country=in&category=health&apiKey=8a6ac3a1466e47afaff19ef069cc02e9")
    Call<HealthNewsResponse> getNews();

    @GET("https://maps.googleapis.com/maps/api/place/nearbysearch/json?radius=5000&types=hospital&key=AIzaSyAg6E9sTXVJDv7RMpO3CU6LNsKTm-UqOMs")
    Call<LocationResponse> getLocation(@Query(value = "location", encoded = true) LatLng location);

    @GET("https://sheets.googleapis.com/v4/spreadsheets/14-cJW9t6MODov0V3cKB669zblPmRu3o_64ZT2fWRfBI/values/Sheet1?key=AIzaSyAXpjl-xLl1w6gbl0bakuLItpvPU_zE8Xk")
    Call<Hospital> getHsopitals();
}
