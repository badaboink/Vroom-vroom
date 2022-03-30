package com.example.myapplication;

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

import java.time.OffsetTime;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements SelectListener { //mainactivitynews

    RecyclerView recyclerView;
    CustomAdapter adapter;
    ProgressDialog dialog;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (hour < 20 && hour > 8) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        dialog = new ProgressDialog(this);
        dialog.setTitle("Fetching news articles...");
        dialog.show();
       // Date startDate = new Date();
        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener, "business");

        //prices

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        showPrices();

        ///menu
        //NavigationView navigationView = findViewById(R.id.);

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

        DAOPrice dao = new DAOPrice();
        final TextView row1 = findViewById(R.id.tabletext1col1);
        final TextView row2 = findViewById(R.id.tabletext2col1);
        final TextView row3 = findViewById(R.id.tabletext3col1);
        final String price1, price2, price3;
        price1=getPriceAccordingToTime(row1, savedPrice1);
        price2=getPriceAccordingToTime(row2, savedPrice2);
        price3=getPriceAccordingToTime(row3, savedPrice3);
        Price prices = new Price(price1,price2,price3);
        if(getChangedHour("changeHour")){
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                OffsetTime offset = OffsetTime.now();
                SaveLoadData.saveDate(Integer.toString(offset.getHour()), savedHour, this.getApplicationContext());
                saveChangedHour("changeHour", false);
            }
        }
        dao.add(prices);
    }
    /*
       kainos
       0-11h    0.5 - 0.6 eur
       12-16h   0.6 - 0.7 eur
       17-18h   0.75 - 0.8 eur
       19-23h  0.4 - 0.5 eur
    */
    private String getPriceAccordingToTime(TextView row, String file){

        String price="";
        int lastUpdatedHour = Integer.parseInt(Objects.requireNonNull(SaveLoadData.load(savedHour, this.getApplicationContext())));
        TextView roww = findViewById(R.id.tabletext1col2);
        roww.setText(Integer.toString(lastUpdatedHour));
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            OffsetTime offset = OffsetTime.now();
            if(offset.getHour() >= 17 && offset.getHour() <= 18){
                if(lastUpdatedHour != 17 && lastUpdatedHour != 18){
                    price = getPrice(0.75, 0.8);
                    price = "€" + price + "/kWh";
                    row.setText(price);
                    SaveLoadData.savePrice(row, file, this.getApplicationContext());
                    saveChangedHour("changeHour", true);
                }
                else{
                    row.setText(SaveLoadData.load(file, this.getApplicationContext()));
                    price=SaveLoadData.load(file,this.getApplicationContext());
                }
            }
            else if (offset.getHour() >= 19 && offset.getHour() <= 23){
                if(lastUpdatedHour < 19){
                    price = getPrice(0.4, 0.5);
                    price = "€" + price + "/kWh";
                    row.setText(price);
                    SaveLoadData.savePrice(row, file, this.getApplicationContext());
                    saveChangedHour("changeHour", true);

                }
                else{
                    row.setText(SaveLoadData.load(file, this.getApplicationContext()));
                    price=SaveLoadData.load(file,this.getApplicationContext());
                }
            }
            else if (offset.getHour() >= 0 && offset.getHour() <= 11){
                if(lastUpdatedHour > 11){
                    price = getPrice(0.5, 0.6);
                    price = "€" + price + "/kWh";
                    row.setText(price);
                    SaveLoadData.savePrice(row, file, this.getApplicationContext());
                    saveChangedHour("changeHour", true);
                }
                else {
                    row.setText(SaveLoadData.load(file, this.getApplicationContext()));
                    price=SaveLoadData.load(file,this.getApplicationContext());
                }
            }
            else{
                if(lastUpdatedHour < 12 || lastUpdatedHour > 16){
                    price = getPrice(0.6, 0.7);
                    price = "€" + price + "/kWh";
                    row.setText(price);
                    SaveLoadData.savePrice(row, file, this.getApplicationContext());
                    saveChangedHour("changeHour", true);
                }
                else {
                    row.setText(SaveLoadData.load(file, this.getApplicationContext()));
                    price=SaveLoadData.load(file,this.getApplicationContext());
                }
            }
        }
        return price;
    }
    public String getPrice(double min, double max) {
        return Double.toString(Math.round((Math.random() * (max - min) + min)*100.0)/100.0);
    }
    public void saveChangedHour(String key, boolean value)
    {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(" SHARED_PREFERENCES_NAME ", android.content.Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
    public boolean getChangedHour(String key)
    {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(" SHARED_PREFERENCES_NAME ", android.content.Context.MODE_PRIVATE);
        return preferences.getBoolean(key, false);
    }

    @Override
    public void OnNewsClicked(NewsHeadlines headlines) {
        startActivity(new Intent(MainActivity.this, DetailActivity.class)
        .putExtra("data",headlines));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.nav_profile)
        {
            Intent intent = new Intent(MainActivity.this,Profile.class);
            startActivity(intent);
            return true;
        }
        else if(id==R.id.nav_settings)
        {
            Intent intent = new Intent(MainActivity.this,Settings.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
