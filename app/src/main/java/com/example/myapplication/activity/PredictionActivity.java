package com.example.myapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.example.myapplication.R;
import com.example.myapplication.activity.MainMenuActivity;
import com.example.myapplication.data.ChartDataItem;
import com.example.myapplication.data.ChartDataResponse;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.ServiceApi;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class PredictionActivity extends AppCompatActivity {

    Button predbackBtn;
    private LineChart lineChart;
    private TextView w1_1;
    private TextView w1_2;
    private TextView w1_3;
    private TextView w1_4;
    private TextView w1_5;

    private TextView w1_7;
    private TextView w1_8;
    private TextView w1_9;
    private TextView w1_10;
    private TextView w1_11;

    private TextView w2_1;
    private TextView w2_2;
    private TextView w2_3;
    private TextView w2_4;
    private TextView w2_5;
    private TextView w2_7;
    private TextView w2_8;
    private TextView w2_9;
    private TextView w2_10;
    private TextView w2_11;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prediction);

        predbackBtn = findViewById(R.id.predbackBtn);

        int[] viewIds_w1 = { R.id.w1_1,R.id.w1_2, R.id.w1_3, R.id.w1_4, R.id.w1_5, R.id.w1_7, R.id.w1_8,
                R.id.w1_9, R.id.w1_10, R.id.w1_11  };
        int[] viewIds_w2 = { R.id.w2_1,R.id.w2_2, R.id.w2_3, R.id.w2_4, R.id.w2_5, R.id.w2_7, R.id.w2_8,
                R.id.w2_9, R.id.w2_10, R.id.w2_11  };
        float[] pre_value={12.3f,14.5f,17.3f,18.3f,18.2f,18.4f,18.3f,18.9f,19.5f,20.1f};

        List<TextView> views_w1 = new ArrayList<>();
        List<TextView> views_w2 = new ArrayList<>();

        for (int viewId : viewIds_w1) {
            TextView view = findViewById(viewId);
            views_w1.add(view);
        }
        for (int viewId : viewIds_w2) {
            TextView view = findViewById(viewId);
            views_w2.add(view);
        }

        ArrayList<Entry> entry_chart1 = new ArrayList<>(); // 데이터를 담을 Arraylist
        ArrayList<Entry> entry_chart2 = new ArrayList<>();

        lineChart = findViewById(R.id.chart);

        LineData chartData = new LineData(); // 차트에 담길 데이터



        for(int i=0;i<10;i++){
            entry_chart1.add(new Entry(i, pre_value[i]));
            TextView value = views_w1.get(i);
            value.setText(Float.toString(pre_value[i]));

        }




        SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        String id = sharedPreferences.getString("user_id", "");

        // Retrofit을 사용하여 데이터를 가져온 후에 다음과 같이 데이터를 처리합니다.
        ServiceApi serviceApi = RetrofitClient.getClient().create(ServiceApi.class);
        Call<ChartDataResponse> call = serviceApi.userPredict(id);

        call.enqueue(new Callback<ChartDataResponse>() {
            @Override
            public void onResponse(Call<ChartDataResponse> call, Response<ChartDataResponse> response) {
                if (response.isSuccessful()) {
                    ChartDataResponse chartDataResponse = response.body();
                    if (chartDataResponse != null) {
                        for (ChartDataItem item : chartDataResponse.getData()) {
                            float value2 = item.getValue2() != null ? item.getValue2() : 0f;
                            entry_chart2.add(new Entry(item.getMonth(), value2));
                        }

                        // 인덱스 기록을 위한 리스트 생성
                        List<Integer> indices = new ArrayList<>();

                        for (int i = 0; i < entry_chart2.size(); i++) {
                            Entry entry = entry_chart2.get(i);
                            float entryValue = entry.getY();

                            // indices 리스트에 인덱스 추가
                            indices.add(i);

                            for (ChartDataItem item : chartDataResponse.getData()) {
                                float value2 = item.getValue2() != null ? item.getValue2() : 0f;

                                // entryValue와 item.getValue2()가 일치하는 경우에만 출력
                                if (entryValue == value2) {
                                    // 표에 값 넣어주기
                                    TextView view = views_w2.get(i);
                                    view.setText(Float.toString(value2));
                                    break; // 찾았으니 루프 종료
                                }
                            }
                        }



                        // 그래프 데이터 설정
                        LineDataSet lineDataSet2 = new LineDataSet(entry_chart2, "성장예측");

                        // 그래프 관련 설정 (색상, 레이블 등)
                        lineDataSet2.setColor(Color.BLACK);

                        chartData.addDataSet(lineDataSet2);

                        lineChart.setData(chartData);
                        lineChart.invalidate(); // 그래프 업데이트
                        Toast.makeText(PredictionActivity.this, "서버 요청 성공", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // 서버 요청 실패 처리
                    // 예: Toast 메시지를 표시하여 사용자에게 알림
                    Toast.makeText(PredictionActivity.this, "서버 요청 실패", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ChartDataResponse> call, Throwable t) {
                // 서버 요청 실패 처리
                // 오류 메시지를 표시하거나 다른 작업을 수행할 수 있습니다.
                // 예: Toast 메시지를 표시하여 사용자에게 알림
                Toast.makeText(PredictionActivity.this, "서버 요청 실패2", Toast.LENGTH_SHORT).show();
            }
        });

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            private final String[] months = new String[]{"9월", "10월", "11월", "12월", "1월", "2월", "3월", "4월", "5월", "6월"};

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                // 데이터 포인트의 x축 값(value)을 사용하여 해당 월의 레이블 반환
                int roundedValue = Math.round(value);
                if (roundedValue >= 0 && roundedValue < months.length) {
                    return months[roundedValue];
                } else {
                    return ""; // 유효하지 않은 범위인 경우 빈 문자열을 반환하거나 다른 처리를 수행할 수 있습니다.
                }
            }
        });

        LineDataSet lineDataSet1 = new LineDataSet(entry_chart1, "우수농가"); // 데이터가 담긴 Arraylist 를 LineDataSet 으로 변환한다.

        lineDataSet1.setColor(Color.RED); // 해당 LineDataSet의 색 설정 :: 각 Line 과 관련된 세팅은 여기서 설정한다.

        chartData.addDataSet(lineDataSet1); // 해당 LineDataSet 을 적용될 차트에 들어갈 DataSet 에 넣는다.


        lineChart.setData(chartData);
        lineChart.invalidate(); // 차트 업데이트
        lineChart.setTouchEnabled(false); // 차트 터치 disable

        // 뒤로가기 버튼 클릭 -> 메인메뉴 화면으로 전환
        predbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

