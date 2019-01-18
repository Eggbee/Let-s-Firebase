package com.example.ty395.randomchatting;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class StartActivity extends AppCompatActivity {
    EditText username;
    Button button;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    boolean check=false;
    String room_key;

    int count,max_count;

    DataSnapshot mDataSnapshot;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        username=(EditText)findViewById(R.id.username);
        button=(Button)findViewById(R.id.button);
        final ArrayList<ChatData> singModle = new ArrayList<>();
        token=FirebaseInstanceId.getInstance().getToken();
        init();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getnEdit = username.getText().toString();
                getnEdit=getnEdit.trim();

                if(getnEdit.getBytes().length<=0) {
                    Toast.makeText(getApplicationContext(), "닉네임을 입력해주세요", Toast.LENGTH_SHORT).show();
                }
                else{

                    if(check == false){ //새로운 방을 만드는 경우
                        RoomData roomData = new RoomData();
                        roomData.setCount(RoomData.ROOM_COUNT);
                        roomData.setMax_count(RoomData.ROOM_MAX_COUNT);

                        count = roomData.getCount();
                        max_count=roomData.getMax_count();

                        room_key = Settings.Secure.getString(StartActivity.this.getContentResolver(), Settings.Secure.ANDROID_ID);
                        room_key=room_key+new Random().nextInt(1524852);

                        databaseReference = firebaseDatabase.getReference(room_key);

                        databaseReference.setValue(roomData);

                    }else{
                        //이미 있는 방에 들어가는 경우, count를 2로 변경
                        Map<String, Object> childUpdates = new HashMap<>();
                        childUpdates.put("/" + room_key+"/count", RoomData.ROOM_MAX_COUNT);

                        databaseReference.updateChildren(childUpdates);

                    }

                    Intent intent = new Intent(StartActivity.this, MainActivity.class);
                    intent.putExtra("username", username.getText().toString().trim());
                    intent.putExtra("roomkey",room_key);
                    intent.putExtra("token",token);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),token,Toast.LENGTH_SHORT).show();
                    Log.d("Debug","토큰토큰토큰토큰   "+token);
                }
            }
        });

    }

    private void init(){
        databaseReference = firebaseDatabase.getReference();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("string",s+"");

                if(dataSnapshot.getChildrenCount() > 0){
                    RoomData roomData = dataSnapshot.getValue(RoomData.class);

                    Log.d("snapshot",dataSnapshot.getValue().toString());
                    Log.d("snapshot",dataSnapshot.getKey());


                    if(roomData.getCount()>=roomData.getMax_count()){
                        //방생성
//                        room_key = Settings.Secure.getString(StartActivity.this.getContentResolver(), Settings.Secure.ANDROID_ID);
//                        room_key=room_key+new Random().nextInt(1524852);
                        check=false; //방이 없어서 생성하는 경우
                    }
                    else{
                        room_key = dataSnapshot.getKey();
                        check=true; //이미 방이 있는 경우
                    }

                }else{

                    //방생성
                    check=false; //방이 없어서 생성하는 경우

                }


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                mDataSnapshot=dataSnapshot;
                RoomData roomData = dataSnapshot.getValue(RoomData.class);
                Log.d("change",dataSnapshot.getValue().toString());
                if(roomData.getCount() >= roomData.max_count){
                    onChildAdded(dataSnapshot, s);

                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d("remove",dataSnapshot.getValue().toString());
                Log.d("remove",dataSnapshot.getKey());

                if (room_key.equals(dataSnapshot.getKey())){
                    room_key="";
                    databaseReference = null;
                }

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        init();
    }
}