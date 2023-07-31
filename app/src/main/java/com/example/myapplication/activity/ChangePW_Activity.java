package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class ChangePW_Activity extends AppCompatActivity {

    // 변경할 PW 텍스트
    public String changePW;

    Button pwCbackBtn, pwCBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_pw);

        pwCbackBtn = (Button) findViewById(R.id.pwCbackBtn);
        pwCBtn = (Button) findViewById(R.id.pwCBtn);

        // 뒤로가기 버튼 클릭 -> 마이페이지 화면으로 전환
        pwCbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MypageActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 완료 버튼 클릭
        pwCBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}