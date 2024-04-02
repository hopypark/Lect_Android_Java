package com.example.activitylifecycle;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("LifeCycle","OnCreate() 메서드 호출");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LifeCycle","onStart() 메서드 호출");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LifeCycle","onStop() 메서드 호출");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LifeCycle","onDestroy() 메서드 호출");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LifeCycle","onPause() 메서드 호출");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LifeCycle","onResume() 메서드 호출");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("LifeCycle","onRestart() 메서드 호출");
    }
}