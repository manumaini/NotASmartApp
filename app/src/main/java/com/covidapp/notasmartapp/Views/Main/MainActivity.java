package com.covidapp.notasmartapp.Views.Main;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.covidapp.notasmartapp.R;
import com.covidapp.notasmartapp.Views.LoginActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseUser user;
    private FirebaseAuth mAuth;

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;

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
        TextView user_email = navigationView.getHeaderView(0).findViewById(R.id.menu_email);
        user_email.setText(getIntent().getStringExtra("user_email"));

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
        if(user == null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.menu_healthNews:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HealthNewsFragment(this)).commit();
                navigationView.setCheckedItem(R.id.menu_healthNews);
                toolbar.setTitle("Health News");
                break;

            case R.id.menu_covidNews:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new CovidFragment()).commit();
                navigationView.setCheckedItem(R.id.menu_covidNews);
                toolbar.setTitle("Co-vid News");
                break;

            case R.id.menu_hospitals:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MapFragment()).commit();
                navigationView.setCheckedItem(R.id.menu_hospitals);
                toolbar.setTitle("Hospitals");
                break;


        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
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