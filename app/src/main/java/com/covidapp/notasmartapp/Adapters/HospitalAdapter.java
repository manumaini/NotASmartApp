package com.covidapp.notasmartapp.Adapters;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.covidapp.notasmartapp.R;
import com.covidapp.notasmartapp.Views.Main.MainActivity;

import java.util.ArrayList;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.Holder> {
    private ArrayList<ArrayList<String> > list ;
    private Context context;

    public HospitalAdapter(ArrayList<ArrayList<String>> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public HospitalAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setData(ArrayList<ArrayList<String>> list){
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
        holder.name.setText(list.get(position).get(0));
        holder.address.setText(list.get(position).get(1));
        holder.beds.setText(list.get(position).get(4)+"/"+list.get(position).get(3));
        holder.phone.setText(list.get(position).get(5));
        holder.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(list.get(position).get(2)));
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private ImageView next;
        private TextView name;
        private TextView address;
        private TextView phone;
        private TextView beds;

        public Holder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.hospital_name);
            address = view.findViewById(R.id.hospital_address);
            phone = view.findViewById(R.id.hospital_phone);
            beds = view.findViewById(R.id.hospital_Beds);
            next = view.findViewById(R.id.hospital_next);
        }
    }
}
