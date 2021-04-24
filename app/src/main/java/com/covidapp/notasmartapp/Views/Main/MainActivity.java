package com.covidapp.notasmartapp.Views.Main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.covidapp.notasmartapp.R;
import com.covidapp.notasmartapp.Views.LoginActivity;
import com.covidapp.notasmartapp.Views.RegistrationActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseUser user;
    private FirebaseAuth mAuth;

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;

    private final int REQUEST_CALL_CODE=1;
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //controllers
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);


        //initialization
        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        toggle.syncState();
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HealthNewsFragment(this)).commit();
            navigationView.setCheckedItem(R.id.menu_healthNews);
            toolbar.setTitle("Health News");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        TextView user_email = navigationView.getHeaderView(0).findViewById(R.id.menu_email);

        if(user == null){
            Log.d(TAG, "onStart: in here 1");
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        } else if(!user.isEmailVerified()){
            Log.d(TAG, "onStart: in here 2");
            startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            finish();
        }else {
            Log.d(TAG, "onStart: in here 3");
            user_email.setText(user.getEmail());
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.menu_healthNews:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HealthNewsFragment(this)).commit();
                navigationView.setCheckedItem(R.id.menu_healthNews);
                toolbar.setTitle(R.string.Health_News);
                break;

            case R.id.menu_covidNews:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new CovidFragment()).commit();
                navigationView.setCheckedItem(R.id.menu_covidNews);
                toolbar.setTitle(R.string.Covid_News);
                break;

            case R.id.menu_hospitals:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MapFragment()).commit();
                navigationView.setCheckedItem(R.id.menu_hospitals);
                toolbar.setTitle(R.string.Hospital);
                break;

            case R.id.menu_covid_symptoms:
                 getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new CovidSymptomsFragment()).commit();
                 navigationView.setCheckedItem(R.id.menu_covid_symptoms);
                 toolbar.setTitle(R.string.Covid_Symptoms);
                 break;

            case R.id.menu_search:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new SearchFragment()).commit();
                navigationView.setCheckedItem(R.id.menu_search);
                toolbar.setTitle(R.string.Search);
                break;

            case R.id.menu_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Settings()).commit();
                navigationView.setCheckedItem(R.id.menu_settings);
                toolbar.setTitle(R.string.Settings);
                break;

            case R.id.menu_Fhospitals:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HospitalsFragment()).commit();
                navigationView.setCheckedItem(R.id.menu_hospitals);
                toolbar.setTitle(R.string.Hospital);
                break;




        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void callAmbulance(){
        if(ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,
                     new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL_CODE);
        }
        else {
            String call="tel:"+"102";
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(call)));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case REQUEST_CALL_CODE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                }else{
                    Toast.makeText(MainActivity.this,"Permission Required",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.call:
                callAmbulance();
                return true;
            default:
                 return true;
        }
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }
    }
}