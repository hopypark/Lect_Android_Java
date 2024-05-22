package com.example.sensorservicedemo;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;

public class SensorService extends Service implements SensorEventListener {
    // 상수 선언
    public static final String MY_ACTION_SENSOR = "MY_ACTION_SENSOR";
    public static final String TAG = "SensorService";
    // 센서 관련 변수
    private SensorManager sm;   // 센서 관리자
    private Sensor sensor_acc; // 가속도 센서
    Intent intent = new Intent(MY_ACTION_SENSOR); // 방송과 센서 정보를 담을 인텐트

    public SensorService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        Log.d(TAG, "onCreate() 매서드 호출 - 센서 관리자 생성");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sensor_acc = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this,sensor_acc, SensorManager.SENSOR_DELAY_NORMAL);
        Log.d(TAG, "onStartCommand() 매서드 호출 - 가속도 센서 등록");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sm.unregisterListener(this);
        Log.d(TAG, "onDestroy() 매서드 호출 - 센서 이벤트리스너 등록 해제");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        intent.putExtra("acc", event.values); // 가속도 센서 값을 인텐트에 담는다.
        sendBroadcast(intent);  // 방송한다.
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

