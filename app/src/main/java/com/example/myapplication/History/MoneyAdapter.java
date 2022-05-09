package com.example.myapplication.History;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class MoneyAdapter extends RecyclerView.Adapter<MoneyHistoryViewHolder>{

   String dates[], info[];
   Double ammount[];
   Context context;

    public MoneyAdapter(Context cx, String dates[], String info[], Double ammount[]){
        this.dates=dates;
        this.info=info;
        this.ammount=ammount;
    };
    @NonNull
    @Override
    public MoneyHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_money_history_view_holder, parent,false);
        return new MoneyHistoryViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MoneyHistoryViewHolder holder, int position) {
        //is duombazes paskui pakeist
        holder.text_date.setText(dates[position]);
        holder.text_info.setText(info[position]);
        holder.text_ammount.setText(ammount[position].toString());
    }

    @Override
    public int getItemCount() {
        return dates.length;
    }

}
