package com.covidapp.notasmartapp.Views.Main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CovidFragment extends Fragment {

    private PieChart pieChart;
    private Api api;
    private TextView totalCases,stateNameText;
    private int position=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_covid,container,false);
        pieChart=view.findViewById(R.id.pieChart);
        totalCases=view.findViewById(R.id.totalCases);
        stateNameText=view.findViewById(R.id.stateName);
        stateNameText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                String[] list=getContext().getResources().getStringArray(R.array.choose_state);
                builder.setTitle("Choose a State")
                        .setSingleChoiceItems(list, position, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                position=i;
                            }
                        });
                builder.create();
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api=RetrofitClient.getInstance().getApi();
        Call<List<CovidStateData>> call=api.getAllCovidData();
        call.enqueue((new Callback<List<CovidStateData>>() {
            @Override
            public void onResponse(Call<List<CovidStateData>> call, Response<List<CovidStateData>> response) {
                List<CovidStateData> dataList=response.body();
                for(CovidStateData data:dataList){

                    String stateName=data.state;
                    stateNameText.setText(stateName);

                    int activeCase = data.activeCases;
                    int recoveredCase = data.recoveredCases;
                    int deaths = data.deaths;
                    int confirmed = data.confirmedCases;
                    ArrayList<PieEntry> pieEntries = new ArrayList<>();
                    totalCases.setText("Total: "+confirmed);

                    pieEntries.add(new PieEntry(activeCase,"Active"));
                    pieEntries.add(new PieEntry(recoveredCase,"Recovered"));
                    pieEntries.add(new PieEntry(deaths,"Deaths"));

                    PieDataSet pieDataSet = new PieDataSet(pieEntries, "Analysis");
                    pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                    PieData pieData = new PieData(pieDataSet);

                    pieChart.setData(pieData);
                    pieChart.getDescription().setEnabled(false);
                    pieChart.setCenterText("Analysis of "+stateName);

                    pieChart.animate();
                    return;
                }
            }

            @Override
            public void onFailure(Call<List<CovidStateData>> call, Throwable t) {

            }
        }));

    }
}
