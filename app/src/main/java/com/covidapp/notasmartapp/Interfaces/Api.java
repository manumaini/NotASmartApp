package com.covidapp.notasmartapp.Interfaces;

import com.covidapp.notasmartapp.POJO.CovidStateData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("/state_data.json")
    Call<CovidStateData> getAllCovidData();

}
