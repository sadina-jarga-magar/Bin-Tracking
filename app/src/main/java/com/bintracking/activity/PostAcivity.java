package com.bintracking.activity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.bintracking.data.EventAPI;
import com.bintracking.data.PostList;
import com.bintracking.util.ShakeDetector;
import com.gamepoint.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostAcivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    FirebaseAuth auth;
   ImageView logout;
    FirebaseUser user;
    SwipeRefreshLayout swipe;
    private ShakeDetector mShakeDetector;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        progressBar = findViewById(R.id.progressLoader);
        recyclerView = findViewById(R.id.postList);
        logout = findViewById(R.id.signout);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //getSupportActionBar().setTitle("Game Tournament");
        getSupportActionBar().hide();
        //apply swipe layout
        //set up swipe refresh page
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
                swipe.setRefreshing(false);
            }
        });

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake() {
                swipe.setRefreshing(true);
                getData();
                swipe.setRefreshing(false);
            }
        });
        getData();
        //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //logout current login user by googl firebase
        logout.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                auth.signOut();
                startActivity(new Intent(getApplicationContext(), PostAcivity.class));
                finish();
            }
        });

    }
    private void getData() {
        Call<PostList> postList = EventAPI.getService().getPostList();
        postList.enqueue(new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {
                PostList list = response.body();
                recyclerView.setAdapter(new PostAdapter(PostAcivity.this, list.getItems()));
                progressBar.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {
            //code here
            }
        });
    }
    @Override
    protected void onResume() {
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);

        if (auth.getCurrentUser() == null) {
            startActivity(new Intent(PostAcivity.this, LoginActivity.class));
            finish();
        }
        super.onResume();
    }
    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

}
