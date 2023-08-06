package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import com.example.myapplication.data.DeleteData;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.ServiceApi;

import com.example.myapplication.data.CalendarData;
import com.example.myapplication.data.CalendarResponse;

import com.example.myapplication.data.MemoData;
import com.example.myapplication.data.MemoResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class CalendarActivity extends AppCompatActivity {

    // 성장 정보 값 (1:초장, 2:관부직경, 3:엽장)
    private EditText inputInfo1;
    private EditText inputInfo2;
    private EditText inputInfo3;
    // 성장 일지 텍스트
    private EditText inputdiary;
    private CalendarView calendar;
    private TextView tv_date;

    private Button cldbackBtn;
    private Button cldsaveBtn;

    private Button clddeleteBtn;
    private ServiceApi service;

    private int rYear;
    private int rMonth;
    private int rDay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);

        calendar = findViewById(R.id.calendarView);
        tv_date = findViewById(R.id.tv_date);

        inputInfo1 = findViewById(R.id.inputInfo1);
        inputInfo2 = findViewById(R.id.inputInfo2);
        inputInfo3 = findViewById(R.id.inputInfo3);
        inputdiary = findViewById(R.id.inputdiary);

        cldbackBtn = (Button) findViewById(R.id.cldbackBtn);
        cldsaveBtn = (Button) findViewById(R.id.cldsaveBtn);
        clddeleteBtn =(Button) findViewById(R.id.clddeleteBtn);

        if(inputInfo1.getText().toString().isEmpty() && inputInfo2.getText().toString().isEmpty() && inputInfo3.getText().toString().isEmpty() && inputdiary.getText().toString().isEmpty()){
            clddeleteBtn.setVisibility(View.INVISIBLE);
        }else{
            clddeleteBtn.setVisibility(View.VISIBLE);
        }


        service = RetrofitClient.getClient().create(ServiceApi.class);

        // 뒤로가기 버튼 클릭 -> 메인메뉴 화면으로 전환
        cldbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                startActivity(intent);
                finish();
            }
        });

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                tv_date.setText(year + "년 " + (month + 1) + "월 " + day + "일 선택");
                rYear = year;
                rMonth = month + 1;
                rDay = day;
                startSt(new CalendarData(year, month +1, day));
            }
        });


        // 저장 버튼 클릭
        cldsaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptMemo();
                clddeleteBtn.setVisibility((View.VISIBLE));
            }
        });

        //삭제 버튼 클릭
        clddeleteBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                attemptDelete();
                if(inputInfo1.getText().toString().isEmpty() && inputInfo2.getText().toString().isEmpty() && inputInfo3.getText().toString().isEmpty() && inputdiary.getText().toString().isEmpty() ){
                    clddeleteBtn.setVisibility(View.INVISIBLE);
                }else{
                    clddeleteBtn.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    //삭제버튼 메서드
    private void attemptDelete(){
        String input1 = inputInfo1.getText().toString();
        String input2 = inputInfo2.getText().toString();
        String input3 = inputInfo3.getText().toString();
        String intputd = inputdiary.getText().toString();

        deleteMemo(new DeleteData(rYear, rMonth, rDay, input1, input2, input3, intputd) );

    }

    private void deleteMemo(DeleteData data){
        service.userDelete(data).enqueue(new Callback<CalendarResponse>() {
            @Override
            public void onResponse(Call<CalendarResponse> call, Response<CalendarResponse> response) {
                CalendarResponse result = response.body();

                inputdiary.setText(null);
                inputInfo1.setText(null);
                inputInfo2.setText(null);
                inputInfo3.setText(null);

                Toast.makeText(CalendarActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<CalendarResponse> call, Throwable t) {
                Toast.makeText(CalendarActivity.this, "삭제 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("삭제 에러 발생", t.getMessage());

            }
        });

    }

    private void attemptMemo() {
        String input1 = inputInfo1.getText().toString();
        String input2 = inputInfo2.getText().toString();
        String input3 = inputInfo3.getText().toString();
        String intputd = inputdiary.getText().toString();

        startMemo(new MemoData(rYear, rMonth, rDay, input1, input2, input3, intputd ));
    }

    private void startSt(CalendarData data) {
        service.userSt(data).enqueue(new Callback<CalendarResponse>() {
            @Override
            public void onResponse(Call<CalendarResponse> call, Response<CalendarResponse> response) {
                CalendarResponse result = response.body();

                inputdiary.setText(result.getsText());
                inputInfo1.setText(result.getsinput1());
                inputInfo2.setText(result.getsinput2());
                inputInfo3.setText(result.getsinput3());

                Toast.makeText(CalendarActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<CalendarResponse> call, Throwable t) {
                Toast.makeText(CalendarActivity.this, "정보조회 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("정보조회 에러 발생", t.getMessage());

            }
        });
    }

    private void startMemo(MemoData data) {
        service.userMemo(data).enqueue(new Callback<MemoResponse>() {
            @Override
            public void onResponse(Call<MemoResponse> call, Response<MemoResponse> response) {
                MemoResponse result = response.body();


                Toast.makeText(CalendarActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<MemoResponse> call, Throwable t) {
                Toast.makeText(CalendarActivity.this, "메모 저장 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("메모 저장 에러 발생", t.getMessage());

            }
        });
    }
}