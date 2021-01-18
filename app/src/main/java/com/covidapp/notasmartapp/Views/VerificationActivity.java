package com.covidapp.notasmartapp.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.covidapp.notasmartapp.R;
import com.covidapp.notasmartapp.Views.Main.MainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.thekhaeng.pushdownanim.PushDownAnim;

public class VerificationActivity extends AppCompatActivity implements View.OnClickListener {

    private LottieAnimationView animationView;
    private Button verification;
    private FirebaseUser user;
    private FirebaseAuth auth;
    private Button resend;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        animationView = findViewById(R.id.animationView);
        verification = findViewById(R.id.Verification_button);
        resend = findViewById(R.id.Resend_button);

        //click listener
        PushDownAnim.setPushDownAnimTo(verification,resend).setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.Resend_button:
                user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(VerificationActivity.this,"Verification email has been sent",Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(VerificationActivity.this,"Enable to send verification email",Toast.LENGTH_LONG).show();
                    }
                });
                break;

            case R.id.Verification_button:
                auth  = FirebaseAuth.getInstance();
                Task usertask = auth.getCurrentUser().reload().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        user = auth.getCurrentUser();
                        if(user.isEmailVerified()){
                            animationView.setAnimation(R.raw.verified);
                            Toast.makeText(VerificationActivity.this,"Email Verified",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(VerificationActivity.this, MainActivity.class);
                            intent.putExtra("user_email",user.getEmail());
                            startActivity(intent);

                        }else{
                            Toast.makeText(VerificationActivity.this,"Not Verified",Toast.LENGTH_LONG).show();

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

                break;
        }

    }
}