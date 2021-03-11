package com.covidapp.notasmartapp.POJO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CovidStateData {

    @SerializedName("statewise")
    public List<Statewise> statewise=null;

    public static class Statewise{
        @SerializedName("state")
        public String name;
        @SerializedName("confirmed")
        public int confirmed;
        @SerializedName("active")
        public int active;
        @SerializedName("deaths")
        public int deaths;
        @SerializedName("recovered")
        public int recovered;

        public Statewise(String name,int confirmed,int active,int deaths,int recovered){
            this.name=name;
            this.confirmed=confirmed;
            this.active=active;
            this.deaths=deaths;
            this.recovered=recovered;
        }
    }
}
