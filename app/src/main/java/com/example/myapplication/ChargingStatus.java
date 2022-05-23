package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChargingStatus extends AppCompatActivity {

    private String distributor1, price1, distributor2, price2, distributor3, price3;
    private String isConnected, chargingStatus;
    private String username, balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charging_status);
    }
    private void readMarketPrice(){
        String url = "http://192.168.1.167/readMarketPrice.php";
        RequestQueue queue = Volley.newRequestQueue(ChargingStatus.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, response -> {
            Log.e("TAG", "RESPONSE IS " + response);
            try {
                JSONObject jsonObject = new JSONObject(response);
                Toast.makeText(ChargingStatus.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
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
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("distributor1", distributor1);
                params.put("price1", price1);
                params.put("distributor2", distributor2);
                params.put("price2", price2);
                params.put("distributor3", distributor3);
                params.put("price3", price3);
                return params;
            }
        };
        queue.add(request);
    }
    private void readChargingStatus(){
        String url = "http://192.168.1.167/readChargingStatus.php";
        RequestQueue queue = Volley.newRequestQueue(ChargingStatus.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, response -> {
            Log.e("TAG", "RESPONSE IS " + response);
            try {
                JSONObject jsonObject = new JSONObject(response);
                Toast.makeText(ChargingStatus.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
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
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("isConnected", isConnected);
                params.put("chargingStatus", chargingStatus);

                return params;
            }
        };
        queue.add(request);
    }
    private void readBalance(){
        String url = "http://192.168.1.167/readChargingStatus.php";
        RequestQueue queue = Volley.newRequestQueue(ChargingStatus.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, response -> {
            Log.e("TAG", "RESPONSE IS " + response);
            try {
                JSONObject jsonObject = new JSONObject(response);
                Toast.makeText(ChargingStatus.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
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
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("balance", balance);

                return params;
            }
        };
        queue.add(request);
    }


}