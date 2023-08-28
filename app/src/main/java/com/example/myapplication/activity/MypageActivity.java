package com.example.myapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
// 0807 수정
public class MypageActivity extends AppCompatActivity {

    Button mypagebackBtn, changePWBtn, changeEmailBtn, logoutBtn;
    private TextView userIdform;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);

        mypagebackBtn = (Button) findViewById(R.id.mypagebackBtn);
        changePWBtn = (Button) findViewById(R.id.changePWBtn);
        changeEmailBtn = (Button) findViewById(R.id.changeEmailBtn);
        logoutBtn = (Button) findViewById(R.id.logoutBtn);
        userIdform = (TextView) findViewById(R.id.userIdform);

        SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        userIdform.setText( sharedPreferences.getString("user_id", ""));

        // 뒤로가기 버튼 클릭 -> 메인메뉴 화면으로 전환
        mypagebackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 비밀번호 변경 버튼 클릭 -> 비밀번호 변경 화면으로 전환
        changePWBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChangePW_Activity.class);
                startActivity(intent);
            }
        });

        // 이메일 변경 버튼 클릭 -> 이메일 변경 화면으로 전환
        changeEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChangeEmail_Activity.class);
                startActivity(intent);
            }
        });

        // 로그아웃 버튼 클릭 -> 로그인 화면으로 전환
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}