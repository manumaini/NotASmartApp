package com.covidapp.notasmartapp.Views.Main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.covidapp.notasmartapp.Clients.RetrofitClient;
import com.covidapp.notasmartapp.POJO.CovidStateData;
import com.covidapp.notasmartapp.R;
import com.github.mikephil.charting.charts.PieChart;
import com.google.android.gms.common.api.Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CovidFragment extends Fragment {

    private PieChart pieChart;
    private Api api;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_covid, container, false);
//        pieChart=view.findViewById(R.id.pieChart);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RetrofitClient.getInstance().getApi().getAllCovidData().enqueue(new Callback<CovidStateData>() {
            @Override
            public void onResponse(Call<CovidStateData> call, Response<CovidStateData> response) {

            }

            @Override
            public void onFailure(Call<CovidStateData> call, Throwable t) {

            }
        });
    }
}
