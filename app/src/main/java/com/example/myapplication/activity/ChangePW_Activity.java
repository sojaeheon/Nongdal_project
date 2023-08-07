package com.example.myapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import com.example.myapplication.data.mypage.ChangePwData;
import com.example.myapplication.data.mypage.ChangePwResponse;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePW_Activity extends AppCompatActivity {

    // 변경할 PW 텍스트
    private EditText changePW;

    private Button pwCbackBtn;
    private Button pwCBtn;

    private ServiceApi service;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_pw);

        pwCbackBtn = (Button) findViewById(R.id.pwCbackBtn);
        pwCBtn = (Button) findViewById(R.id.pwCBtn);

        changePW = (EditText) findViewById((R.id.changePW));

        service = RetrofitClient.getClient().create(ServiceApi.class);

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
                attemptChangeP();
            }
        });
    }

    private void attemptChangeP() {
        SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        changePW.setError(null);

        String pw = changePW.getText().toString();
        String id = sharedPreferences.getString("user_id", "");

        boolean cancel = false;
        View focusView = null;


        // 패스워드의 유효성 검사
        if (pw.isEmpty()) {
            changePW.setError("비밀번호를 입력해주세요.");
            focusView = changePW;
            cancel = true;
        } else if (!isPasswordValid(pw)) {
            changePW.setError("6자 이상의 비밀번호를 입력해주세요.");
            focusView = changePW;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startChangeP(new ChangePwData(pw, id));
        }
    }

    private void startChangeP(ChangePwData data) {
        service.userChangeP(data).enqueue(new Callback<ChangePwResponse>() {
            @Override
            public void onResponse(Call<ChangePwResponse> call, Response<ChangePwResponse> response) {
                ChangePwResponse result = response.body();
                Toast.makeText(ChangePW_Activity.this, result.getMessage(), Toast.LENGTH_SHORT).show();

                if (result.getCode() == 200) {
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ChangePwResponse> call, Throwable t) {
                Toast.makeText(ChangePW_Activity.this, "비밀번호 변경 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("비밀번호 변경 에러 발생", t.getMessage());
            }
        });
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }
}