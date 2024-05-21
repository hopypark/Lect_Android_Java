package com.example.bindservicedemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "BIND_LOCAL_SERVICE";
    LocalService mService; // LocalService 객체를 받기 위해
    boolean mBound = false; // 연결 상태 확인을 위한 변수
    Button btnService;      // 버튼
    TextView tv_Number; // 무작위수 출력을 위한 텍스트뷰

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_Number = findViewById(R.id.tv_number);
        btnService = findViewById(R.id.btn_RndGen);
        btnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBound){
                    int num = mService.getRandomNumber();
                    tv_Number.setText(""+num);
                }
            }
        });
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected() 호출 - 서비스 객체 가져오기");
            LocalService.LocalBinder binder = (LocalService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected() 호출");
            mBound = false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() 호출 - 서비스를 시작함");
        Intent intent = new Intent(getApplicationContext(), LocalService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() 호출 - 서비스를 중지함");
        if(mBound) {
            unbindService(serviceConnection); mBound = false;
        }
    }
}

