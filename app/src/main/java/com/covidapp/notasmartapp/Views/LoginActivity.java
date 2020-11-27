package com.covidapp.notasmartapp.Views;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.covidapp.notasmartapp.Interfaces.MainContract;
import com.covidapp.notasmartapp.R;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements MainContract.LoginView, View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void onSuccess(FirebaseUser user) {

    }

    @Override
    public void onFailed(String error) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onClick(View view) {

    }
}