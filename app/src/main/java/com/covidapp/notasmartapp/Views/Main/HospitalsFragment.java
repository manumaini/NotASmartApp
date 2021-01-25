package com.covidapp.notasmartapp.Views.Main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.covidapp.notasmartapp.Adapters.HospitalAdapter;
import com.covidapp.notasmartapp.Data.Models.Hospital;
import com.covidapp.notasmartapp.Interfaces.MainContract;
import com.covidapp.notasmartapp.Presenters.HospitalPresenter;
import com.covidapp.notasmartapp.R;

import java.util.ArrayList;

public class HospitalsFragment extends Fragment implements MainContract.HospitalView {

    private RecyclerView recyclerView;
    private HospitalPresenter presenter;
    private HospitalAdapter adapter;
    private RelativeLayout loading;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_hospitals,container,false);
        presenter = new HospitalPresenter(getContext(),this);
        adapter = new HospitalAdapter(getContext());
        loading = view.findViewById(R.id.loading_screen);
        recyclerView = view.findViewById(R.id.hospitals_recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter.loadHospitals();
        return view;
    }


    @Override
    public void onSuccess(ArrayList<Hospital> list) {
        adapter.setData(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailed(String error) {
        Toast.makeText(getContext(),error,Toast.LENGTH_LONG).show();

    }

    @Override
    public void showLoading() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loading.setVisibility(View.GONE);
    }
}
