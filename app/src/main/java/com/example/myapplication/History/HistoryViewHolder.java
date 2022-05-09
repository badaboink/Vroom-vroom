package com.example.myapplication.History;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class HistoryViewHolder extends RecyclerView.ViewHolder {
    TextView text_date, text_info, text_ammount;
    CardView cardView;
    public HistoryViewHolder(@NonNull View itemView) {
        super(itemView);
        text_date=itemView.findViewById(R.id.text_date);
        text_info=itemView.findViewById(R.id.text_info);
        text_ammount=itemView.findViewById(R.id.text_ammount);
        cardView=itemView.findViewById(R.id.money_container);
    }
}
