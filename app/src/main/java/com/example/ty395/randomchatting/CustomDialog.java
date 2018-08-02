package com.example.ty395.randomchatting;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class CustomDialog {
    private Context context;

    public CustomDialog(Context context) {
        this.context = context;
    }
    void CallFuntion(){
        final Dialog dialog=new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.main_dialog);
        dialog.show();
        final LinearLayout voice=(LinearLayout)dialog.findViewById(R.id.voice);
        final LinearLayout picture=(LinearLayout)dialog.findViewById(R.id.picture);
        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"아직 구현이 되지 않은 기능입니다",Toast.LENGTH_SHORT).show();
            }
        });
        voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"아직 구현이 되지 않은 기능입니다",Toast.LENGTH_SHORT).show();
            }
        });
    }


}
