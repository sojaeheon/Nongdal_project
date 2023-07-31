package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class Find_IDPW_Activity extends AppCompatActivity {

    // Email 텍스트
    public String findEmail;

    Button findbackBtn, findCBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_idpw);

        findbackBtn = (Button) findViewById(R.id.findbackBtn);
        findCBtn = (Button) findViewById(R.id.findCBtn);

        // 뒤로가기 버튼 클릭 -> 로그인 화면으로 전환
        findbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 완료 버튼 클릭
        findCBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}