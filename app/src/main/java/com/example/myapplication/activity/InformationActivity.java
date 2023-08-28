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

public class InformationActivity extends AppCompatActivity {
    Button infobackBtn;

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


    }

    private void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
        Toast.makeText(InformationActivity.this,"하이퍼링크연결",Toast.LENGTH_SHORT).show();


    }
}