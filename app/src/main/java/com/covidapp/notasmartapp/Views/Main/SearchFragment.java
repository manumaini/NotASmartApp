package com.covidapp.notasmartapp.Views.Main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.covidapp.notasmartapp.Adapters.DiseaseAdapter;
import com.covidapp.notasmartapp.Data.Models.DiseaseSample;
import com.covidapp.notasmartapp.Interfaces.MainContract;
import com.covidapp.notasmartapp.Presenters.SearchPresenter;
import com.covidapp.notasmartapp.R;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements MainContract.SearchView {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private RelativeLayout loading;
    private SearchPresenter presenter;
    private DiseaseAdapter adapter;
    private List<DiseaseSample> items;
    private List<DiseaseSample> results;
    private final String TAG = "SearchFragment";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search,container,false);

        //controllers
        searchView = view.findViewById(R.id.Search_searchView);
        recyclerView = view.findViewById(R.id.Search_rcView);
        loading = view.findViewById(R.id.loading_screen);

        //initialization
        presenter = new SearchPresenter(getContext(),this);
        adapter = new DiseaseAdapter();
        items = new ArrayList<>();
        results = new ArrayList<>();

        //load
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter.loadData();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                Log.d(TAG, "onQueryTextChange: "+s);
                results.clear();

                for(DiseaseSample sample : items){
                    if(sample.getDiseaseName().contains(s)){
                        results.add(sample);
                    }
                }
                adapter.updateData(results);

                return false;
            }
        });

        return view;
    }

    @Override
    public void onSuccess(List<DiseaseSample> list) {
        items = list;
        adapter.updateData(list);

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
