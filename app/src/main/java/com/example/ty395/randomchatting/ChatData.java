package com.example.ty395.randomchatting;

public class ChatData {
    String username;
    String message;

    public ChatData() {}

    public ChatData(String username,String message){
        this.username =username;
        this.message=message;
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

    public String getMessage() {
        return message;
    }

}
