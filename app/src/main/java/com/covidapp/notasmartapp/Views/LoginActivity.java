package com.covidapp.notasmartapp.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.covidapp.notasmartapp.Interfaces.MainContract;
import com.covidapp.notasmartapp.Presenters.LoginPresenter;
import com.covidapp.notasmartapp.R;
import com.covidapp.notasmartapp.Views.Main.MainActivity;
import com.google.firebase.auth.FirebaseUser;
import com.thekhaeng.pushdownanim.PushDownAnim;

public class LoginActivity extends AppCompatActivity implements MainContract.LoginView, View.OnClickListener {

    private EditText email;
    private EditText password;
    private Button login;
    private LoginPresenter presenter;
    private RelativeLayout loading;
    private TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //controllers
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        login = findViewById(R.id.loginButton);
        loading = findViewById(R.id.loading_screen);
        signUp = findViewById(R.id.login_signUp);

        //initialization
        PushDownAnim.setPushDownAnimTo(login, signUp).setOnClickListener(this);
        presenter = new LoginPresenter(this, this);


    }

    @Override
    public void onSuccess(FirebaseUser user) {
        Toast.makeText(this, user.getEmail() + " logged in", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFailed(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
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
        switch (view.getId()) {
            case R.id.loginButton:
                if (email.getText().toString().isEmpty()) {
                    email.setError("Required Field");
                } else if (password.getText().toString().isEmpty()) {
                    password.setError("Required Field");
                } else {
                    presenter.login(email.getText().toString().trim(), password.getText().toString().trim());
                }
                break;

            case R.id.login_signUp:
                startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
                break;
        }

    }
}