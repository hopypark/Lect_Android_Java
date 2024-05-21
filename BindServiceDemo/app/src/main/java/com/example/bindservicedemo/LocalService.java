package com.example.bindservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class LocalService extends Service {
    private static final String TAG = "BIND_LOCAL_SERVICE";
    // Inner
    private final IBinder mBinder = (IBinder) new LocalBinder();
    private final Random random = new Random();

    public class LocalBinder extends Binder{
        LocalService getService(){
            Log.d(TAG, "LocalBinder 클래스의 getService() 호출");
            return LocalService.this;
        }
    }

    public LocalService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    // 난수 생성함수
    public int getRandomNumber(){
        return random.nextInt(100);
    }
}

