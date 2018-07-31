package com.example.ty395.randomchatting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String USER_NAME;
    RecyclerView recyclerView;
    ImageButton send;
    ImageButton image;
    EditText chat_message;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    String room_key;
    int count,max_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.list);
        send = (ImageButton) findViewById(R.id.send);
        chat_message = (EditText) findViewById(R.id.chat_message);

        Intent intent = getIntent();
        USER_NAME = intent.getStringExtra("username");
        room_key=intent.getStringExtra("roomkey");
        count=intent.getIntExtra("count",0);
        max_count=intent.getIntExtra("max_count",2);

        databaseReference = firebaseDatabase.getReference("message").child(room_key);

        image=(ImageButton)findViewById(R.id.image);
        final ArrayList<ChatData> singModles = new ArrayList<>();

        final RecycleAdapter adapter = new RecycleAdapter(MainActivity.this, R.layout.recycler_item, singModles);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
//        singModles.add(new ChatData(ChatData.YOUR_TYPE,R.layout.recycler_item));
//        singModles.add(new ChatData(ChatData.MY_TYPE,R.layout.recycler_my_item));

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ChatData chatData = new ChatData(USER_NAME, chat_message.getText().toString());  // 유저 이름과 메세지로 chatData 만들기
                ChatData chatData = new ChatData();
                chatData.setUsername(USER_NAME);
                chatData.setMymessage(chat_message.getText().toString());
                chatData.setCount(count);
                chatData.setMax_count(max_count);
                databaseReference.push().setValue(chatData);  // 기본 database 하위 message라는 child에 chatData를 list로 만들기
                chat_message.setText("");
            }
        });


        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatData chatData = dataSnapshot.getValue(ChatData.class);
                Log.d("addChildEventListener", chatData.getUsername());

                count=chatData.getCount();
                max_count=chatData.getMax_count();

                singModles.add(chatData);
                adapter.notifyDataSetChanged();
                if(chatData.getUsername().equals(USER_NAME)){
                    chatData.setType(ChatData.MY_TYPE);
                    chatData.setMessage(chatData.getMymessage());

                }else{
                    chatData.setType(ChatData.YOUR_TYPE);
                    chatData.setMessage(chatData.getMymessage());
                }
                recyclerView.scrollToPosition(adapter.getItemCount()-1);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
