package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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

import java.util.List;

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

        showPrices();
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

    private void showNews(List<NewsHeadlines> list) {
        recyclerView=findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        adapter = new CustomAdapter(this, list, this);
        recyclerView.setAdapter(adapter);
    }
    private void showPrices(){
        TextView row1 = findViewById(R.id.tabletext1col1);
        TextView row2 = findViewById(R.id.tabletext2col1);
        TextView row3 = findViewById(R.id.tabletext3col1);
        row1.setText(getPrice());
        row2.setText(getPrice());
        row3.setText(getPrice());

    }
    private String getPrice(){
        String price = Double.toString(Math.round(Math.random()*100.0) / 100.0);
        price = "€" + price + "/kWh";
        return price;
    }

    @Override
    public void OnNewsClicked(NewsHeadlines headlines) {
        startActivity(new Intent(MainActivity.this, DetailActivity.class)
        .putExtra("data",headlines));
    }
}
