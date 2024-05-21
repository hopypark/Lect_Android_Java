package com.example.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private BroadcastReceiver airplaneReceiver; // 방송수신자 참조변수
    public static final String MY_ACTION_BROADCAST = "MY_ACTION_BROADCAST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnBroadCast = findViewById(R.id.btn_broadCast);
        btnBroadCast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MY_ACTION_BROADCAST);
                intent.putExtra("msg", "안녕하세요.");
                sendBroadcast(intent);
            }
        });
        airplaneReceiver = new MyAirplaneReceiver();
        IntentFilter filter = new IntentFilter();   // 리시버에서 받고자하는 방송 내용에 대한 필터
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);  // AIRPLANE 모드가 변경되는 것 설정
        filter.addAction(MY_ACTION_BROADCAST);
        // RECEIVER_EXPORT flag는 시스템 또는 유저가 소유한 다른 앱에서 보낸 브로드캐스트를 수신하고자 할 때
        // RECEIVER_NOT_EXPORTED 는 내 앱에서 보낸 브로드캐스트만 수신하고자 할 때
        registerReceiver(airplaneReceiver, filter, RECEIVER_EXPORTED);
    }

    @Override
    protected void onPause() {
        super.onPause();
        IntentFilter filter = new IntentFilter();   // 리시버에서 받고자하는 방송 내용에 대한 필터
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);  // AIRPLANE 모드가 변경되는 것 설정
        filter.addAction(MY_ACTION_BROADCAST);
        registerReceiver(airplaneReceiver, filter, RECEIVER_EXPORTED);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        unregisterReceiver(airplaneReceiver);
    }
}

