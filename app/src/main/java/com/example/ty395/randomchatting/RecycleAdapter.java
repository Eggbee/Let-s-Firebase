package com.example.ty395.randomchatting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<ChatData> singModel;
    int recycler_item;


    public RecycleAdapter(Context context, int recycler_item, ArrayList<ChatData> singModels) {
        this.context = context;
        this.singModel = singModels;
        this.recycler_item=recycler_item;
    }

    @Override
    public int getItemCount() {
        return singModel.size() ;
    }

    public int getItemViewType(int position) {

        switch (singModel.get(position).type) {
            case 0:
                return ChatData.YOUR_TYPE;
            case 1:
                return ChatData.MY_TYPE;
             default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
            return new ViewHolder1(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_my_item , parent, false);
            return new ViewHolder2(v);
        }

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatData chatData = singModel.get(position);
        if(chatData != null){
            switch (chatData.type) {
                case ChatData.YOUR_TYPE:
                    ((ViewHolder1) holder).message.setText(chatData.message);
                    ((ViewHolder1) holder).username.setText(chatData.username);
                    break;
                 case ChatData.MY_TYPE:
                     ((ViewHolder2) holder).mymessage.setText(chatData.mymessage);
            }
        }
    }


    public static class ViewHolder1 extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView username, message;

        public ViewHolder1(View itemView) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.username);
            message = (TextView) itemView.findViewById(R.id.message);
        }
    }
    public static class ViewHolder2 extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mymessage;

        public ViewHolder2(View itemView) {
            super(itemView);
            mymessage = (TextView) itemView.findViewById(R.id.mymessage);
        }
    }
}
