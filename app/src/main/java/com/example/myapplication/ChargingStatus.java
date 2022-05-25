package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChargingStatus extends AppCompatActivity {


    private String isConnected, chargingStatus;
    private String username, balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charging_status);

        //Button B4 = findViewById(R.id.button4);
        //B4.setOnClickListener(v -> readBalance());
    }

    private void readChargingStatus(){
        String url = "http://192.168.231.121/readChargingStatus.php";
        RequestQueue queue = Volley.newRequestQueue(ChargingStatus.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, response -> {
            Log.e("TAG", "RESPONSE IS " + response);
            try {
                JSONObject jsonObject = new JSONObject(response);
                isConnected = jsonObject.getString("isConnected");
                chargingStatus = jsonObject.getString("chargingStatus");

//                TextView dis1 = findViewById(R.id.info);
//                dis1.setText(chargingStatus);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {
            Toast.makeText(ChargingStatus.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

        };
        queue.add(request);
    }
    private void readBalance(){
        String url = "http://192.168.231.121/readBalance.php";
        RequestQueue queue = Volley.newRequestQueue(ChargingStatus.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, response -> {
            Log.e("TAG", "RESPONSE IS " + response);
            try {
                JSONObject jsonObject = new JSONObject(response);
                username = jsonObject.getString("username");
                balance = jsonObject.getString("balance");
                //TextView dis1 = findViewById(R.id.info);
                //dis1.setText(balance);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {
            Toast.makeText(ChargingStatus.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

        };
        queue.add(request);
    }


}