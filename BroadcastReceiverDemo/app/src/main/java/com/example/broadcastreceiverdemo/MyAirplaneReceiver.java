package com.example.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyAirplaneReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        if (intent.getAction().equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)){
            // 비행기 모드에 대한 방송 수신 처리
            boolean state = intent.getBooleanExtra("state", false);
            if (state){
                Log.d(TAG, "ACTION: 비행기 모드 설정됨");
            } else {
                Log.d(TAG, "ACTION: 비행기 모드 해제됨");
            }
        }else if (intent.getAction().equals(MainActivity.MY_ACTION_BROADCAST)){
            String msg = intent.getStringExtra("msg");
            Log.d(TAG,"사용자가 보낸 메시지: " + msg);
        }
    }
}
