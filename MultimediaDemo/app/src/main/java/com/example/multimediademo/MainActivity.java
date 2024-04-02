package com.example.multimediademo;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer = new MediaPlayer();
    // 비디오뷰 추가
    VideoView videoView = null; // 비디오 재생을 위한 비디오뷰 인스턴스 생성

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = findViewById(R.id.videoView); // 화면의 VideoView와 연결
        Button btnAudio = findViewById(R.id.btn_audio); // 화면의 오디오 재생 버튼과 연결
        btnAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( mediaPlayer.isPlaying())
                    mediaPlayer.pause();
                else {
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.hare);
                    mediaPlayer.setLooping(true);
                    mediaPlayer.start();
                }
            }
        });

        Button btnVideo = findViewById(R.id.btn_video);
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()){
                    videoView.pause();
                }else {
                    Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/movie");
                    videoView.setVideoURI(uri); // uri 위치에 있는 영상을 VideoView에 소스로 설정
                    videoView.start();  // 재싱 시작
                    videoView.setVisibility(View.VISIBLE);  // VideoView화면에 보이도록 설정
                    // 미디어 컨트롤러 추가
                    MediaController mediaController = new MediaController(getApplicationContext());
                    videoView.setMediaController(mediaController); // 비디오뷰에 컨트롤러 등록
                }
            }
        }); //
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop(); // 재생 중지
            mediaPlayer.release(); // 메모리 리소수 해제
            mediaPlayer = null; // null 처리
        }
        if (videoView.isPlaying()){
            videoView.pause();
            videoView = null;
        }
    }
}