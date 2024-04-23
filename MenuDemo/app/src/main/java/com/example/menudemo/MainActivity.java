package com.example.menudemo;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView tvMenu;
    Button btnConfirm; // 동의하세요 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 컨텍스트메뉴를 TextView에 등록
        tvMenu = findViewById(R.id.tv_textMenu);
        registerForContextMenu(tvMenu);
        // 동의 버튼 클릭 설정
        btnConfirm = findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
                alertBuilder.setMessage("내용에 동의하시겠습니까?");
                // '예'를 선택한 경우
                alertBuilder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "동의하였습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                // '아니오'를 선택한 경우
                alertBuilder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "취소하였습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                //
                AlertDialog alertDialog = alertBuilder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true; // false - not be shown
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       int id = item.getItemId();

       if( id == R.id.menu_apple) {
               Toast.makeText(this, "사과 메뉴를 눌렀습니다.", Toast.LENGTH_SHORT).show();
       } else if (id == R.id.menu_graph) {
               Toast.makeText(this, "포도 메뉴를 눌렀습니다.", Toast.LENGTH_SHORT).show();
       }else if (id == R.id.menu_banana){
                Toast.makeText(this, "바나나 메뉴를눌렀습니다.", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//      super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("코드로 만든 컨텍스트 메뉴");
        menu.add(0,1,1,"배경색: RED");
        menu.add(0,2,2,"배경색: GREEN");
        menu.add(0,3,3,"배경색: BLUE");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if( id == R.id.context_bg_red) {
            tvMenu.setBackgroundColor(Color.BLACK);
        } else if (id == R.id.context_bg_green) {
            tvMenu.setBackgroundColor(Color.GREEN);
        }else if (id == R.id.context_bg_green){
            tvMenu.setBackgroundColor(Color.BLUE);
        }
        return super.onContextItemSelected(item);
    }
}

