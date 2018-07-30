package com.example.ty395.randomchatting;

public class ChatData {
    public static final int YOUR_TYPE=0;
    public static final int MY_TYPE=1;
    int type;
    String username;
    String message;
    String mymessage;
    int userid;
    int count;
    int max_count;
    public ChatData() {}

    public ChatData(String username,String message,String mymessage,int type,int userid,int count,int max_count){
        this.username =username;
        this.message=message;
        this.mymessage=mymessage;
        this.type=type;
        this.userid=userid;
        this.count=count;
        this.max_count=max_count;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {return username; }

    public String getMymessage() {
        return mymessage;
    }

    public String getMessage() {
        return message;
    }
    public void setMymessage(String mymessage){
        this.mymessage=mymessage;
    }

    public int getType() { return type; }

    public void setType(int type) { this.type = type; }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMax_count() {
        return max_count;
    }

    public void setMax_count(int max_count) {
        this.max_count = max_count;
    }

    public void setUserid(int userid) { this.userid = userid; }

    public int getUserid() { return this.userid; }
}
