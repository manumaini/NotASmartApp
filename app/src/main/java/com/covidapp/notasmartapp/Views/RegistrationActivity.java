package com.covidapp.notasmartapp.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.covidapp.notasmartapp.Interfaces.MainContract;
import com.covidapp.notasmartapp.Presenters.RegistrationPresenter;
import com.covidapp.notasmartapp.R;
import com.google.firebase.auth.FirebaseUser;
import com.thekhaeng.pushdownanim.PushDownAnim;

public class RegistrationActivity extends AppCompatActivity implements MainContract.RegistrationView, View.OnClickListener {


    private EditText email;
    private EditText password;
    private Button register;
    private RegistrationPresenter presenter;
    private RelativeLayout loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //controllers
        email = findViewById(R.id.registration_email);
        password = findViewById(R.id.registration_password);
        register = findViewById(R.id.registerButton);
        loading = findViewById(R.id.loading_screen);

        //initializations
        PushDownAnim.setPushDownAnimTo(register).setOnClickListener(this);
        presenter = new RegistrationPresenter(this,this);
    }

    @Override
    public void onSuccess(FirebaseUser user) {
        Toast.makeText(this, user.getEmail()+"registered", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(RegistrationActivity.this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFailed(String error) {
        Toast.makeText(this,error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        loading.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoading() {
        loading.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.registerButton:
                if(email.getText().toString().isEmpty()){
                    email.setError("Required Field");
                }else if(password.getText().toString().isEmpty()){
                    password.setError("Required Field");
                }else{
                    presenter.register(email.getText().toString(),password.getText().toString());
                }
                break;
        }
    }
}