package com.covidapp.notasmartapp.Interfaces;

import com.covidapp.notasmartapp.POJO.CovidStateData;
import com.covidapp.notasmartapp.POJO.HealthNewsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("/state_data.json")
    Call<List<CovidStateData>> getAllCovidData();

    @GET("http://newsapi.org/v2/top-headlines?country=in&category=health&apiKey=8a6ac3a1466e47afaff19ef069cc02e9")
    Call<HealthNewsResponse> getNews();

}
