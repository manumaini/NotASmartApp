package com.covidapp.notasmartapp.POJO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CovidStateData {

    @SerializedName("state")
    private String state;
    @SerializedName("active")
    private int activeCases;
    @SerializedName("confirmed")
    private int confirmedCases;
    @SerializedName("recovered")
    private int recoveredCases;
    @SerializedName("deaths")
    private int deaths;
    @SerializedName("districtData")
    private List<CovidDistrictData> districtData=null;

    public class CovidDistrictData{
        @SerializedName("name")
        private String name;
        @SerializedName("confirmed")
        private int confirmedDistrictCases;
    }
}
