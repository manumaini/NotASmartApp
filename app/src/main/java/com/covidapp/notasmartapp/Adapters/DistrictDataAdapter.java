package com.covidapp.notasmartapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.covidapp.notasmartapp.POJO.CovidStateData;
import com.covidapp.notasmartapp.R;

import java.util.ArrayList;

public class DistrictDataAdapter extends ArrayAdapter<CovidStateData.CovidDistrictData> {

    public DistrictDataAdapter(@NonNull Context context, ArrayList<CovidStateData.CovidDistrictData> object) {
        super(context,0,object);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listView=convertView;
        if(listView==null) {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.district_list_view, parent, false);
        }
        CovidStateData.CovidDistrictData dataPos=getItem(position);

        TextView districtName=listView.findViewById(R.id.districtName);
        districtName.setText(dataPos.name);

        TextView confirmed=listView.findViewById(R.id.confirmedCase);
        confirmed.setText(dataPos.confirmedDistrictCases+"");

        return listView;
    }
}
