package com.covidapp.notasmartapp.POJO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CovidStateData {

    @SerializedName("state")
    public String state;
    @SerializedName("active")
    public int activeCases;
    @SerializedName("confirmed")
    public int confirmedCases;
    @SerializedName("recovered")
    public int recoveredCases;
    @SerializedName("deaths")
    public int deaths;
    @SerializedName("districtData")
    public List<CovidDistrictData> districtData=null;

    public static class CovidDistrictData{
        @SerializedName("name")
        public String name;
        @SerializedName("confirmed")
        public int confirmedDistrictCases;

        public CovidDistrictData(String name,int confirmedDistrictCases){
            this.name=name;
            this.confirmedDistrictCases=confirmedDistrictCases;
        }
    }
}
