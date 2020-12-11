package com.covidapp.notasmartapp.Views.Main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.covidapp.notasmartapp.Adapters.DistrictDataAdapter;
import com.covidapp.notasmartapp.Clients.RetrofitClient;
import com.covidapp.notasmartapp.Interfaces.Api;
import com.covidapp.notasmartapp.POJO.CovidStateData;
import com.covidapp.notasmartapp.R;
import com.github.mikephil.charting.animation.Easing;
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
    private String getState="";
    private ListView listView;
    private ArrayList<CovidStateData.CovidDistrictData> districtList;
    private DistrictDataAdapter districtDataAdapter;
    private RelativeLayout loading;
    private String[] states={"Maharashtra","Tamil Nadu","Karnataka","Punjab","Gujarat","Kerala","Uttar Pradesh",
                             "West Bengal","Odisha","Andhra Pradesh","Delhi","Telangana","Rajasthan","Haryana","Chhattisgarh",
                             "Assam","Madhya Pradesh","Jammu and Kashmir","Uttarakhand","Goa","Jharkhand","Himachal Pradesh",
                             "Puducherry","Tripura","Manipur","Chandigarh","Arunachal Pradesh","Meghalaya","Nagaland",
                             "Ladakh","Sikkim","Andaman and Nicobar Islands","Mizoram","Daman and Diu","Dadra and Nagar Haveli",
                             "Lakshadweep"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_covid,container,false);
        pieChart=view.findViewById(R.id.pieChart);
        totalCases=view.findViewById(R.id.totalCases);
        stateNameText=view.findViewById(R.id.stateName);
        listView=view.findViewById(R.id.listView);
        loading = view.findViewById(R.id.loading_screen);
        districtList=new ArrayList<>();
        stateNameText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                String[] list=getActivity().getResources().getStringArray(R.array.choose_state);
                builder.setTitle(getActivity().getResources().getString(R.string.choose_a_state)+"...")
                        .setSingleChoiceItems(list, position, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                position=i;
                                stateNameText.setText(list[position]);
                                getState=(states[position]);
                                updateUI(getState);
                                dialog.cancel();
                            }
                        });
                AlertDialog dialog=builder.create();
                dialog.setCancelable(true);
                dialog.show();
            }

        });
        return view;
    }

    public void showLoading(){
       loading.setVisibility(View.VISIBLE);
    }

    public void hideLoading(){
        loading.setVisibility(View.GONE);
    }




    public void loadData(){
        showLoading();
        api=RetrofitClient.getInstance().getApi();
        Call<List<CovidStateData>> call=api.getAllCovidData();
        call.enqueue((new Callback<List<CovidStateData>>() {
            @Override
            public void onResponse(Call<List<CovidStateData>> call, Response<List<CovidStateData>> response) {
                List<CovidStateData> dataList=response.body();
                for(CovidStateData data:dataList){
                    hideLoading();

                    String stateName=data.state;
                    stateNameText.setText(stateName);

                    int activeCase = data.activeCases;
                    int recoveredCase = data.recoveredCases;
                    int deaths = data.deaths;
                    int confirmed = data.confirmedCases;
                    ArrayList<PieEntry> pieEntries = new ArrayList<>();
                    totalCases.setText(getActivity().getResources().getString(R.string.Total)+" : "+confirmed);

                    pieEntries.add(new PieEntry(activeCase,"Active"));
                    pieEntries.add(new PieEntry(recoveredCase,"Recovered"));
                    pieEntries.add(new PieEntry(deaths,"Deaths"));

                    PieDataSet pieDataSet = new PieDataSet(pieEntries,"Analysis");
                    pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                    pieDataSet.setSliceSpace(1f);
                    pieDataSet.setSelectionShift(3f);
                    pieDataSet.setValueTextSize(13f);

                    PieData pieData = new PieData(pieDataSet);

                    pieChart.setData(pieData);
                    pieChart.getDescription().setEnabled(false);
                    pieChart.setDrawHoleEnabled(false);
                    pieChart.animateY(1000,Easing.EaseInCubic);
                    pieChart.setDragDecelerationFrictionCoef(0.67f);
                    pieChart.setDrawEntryLabels(false);
                    pieChart.setExtraOffsets(5,10,5,5);

                    List<CovidStateData.CovidDistrictData> district=data.districtData;
                    for(CovidStateData.CovidDistrictData dData : district){
                        String dName=dData.name;
                        int dConfirmed=dData.confirmedDistrictCases;
                        districtList.add(new CovidStateData.CovidDistrictData(dName,dConfirmed));
                    }
                    districtDataAdapter=new DistrictDataAdapter(getContext(),districtList);
                    listView.setAdapter(districtDataAdapter);
                    return;
                }
            }

            @Override
            public void onFailure(Call<List<CovidStateData>> call, Throwable t) {

            }
        }));
    }

    private void updateUI(String getState) {
        showLoading();
        api = RetrofitClient.getInstance().getApi();
        Call<List<CovidStateData>> call = api.getAllCovidData();
        call.enqueue(new Callback<List<CovidStateData>>() {
            @Override
            public void onResponse(Call<List<CovidStateData>> call, Response<List<CovidStateData>> response) {
                hideLoading();
                List<CovidStateData> dataList=response.body();
                for(CovidStateData data:dataList) {

                    if(data.state.equals(getState)) {
                        String stateName = data.state;
                        stateNameText.setText(stateName);

                        int activeCase = data.activeCases;
                        int recoveredCase = data.recoveredCases;
                        int deaths = data.deaths;
                        int confirmed = data.confirmedCases;
                        ArrayList<PieEntry> pieEntries = new ArrayList<>();
                        totalCases.setText(getActivity().getResources().getString(R.string.Total)+" : " + confirmed);

                        pieEntries.add(new PieEntry(activeCase, "Active"));
                        pieEntries.add(new PieEntry(recoveredCase, "Recovered"));
                        pieEntries.add(new PieEntry(deaths, "Deaths"));

                        PieDataSet pieDataSet = new PieDataSet(pieEntries,"Analysis");
                        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                        pieDataSet.setSliceSpace(1f);
                        pieDataSet.setSelectionShift(2f);
                        pieDataSet.setValueTextSize(13f);

                        PieData pieData = new PieData(pieDataSet);

                        pieChart.setData(pieData);
                        pieChart.animateY(1000,Easing.EaseInCubic);
                        pieChart.getDescription().setEnabled(false);
                        pieChart.setDrawHoleEnabled(false);
                        pieChart.setDragDecelerationFrictionCoef(0.67f);
                        pieChart.setExtraOffsets(5,10,5,5);
                        pieChart.notifyDataSetChanged();

                        districtList.clear();
                        List<CovidStateData.CovidDistrictData> district=data.districtData;
                        for(CovidStateData.CovidDistrictData dData : district){
                            String dName=dData.name;
                            int dConfirmed=dData.confirmedDistrictCases;
                            districtList.add(new CovidStateData.CovidDistrictData(dName,dConfirmed));
                        }
                        districtDataAdapter=new DistrictDataAdapter(getContext(),districtList);
                        listView.setAdapter(districtDataAdapter);
                        return;
                    }
                }
            }
            @Override
            public void onFailure(Call<List<CovidStateData>> call, Throwable t) {

            }
        });
    }
}
