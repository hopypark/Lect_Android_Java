package com.example.sensordemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    ImageView imgSmile; // smile.png
    float iv_width, iv_height = 0f; // 이미지의 가로, 세로 크기
    float x, y; // 이미지의 기준점(왼쪽 상당)이 이동해야할 위치
    // 근접 센서에서 사용할 변수
    TextView tv_distance;   // 센서 거리 정보를 표시할 텍스트뷰
    SensorManager sm;   // 센서 관리자
    Sensor sensor_proximity, sensor_accelerometer;    // 근접 센서용


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 상단의 정보창 제거(배터리, ...)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){ // API >= 30(Android 11, R 이상)
            WindowInsetsController inset = getWindow().getInsetsController();
            if (inset != null) inset.hide(WindowInsets.Type.statusBars());
        }else { // API < 30
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        // 이미지 뷰 처리
        imgSmile = findViewById(R.id.img_smile);
        imgSmile.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        iv_width = imgSmile.getMeasuredWidth();
        iv_height = imgSmile.getMeasuredHeight();
        // 센서 관련 처리
        tv_distance = findViewById(R.id.tv_distance);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor_proximity = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensor_accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 터치한 위치
        int touch_x = (int) event.getX();
        int touch_y = (int) event.getY();
        // 이미지 중심이 터치한 위치로 이동하도록 좌표 재계산
        x = touch_x - iv_width / 2;
        y = touch_y - iv_height / 2;

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                // smile.png 이동
                imgSmile.setX(x);
                imgSmile.setY(y);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this); // 센서 이벤트 리스너를 삭제
    }

    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(this, sensor_proximity, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this, sensor_accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_PROXIMITY){
            tv_distance.setText("거리: " + event.values[0]);
        } else if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            tv_distance.setText("ACC : " + event.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

