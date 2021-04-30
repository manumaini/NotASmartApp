package com.covidapp.notasmartapp.Views.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.covidapp.notasmartapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class ContactUs extends AppCompatActivity {

    private EditText userName,userEmail,userQuery;
    private Button submit;
    private String usersQuery="";
    private Toolbar toolbar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        toolbar=findViewById(R.id.toolbar);
        userName=findViewById(R.id.user_name);
        userEmail=findViewById(R.id.user_email);
        userQuery=findViewById(R.id.user_query);
        submit=findViewById(R.id.submit_contact);
        toolbar.setTitle(R.string.contact_us);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(userName.getText().toString().isEmpty()){
//                    userName.setError("Requiered field");
//                }
//                else if(userEmail.getText().toString().isEmpty()){
//                    userEmail.setError("Required field");
//                }
//                else if(userQuery.getText().toString().isEmpty()){
//                    userQuery.setError("Required field");
//                }
//                else {
//                    mAuth.getCurrentUser();
//                }
//            }
//        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}