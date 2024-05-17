package com.example.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MusicService extends Service {
    private static final String TAG = "MusicService"; // LogCat을 위한 태그명
    MediaPlayer player; // 음악 재생기 변수

    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.schoolbell);
        player.setLooping(true);
        Log.d(TAG, "onCreate() 호출 - 음악 재생기 생성");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start(); // 재생 시작
        Log.d(TAG, "onStartCommand() 호출 - 음악 재생 서비스 시작");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop(); player.release();
        Log.d(TAG, "onDestroy() 호출 - 음악 재생 서비스 종료");
    }
}

