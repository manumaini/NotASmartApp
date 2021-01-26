package com.covidapp.notasmartapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.covidapp.notasmartapp.Data.Models.Hospital;
import com.covidapp.notasmartapp.R;

import java.util.ArrayList;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.Holder> {
    private ArrayList<Hospital> list ;
    private Context context;

    public HospitalAdapter(ArrayList<Hospital> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public HospitalAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setData(ArrayList<Hospital> list){
        this.list = list;
    }

    @NonNull
    @Override
    public HospitalAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_hospital,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalAdapter.Holder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.address.setText(list.get(position).getAddress());
        holder.ratingBar.setRating(list.get(position).getRating());
        holder.phone.setText(list.get(position).getPhone());
        holder.beds.setText(String.valueOf(list.get(position).getAvailableBeds()));

        //status open/close
        if (list.get(position).isOpen()){
            holder.statusIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.circle_green));
            holder.statusText.setText("open now");
        }else{
            holder.statusIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.circle_red));
            holder.statusText.setText("closed");
        }

        //photo
        Glide.with(context).load(list.get(position).getImageUrl()).centerCrop().into(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView name;
        private TextView address;
        private RatingBar ratingBar;
        private ImageView statusIcon;
        private TextView statusText;
        private TextView phone;
        private TextView beds;

        public Holder(@NonNull View view) {
            super(view);
            image = view.findViewById(R.id.hospital_image);
            name = view.findViewById(R.id.hospital_name);
            address = view.findViewById(R.id.hospital_address);
            ratingBar = view.findViewById(R.id.hospital_rating);
            statusIcon = view.findViewById(R.id.hospital_statusIcon);
            statusText = view.findViewById(R.id.hospital_statusText);
            phone = view.findViewById(R.id.hospital_phone);
            beds = view.findViewById(R.id.hospital_Beds);
        }
    }
}
