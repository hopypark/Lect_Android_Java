package com.example.intentdemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // Intent를 받기 위한 ActivityResultLauncher 객체 생성
    ActivityResultLauncher<Intent> activityResultLauncher;
    TextView textView; // SubActivity로부터 받은 텍스트를 표시하기 위한 텍스트뷰

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tvReturn);

        Button btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                activityResultLauncher.launch(intent);
            }
        });
        // ActivityResultLauncher에 SubActivity로부터 데이터를 받을 때의 처리 내용을 등록
        // 어떻게? registerForActivityResult(Contract 자료형, 콜백 매서드)
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK){
                            Intent intent = result.getData();
                            textView.setText(intent.getStringExtra("INPUT_TEXT"));
                        }
                    }
                }
        );
    } // onCreate() 매서드

    // 연락처보기와 전화걸기 - 암시적 인텐트
    public void btnClick(View view){
        Intent intent = null;
        if (view.getId() == R.id.btnContact) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"));
        }else if (view.getId() == R.id.btnCall){
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:(+82)1012341234"));
        }
        if (intent != null) startActivity(intent);
    }

}   // MainActivity 클래스