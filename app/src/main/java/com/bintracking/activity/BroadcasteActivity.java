package com.bintracking.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bintracking.R;


public class BroadcasteActivity extends AppCompatActivity {
    BroadcasteReceiverExample broadcasteReceiverExample = new BroadcasteReceiverExample();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcaste);
    }
    @Override
    protected void onStart() {
        super.onStart();

        unregisterReceiver(broadcasteReceiverExample);
    }
}
