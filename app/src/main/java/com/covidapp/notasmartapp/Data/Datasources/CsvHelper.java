package com.covidapp.notasmartapp.Data.Datasources;

import android.content.Context;
import android.util.Log;

import com.covidapp.notasmartapp.Data.Models.DiseaseSample;
import com.covidapp.notasmartapp.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class CsvHelper {

    private List<DiseaseSample> diseaseSamples;
    private Context context;
    private String TAG = "CsvHelper";

    public CsvHelper(Context context){
        diseaseSamples = new ArrayList<>();
        this.context = context;
    }

    public List<DiseaseSample> getdata(){
        if(diseaseSamples.size() >0){
            return diseaseSamples;
        }else{
            return null;
        }

    }
    public boolean loadData(){

        InputStream descriptions = context.getApplicationContext().getResources().openRawResource(R.raw.symptom_description);
        InputStream precautions = context.getApplicationContext().getResources().openRawResource(R.raw.symptom_precaution);

        BufferedReader descReader = new BufferedReader(new InputStreamReader(descriptions, Charset.forName("UTF-8")));
        BufferedReader preReader = new BufferedReader(new InputStreamReader(precautions, Charset.forName("UTF-8")));

        String descLine = "";
        String preLine = "";

        while(true){
            try {
                descLine = descReader.readLine();
                preLine = preReader.readLine();
                if(descLine == null || preLine == null){
                    break;
                }else{
                    String[] descTokens = descLine.split(",");
                    String[] preTokens = preLine.split(",");
                    Log.d(TAG, "loadData: "+descTokens[0]+" "+descTokens[1]+" "+preTokens[1]+" "+preTokens[2]+" "+preTokens[3]+" "+preTokens[4]);
                    DiseaseSample sample = new DiseaseSample(descTokens[0],descTokens[1],preTokens[1],preTokens[2],preTokens[3],preTokens[4]);
                    diseaseSamples.add(sample);

                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

        }

        return true;

    }
}
