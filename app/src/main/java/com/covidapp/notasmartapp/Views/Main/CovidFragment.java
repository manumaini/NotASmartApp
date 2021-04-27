package com.covidapp.notasmartapp.Views.Main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.covidapp.notasmartapp.Clients.RetrofitClient;
import com.covidapp.notasmartapp.Interfaces.Api;
import com.covidapp.notasmartapp.POJO.CovidStateData;
import com.covidapp.notasmartapp.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import com.covidapp.notasmartapp.Adapters.DistrictDataAdapter;

public class CovidFragment extends Fragment {


    private final static String TAG = "CovidFragment";

    private LineChart confirmedchart;
    private BarChart deceasedChart;
    private Api api;
    private Typeface typeface = Typeface.DEFAULT;
    private TextView totalCases, stateNameText, activeCases, deceasedCases, recoveredCases;
    private int position = 0;
    private String getState = "";
    private RelativeLayout loading;
    private ImageView dropDown;
    private String[] states = {"Maharashtra", "Tamil Nadu", "Karnataka", "Punjab", "Gujarat", "Kerala", "Uttar Pradesh",
            "West Bengal", "Odisha", "Andhra Pradesh", "Delhi", "Telangana", "Rajasthan", "Haryana", "Chhattisgarh",
            "Assam", "Madhya Pradesh", "Jammu and Kashmir", "Uttarakhand", "Goa", "Jharkhand", "Himachal Pradesh",
            "Puducherry", "Tripura", "Manipur", "Chandigarh", "Arunachal Pradesh", "Meghalaya", "Nagaland",
            "Ladakh", "Sikkim", "Andaman and Nicobar Islands", "Mizoram", "Daman and Diu", "Dadra and Nagar Haveli",
            "Lakshadweep"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_covid, container, false);

        stateNameText = view.findViewById(R.id.stateName);
        loading = view.findViewById(R.id.loading_screen);
        dropDown = view.findViewById(R.id.drop_down);
        totalCases = view.findViewById(R.id.totalCases);
        activeCases = view.findViewById(R.id.activeCases);
        deceasedCases = view.findViewById(R.id.deceasedCases);
        recoveredCases = view.findViewById(R.id.recoveredCases);
        confirmedchart = view.findViewById(R.id.confirmedChart);
        deceasedChart = view.findViewById(R.id.BarChart);


        loadData();
        dropDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                String[] list = getActivity().getResources().getStringArray(R.array.choose_state);
                builder.setTitle(getActivity().getResources().getString(R.string.choose_a_state) + "...")
                        .setSingleChoiceItems(list, position, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                position = i;
                                stateNameText.setText(list[position]);
                                getState = (states[position]);
                                updateUI(getState);
                                dialog.cancel();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.setCancelable(true);
                dialog.show();
            }

        });
        return view;
    }

    public void chartsInIt(){
        confirmedchart.setViewPortOffsets(0, 0, 0, 0);
        confirmedchart.setBackgroundColor(getResources().getColor(R.color.colorLightGrey));

        // no description text
        confirmedchart.getDescription().setEnabled(true);

        // enable touch gestures
        confirmedchart.setTouchEnabled(true);

        // enable scaling and dragging
        confirmedchart.setDragEnabled(true);
        confirmedchart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        confirmedchart.setPinchZoom(false);

        confirmedchart.setDrawGridBackground(false);
        confirmedchart.setMaxHighlightDistance(300);
        XAxis x = confirmedchart.getXAxis();
        x.setEnabled(false);

        YAxis y = confirmedchart.getAxisLeft();
        y.setTypeface(typeface);
        y.setLabelCount(6, false);
        y.setTextColor(Color.WHITE);
        y.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        y.setDrawGridLines(false);
        y.setAxisLineColor(Color.WHITE);
        confirmedchart.getAxisRight().setEnabled(true);

        confirmedchart.getLegend().setEnabled(false);

        confirmedchart.animateXY(2000, 2000);

        confirmedchart.invalidate();
    }

    public void setDeceasedChartData(List<CovidStateData.CasesTimeSeries> serieslist){

        ArrayList<BarEntry> values = new ArrayList<>();
        ArrayList<String> xValues = new ArrayList<>();

        XAxis xAxis = deceasedChart.getXAxis();
        xAxis.setLabelRotationAngle(-75);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return xValues.get((int)value);
            }
        });

        for(int i = serieslist.size()-1 ; i >= 0 ; i--){
            values.add(new BarEntry(serieslist.size()-1-i,Integer.parseInt(serieslist.get(i).getDailydeceased())));
            xValues.add(serieslist.get(i).getDateymd());
        }


        BarDataSet set1;
        set1 = new BarDataSet(values, "Deceased Cases");

        set1.setHighLightColor(Color.rgb(244, 117, 117));
        set1.setColor(getResources().getColor(R.color.squareRed));


        BarData data = new BarData(set1);

        data.setValueTypeface(typeface);
        data.setValueTextSize(9f);
        data.setDrawValues(false);

        //animation
        deceasedChart.animateXY(2000, 2000);
        deceasedChart.invalidate();

        // set data
        deceasedChart.setData(data);
        deceasedChart.setVisibleXRangeMaximum(20);
        deceasedChart.moveViewToX(0);

    }

    public void setConfirmedChartData(List<CovidStateData.CasesTimeSeries> serieslist){

        ArrayList<Entry> values = new ArrayList<>();
        ArrayList<Entry> values1 = new ArrayList<>();

        ArrayList<String> xValues = new ArrayList<>();


        for(int i = serieslist.size()-1 ; i >= 0 ; i--){
            values.add(new Entry(serieslist.size()-1-i,Integer.parseInt(serieslist.get(i).getDailyconfirmed())));
            values1.add(new Entry(serieslist.size()-1-i,Integer.parseInt(serieslist.get(i).getDailyrecovered())));

            xValues.add(serieslist.get(i).getDateymd());
        }

        //X-axis
        XAxis xAxis = confirmedchart.getXAxis();
        xAxis.setLabelRotationAngle(-75);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return xValues.get((int)value);
            }
        });

        LineDataSet set1;
        set1 = new LineDataSet(values, "Confirmed Cases");
        set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set1.setCubicIntensity(0.2f);
        set1.setDrawFilled(true);
        set1.setDrawCircles(false);
        set1.setLineWidth(1.8f);
        set1.setCircleRadius(4f);
        set1.setCircleColor(getResources().getColor(R.color.colorPrimary));
        set1.setHighLightColor(Color.rgb(244, 117, 117));
        set1.setColor(getResources().getColor(R.color.colorPrimary));
        set1.setFillColor(getResources().getColor(R.color.colorPrimary));
        set1.setFillAlpha(100);
        set1.setDrawHorizontalHighlightIndicator(false);
        set1.setFillFormatter(new IFillFormatter() {
            @Override
            public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                return confirmedchart.getAxisLeft().getAxisMinimum();
            }
        });

        LineDataSet set2;
        set2 = new LineDataSet(values1, "Recovered Cases");
        set2.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set2.setCubicIntensity(0.2f);
        set2.setDrawFilled(true);
        set2.setDrawCircles(false);
        set2.setLineWidth(1.8f);
        set2.setCircleRadius(4f);
        set2.setCircleColor(getResources().getColor(R.color.squareBlue));
        set2.setHighLightColor(Color.rgb(244, 117, 117));
        set2.setColor(getResources().getColor(R.color.squareBlue));
        set2.setFillColor(getResources().getColor(R.color.squareBlue));
        set2.setFillAlpha(100);
        set2.setDrawHorizontalHighlightIndicator(false);
        set2.setFillFormatter(new IFillFormatter() {
            @Override
            public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                return confirmedchart.getAxisLeft().getAxisMinimum();
            }
        });


        ArrayList<ILineDataSet>dataSets = new ArrayList<>();
        dataSets.add(set1);
        dataSets.add(set2);


        // create a data object with the data sets
        LineData data = new LineData(dataSets);

        data.setValueTypeface(typeface);
        data.setValueTextSize(9f);
        data.setDrawValues(false);

        //animation
        confirmedchart.animateXY(2000, 2000);
        confirmedchart.invalidate();

        // set data
        confirmedchart.setData(data);
        confirmedchart.setVisibleXRangeMaximum(20);
        confirmedchart.moveViewToX(0);
    }

    public void showLoading() {
        loading.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        loading.setVisibility(View.GONE);
    }


    public void loadData() {
        showLoading();
        api = RetrofitClient.getInstance().getApi();
        Call<CovidStateData> call = api.getAllCovidData();
        String[] list = getActivity().getResources().getStringArray(R.array.choose_state);
        Log.d(TAG, "loadData: in covidfragment loaddata");
        call.enqueue(new Callback<CovidStateData>() {
            @Override
            public void onResponse(Call<CovidStateData> call, Response<CovidStateData> response) {
                hideLoading();
                List<CovidStateData.Statewise> dataList = response.body().statewise;
                List<CovidStateData.CasesTimeSeries> serieslist = response.body().cases_time_series;
                Log.d("hello", dataList.toString()+ "    "+ serieslist.toString());

                setConfirmedChartData(serieslist);
                setDeceasedChartData(serieslist);

                stateNameText.setText(list[0]);
                int activeCase = dataList.get(0).active;
                int recoveredCase = dataList.get(0).recovered;
                int deaths = dataList.get(0).deaths;
                int confirmed = dataList.get(0).confirmed;

                totalCases.setText(confirmed + "");
                activeCases.setText(activeCase + "");
                deceasedCases.setText(deaths + "");
                recoveredCases.setText(recoveredCase + "");

            }

            @Override
            public void onFailure(Call<CovidStateData> call, Throwable t) {
                Log.d(TAG, "onFailure: "+ t.getLocalizedMessage());
            }
        });

    }

    private void updateUI(String getState) {
        showLoading();
        api = RetrofitClient.getInstance().getApi();
        Call<CovidStateData> call = api.getAllCovidData();
        call.enqueue(new Callback<CovidStateData>() {
            @Override
            public void onResponse(Call<CovidStateData> call, Response<CovidStateData> response) {
                hideLoading();
                List<CovidStateData.Statewise> dataList = response.body().statewise;
                for (CovidStateData.Statewise state : dataList) {

                    if (state.name.equals(getState)) {
                        String stateName = state.name;
                        int activeCase = state.active;
                        int recoveredCase = state.recovered;
                        int deaths = state.deaths;
                        int confirmed = state.confirmed;
                        totalCases.setText(confirmed + "");
                        activeCases.setText(activeCase + "");
                        deceasedCases.setText(deaths + "");
                        recoveredCases.setText(recoveredCase + "");


                        return;
                    }
                }
            }

            @Override
            public void onFailure(Call<CovidStateData> call, Throwable t) {
            }
        });
    }
}