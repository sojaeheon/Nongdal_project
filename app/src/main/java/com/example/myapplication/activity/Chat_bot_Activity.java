package com.example.myapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.MessageAdapter;
import com.example.myapplication.model.Message;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Chat_bot_Activity extends AppCompatActivity {
    private Button c_bot_backBtn;
    RecyclerView recycler_view;
    TextView tv_welcome;
    EditText et_msg;
    ImageButton btn_send;

    List<Message> messageList;
    MessageAdapter messageAdapter;

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();

    private static final String MY_SECRET_KEY = "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";

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
        setContentView(R.layout.chat_bot);


        recycler_view = findViewById(R.id.recycler_view);
        tv_welcome = findViewById(R.id.tv_welcome);
        et_msg = findViewById(R.id.et_msg);
        btn_send = findViewById(R.id.btn_send);
        c_bot_backBtn = (Button) findViewById(R.id.c_bot_backBtn);

        recycler_view.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setStackFromEnd(true);
        recycler_view.setLayoutManager(manager);

        messageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(messageList);
        recycler_view.setAdapter(messageAdapter);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String question = et_msg.getText().toString().trim();
                addToChat(question, Message.SENT_BY_ME);
                et_msg.setText("");
                callAPI(question);
                tv_welcome.setVisibility(View.GONE);
            }
        });

        c_bot_backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 앱 시작 시 이전 대화 기록 불러오기
        String chatHistory = loadChatHistoryFromSharedPreferences();
        if (!chatHistory.isEmpty()) {
            // 이전 대화 기록이 있는 경우, RecyclerView에 추가
            String[] chatMessages = chatHistory.split("\n");
            for (String message : chatMessages) {
                String[] parts = message.split(": ");
                if (parts.length == 2) {
                    String sender = parts[0];
                    String text = parts[1];

                    // 이미 대화 목록에 있는 내용은 중복으로 추가하지 않음
                    if (!isMessageAlreadyAdded(sender, text)) {
                        addToChat(text, sender);
                    }
                }
            }
        }

    }
    // 대화 목록에 이미 있는 메시지인지 확인
    boolean isMessageAlreadyAdded(String sender, String text) {
        for (Message message : messageList) {
            if (message.getSentBy().equals(sender) && message.getMessage().equals(text)) {
                return true;
            }
        }
        return false;
    }
    void addToChat(String message, String sentBy){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageList.add(new Message(message, sentBy));
                messageAdapter.notifyDataSetChanged();
                recycler_view.smoothScrollToPosition(messageAdapter.getItemCount());

                // 대화 기록을 SharedPreferences에 저장
                saveMessageToSharedPreferences(message, sentBy);
            }
        });
    }
    String loadChatHistoryFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyChatHistory", MODE_PRIVATE);
        return sharedPreferences.getString("chat_history", "");
    }
    void addResponse(String response){
        messageList.remove(messageList.size()-1);
        addToChat(response, Message.SENT_BY_BOT);
    }

    void callAPI(String question){
        //okhttp
        messageList.add(new Message("...", Message.SENT_BY_BOT));

        JSONObject object = new JSONObject();
        try {
            object.put("model", "text-davinci-003");
            object.put("prompt", question);
            object.put("max_tokens", 4000);
            object.put("temperature", 0);
        } catch (JSONException e){
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, object.toString());
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/completions")
                .header("Authorization", "Bearer "+MY_SECRET_KEY)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                addResponse("Failed to load response due to "+e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    JSONObject jsonObject = null;
                    try {
                        //아래 body().toString()이 아니라 .string() 주의
                        jsonObject = new JSONObject(response.body().string());
                        JSONArray jsonArray = jsonObject.getJSONArray("choices");
                        String result = jsonArray.getJSONObject(0).getString("text");
                        addResponse(result.trim());
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                } else {
                    addResponse("Failed to load response due to "+response.body().string());
                }
            }
        });
    }
}
