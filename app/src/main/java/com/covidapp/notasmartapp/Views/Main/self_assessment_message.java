package com.covidapp.notasmartapp.Views.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.covidapp.notasmartapp.R;

public class self_assessment_message extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_assessment_message);
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Starting Self Assessment...");
    }
}