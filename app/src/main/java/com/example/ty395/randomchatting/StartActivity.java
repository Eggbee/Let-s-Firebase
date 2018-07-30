package com.example.ty395.randomchatting;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class StartActivity extends AppCompatActivity {
    EditText username;
    Button button;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    boolean check=false;

    String main_key;
    String sub_key;

    String room_key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        username=(EditText)findViewById(R.id.username);
        button=(Button)findViewById(R.id.button);
        final ArrayList<ChatData> singModle = new ArrayList<>();
        databaseReference = firebaseDatabase.getReference("message");



        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("snapshot",dataSnapshot.getKey());
                main_key=dataSnapshot.getKey();


                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Log.d("snapshot",snapshot.getValue().toString());
                    Log.d("snapshot",snapshot.getKey());

                    ChatData chatdata = snapshot.getValue(ChatData.class);
                    Log.d("snapshot", chatdata.getCount()+"");

                    if(chatdata.getCount()==1){
                        sub_key=snapshot.getKey();
                        Log.d("snapshot2", sub_key+"");

                    }

                    if(chatdata.getCount()>chatdata.getMax_count()){
                        //방생성
                        room_key = Settings.Secure.getString(StartActivity.this.getContentResolver(), Settings.Secure.ANDROID_ID);

                    }
                    else{
                        room_key=dataSnapshot.getKey();
                        check=true;
                    }
                }

                /*
                if(chatdata.getCount()< chatdata.getMax_count()){
                    //방생성
                    room_key = Settings.Secure.getString(StartActivity.this.getContentResolver(), Settings.Secure.ANDROID_ID);
                    Log.d("getKey2", childDataSnapshot.getKey());
                    Log.d("Max_count",chatdata.getMax_count()+"");
                    Log.d("Max_count",chatdata.getCount()+"");


                }
                else{
                    room_key=dataSnapshot.getKey();
                    check=true;

                }
                */

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


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getnEdit=username.getText().toString();
                getnEdit=getnEdit.trim();

                if(getnEdit.getBytes().length<=0) {
                    Toast.makeText(getApplicationContext(), "닉네임을 입력해주세요", Toast.LENGTH_SHORT).show();
                }
                else{

                    if(! check){ //새로운 방을 만드는 경우
                        ChatData chatData=new ChatData();
                        chatData.setCount(1);
                        chatData.setMax_count(2);
                        chatData.setUsername(username.getText().toString());
                        room_key = Settings.Secure.getString(StartActivity.this.getContentResolver(), Settings.Secure.ANDROID_ID);

                        databaseReference = firebaseDatabase.getReference("message").child(room_key);
                        databaseReference.push().setValue(chatData);
                    }else{
                        //이미 있는 방에 들어가는 경우, count를 2로 변경
                        Map<String, Object> childUpdates = new HashMap<>();
                        childUpdates.put("/" + main_key+"/"+sub_key+"/count", 2);

                        databaseReference.updateChildren(childUpdates);

                    }

                    Intent intent = new Intent(StartActivity.this, MainActivity.class);
                    intent.putExtra("username", username.getText().toString());
                    intent.putExtra("roomkey",room_key);
                    startActivity(intent);

                }
            }
        });

    }
}
