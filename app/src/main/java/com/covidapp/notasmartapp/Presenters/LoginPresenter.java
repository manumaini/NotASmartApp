package com.covidapp.notasmartapp.Presenters;

import android.content.Context;

import androidx.annotation.NonNull;

import com.covidapp.notasmartapp.Interfaces.MainContract;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPresenter implements MainContract.LoginPresenter {

    private Context context;
    private MainContract.LoginView view;
    private FirebaseAuth mAuth;

    public LoginPresenter(Context context, MainContract.LoginView view) {
        this.context = context;
        this.view = view;
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void login(String email, String password) {
        view.showLoading();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    view.onSuccess(user);
                }else{
                    view.onFailed(task.getException().getLocalizedMessage());
                }

                view.hideLoading();

            }
        });
    }

}
