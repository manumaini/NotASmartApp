package com.covidapp.notasmartapp.Views.Main;

import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.covidapp.notasmartapp.Clients.RetrofitClient;
import com.covidapp.notasmartapp.Interfaces.Api;
import com.covidapp.notasmartapp.POJO.CovidStateData;
import com.covidapp.notasmartapp.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CovidFragment extends Fragment {

    private PieChart pieChart;
    private Api api;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_covid,container,false);
        pieChart=view.findViewById(R.id.pieChart);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api=RetrofitClient.getInstance().getApi();
        Call<CovidStateData> call=api.getAllCovidData();
        call.enqueue(new Callback<CovidStateData>() {
            @Override
            public void onResponse(Call<CovidStateData> call, Response<CovidStateData> response) {
                for (int i = 0; i < 1; i++) {
                    CovidStateData data = response.body();
                    int activeCase = data.activeCases;
                    int recoveredCase = data.recoveredCases;
                    int deaths = data.deaths;
                    int confirmed = data.confirmedCases;
                    ArrayList<PieEntry> pieEntries = new ArrayList<>();

                    pieEntries.add(new PieEntry(activeCase, "Active"));
                    pieEntries.add(new PieEntry(recoveredCase, "Recovered"));
                    pieEntries.add(new PieEntry(deaths, "Deaths"));

                    PieDataSet pieDataSet = new PieDataSet(pieEntries, "Analysis");
                    pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                    pieDataSet.setValueTextColor(Color.BLACK);
                    pieDataSet.setValueTextSize(16f);

                    PieData pieData = new PieData(pieDataSet);

                    pieChart.setData(pieData);
                    pieChart.getDescription().setEnabled(false);
                    pieChart.setCenterText("Analysis of Maharashtra");
                    pieChart.animate();
                }
            }
            @Override
            public void onFailure(Call<CovidStateData> call, Throwable t) {
                t.getMessage();
                call.cancel();
            }
        });
    }
}
