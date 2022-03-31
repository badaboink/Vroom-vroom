package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.myapplication.Prices.SaveLoadData.savedPrice1;
import static com.example.myapplication.Prices.SaveLoadData.savedPrice2;
import static com.example.myapplication.Prices.SaveLoadData.savedPrice3;


import com.example.myapplication.Prices.SaveLoadData;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        TextView text1 = (TextView) findViewById(R.id.textPrice1);
        TextView text2 = (TextView) findViewById(R.id.textPrice2);
        TextView text3 = (TextView) findViewById(R.id.textPrice3);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button returnButton = (Button) findViewById(R.id.returnButton);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String price1;
                if(text1.getText().toString().matches("[0-9].[0-9]+")){
                    price1 = text1.getText().toString();
                    price1 = "€" + price1 + "/kWh";
                    text1.setText(price1);
                    SaveLoadData.savePrice(text1, savedPrice1, getApplicationContext());
                }


            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String price2;
                if(text2.getText().toString().matches("[0-9].[0-9]+")){
                    price2 = text2.getText().toString();
                    price2 = "€" + price2 + "/kWh";
                    text2.setText(price2);
                    SaveLoadData.savePrice(text2, savedPrice2, getApplicationContext());
                }

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String price3;
                if(text3.getText().toString().matches("[0-9].[0-9]+")){
                    price3 = text3.getText().toString();
                    price3 = "€" + price3 + "/kWh";
                    text3.setText(price3);
                    SaveLoadData.savePrice(text3, savedPrice3, getApplicationContext());
                }


            }
        });
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startIntent);
            }
        });
    }

}