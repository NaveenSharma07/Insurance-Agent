package com.example.insuranceagent;

import android.content.Context;
import android.service.autofill.UserData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class adapter extends RecyclerView.Adapter<adapter.MyViewHolder> {

    Context context;
    ArrayList<userdata> list;

    public adapter(Context context, ArrayList<userdata> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.itemview,parent,false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       userdata user = list.get(position);
       holder.name.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        public MyViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.name);
        }
    }
}
