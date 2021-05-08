package com.covidapp.notasmartapp.Views.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.covidapp.notasmartapp.POJO.Contact;
import com.covidapp.notasmartapp.R;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.firestore.auth.User;

public class ContactUs extends AppCompatActivity {

    private EditText userName,userEmail,userQuery;
    private Button submit;
    private String usersQuery="";
    private Toolbar toolbar;
    private ImageButton gmail,facebook,twitter,instagram;
//    FirebaseDatabase database;
//    DatabaseReference mDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        toolbar=findViewById(R.id.toolbar);
        userName=findViewById(R.id.user_name);
        userEmail=findViewById(R.id.user_email);
        userQuery=findViewById(R.id.user_query);
        submit=findViewById(R.id.submit_contact);
        gmail=findViewById(R.id.gmail_button);
        twitter=findViewById(R.id.twitter_button);
        facebook=findViewById(R.id.facebook_button);
        instagram=findViewById(R.id.instagram_button);
        toolbar.setTitle(R.string.contact_us);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                database=FirebaseDatabase.getInstance();
//                mDatabaseReference=database.getReference();
//                Contact contact = new Contact(userName.getText().toString(),userEmail.getText().toString(),userQuery.getText().toString());
//                mDatabaseReference.child("contact").setValue(contact);
//                userName.setText("");
//                userEmail.setText("");
//                userQuery.setText("");
//                Toast.makeText(getApplicationContext(),"Your Query is sent successfully",Toast.LENGTH_SHORT).show();
//            }
//        });

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri insta = Uri.parse("https://www.instagram.com/invites/contact/?i=dqpek6qk5h8u&utm_content=lyjsc54");
                Intent instaIntent = new Intent(Intent.ACTION_VIEW,insta);
                startActivity(instaIntent);
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri tweet = Uri.parse("https://twitter.com/AppCorona?s=08");
                Intent twitterIntent = new Intent(Intent.ACTION_VIEW,tweet);
                startActivity(twitterIntent);
            }
        });

        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gmailIntent=new Intent(Intent.ACTION_SEND);
                gmailIntent.setType("plain/text");
                gmailIntent.putExtra(Intent.EXTRA_EMAIL,new String[]{"gocorona2021saviour@gmail.com"});
                gmailIntent.setPackage("com.google.android.gm");
                if(gmailIntent.resolveActivity(getPackageManager())!=null){
                    startActivity(gmailIntent);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}