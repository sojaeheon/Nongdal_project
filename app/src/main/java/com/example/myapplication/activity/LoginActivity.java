package com.example.myapplication.activity;

//안녕 나는 소재헌
//안녕 나는 박재형
//이경민
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;

import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.ServiceApi;
import com.example.myapplication.data.startpage.LoginData;
import com.example.myapplication.data.startpage.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
//    public static final int REQUEST_CODE_MENU = 100;

    // ID, PW 텍스트
    private EditText loginID;
    private EditText loginPW;
    private ServiceApi service;

    private Button loginBtn;
    private Button signupBtn;
    private Button findBtn;

    // 사용자의 대화 기록을 SharedPreferences에 저장하는 메서드
    void saveMessageToSharedPreferences(String message, String sentBy) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyChatHistory", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // 현재 대화 기록 불러오기
        String chatHistory = sharedPreferences.getString("chat_history", "");

        // 새로운 메시지 추가
        chatHistory += sentBy + ": " + message + "\n";

        // 수정된 대화 기록 저장
        editor.putString("chat_history", chatHistory);
        editor.apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);

        loginBtn = (Button)findViewById(R.id.loginBtn);
        signupBtn = (Button)findViewById(R.id.signupBtn);
        findBtn = (Button)findViewById(R.id.findBtn);

        loginID = (EditText)findViewById(R.id.loginID);
        loginPW = (EditText)findViewById(R.id.loginPW);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        // 로그인 버튼 클릭 -> 메인메뉴 화면으로 전환
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("user_id",loginID.getText().toString()); // 로그인한 사용자의 아이디를 저장
                editor.apply();
            }
        });

        // 회원가입 버튼 클릭 -> 회원가입 화면으로 전환
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });

        // ID/PW 찾기 버튼 클릭 -> ID/PW 찾기 화면으로 전환
        findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Find_IDPW_Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void attemptLogin() {
        loginID.setError(null);
        loginPW.setError(null);

        String id = loginID.getText().toString();
        String pw = loginPW.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // 패스워드 유효성 검사
        if (pw.isEmpty()) {
            loginPW.setError("비밀번호를 입력해주세요.");
            focusView = loginPW;
            cancel = true;
        } else if (!isPasswordValid(pw)) {
            loginPW.setError("6자 이상의 비밀번호를 입력해주세요.");
            focusView = loginPW;
            cancel = true;
        }

        if (id.isEmpty()) {
            loginID.setError("아이디를 입력해주세요.");
            focusView = loginID;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startLogin(new LoginData(id, pw));
        }
    }

    private void startLogin(LoginData data) {
        service.userLogin(data).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse result = response.body();
                Toast.makeText(LoginActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();

                if (result.getCode() == 200) {
                    // 로그인 성공 시 메인 페이지
                    Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                    startActivity(intent);
                    finish();

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());
            }
        });
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }
}