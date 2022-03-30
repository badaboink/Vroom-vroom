package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.myapplication.Prices.SaveLoadData.savedPrice1;
import static com.example.myapplication.Prices.SaveLoadData.savedPrice2;
import static com.example.myapplication.Prices.SaveLoadData.savedPrice3;


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

}