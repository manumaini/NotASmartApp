package com.covidapp.notasmartapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.covidapp.notasmartapp.Data.Models.DiseaseSample;
import com.covidapp.notasmartapp.R;

import java.util.ArrayList;
import java.util.List;

public class DiseaseAdapter extends RecyclerView.Adapter<DiseaseAdapter.DiseaseHolder> {

    private List<DiseaseSample> samples;

    public DiseaseAdapter() {
        samples = new ArrayList<>();
    }

    public void updateData(List<DiseaseSample> list){
        samples = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DiseaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.layout_disease,parent,false);
        return new DiseaseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiseaseHolder holder, int position) {

        holder.Title.setText(samples.get(position).getDiseaseName());
        holder.Description.setText(samples.get(position).getDiseaseDesc());
        holder.Precautions.setText("> "+samples.get(position).getPrecaution1()
                +"\n> "+samples.get(position).getPrecaution2()
                +"\n> "+samples.get(position).getPrecaution3()
                +"\n> "+samples.get(position).getPrecaution4());


    }

    @Override
    public int getItemCount() {
        return samples.size();
    }

    public class DiseaseHolder extends RecyclerView.ViewHolder {

        private TextView Title;
        private TextView Description;
        private TextView Precautions;
        public DiseaseHolder(@NonNull View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.Disease_title);
            Description = itemView.findViewById(R.id.Disease_desc);
            Precautions = itemView.findViewById(R.id.Disease_precautions);
        }
    }
}
