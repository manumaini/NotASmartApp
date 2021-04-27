package com.covidapp.notasmartapp.Presenters;

import android.content.Context;

import com.covidapp.notasmartapp.Clients.RetrofitClient;
import com.covidapp.notasmartapp.Interfaces.MainContract;
import com.covidapp.notasmartapp.POJO.Hospital;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalPresenter implements MainContract.HospitalPresenter {
    private Context context;
    private MainContract.HospitalView view;
    private static final String TAG = "HospitalPresenter";
    private ArrayList<Hospital> list;

    public HospitalPresenter(Context context, MainContract.HospitalView view) {
        this.context = context;
        this.view = view;
        list = new ArrayList<>();
    }

    @Override
    public void loadHospitals() {
        view.showLoading();
        RetrofitClient.getInstance().getApi().getHsopitals().enqueue(new Callback<Hospital>() {
            @Override
            public void onResponse(Call<Hospital> call, Response<Hospital> response) {
                if(response.isSuccessful()){
                    Hospital hospital = response.body();
                    ArrayList<ArrayList<String>> values = hospital.getValues();
                    view.onSuccess(values);
                    view.hideLoading();

                }
            }

            @Override
            public void onFailure(Call<Hospital> call, Throwable t) {
                view.onFailed(t.getLocalizedMessage());
                view.hideLoading();
            }
        });


    }
}
