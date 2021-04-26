package com.covidapp.notasmartapp.Views.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.covidapp.notasmartapp.R;

public class self_assessment_message extends AppCompatActivity {
    private Toolbar toolbar;
    private Button startTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_assessment_message);
        toolbar=findViewById(R.id.toolbar);
        startTest=findViewById(R.id.start_assessment);
        toolbar.setTitle("Starting Self Assessment...");
        startTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(self_assessment_message.this,AssessmentTest.class);
                startActivity(intent);
            }
        });
    }
}