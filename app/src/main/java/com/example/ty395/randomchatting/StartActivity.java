package com.example.ty395.randomchatting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {
    EditText username;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        username=(EditText)findViewById(R.id.username);
        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getnEdit=username.getText().toString();
                getnEdit=getnEdit.trim();
                if(getnEdit.getBytes().length<=0){
                    Toast.makeText(getApplicationContext(),"닉네임을 입력해주세요",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(StartActivity.this, MainActivity.class);
                    intent.putExtra("username", username.getText().toString());
                    startActivity(intent);
                }
            }
        });

    }
}
