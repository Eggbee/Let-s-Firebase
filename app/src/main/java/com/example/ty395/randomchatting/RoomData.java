package com.example.ty395.randomchatting;

public class RoomData {

    static final int ROOM_COUNT = 1;
    static final int ROOM_MAX_COUNT = 2;

    int count;
    int max_count;
    public RoomData() {}

    public RoomData(String username, String message, String mymessage, int type, int userid, int count, int max_count){

        this.count=count;
        this.max_count=max_count;
    }


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
}
