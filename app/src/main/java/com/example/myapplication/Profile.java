package com.example.myapplication;

import static com.example.myapplication.MainActivity.isadmin;
import static com.example.myapplication.MainActivity.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;
import android.widget.TextView;


import com.example.myapplication.History.MoneyTransfers;


public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TextView history = findViewById(R.id.moneyHistoryProfile);

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent historyI = new Intent(getApplicationContext(), MoneyTransfers.class);
                startActivity(historyI);
            }
        });

        //username.setText
        Button changeprice = (Button) findViewById(R.id.accessprice);
        Button logout = (Button) findViewById(R.id.btn_logout);
        TextView username = findViewById(R.id.profile_email);
        TextView car = findViewById(R.id.profile_car);
        TextView carnr = findViewById(R.id.profile_car_nums);
        TextView card = findViewById(R.id.profile_card);
        username.setText(LoginActivity.emailFromDb);
        car.setText(LoginActivity.autoFromDB);
        carnr.setText(LoginActivity.autoNrFromDb);
        card.setText("**** **** ***** "+ LoginActivity.cardFromDb.substring(12, 16));

        changeprice.setVisibility(View.GONE);
        if(MainActivity.isitadmin())
        {
            changeprice.setVisibility(View.VISIBLE);
            changeprice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent startIntent = new Intent(getApplicationContext(), Admin.class);
                    startActivity(startIntent);
                }
            });
        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isadmin = false;
                login = false;
                Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startIntent);
            }
        });
    }
    public static void SetName(String name){

    }
}