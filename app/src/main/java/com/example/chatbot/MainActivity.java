package com.example.chatbot;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ChatMsgAdapter adp;
    Button btnSend;
    EditText etMsg;
    List<ChatMsg> chatMsgList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //뷰 객체 연결
        recyclerView = findViewById(R.id.recyclerView);
        btnSend = findViewById(R.id.btn_send);
        etMsg = findViewById(R.id.et_msg);

        //채팅 메시지 데이터를 담을 list 생성
        chatMsgList = new ArrayList<>();
        //리사이클러뷰 초기화
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adp = new ChatMsgAdapter();
        recyclerView.setAdapter(adp);

        //메시지 전송버튼 클릭 리스너 설정 (람다식으로 작성함)
        btnSend.setOnClickListener(v -> {
            //etMsg에 쓰여있는 텍스트를 가져옵니다.
            String msg = etMsg.getText().toString();
            //새로운 ChatMsg 객체를 생성하여 어댑터에 추가합니다.
            adp.addChatMsg(new ChatMsg(ChatMsg.TYPE_MY_CHAT, msg));
            //etMsg의 텍스트를 초기화합니다.
            etMsg.setText(null);
            //키보드를 내립니다.
            InputMethodManager manager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        });

        //EditText 객체에 text가 변경될 때 실행될 리스너 설정
        etMsg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //입력창에 메시지가 입력되었을 때만 버튼이 클릭 가능하도록 설정
                btnSend.setEnabled(s.length() > 0);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        // 테스트를 위한 더미 데이터 생성

        // i가 짝수일 경우 내 메시지, 홀수일 경우 챗봇의 메시지로 생성되도록 10개의 채팅메시지 객체를 만들어 리스트에 넣습니다.
        for (int i = 0; i < 10; i++) {
            chatMsgList.add(new ChatMsg(i % 2, "메시지 " + i));
        }
        adp.setDataList(chatMsgList);
    }
}