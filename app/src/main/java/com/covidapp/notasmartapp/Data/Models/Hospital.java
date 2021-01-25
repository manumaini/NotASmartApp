package com.covidapp.notasmartapp.Data.Models;

public class Hospital {
    private String Name;
    private String Address;
    private String State;
    private String Zip;
    private String Phone;
    private String ImageUrl;
    private int Rating;
    private int TotalRatings;
    private String Latitude;
    private String Longitude;
    private int AvailableBeds;
    private int TotalBeds;
    private boolean Open;



    public Hospital(String name, String address, String state, String zip, String phone, String imageUrl, int rating, int totalRatings, String latitude, String longitude, int availableBeds, boolean open, int totalBeds) {
        Name = name;
        Address = address;
        State = state;
        Zip = zip;
        Phone = phone;
        ImageUrl = imageUrl;
        Rating = rating;
        TotalRatings = totalRatings;
        Latitude = latitude;
        Longitude = longitude;
        AvailableBeds = availableBeds;
        Open = open;
        this.TotalBeds = totalBeds;
    }



    public Hospital() {
    }


    public int getAvailableBeds() {
        return AvailableBeds;
    }

    public void setAvailableBeds(int availableBeds) {
        AvailableBeds = availableBeds;
    }

    public int getTotalBeds() {
        return TotalBeds;
    }

    public void setTotalBeds(int totalBeds) {
        TotalBeds = totalBeds;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getZip() {
        return Zip;
    }

    public void setZip(String zip) {
        Zip = zip;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        Rating = rating;
    }

    public int getTotalRatings() {
        return TotalRatings;
    }

    public void setTotalRatings(int totalRatings) {
        TotalRatings = totalRatings;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }



    public boolean isOpen() {
        return Open;
    }

    public void setOpen(boolean open) {
        Open = open;
    }
}
