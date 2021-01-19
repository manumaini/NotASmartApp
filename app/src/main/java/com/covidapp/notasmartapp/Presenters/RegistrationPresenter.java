package com.covidapp.notasmartapp.Presenters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.covidapp.notasmartapp.Interfaces.MainContract;
import com.covidapp.notasmartapp.Views.RegistrationActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class RegistrationPresenter implements MainContract.RegistrationPresenter {

    private Context context;
    private MainContract.RegistrationView view;
    private FirebaseAuth mAuth;
    private String TAG;

    public RegistrationPresenter(Context context, MainContract.RegistrationView view) {
        this.context = context;
        this.view = view;
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void register(String email, String password) {
        view.showLoading();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Log.d(TAG, "onComplete: success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    //email verification
                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(context,"Verification email has been sent",Toast.LENGTH_LONG).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: verification email not sent");
                        }
                    });

                    view.onSuccess(user);
                }else{
                    view.onFailed(task.getException().getLocalizedMessage());
                }
                view.hideLoading();
            }
        });
    }

    public void firebaseAuthWithGoogle(String idToken){
        view.showLoading();
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("Google", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            view.onSuccess(user);
                        } else {
                            Log.w("Google", "signInWithCredential:failure", task.getException());
                            view.onFailed(task.getException().getLocalizedMessage());
                        }
                    }
                });
        view.hideLoading();
    }

}