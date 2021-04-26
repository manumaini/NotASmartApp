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
        toolbar.setTitle("Your Assessment Result");
        resultimage=findViewById(R.id.resultImage);
        resultText=findViewById(R.id.resultText);
        goBack=findViewById(R.id.goBack);
        Intent intent = getIntent();
        int score=intent.getIntExtra("score",0);
        if(score==0){
            resultimage.setImageResource(R.drawable.safe);
            resultText.setText("Your infection risk is low.We recommend you to stay at home to avoid" +
                    "any chance of getting Novel Coronavirus.\n Retake self Assessmentif you develop any sumptoms or come in contact with " +
                    "any of the COVID-19 patient");
        }
        else if(score<=10){
            resultimage.setImageResource(R.drawable.unsafe);
            resultText.setText("According to your self assessment test, you may have been infected by Novel Coronavirus." +
                    "So go to the hospital immediately following all the necessary measures and get it tested.\n" +
                    "Before your result come, keep yourself isolated and dont go outside.");
        }
        else {
            resultimage.setImageResource(R.drawable.danger);
            resultText.setText("According to your self assessment test, it is highly sure that you have been infected" +
                    "by Novel Coronavirus. So isolate yourself and go to nearby hosptal and get it tested.\n" +
                    "Till then Avoid contact and follow all the precautions and measures which you will be given by the hospital");
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