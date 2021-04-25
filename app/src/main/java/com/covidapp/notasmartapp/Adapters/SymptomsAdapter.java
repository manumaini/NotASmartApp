package com.covidapp.notasmartapp.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.covidapp.notasmartapp.POJO.Symptoms;
import com.covidapp.notasmartapp.R;
import com.covidapp.notasmartapp.Views.Main.CovidSymptomsFragment;

import java.util.List;

public class SymptomsAdapter extends RecyclerView.Adapter<SymptomsAdapter.ViewHolder>{

    private List<Symptoms> symptoms;
    private Context context;
    private CovidSymptomsFragment mContext;

    public SymptomsAdapter(Context context,List<Symptoms> symptoms){
        this.context = context;
        this.symptoms=symptoms;
    }

    public SymptomsAdapter(CovidSymptomsFragment mContext,List<Symptoms> symptoms){
        this.mContext=mContext;
        this.symptoms=symptoms;
    }

    @NonNull
    @Override
    public SymptomsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.symptoms_card,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SymptomsAdapter.ViewHolder holder, int position) {
          Symptoms list = symptoms.get(position);
          double percentage = Double.valueOf(list.getPercentage());
          if(percentage>=20.0)
              holder.sympPerc.setBackgroundColor(Color.RED);
          else if(percentage>=10.0&&percentage<=20.0)
              holder.sympPerc.setBackgroundColor(Color.YELLOW);
          else
              holder.sympPerc.setBackgroundColor(Color.GREEN);
          holder.sympName.setText(list.getTypeName());
          holder.sympPerc.setText(list.getPercentage()+"%");
    }

    @Override
    public int getItemCount() {
        return symptoms.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView sympName,sympPerc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sympName=itemView.findViewById(R.id.symptomName);
            sympPerc=itemView.findViewById(R.id.symptomPercentage);
        }
    }
}
