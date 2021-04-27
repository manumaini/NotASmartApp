package com.covidapp.notasmartapp.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Hospital {
    @SerializedName("range")
    @Expose
    private String range;
    @SerializedName("majorDimension")
    @Expose
    private String majorDimension;
    @SerializedName("values")
    @Expose
    private ArrayList<ArrayList<String>> values = null;

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getMajorDimension() {
        return majorDimension;
    }

    public void setMajorDimension(String majorDimension) {
        this.majorDimension = majorDimension;
    }

    public ArrayList<ArrayList<String>> getValues() {
        return values;
    }

    public void setValues(ArrayList<ArrayList<String>> values ) {
        this.values = values;
    }
}
