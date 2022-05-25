package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.History.MoneyTransfers;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Settings extends AppCompatActivity {

    private String sell, buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);
        getPriceDetailed();

        TextView moneyHistory = (TextView) findViewById(R.id.history);
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
        moneyHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this, MoneyTransfers.class);
                startActivity(intent);
            }
        });

    }
    private void getPriceDetailed(){
        String url = "http://192.168.245.121/readElectricityPrices.php";

        RequestQueue queue = Volley.newRequestQueue(Settings.this);

        StringRequest request = new StringRequest(Request.Method.POST, url, response -> {
            try {
                TextView priceToSell = (TextView) findViewById(R.id.priceToSell);
                TextView priceToBuy = (TextView) findViewById(R.id.priceToBuy);
                JSONObject jsonObject = new JSONObject(response);

                sell = jsonObject.getString("sellToGrid");
                buy = jsonObject.getString("buyFromGrid");
                priceToSell.setText(sell);
                priceToBuy.setText(buy);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            Toast.makeText(Settings.this, "Fail to get price" + error, Toast.LENGTH_SHORT).show();
        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };

        queue.add(request);
    }
}