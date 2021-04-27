package com.covidapp.notasmartapp.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CovidStateData {

    @SerializedName("statewise")
    public List<Statewise> statewise=null;

    @SerializedName("cases_time_series")
    public List<CasesTimeSeries> cases_time_series=null;

    public static class CasesTimeSeries{
        @SerializedName("dailyconfirmed")
        @Expose
        private String dailyconfirmed;
        @SerializedName("dailydeceased")
        @Expose
        private String dailydeceased;
        @SerializedName("dailyrecovered")
        @Expose
        private String dailyrecovered;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("dateymd")
        @Expose
        private String dateymd;
        @SerializedName("totalconfirmed")
        @Expose
        private String totalconfirmed;
        @SerializedName("totaldeceased")
        @Expose
        private String totaldeceased;
        @SerializedName("totalrecovered")
        @Expose
        private String totalrecovered;

        public String getDailyconfirmed() {
            return dailyconfirmed;
        }

        public void setDailyconfirmed(String dailyconfirmed) {
            this.dailyconfirmed = dailyconfirmed;
        }

        public String getDailydeceased() {
            return dailydeceased;
        }

        public void setDailydeceased(String dailydeceased) {
            this.dailydeceased = dailydeceased;
        }

        public String getDailyrecovered() {
            return dailyrecovered;
        }

        public void setDailyrecovered(String dailyrecovered) {
            this.dailyrecovered = dailyrecovered;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDateymd() {
            return dateymd;
        }

        public void setDateymd(String dateymd) {
            this.dateymd = dateymd;
        }

        public String getTotalconfirmed() {
            return totalconfirmed;
        }

        public void setTotalconfirmed(String totalconfirmed) {
            this.totalconfirmed = totalconfirmed;
        }

        public String getTotaldeceased() {
            return totaldeceased;
        }

        public void setTotaldeceased(String totaldeceased) {
            this.totaldeceased = totaldeceased;
        }

        public String getTotalrecovered() {
            return totalrecovered;
        }

        public void setTotalrecovered(String totalrecovered) {
            this.totalrecovered = totalrecovered;
        }
    }

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getConfirmed() {
            return confirmed;
        }

        public void setConfirmed(int confirmed) {
            this.confirmed = confirmed;
        }

        public int getActive() {
            return active;
        }

        public void setActive(int active) {
            this.active = active;
        }

        public int getDeaths() {
            return deaths;
        }

        public void setDeaths(int deaths) {
            this.deaths = deaths;
        }

        public int getRecovered() {
            return recovered;
        }

        public void setRecovered(int recovered) {
            this.recovered = recovered;
        }
    }
}
