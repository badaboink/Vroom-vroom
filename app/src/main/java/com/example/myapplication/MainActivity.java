package com.example.myapplication;


import static com.example.myapplication.Prices.SaveLoadData.savedHour;
import static com.example.myapplication.Prices.SaveLoadData.savedPrice1;
import static com.example.myapplication.Prices.SaveLoadData.savedPrice2;
import static com.example.myapplication.Prices.SaveLoadData.savedPrice3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
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
import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.time.OffsetTime;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  SelectListener, NavigationView.OnNavigationItemSelectedListener{ //mainactivitynews

    RecyclerView recyclerView;
    CustomAdapter adapter;
    ProgressDialog dialog;
    DrawerLayout drawer;
    Button chargeButton;
    static Double money;
    static int Battery = 0;
    static String car = "";
    static String carnr = "";
    private static final DecimalFormat df = new DecimalFormat("0.00");


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        /*if (savedInstanceState != null) {
            Double totalSum= getTotalSum();
            if(totalSum!=0.00) money = 250.16-totalSum;
        }
        else money = 250.16;*/

        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();

        if (finishedcharge()){
            //String myString = bundle.containsKey("money") ? bundle.getString("money") : "250.16";
             //String moneys = bundle.getString("money");
            money = bundle.getDouble("money");

        }
        else money = 256.16;

        setContentView(R.layout.activity_main);


        TextView moneyView = findViewById(R.id.tabletextmoney);
        String moneystr = df.format((money));

        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (hour > 20 || hour == 12 || hour < 8) {
            if(hour!=0)
            {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        }

        dialog = new ProgressDialog(this);
        dialog.setTitle("Kraunamos naujienos...");
        dialog.show();
       // Date startDate = new Date();
        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener, "business");


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView drawerName = headerView.findViewById(R.id.drawer_name);
        TextView drawerEmail = headerView.findViewById(R.id.drawer_email);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //prices
        showPrices();

        ///button
        chargeButton = (Button)findViewById(R.id.butt_charge);

        if(!isloggedin()){
            chargeButton.setVisibility(View.GONE);
            drawerEmail.setText("Prisijunkite");
            moneyView.setText("");

        }

        if(isloggedin())
        {
            moneyView.setText(moneystr +" €");
            drawerEmail.setText(LoginActivity.emailFromDb);
            drawerName.setText(LoginActivity.nameFromDb);
            Battery = Integer.parseInt(LoginActivity.batteryFromDb);
            chargeButton.setVisibility(View.VISIBLE);

        }
        chargeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PaySelect.class);
                startActivity(intent);
                //OnChargeClicked();
            }
        });

        if(finishedcharge())
        {
            Battery = bundle.getInt("battery");
            charge = false;

        }
      }


    public static void SetMoney(Double m)
    {
        money=m;
    }
    public static Double GetMoney()
    {
        return money;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_profile:
                if(!login)
                {
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                    return true;
                }
                else
                {
                    Intent intent = new Intent(MainActivity.this,Profile.class);
                    Bundle bundle = new Bundle();
                    if(car == "" || carnr == "")
                    {
                        car = LoginActivity.autoFromDB;
                        carnr = LoginActivity.autoNrFromDb;
                    }
                    bundle.putString("car", car);
                    bundle.putString("carnumbers", carnr);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    return true;
                }
            case R.id.nav_battery:
                if(isloggedin())
                {
                    Intent intent = new Intent(MainActivity.this,BatteryCheck.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("battery", Battery);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    return true;
                }
                else
                    Toast.makeText(MainActivity.this, "Prisijunkite", Toast.LENGTH_SHORT).show();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
            super.onBackPressed();
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
        //dao.add(prices);
    }
    /*
       kainos
       0-11h    0.5 - 0.6 eur
       12-16h   0.6 - 0.7 eur
       17-18h   0.75 - 0.8 eur
       19-23h  0.4 - 0.5 eur
    */
    @SuppressLint("SetTextI18n")
    private String getPriceAccordingToTime(TextView row, String file){

        String price="";
        int lastUpdatedHour = 0;
        String lastUpdatedHourString = SaveLoadData.load(savedHour, this.getApplicationContext());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            OffsetTime offset = OffsetTime.now();
            if(lastUpdatedHourString == null){
                lastUpdatedHour = offset.getHour() + 12;
                if(lastUpdatedHour >= 24){
                    lastUpdatedHour = lastUpdatedHour - 24;
                }
            }
            else{
                lastUpdatedHour = Integer.parseInt(lastUpdatedHourString);
            }
            if(offset.getHour() >= 17 && offset.getHour() <= 18){
                if(lastUpdatedHour != 17 && lastUpdatedHour != 18){
                    price = getPrice(0.75, 0.81);
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
                    price = getPrice(0.41, 0.51);
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
                    price = getPrice(0.51, 0.61);
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
                    price = getPrice(0.61, 0.71);
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
    protected static boolean login = false;
    protected static boolean isadmin = false;
    protected static boolean charge = false;
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.nav_profile:
                if(!login)
                {
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                    return true;
                }
                else
                {
                    Intent intent = new Intent(MainActivity.this,Profile.class);
                    Bundle bundle = new Bundle();
                    if(car == "" || carnr == "")
                    {
                        car = LoginActivity.autoFromDB;
                        carnr = LoginActivity.autoNrFromDb;
                    }
                    bundle.putString("car", car);
                    bundle.putString("carnumbers", carnr);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    return true;
                }
            case R.id.nav_settings:
                if(login)
                {
                    Intent intent = new Intent(MainActivity.this,Settings.class);
                    startActivity(intent);
                    return true;
                }
                else{
                   Toast.makeText(MainActivity.this, "Prisijunkite!", Toast.LENGTH_SHORT).show();
                }
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    public static void changelogin()
    {
        login = true;
    }
    public static void adminloggedin()
    {
        isadmin = true;
    }
    public static void finishedcharging()
    {
        charge = true;
    }
    public static boolean isloggedin()
    {
        return login;
    }
    public static boolean isitadmin()
    {
        return isadmin;
    }
    public static boolean finishedcharge() { return charge;}

    public Double readFromFile(String filename)
    {
        FileInputStream fis =null;
        String moneyfile="200.00";
        try{
            fis = openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bf = new BufferedReader(isr);
            String text;
            while((text=bf.readLine())!=null){ //paskutini grazina
                moneyfile=text;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Double.parseDouble(moneyfile);
    }

}
