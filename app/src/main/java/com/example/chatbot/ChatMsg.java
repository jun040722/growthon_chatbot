package com.example.chatbot;

public class ChatMsg {
    // 0 - 내가 보낸 메시지, 1 - 챗봇이 보낸 메시지
    public static final int TYPE_MY_CHAT = 0;
    public static final int TYPE_BOT_CHAT = 1;

    public int type; // 뷰타입
    public String msg; // 메시지 내용

    public ChatMsg(int type, String msg) {
        this.type = type;
        this.msg = msg;
    }
}
