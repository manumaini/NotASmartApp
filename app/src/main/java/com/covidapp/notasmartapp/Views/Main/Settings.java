package com.covidapp.notasmartapp.Views.Main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.covidapp.notasmartapp.R;

import java.util.Locale;

public class Settings extends Fragment {

    private Button languageChange;
    private Locale locale;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_settings,container,false);
        languageChange=view.findViewById(R.id.languageChange);
        languageChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] select = {"English", "हिंदी", "मराठी", "ગુજરાતી"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Choose a Language");
                String chosen;
                builder.setSingleChoiceItems(select, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                setLocale("en");
                                getActivity().recreate();
                                break;
                            case 1:
                                setLocale("hi");
                                getActivity().recreate();
                                break;
                            case 2:
                                setLocale("mr");
                                getActivity().recreate();
                                break;
                            case 3:
                                setLocale("gu");
                                getActivity().recreate();
                                break;
                            default:
                        }
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.setCancelable(false);
                dialog.show();
            }});
        return view;
    }

    void setLocale(String lang){
        locale=new Locale(lang);
        locale.setDefault(locale);
        Configuration conf=new Configuration();
        conf.locale=locale;
        getContext().getResources().updateConfiguration(conf,getContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor=getContext().getSharedPreferences("Settings", Context.MODE_PRIVATE).edit();
        editor.putString("lang",lang);
        editor.apply();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}