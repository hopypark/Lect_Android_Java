package com.example.sensorservicedemo;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    SensorReceiver sensorReceiver;
    Intent intent;  // 센서 서비스를 구동하는데 사용되는 인텐트
    TextView tv_accX, tv_accY, tv_accZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_accX = findViewById(R.id.tv_accX);
        tv_accY = findViewById(R.id.tv_accY);
        tv_accZ = findViewById(R.id.tv_accZ);
        intent = new Intent(getApplicationContext(), SensorService.class);
        startService(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(sensorReceiver != null){
            unregisterReceiver(sensorReceiver);
            sensorReceiver = null;
        }
        stopService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(sensorReceiver != null){
            unregisterReceiver(sensorReceiver);
            sensorReceiver = null;
        }
        stopService(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorReceiver = new SensorReceiver(MainActivity.this);
        IntentFilter filter = new IntentFilter();
        filter.addAction(SensorService.MY_ACTION_SENSOR);
        registerReceiver(sensorReceiver, filter, RECEIVER_EXPORTED);
        startService(intent);
    }
}

