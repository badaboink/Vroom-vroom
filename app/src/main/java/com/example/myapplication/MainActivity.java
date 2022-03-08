package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextClock;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.News.CustomAdapter;
import com.example.myapplication.News.DetailActivity;
import com.example.myapplication.News.OnFetchDataListener;
import com.example.myapplication.News.RequestManager;
import com.example.myapplication.News.SelectListener;
import com.example.myapplication.News.News_Models.NewsApiResponse;
import com.example.myapplication.News.News_Models.NewsHeadlines;


import java.text.DateFormat;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements SelectListener { //mainactivitynews

    RecyclerView recyclerView;
    CustomAdapter adapter;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Fetching news articles...");
        dialog.show();
        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener, "business");

    }

    private final OnFetchDataListener<NewsApiResponse> listener=new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadlines> list, String message) {
        showNews(list);
        dialog.dismiss();
        }

        @Override
        public void onError(String message) {

        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void showNews(List<NewsHeadlines> list) {
        recyclerView=findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        adapter = new CustomAdapter(this, list, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnNewsClicked(NewsHeadlines headlines) {
        startActivity(new Intent(MainActivity.this, DetailActivity.class)
        .putExtra("data",headlines));
    }
}
