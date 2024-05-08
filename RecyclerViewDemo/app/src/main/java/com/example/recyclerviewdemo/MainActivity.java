package com.example.recyclerviewdemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    final String[] heroList = {
            "Hero01","Hero02","Hero03","Hero04","Hero05","Hero06","Hero07","Hero08","Hero09","Hero10",
            "Hero11","Hero12","Hero13","Hero14","Hero15","Hero16","Hero17","Hero18","Hero19","Hero20",
            "Hero21","Hero22","Hero23","Hero24","Hero25","Hero26","Hero27","Hero28","Hero29","Hero30",
            "Hero31","Hero32","Hero33","Hero34","Hero35","Hero36","Hero37","Hero38","Hero39","Hero40",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        HeroListAdapter adapter = new HeroListAdapter(Arrays.asList(heroList));
        recyclerView.setAdapter(adapter);
    }
}

