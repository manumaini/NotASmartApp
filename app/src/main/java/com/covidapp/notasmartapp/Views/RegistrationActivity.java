package com.covidapp.notasmartapp.Views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.covidapp.notasmartapp.Interfaces.MainContract;
import com.covidapp.notasmartapp.Presenters.RegistrationPresenter;
import com.covidapp.notasmartapp.R;
import com.covidapp.notasmartapp.Views.Main.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.thekhaeng.pushdownanim.PushDownAnim;

public class RegistrationActivity extends AppCompatActivity implements MainContract.RegistrationView, View.OnClickListener {


    private EditText email;
    private EditText password;
    private Button register;
    private RegistrationPresenter presenter;
    private RelativeLayout loading;
    private EditText phone;
    private SignInButton googleSignUp;
    private GoogleSignInClient googleSignInClient;
    private int RC_SIGN_IN=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //controllers
        email = findViewById(R.id.registration_email);
        password = findViewById(R.id.registration_password);
        register = findViewById(R.id.registerButton);
        loading = findViewById(R.id.loading_screen);
        phone = findViewById(R.id.registration_phone);
        googleSignUp=findViewById(R.id.googleSignUpButton);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient= GoogleSignIn.getClient(RegistrationActivity.this,gso);

        googleSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        //initializations
        PushDownAnim.setPushDownAnimTo(register).setOnClickListener(this);
        presenter = new RegistrationPresenter(this, this);
    }

    @Override
    public void googleSignin(FirebaseUser user) {
        Toast.makeText(this, user.getEmail() + "registered", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
        intent.putExtra("user_email", user.getEmail());
        startActivity(intent);
    }

    @Override
    public void onSuccess(FirebaseUser user) {
        Toast.makeText(this, user.getEmail() + "registered", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(RegistrationActivity.this, VerificationActivity.class);
        intent.putExtra("user_email", user.getEmail());
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
            case R.id.registerButton:
                if (email.getText().toString().isEmpty()) {
                    email.setError("Required Field");
                } else if (password.getText().toString().isEmpty()) {
                    password.setError("Required Field");
                } else if (phone.getText().toString().isEmpty()) {
                    phone.setError("Required Field");
                } else {
                    presenter.register(email.getText().toString().trim(), password.getText().toString().trim());
                }
                break;
        }
    }

    private void signIn(){
        googleSignInClient.signOut();
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleUserAccount(task);
        }
    }

    private void handleUserAccount(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            Log.d("Google", "firebaseAuthWithGoogle:" + account.getId());
            presenter.firebaseAuthWithGoogle(account.getIdToken());
        } catch (ApiException e) {
            Log.w("Google", "Google sign in failed", e);
        }
    }
}