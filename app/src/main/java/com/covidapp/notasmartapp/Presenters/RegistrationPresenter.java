package com.covidapp.notasmartapp.Presenters;

import android.content.Context;

import com.covidapp.notasmartapp.Interfaces.MainContract;

public class RegistrationPresenter implements MainContract.RegistrationPresenter {

    private Context context;
    private MainContract.RegistrationView view;

    public RegistrationPresenter(Context context, MainContract.RegistrationView view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void register(String email, String password) {

        

    }
}
