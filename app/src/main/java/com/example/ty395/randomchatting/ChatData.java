package com.example.ty395.randomchatting;

public class ChatData {
    public static final int YOUR_TYPE = 0;
    public static final int MY_TYPE = 1;
    int type;
    String username;
    String message;
    String mymessage;
    String token;
    int userid;

    public ChatData() {
    }

    public ChatData(String username, String message, String mymessage, int type, int userid, String token) {
        this.username = username;
        this.message = message;
        this.mymessage = mymessage;
        this.type = type;
        this.userid = userid;
        this.token = token;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getMymessage() {
        return mymessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMymessage(String mymessage) {
        this.mymessage = mymessage;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getUserid() {
        return this.userid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
