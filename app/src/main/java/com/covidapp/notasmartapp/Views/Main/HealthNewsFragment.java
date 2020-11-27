package com.covidapp.notasmartapp.Views.Main;

import android.content.Context;
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

import com.covidapp.notasmartapp.Adapters.HealthNewsAdapter;
import com.covidapp.notasmartapp.Interfaces.MainContract;
import com.covidapp.notasmartapp.POJO.Article;
import com.covidapp.notasmartapp.Presenters.HealthNewsPresenter;
import com.covidapp.notasmartapp.R;

import java.util.List;

public class HealthNewsFragment extends Fragment implements MainContract.HealthNewsView {

    private RecyclerView recyclerView;
    private Context context;
    private HealthNewsAdapter adapter;
    private HealthNewsPresenter presenter;
    private RelativeLayout loading;

    public HealthNewsFragment(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_healthnews,container,false);
        recyclerView = view.findViewById(R.id.healthNews_recyclerView);
        loading = view.findViewById(R.id.loading_screen);
        presenter = new HealthNewsPresenter(context,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new HealthNewsAdapter();
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.loadNews();
    }

    @Override
    public void onSuccess(List<Article> list) {
        adapter.setListData(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailed(String error) {
        Toast.makeText(context,error,Toast.LENGTH_LONG).show();
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
