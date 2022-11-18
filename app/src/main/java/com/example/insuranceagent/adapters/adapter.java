package com.example.insuranceagent.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.insuranceagent.R;
import com.example.insuranceagent.userdata;

import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class adapter extends RecyclerView.Adapter<adapter.MyViewHolder> implements Filterable {

    Context context;
    ArrayList<userdata> list;
    ArrayList<String> listall;

    public adapter(Context context, ArrayList<userdata> list) {
        this.context = context;
        this.list = list;
        this.listall = new ArrayList<>(listall);
    }
//    Calendar calendar = Calendar.getInstance();
//    String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.itemview,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       userdata user = list.get(position);
        holder.user.setText(user.getUser());
        holder.dateofissue.setText(user.getDateofissue());
        holder.dateofexpiry.setText(user.getDateofexpiry());
        }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        // run on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<String> filteredList = new ArrayList<>();

            if(charSequence.toString().isEmpty()){
               filteredList.addAll(listall);
            }
            else{
                for(String list : listall){
                 if(list.toLowerCase().contains(charSequence.toString().toLowerCase())){
                     filteredList.add(list);
                 }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }
        // runs on ui thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
           list.clear();
           list.addAll((Collection<? extends userdata>) filterResults.values);
           notifyDataSetChanged();
        }
    };
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView user,dateofissue,dateofexpiry;
        public MyViewHolder(View itemView){
            super(itemView);
            user = itemView.findViewById(R.id.user);
            dateofexpiry = itemView.findViewById(R.id.dateofexpiry);
            dateofissue = itemView.findViewById(R.id.dateofissue);
        }
    }
}
