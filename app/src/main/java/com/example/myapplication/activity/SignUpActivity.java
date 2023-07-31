package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myapplication.R;

import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.ServiceApi;
import com.example.myapplication.data.JoinData;
import com.example.myapplication.data.JoinResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class SignUpActivity extends AppCompatActivity {
    // 회원가입 ID, PW, 확인 PW, E-mail 텍스트
    private EditText createID;
    private EditText createPW;
    private EditText checkPW;
    private EditText createEmail;

    private Button signupbackBtn;
    private Button checkIDdupBtn;
    private Button signupsaveBtn;

    private ServiceApi service;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        signupbackBtn = (Button) findViewById(R.id.signupbackBtn);
        checkIDdupBtn = (Button) findViewById(R.id.checkIDdupBtn);
        signupsaveBtn = (Button) findViewById(R.id.signupsaveBtn);

        createID = (EditText) findViewById(R.id.createID);
        createPW = (EditText) findViewById(R.id.createPW);
        checkPW = (EditText) findViewById(R.id.checkPW);
        createEmail = (EditText) findViewById(R.id.createEmail);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        // 뒤로가기 버튼 클릭 -> 로그인 화면으로 전환
        signupbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 저장 버튼 클릭
        signupsaveBtn = findViewById(R.id.signupsaveBtn);
        signupsaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptJoin();
            }
        });
    }

    private void attemptJoin() {
        createID.setError(null);
        createPW.setError(null);
        checkPW.setError(null);
        createEmail.setError(null);

        String id = createID.getText().toString();
        String pw = createPW.getText().toString();
        String email = createEmail.getText().toString();
        String cpw = checkPW.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // 아이디의 유효성 검사
        if (id.isEmpty()) {
            createID.setError("비밀번호를 입력해주세요.");
            focusView = createID;
            cancel = true;
        }
        // 패스워드의 유효성 검사
        if (pw.isEmpty()) {
            createPW.setError("비밀번호를 입력해주세요.");
            focusView = createPW;
            cancel = true;
        } else if (!isPasswordValid(pw)) {
            createPW.setError("6자 이상의 비밀번호를 입력해주세요.");
            focusView = createPW;
            cancel = true;
        }

        // 이메일의 유효성 검사
        if (email.isEmpty()) {
            createEmail.setError("이메일을 입력해주세요.");
            focusView = createEmail;
            cancel = true;
        } else if (!isEmailValid(email)) {
            createEmail.setError("@를 포함한 유효한 이메일을 입력해주세요.");
            focusView = createEmail;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startJoin(new JoinData(id, pw, email));
        }
    }

    private void startJoin(JoinData data) {
        service.userJoin(data).enqueue(new Callback<JoinResponse>() {
            @Override
            public void onResponse(Call<JoinResponse> call, Response<JoinResponse> response) {
                JoinResponse result = response.body();
                Toast.makeText(SignUpActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();

                if (result.getCode() == 200) {
                      finish();
                 }
            }

            @Override
            public void onFailure(Call<JoinResponse> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "회원가입 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("회원가입 에러 발생", t.getMessage());
            }
        });
    }
    private boolean isEmailValid(String email) {
        return email.contains("@");
    }
    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }
}