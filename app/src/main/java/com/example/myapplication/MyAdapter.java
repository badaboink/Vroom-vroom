package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    String[] source;
    String[] title;
    int[] images;
    String[] url; //ir visi kiti dar
    private OnNoteListener mOnNoteListener;

    public MyAdapter(Context context, String[] source, String[] title, int[] images, String[] url, OnNoteListener onNoteListener) {
        this.context = context;
        this.source = source;
        this.title = title;
        this.images = images;
        this.url = url;
        this.mOnNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.headline_list_items, parent, false);
        return new MyViewHolder(view, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.source.setText(source[position]);
        holder.title.setText(title[position]);
        holder.image.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return source.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView source, title;
        ImageView image;
        OnNoteListener onNoteListener;
        public MyViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            source = itemView.findViewById(R.id.text_source);
            title=itemView.findViewById(R.id.text_title);
            image=itemView.findViewById(R.id.img_headline);
            this.onNoteListener=onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getBindingAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}
