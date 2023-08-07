package com.example.myapplication.activity;

import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.data.mypage.ChangeEmailData;
import com.example.myapplication.data.mypage.ChangeEmailResponse;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeEmail_Activity extends AppCompatActivity {
    // 변경할 Email 텍스트
    private EditText ChangeEmail;

    private ServiceApi service;

    private Button mailCbackBtn;
    private Button mailCBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_email);

        mailCbackBtn = (Button) findViewById(R.id.mailCbackBtn);
        mailCBtn = (Button) findViewById(R.id.mailCBtn);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        ChangeEmail = (EditText) findViewById(R.id.ChangeEmail);
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
                attemptChangeE();
            }
        });
    }

    private void attemptChangeE() {
        SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        ChangeEmail.setError(null);

        String email = ChangeEmail.getText().toString();
        String id = sharedPreferences.getString("user_id", "");

        boolean cancel = false;
        View focusView = null;


        // 이메일의 유효성 검사
        if (email.isEmpty()) {
            ChangeEmail.setError("이메일을 입력해주세요.");
            focusView = ChangeEmail;
            cancel = true;
        } else if (!isEmailValid(email)) {
            ChangeEmail.setError("@를 포함한 유효한 이메일을 입력해주세요.");
            focusView = ChangeEmail;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startChangeE(new ChangeEmailData(email, id));
        }
    }

    private void startChangeE(ChangeEmailData data) {
        service.userChangeE(data).enqueue(new Callback<ChangeEmailResponse>() {
            @Override
            public void onResponse(Call<ChangeEmailResponse> call, Response<ChangeEmailResponse> response) {
                ChangeEmailResponse result = response.body();
                Toast.makeText(ChangeEmail_Activity.this, result.getMessage(), Toast.LENGTH_SHORT).show();

                if (result.getCode() == 200) {
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ChangeEmailResponse> call, Throwable t) {
                Toast.makeText(ChangeEmail_Activity.this, "이메일 변경 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("이메일 변경 에러 발생", t.getMessage());
            }
        });
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }
}