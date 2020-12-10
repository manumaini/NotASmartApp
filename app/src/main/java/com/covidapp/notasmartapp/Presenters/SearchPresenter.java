package com.covidapp.notasmartapp.Presenters;

import android.content.Context;

import com.covidapp.notasmartapp.Data.Datasources.CsvHelper;
import com.covidapp.notasmartapp.Interfaces.MainContract;

public class SearchPresenter implements MainContract.SearchPresenter {

    private Context context;
    private MainContract.SearchView view ;
    private CsvHelper csvHelper;

    public SearchPresenter(Context context, MainContract.SearchView view) {
        this.context = context;
        this.view = view;
        csvHelper = new CsvHelper(context);
    }

    @Override
    public void loadData() {
        view.showLoading();
        if(csvHelper.loadData()){
            view.onSuccess(csvHelper.getdata());
            view.hideLoading();
        }else{
            view.onFailed("Can't load data");
            view.hideLoading();
        }


    }

    @Override
    public void getData() {

    }
}
