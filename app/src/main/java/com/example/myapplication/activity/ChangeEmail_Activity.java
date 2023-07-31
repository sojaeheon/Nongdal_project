package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class ChangeEmail_Activity extends AppCompatActivity {
    // 변경할 Email 텍스트
    public String changeEmail;

    Button mailCbackBtn, mailCBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_email);

        mailCbackBtn = (Button) findViewById(R.id.mailCbackBtn);
        mailCBtn = (Button) findViewById(R.id.mailCBtn);


        // 뒤로가기 버튼 클릭 -> 마이페이지 화면으로 전환
        mailCbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MypageActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 완료 버튼 클릭
        mailCBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}