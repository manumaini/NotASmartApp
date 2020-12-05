package com.covidapp.notasmartapp.Data.Models;

public class LatLng {
    private double lat;
    private double lng;

    public LatLng(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public String toString() {
        return String.format("%.7f,%.7f", lat, lng);
    }
}
