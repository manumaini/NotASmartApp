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
    private int position=0;

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
                builder.setTitle(getActivity().getResources().getString(R.string.choose_lang)+" ");
                String chosen;
                builder.setSingleChoiceItems(select, position, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                position=which;
                                setLocale("en");
                                getActivity().recreate();
                                break;
                            case 1:
                                position=which;
                                setLocale("hi");
                                getActivity().recreate();
                                break;
                            case 2:
                                position=which;
                                setLocale("mr");
                                getActivity().recreate();
                                break;
                            case 3:
                                position=which;
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

    private void setLocale(String lang){
        locale=new Locale(lang);
        locale.setDefault(locale);
        Configuration conf=new Configuration();
        conf.locale=locale;
        getContext().getResources().updateConfiguration(conf,getContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor=this.getContext().getSharedPreferences("Settings", Context.MODE_PRIVATE).edit();
        editor.putString("lang",lang);
        editor.apply();
    }

    public int loadLocale(){
        SharedPreferences prefs=this.getContext().getSharedPreferences("Settings",Context.MODE_PRIVATE);
        String language=prefs.getString("lang","");
        setLocale(language);
        switch(language){
            case "en":
                return 0;
            case "hi":
                return 1;
            case "mr":
                return 2;
            case "gu":
                return 3;
            default:
                return -1;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}