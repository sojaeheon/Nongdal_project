package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class MainMenuActivity extends AppCompatActivity {

    Button mypageBtn, cldBtn, predBtn, infoBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);

        mypageBtn = (Button) findViewById(R.id.mypageBtn);
        cldBtn = (Button) findViewById(R.id.cldBtn);
        predBtn = (Button) findViewById(R.id.predBtn);
        infoBtn = (Button) findViewById(R.id.infoBtn);

        // 마이페이지 버튼 클릭 -> 마이페이지 화면으로 전환
        mypageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MypageActivity.class);
                startActivity(intent);
            }
        });

        // 캘린더 버튼 클릭 -> 캘린더 화면으로 전환
        cldBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
                startActivity(intent);
            }
        });

        // 성장 예측 버튼 클릭 -> 성장 예측 화면으로 전환
        predBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PredictionActivity.class);
                startActivity(intent);
            }
        });

        // 농업 정보 버튼 클릭 -> 농업 정보 화면으로 전환
        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InformationActivity.class);
                startActivity(intent);
            }
        });
    }
}