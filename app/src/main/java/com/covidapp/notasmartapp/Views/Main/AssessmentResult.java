package com.covidapp.notasmartapp.Views.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.covidapp.notasmartapp.R;

public class AssessmentResult extends AppCompatActivity {

    ImageView resultimage;
    TextView resultText;
    Button goBack;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_result);
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.your_assessment_result);
        resultimage=findViewById(R.id.resultImage);
        resultText=findViewById(R.id.resultText);
        goBack=findViewById(R.id.goBack);
        Intent intent = getIntent();
        int score=intent.getIntExtra("score",0);
        if(score==0){
            resultimage.setImageResource(R.drawable.safe);
            resultText.setText(R.string.safe_result);
        }
        else if(score<=10){
            resultimage.setImageResource(R.drawable.unsafe);
            resultText.setText(R.string.unsafe_result);
        }
        else {
            resultimage.setImageResource(R.drawable.danger);
            resultText.setText(R.string.danger_result);
        }
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AssessmentResult.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}