package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        TextView text = (TextView) findViewById(R.id.addCard);
        TextView text2 = (TextView) findViewById(R.id.priceSettings);
        TextView text3 = (TextView) findViewById(R.id.infosettings);
        TextView text4 = (TextView) findViewById(R.id.addCar);
        TextView text5 = (TextView) findViewById(R.id.changePassword);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Settings.this, CreditCardActivity.class);
                startActivity(intent);
            }

        });
        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this, ChargingStatus.class);
                startActivity(intent);
            }
        });
        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this, ChangeCar.class);
                startActivity(intent);
            }
        });
        text5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this, ChangePassword.class);
                startActivity(intent);
            }
        });
    }
}