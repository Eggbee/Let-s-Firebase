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

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    Context context;
    ArrayList<ChatData> singModel;
    int recycler_item;
    public RecycleAdapter(Context context, int recycler_item, ArrayList<ChatData> singModels) {
        this.context = context;
        this.singModel = singModels;
        this.recycler_item=recycler_item;
    }


    @NonNull
    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapter.ViewHolder holder, int position) {
        ChatData chatData = singModel.get(position);
        holder.username.setText(chatData.getUsername());
        holder.message.setText(chatData.getMessage());

    }

    @Override
    public int getItemCount() {
        Log.d("getItemCount", singModel.size()+"");
        return singModel.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView username, message;

        public ViewHolder(View itemView) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.username);
            message = (TextView) itemView.findViewById(R.id.message);
        }
    }


}
