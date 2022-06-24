package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.device.ScanManager;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {


    private static final String SCAN_ACTION = ScanManager.ACTION_DECODE;
    private TextInputEditText input;
    private ScanManager mScanManager;

    private BroadcastReceiver mScanReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent.getStringExtra(SCAN_ACTION);
            if(result != null) {
                input.setText(result);
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}