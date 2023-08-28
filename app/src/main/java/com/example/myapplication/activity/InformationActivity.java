package com.example.myapplication.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.data.Fertilizer;

import java.util.ArrayList;


import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class InformationActivity extends AppCompatActivity {
    Button infobackBtn;

    TextView text;
    EditText cityEditText;
    Button fetchWeatherButton;
    String datas;


    private ArrayList<Fertilizer> data = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* xml과 연결 */
        setContentView(R.layout.information);

        ListView listView = (ListView) findViewById(R.id.Fertilizer_listview);

        /* 서버와 연동했닫면 값을 받아서 띄울 수 있지만,
         * 연동이 되어있지 않으므로
         * 하드코딩으로 값을 집어넣는다.
         * FriendsItem에 정의 한 구조대로 값을 넣을 수 있다.
         */
        data = new ArrayList<>();
        // 1번 아이템
        Fertilizer fertilizer1 = new Fertilizer(R.drawable.longstarplus, "롱스타플러스", "국내최초 질소·칼리 코팅비료! 2세대 완효성 비료의 대표제품!","https://www.farmhannong.com/kor/product/product_ct01/view.do?seq=4396");

        //2번 아이템
        Fertilizer fertilizer2 = new Fertilizer(R.drawable.longstar_k, "롱스타K", "인산 강화 질소·칼리 코팅비료!","https://www.farmhannong.com/kor/product/product_ct01/view.do?seq=5016");
        //3번 아이템
        Fertilizer fertilizer3 = new Fertilizer(R.drawable.longstar_speed, "롱스타K 스피드", "조생종 생육에 딱 맞춘 조생종 전용 완효성 비료","https://www.farmhannong.com/kor/product/product_ct01/view.do?seq=4397");
        //4번 아이템
        Fertilizer fertilizer4 = new Fertilizer(R.drawable.roots, "Roots", "관주용 · 양액재배용 4종 복합비료(액비)","https://www.farmhannong.com/kor/product/product_ct01/view.do?seq=4572");
        //5번 아이템
        Fertilizer fertilizer5 = new Fertilizer(R.drawable.banporo, "반포로32", "고농도 밑거름으로 가지거름 생략!","https://www.farmhannong.com/kor/product/product_ct01/view.do?seq=4218");
        //6번 아이템
        Fertilizer fertilizer6 = new Fertilizer(R.drawable.s_feed, "에스피드(S-Feed)", "작물에 필요한 영양을 빠르게 공급하는 관주용 비료","https://www.farmhannong.com/kor/product/product_ct01/view.do?seq=4392");
        //7번 아이템
        Fertilizer fertilizer7 = new Fertilizer(R.drawable.s_feed_ca, "에스피드칼슘(S-Feed Ca)", "완전 수용성 고효율 칼슘을 다량 함유한 관주용 비료!","https://www.farmhannong.com/kor/product/product_ct01/view.do?seq=5050");
        //8번 아이템
        Fertilizer fertilizer8 = new Fertilizer(R.drawable.eco_micro, "에코마이크로(Eco-Micro)", "고온 · 습해에 강하고 양분 흡수력을 키워주는 엽면시비 전용 4종비료","https://www.farmhannong.com/kor/product/product_ct01/view.do?seq=4521");
        //9번 아이템
        Fertilizer fertilizer9 = new Fertilizer(R.drawable.eco_sol, "에코솔(Eco-sol)", "작물에 필요한 영양을 빠르고 정확히 공급하는 경제적인 노지관주용 비료","https://www.farmhannong.com/kor/product/product_ct01/view.do?seq=4484");
        //10번 아이템
        Fertilizer fertilizer10 = new Fertilizer(R.drawable.n_k, "엔케이30", "드론에도 사용할 수 있는 수도·원예·과수용 웃거름 비료","https://www.farmhannong.com/kor/product/product_ct01/view.do?seq=5349");

        //리스트에 추가
        data.add(fertilizer1);
        data.add(fertilizer2);
        data.add(fertilizer3);
        data.add(fertilizer4);
        data.add(fertilizer5);
        data.add(fertilizer6);
        data.add(fertilizer7);
        data.add(fertilizer8);
        data.add(fertilizer9);
        data.add(fertilizer10);


        /* 리스트 속의 아이템 연결 */
        FertilizerAdapter adapter = new FertilizerAdapter(this, R.layout.friends_item, data);
        listView.setAdapter(adapter);

        /* 아이템 클릭시 작동 */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                /* putExtra의 첫 값은 식별 태그, 뒤에는 다음 화면에 넘길 값 */
                Fertilizer selectedFertilizer = data.get(position);
                String hyperlink = selectedFertilizer.getHyperlink();


                openWebPage(hyperlink);
            }

        });



        infobackBtn = findViewById(R.id.infobackBtn);

        // 뒤로가기 버튼 클릭 -> 메인메뉴 화면으로 전환
        infobackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                startActivity(intent);
                finish();
            }
        });

        text = findViewById(R.id.text);
        cityEditText = findViewById(R.id.cityEditText);
        fetchWeatherButton = findViewById(R.id.fetchWeatherButton);

        fetchWeatherButton.setOnClickListener(v -> {
            String cityName = cityEditText.getText().toString();
            String cityCoordinates = getCityCoordinates(cityName);

            if (cityCoordinates != null) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        datas = getWeatherXmlData(cityCoordinates);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                text.setText(datas);
                            }
                        });
                    }
                }).start();
            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        text.setText("유효하지 않은 도시 이름");
                    }
                });
            }
        });
    }
    String getCityCoordinates(String cityName) {
        HashMap<String, String> cityCoordinatesMap = new HashMap<>();
        cityCoordinatesMap.put("서울", "60 127");
        cityCoordinatesMap.put("부산", "97 74");
        cityCoordinatesMap.put("대구", "89 90");
        cityCoordinatesMap.put("인천", "54 125");
        cityCoordinatesMap.put("광주", "59 74");
        cityCoordinatesMap.put("대전", "68 100");
        cityCoordinatesMap.put("울산", "102 84");
        cityCoordinatesMap.put("세종", "65 107");
        cityCoordinatesMap.put("수원", "60 121");
        cityCoordinatesMap.put("성남", "63 124");
        cityCoordinatesMap.put("의정부", "61 130");
        cityCoordinatesMap.put("안양", "59 123");
        cityCoordinatesMap.put("부천", "57 125");
        cityCoordinatesMap.put("광명", "58 125");
        cityCoordinatesMap.put("평택", "62 114");
        cityCoordinatesMap.put("동두천", "61 134");
        cityCoordinatesMap.put("안산", "58 121");
        cityCoordinatesMap.put("고양", "57 128");
        cityCoordinatesMap.put("과천", "60 123");
        cityCoordinatesMap.put("구리", "62 128");
        cityCoordinatesMap.put("남양주", "64 129");
        cityCoordinatesMap.put("오산", "62 118");
        cityCoordinatesMap.put("시흥", "57 124");
        cityCoordinatesMap.put("군포", "59 122");
        cityCoordinatesMap.put("의왕", "60 122");
        cityCoordinatesMap.put("하남", "64 126");
        cityCoordinatesMap.put("용인", "66 118");
        cityCoordinatesMap.put("파주", "58 131");
        cityCoordinatesMap.put("이천", "69 122");
        cityCoordinatesMap.put("안성", "64 114");
        cityCoordinatesMap.put("김포", "55 128");
        cityCoordinatesMap.put("화성", "59 120");
        cityCoordinatesMap.put("경기광주", "64 123");
        cityCoordinatesMap.put("양주", "60 132");
        cityCoordinatesMap.put("포천", "64 134");
        cityCoordinatesMap.put("여주", "71 121");
        cityCoordinatesMap.put("연천", "61 138");
        cityCoordinatesMap.put("가평", "69 132");
        cityCoordinatesMap.put("양평", "70 125");
        cityCoordinatesMap.put("청주", "70 105");
        cityCoordinatesMap.put("충주", "77 114");
        cityCoordinatesMap.put("제천", "81 118");
        cityCoordinatesMap.put("보은", "73 103");
        cityCoordinatesMap.put("옥천", "74 100");
        cityCoordinatesMap.put("영동", "77 98");
        cityCoordinatesMap.put("증평", "71 110");
        cityCoordinatesMap.put("진천", "69 111");
        cityCoordinatesMap.put("괴산", "72 110");
        cityCoordinatesMap.put("음성", "74 113");
        cityCoordinatesMap.put("단양", "84 117");
        cityCoordinatesMap.put("천안", "62 108");
        cityCoordinatesMap.put("공주", "61 100");
        cityCoordinatesMap.put("보령", "52 103");
        cityCoordinatesMap.put("아산", "60 110");
        cityCoordinatesMap.put("서산", "52 107");
        cityCoordinatesMap.put("논산", "63 96");
        cityCoordinatesMap.put("계룡", "65 98");
        cityCoordinatesMap.put("당진", "55 112");
        cityCoordinatesMap.put("금산", "67 96");
        cityCoordinatesMap.put("부여", "58 99");
        cityCoordinatesMap.put("서천", "55 96");
        cityCoordinatesMap.put("청양", "57 103");
        cityCoordinatesMap.put("홍성", "53 104");
        cityCoordinatesMap.put("예산", "58 107");
        cityCoordinatesMap.put("태안", "46 109");
        cityCoordinatesMap.put("전주", "63 89");
        cityCoordinatesMap.put("군산", "56 92");
        cityCoordinatesMap.put("익산", "60 91");
        cityCoordinatesMap.put("정읍", "58 85");
        cityCoordinatesMap.put("남원", "67 82");
        cityCoordinatesMap.put("김제", "60 89");
        cityCoordinatesMap.put("완주", "66 95");
        cityCoordinatesMap.put("진안", "69 89");
        cityCoordinatesMap.put("장수", "70 85");
        cityCoordinatesMap.put("임실", "64 83");
        cityCoordinatesMap.put("순창", "60 80");
        cityCoordinatesMap.put("고창", "54 78");
        cityCoordinatesMap.put("부안", "55 87");
        cityCoordinatesMap.put("목포", "50 66");
        cityCoordinatesMap.put("여수", "74 63");
        cityCoordinatesMap.put("순천", "67 69");
        cityCoordinatesMap.put("나주", "56 71");
        cityCoordinatesMap.put("광양", "74 71");
        cityCoordinatesMap.put("담양", "61 77");
        cityCoordinatesMap.put("곡성", "66 74");
        cityCoordinatesMap.put("구례", "69 76");
        cityCoordinatesMap.put("고흥", "65 62");
        cityCoordinatesMap.put("보성", "64 69");
        cityCoordinatesMap.put("화순", "59 69");
        cityCoordinatesMap.put("장흥", "59 64");
        cityCoordinatesMap.put("강진", "58 65");
        cityCoordinatesMap.put("해남", "55 61");
        cityCoordinatesMap.put("영암", "54 65");
        cityCoordinatesMap.put("무안", "48 73");
        cityCoordinatesMap.put("함평", "53 72");
        cityCoordinatesMap.put("영광", "53 77");
        cityCoordinatesMap.put("장성", "55 76");
        cityCoordinatesMap.put("완도", "59 58");
        cityCoordinatesMap.put("신안", "50 66");
        cityCoordinatesMap.put("포항", "102 94");
        cityCoordinatesMap.put("경주", "100 91");
        cityCoordinatesMap.put("김천", "80 96");
        cityCoordinatesMap.put("안동", "91 106");
        cityCoordinatesMap.put("구미", "84 96");
        cityCoordinatesMap.put("영주", "89 111");
        cityCoordinatesMap.put("영천", "95 93");
        cityCoordinatesMap.put("상주", "81 102");
        cityCoordinatesMap.put("문경", "81 106");
        cityCoordinatesMap.put("경산", "91 90");
        cityCoordinatesMap.put("군위", "88 99");
        cityCoordinatesMap.put("청송", "96 103");
        cityCoordinatesMap.put("영양", "97 108");
        cityCoordinatesMap.put("영덕", "102 103");
        cityCoordinatesMap.put("청도", "91 86");
        cityCoordinatesMap.put("고령", "83 87");
        cityCoordinatesMap.put("성주", "83 91");
        cityCoordinatesMap.put("칠곡", "85 93");
        cityCoordinatesMap.put("예천", "86 107");
        cityCoordinatesMap.put("봉화", "90 113");
        cityCoordinatesMap.put("울진", "102 115");
        cityCoordinatesMap.put("울릉", "127 127");
        cityCoordinatesMap.put("창원", "90 77");
        cityCoordinatesMap.put("진주", "81 75");
        cityCoordinatesMap.put("통영", "87 68");
        cityCoordinatesMap.put("사천", "80 71");
        cityCoordinatesMap.put("김해", "95 77");
        cityCoordinatesMap.put("밀양", "92 83");
        cityCoordinatesMap.put("거제", "90 69");
        cityCoordinatesMap.put("양산", "97 79");
        cityCoordinatesMap.put("의령", "83 78");
        cityCoordinatesMap.put("함안", "86 77");
        cityCoordinatesMap.put("창녕", "87 83");
        cityCoordinatesMap.put("고성", "85 71");
        cityCoordinatesMap.put("남해", "77 68");
        cityCoordinatesMap.put("하동", "74 73");
        cityCoordinatesMap.put("산청", "76 80");
        cityCoordinatesMap.put("함양", "74 82");
        cityCoordinatesMap.put("거창", "77 86");
        cityCoordinatesMap.put("합천", "81 84");
        cityCoordinatesMap.put("제주", "52 38");
        cityCoordinatesMap.put("춘천", "73 134");
        cityCoordinatesMap.put("원주", "76 122");
        cityCoordinatesMap.put("강릉", "92 131");
        cityCoordinatesMap.put("동해", "97 127");
        cityCoordinatesMap.put("태백", "95 119");
        cityCoordinatesMap.put("속초", "87 141");
        cityCoordinatesMap.put("삼척", "98 125");
        cityCoordinatesMap.put("홍천", "75 130");
        cityCoordinatesMap.put("횡성", "77 125");
        cityCoordinatesMap.put("평창", "84 123");
        cityCoordinatesMap.put("정선", "89 123");
        cityCoordinatesMap.put("철원", "65 139");
        cityCoordinatesMap.put("화천", "72 139");
        cityCoordinatesMap.put("양구", "77 139");
        cityCoordinatesMap.put("고성", "85 145");
        cityCoordinatesMap.put("양양", "88 138");


        // 다른 도시와 좌표를 매핑
        return cityCoordinatesMap.get(cityName);
    }
    String getWeatherXmlData(String cityCoordinates) {
        String serviceKey = "8a3vhnOS2niFP6g3IRrw%2FLknXz2CJKHfFxbFPwbCWl1YVLJCHadWlSgwS5jHHCMGaJaxJW%2F1bcJ1kynMj48p4w%3D%3D";
        String numOfRows = "7";
        String pageNo = "1";

        SimpleDateFormat realTimeFormat = new SimpleDateFormat("yyyyMMddHHmm", Locale.getDefault());
        Date currentTime = new Date();
        String baseDateTime = realTimeFormat.format(currentTime);

        int hour = Integer.parseInt(baseDateTime.substring(8, 10));

        String baseTime = null;
        if (hour >= 0 && hour < 2) {
            baseTime = "2300";
            long previousDayMillis = currentTime.getTime() - 24 * 60 * 60 * 1000;
            Date previousDay = new Date(previousDayMillis);
            baseDateTime = realTimeFormat.format(previousDay);
        } else if (hour >= 2 && hour < 5) {
            baseTime = "0200";
        } else if (hour >= 5 && hour < 8) {
            baseTime = "0500";
        } else if (hour >= 8 && hour < 11) {
            baseTime = "0800";
        } else if (hour >= 11 && hour < 14) {
            baseTime = "1100";
        } else if (hour >= 14 && hour < 17) {
            baseTime = "1400";
        } else if (hour >= 17 && hour < 20) {
            baseTime = "1700";
        } else if (hour >= 20 && hour < 23) {
            baseTime = "2000";
        }

        String[] coordinates = cityCoordinates.split(" ");
        String nx = coordinates[0];
        String ny = coordinates[1];

        String queryUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?serviceKey=" + serviceKey +
                "&numOfRows=" + numOfRows + "&pageNo=" + pageNo + "&base_date=" + baseDateTime.substring(0, 8) + "&base_time=" + baseTime + "&nx=" + nx + "&ny=" + ny;

        StringBuilder buffer = new StringBuilder();
        try {
            URL url = new URL(queryUrl);
            InputStream is = url.openStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new InputStreamReader(is, "UTF-8"));

            int eventType = xpp.getEventType();
            String currentCategory = "";
            String fcstValue = "";

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        String tag = xpp.getName();

                        if (tag.equals("item")) {
                            buffer.append("\n");
                        } else if (tag.equals("category")) {
                            xpp.next();
                            currentCategory = xpp.getText();
                        } else if (tag.equals("fcstValue")) {
                            xpp.next();
                            fcstValue = xpp.getText();
                        }

                        break;
                    case XmlPullParser.TEXT:
                        break;
                    case XmlPullParser.END_TAG:
                        String endTag = xpp.getName();
                        if (endTag.equals("item")) {
                            buffer.append(getCategoryName(currentCategory, fcstValue));
                        }
                        break;
                }
                eventType = xpp.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return buffer.toString();
    }
    String getCategoryName(String category, String fcstValue) {
        String categoryName = "";
        switch (category) {
            case "POP":
                categoryName = "강수확률 : " + fcstValue + "%";
                break;
            case "PTY":
                String ptyStatus = "";
                switch (fcstValue) {
                    case "0":
                        ptyStatus = "없음";
                        break;
                    case "1":
                        ptyStatus = "비";
                        break;
                    case "2":
                        ptyStatus = "비/눈";
                        break;
                    case "3":
                        ptyStatus = "눈";
                        break;
                    default:
                        ptyStatus = "소나기";
                        break;
                }
                categoryName = "강수형태 : " + ptyStatus;
                break;
            case "PCP1":
                categoryName = "1시간 강수량 : " + fcstValue + "mm";
                break;
            case "REH":
                categoryName = " 습도 : " + fcstValue + "%";
                break;
            case "SNO":
                categoryName = "1시간 신적설 : " + fcstValue + "cm";
                break;
            case "SKY":
                String skyStatus = "";
                switch (fcstValue) {
                    case "1":
                        skyStatus = "맑음";
                        break;
                    case "3":
                        skyStatus = "구름많음";
                        break;
                    default:
                        skyStatus = "흐림";
                        break;
                }
                categoryName = "하늘상태 : " + skyStatus;
                break;
            case "TMP":
                categoryName = "1시간 기온 : " + fcstValue + "°C";
                break;
            case "UUU":
                categoryName= "풍속(동서) : " + fcstValue + "m/s";
                break;
            case "VVV":
                categoryName= "풍속(남북) : " + fcstValue + "m/s";
                break;
            case "WAV":
                categoryName= "파고 : " + fcstValue + "M";
                break;
            case "VEC":
                categoryName= "풍향 : " + fcstValue + "deg";
                break;
            case "WSD":
                categoryName= "풍속 : " + fcstValue + "m/s";
                break;
            // 나머지 카테고리도 유사하게 추가
            // ...
            default:
                break;
        }
        return categoryName;


    }

    private void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
        Toast.makeText(InformationActivity.this,"하이퍼링크연결",Toast.LENGTH_SHORT).show();


    }
}