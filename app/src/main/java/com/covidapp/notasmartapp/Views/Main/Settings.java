package com.covidapp.notasmartapp.Views.Main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.covidapp.notasmartapp.R;
import com.covidapp.notasmartapp.Views.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.Locale;

public class Settings extends Fragment implements View.OnClickListener {

    private Button languageChange;
    private Locale locale;
    private Button signOut,contactUs;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_settings,container,false);
        languageChange=view.findViewById(R.id.languageChange);
        signOut = view.findViewById(R.id.Settings_logout);
        contactUs=view.findViewById(R.id.contact_us);


        PushDownAnim.setPushDownAnimTo(signOut).setOnClickListener(this);
        languageChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] select = {"English", "हिंदी", "मराठी", "ગુજરાતી"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(getActivity().getResources().getString(R.string.choose_lang)+" ");
                builder.setSingleChoiceItems(select,-1, new DialogInterface.OnClickListener() {
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
        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contactIntent = new Intent(getContext(),ContactUs.class);
                startActivity(contactIntent);
            }
        });
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

//    public int loadLocale(){
//        SharedPreferences prefs=this.getContext().getSharedPreferences("Settings",Context.MODE_PRIVATE);
//        String language=prefs.getString("lang","");
//        setLocale(language);
//        switch(language){
//            case "en":
//                return 0;
//            case "hi":
//                return 1;
//            case "mr":
//                return 2;
//            case "gu":
//                return 3;
//            default:
//                return -1;
//        }
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Settings_logout:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                break;
        }

    }
}