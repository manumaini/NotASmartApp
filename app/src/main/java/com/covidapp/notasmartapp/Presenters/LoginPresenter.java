package com.covidapp.notasmartapp.Presenters;

import android.content.Context;

import com.covidapp.notasmartapp.Interfaces.MainContract;

public class LoginPresenter implements MainContract.LoginPresenter {

    private Context context;
    private MainContract.LoginView view;

    public LoginPresenter(Context context, MainContract.LoginView view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void login(String email, String password) {

    }
}
