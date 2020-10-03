package com.bintracking.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.gamepoint.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    ImageView firsttab,secondtab,thirdtab,fourtab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        firsttab = findViewById(R.id.blogview);
        secondtab = findViewById(R.id.chatadd);
        thirdtab = findViewById(R.id.eventadd);
        fourtab = findViewById(R.id.mapadd);

        //navigation view hide
        getSupportActionBar().hide();
        //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firsttab.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, PostAcivity.class);
                startActivity(intent);
            }
        });
        secondtab.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });
        thirdtab.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ForgetAndChangePasswordActivity.class).putExtra("Mode", 0));

            }
        });
        fourtab.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });

    }



    //click event for image icon

    @Override
    protected void onResume() {
         if (auth.getCurrentUser() == null) {
            startActivity(new Intent(DashboardActivity.this, LoginActivity.class));
            finish();
        }
        super.onResume();
    }
    @Override
    protected void onPause() {

        super.onPause();
    }


}
