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
import com.example.myapplication.data.mypage.ChangeEmailData;
import com.example.myapplication.data.startpage.FindIdData;
import com.example.myapplication.data.startpage.FindIdResponse;
import com.example.myapplication.data.startpage.FindPwData;
import com.example.myapplication.data.startpage.FindPwResponse;

import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Find_IDPW_Activity extends AppCompatActivity {

    // Email 텍스트
    public String findEmail;

    private ServiceApi service;

    private Button findbackBtn;
    private Button findCBtn_pw;
    private Button findCBtn_id;

    private EditText find_p;

    private EditText find_i;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_idpw);

        findbackBtn = (Button) findViewById(R.id.findbackBtn);
        findCBtn_id = (Button) findViewById(R.id.findCBtn_id);
        findCBtn_pw = (Button) findViewById(R.id.findCBtn_pw);

        find_i = (EditText) findViewById(R.id.find_i);
        find_p = (EditText) findViewById(R.id.find_p);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        // 뒤로가기 버튼 클릭 -> 로그인 화면으로 전환
        findbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 아이디 찾기 버튼 클릭
        findCBtn_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptFindI();
            }
        });

        // 비밀번호 찾기 버튼 클릭
        findCBtn_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptFindP();
            }
        });
    }

    private void attemptFindI() {
        String Email = find_i.getText().toString();
        startFindI(new FindIdData(Email));
    }

    private void startFindI(FindIdData data) {
        service.userFindI(data).enqueue(new Callback<FindIdResponse>() {
            @Override
            public void onResponse(Call<FindIdResponse> call, Response<FindIdResponse> response) {
                FindIdResponse result = response.body();
                Toast.makeText(Find_IDPW_Activity.this, result.getMessage(), Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<FindIdResponse> call, Throwable t) {
                Toast.makeText(Find_IDPW_Activity.this, "아이디 찾기 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("아이디 찾기 에러 발생", t.getMessage());
            }
        });
    }

    private void attemptFindP() {
        String id = find_p.getText().toString();
        startFindP(new FindPwData(id));
    }

    private void startFindP(FindPwData data) {
        service.userFindP(data).enqueue(new Callback<FindPwResponse>() {
            @Override
            public void onResponse(Call<FindPwResponse> call, Response<FindPwResponse> response) {
                FindPwResponse result = response.body();
                Toast.makeText(Find_IDPW_Activity.this, result.getMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<FindPwResponse> call, Throwable t) {
                Toast.makeText(Find_IDPW_Activity.this, "비밀번호 찾기 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("비밀번호 찾기 에러 발생", t.getMessage());
            }
        });
    }
}