package com.covidapp.notasmartapp.Views.Main;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.covidapp.notasmartapp.R;

public class AssessmentTest extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView questionText;
    private Button optionA,optionB,optionC,optionD,nextButton;
    private String[] questions={"Are you experiencing any of the following symptoms","Have you ever had any of the following",
              "Have you done any of the following in the last 28-45 days","Have you avoided any of the below in public places"};
    private String[][] options={{"Fever or Muscle Pain","Dry Cough or lack of taste","Shortness of Breath","None of the above"},
            {"Diabetes","Lung Disease","Heart Disease","None of the above"},
            {"Visited Internationally","Met anyone who came from foreign country","Met anyone who later on gets COVID positive","None of the above"},
            {"Wearing Masks","Hand Sanitization","Social Distancing","No"}};
    private int score=0,i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_test);
        toolbar = findViewById(R.id.toolbar);
        questionText=findViewById(R.id.questionText);
        optionA=findViewById(R.id.optionMajor1);
        optionB=findViewById(R.id.optionMajor2);
        optionC=findViewById(R.id.optionMajor3);
        optionD=findViewById(R.id.optionMinor);
        nextButton=findViewById(R.id.nextButton);
        nextButton.setVisibility(View.GONE);
        toolbar.setTitle("Self Assessment Test");
            setQuestionAndAnswers();
                optionA.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        restoreColor();
                        optionA.setBackground(getResources().getDrawable(R.drawable.option_selected));
                        optionA.setTextColor(Color.BLACK);
                        handleMajorOptions();
                    }
                });
                optionB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        restoreColor();
                        optionB.setBackground(getResources().getDrawable(R.drawable.option_selected));
                        optionB.setTextColor(Color.BLACK);
                        handleMajorOptions();
                    }
                });
                optionC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        restoreColor();
                        optionC.setBackground(getResources().getDrawable(R.drawable.option_selected));
                        optionC.setTextColor(Color.BLACK);
                        handleMajorOptions();
                    }
                });
                optionD.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        restoreColor();
                        optionD.setBackground(getResources().getDrawable(R.drawable.option_selected));
                        optionD.setTextColor(Color.BLACK);
                        handleMinorOption();
                    }
                });
                nextButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        restoreColor();
                        if(i==3){
                          Toast.makeText(getApplicationContext(),"Finished",Toast.LENGTH_SHORT).show();
                          Intent intent = new Intent(AssessmentTest.this,AssessmentResult.class);
                          intent.putExtra("score",score);
                          startActivity(intent);
                        }
                        else {
                            i++;
                            setQuestionAndAnswers();
                        }
                    }
                });
    };

    void setQuestionAndAnswers(){
        questionText.setText((i+1)+". "+questions[i]+"?");
        optionA.setText("A. "+options[i][0]);
        optionB.setText("B. "+options[i][1]);
        optionC.setText("C. "+options[i][2]);
        optionD.setText("D. "+options[i][3]);
        if(i==2){
            nextButton.setText("Finish");
        }
        else
             nextButton.setText("Next");
    }

    void handleMajorOptions(){
         score = score+5;
         nextButton.setVisibility(View.VISIBLE);
    }
    void handleMinorOption(){
        score+=0;
        nextButton.setVisibility(View.VISIBLE);
    }
    void restoreColor(){
        optionA.setBackground(getResources().getDrawable(R.drawable.options_background));
        optionA.setTextColor(getResources().getColor(R.color.colorWhite));

        optionB.setBackground(getResources().getDrawable(R.drawable.options_background));
        optionB.setTextColor(getResources().getColor(R.color.colorWhite));

        optionC.setBackground(getResources().getDrawable(R.drawable.options_background));
        optionC.setTextColor(getResources().getColor(R.color.colorWhite));

        optionD.setBackground(getResources().getDrawable(R.drawable.options_background));
        optionD.setTextColor(getResources().getColor(R.color.colorWhite));
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder= new AlertDialog.Builder(AssessmentTest.this);
        builder.setMessage(R.string.Assessment_dialog)
                .setPositiveButton(R.string.text_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(AssessmentTest.this,MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),R.string.assessment_cancelled,Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(R.string.text_no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }
}
