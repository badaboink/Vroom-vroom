package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import static com.example.myapplication.Prices.SaveLoadData.savedHour;
import static com.example.myapplication.Prices.SaveLoadData.savedPrice1;
import static com.example.myapplication.Prices.SaveLoadData.savedPrice2;
import static com.example.myapplication.Prices.SaveLoadData.savedPrice3;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import com.example.myapplication.DAOPrice;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.News.CustomAdapter;
import com.example.myapplication.News.DetailActivity;
import com.example.myapplication.News.News_Models.NewsApiResponse;
import com.example.myapplication.News.News_Models.NewsHeadlines;
import com.example.myapplication.News.OnFetchDataListener;
import com.example.myapplication.News.RequestManager;
import com.example.myapplication.News.SelectListener;
import com.example.myapplication.Prices.SaveLoadData;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import com.example.myapplication.Prices.SaveLoadData;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        TextView text1 = (TextView) findViewById(R.id.textPrice1);
        TextView text2 = (TextView) findViewById(R.id.textPrice2);
        TextView text3 = (TextView) findViewById(R.id.textPrice3);
        Button button = (Button) findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String price1, price2, price3;
                price1 = text1.getText().toString();
                price1 = "€" + price1 + "/kWh";
                text1.setText(price1);
                price2 = text2.getText().toString();
                price2 = "€" + price2 + "/kWh";
                text2.setText(price2);
                price3 = text3.getText().toString();
                price3 = "€" + price3 + "/kWh";
                text3.setText(price3);
                SaveLoadData.savePrice(text1, savedPrice1, getApplicationContext());
                SaveLoadData.savePrice(text2, savedPrice2, getApplicationContext());
                SaveLoadData.savePrice(text3, savedPrice3, getApplicationContext());
                /*if(text2.getText()!=null){
                    SaveLoadData.savePrice(text2, savedPrice2, getApplicationContext());
                }
                if(text3.getText()!=null){
                    SaveLoadData.savePrice(text3, savedPrice3, getApplicationContext());
                }*/
            }
        });
    }
    public static void savePrice(TextView row, String FILE_NAME, Context FILE) {
        String text = row.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = FILE.openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}