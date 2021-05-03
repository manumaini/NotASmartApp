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
    private String[] questionsList;
    private String[] optionsAList;
    private String[] optionsBList;
    private String[] optionsCList;
    private String[] optionsDList;
    private int score=0,i=0,major=0,minor=0;

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

        questionsList=getResources().getStringArray(R.array.questions);
        optionsAList=getResources().getStringArray(R.array.optionAList);
        optionsBList=getResources().getStringArray(R.array.optionBList);
        optionsCList=getResources().getStringArray(R.array.optionCList);
        optionsDList=getResources().getStringArray(R.array.optionDList);

        toolbar.setTitle(R.string.self_assessment_test);
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
                            if(major==1)
                                 score+=5;
                            else
                                score+=0;
                            i++;
                            setQuestionAndAnswers();
                        }
                    }
                });
    };

    void setQuestionAndAnswers(){
        questionText.setText((i+1)+". "+questionsList[i]+"?");
        optionA.setText("A. "+optionsAList[i]);
        optionB.setText("B. "+optionsBList[i]);
        optionC.setText("C. "+optionsCList[i]);
        optionD.setText("D. "+optionsDList[i]);
        if(i==3){
            nextButton.setText(R.string.finish);
        }
        else
             nextButton.setText(R.string.next);
    }

    void handleMajorOptions(){
         major=1;
         minor=0;
         nextButton.setVisibility(View.VISIBLE);
    }
    void handleMinorOption(){
        major=0;
        minor=1;
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
