package com.example.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.service.autofill.Transformation;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Battery extends AppCompatActivity {
    private String URL = "http://10.0.2.2/login/battery.php";
    private String email = LoginActivity.emailFromDb, password, batteryfordb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_charging);

        // set battery percent to random
        //Random random = new Random();
        //int battery = random.nextInt(100);
        //int chargeFrom = Integer.parseInt(LoginActivity.batteryFromDb);
        int chargeFrom = 0;
        int battery = 0;

        Bundle bundle = getIntent().getExtras();
        battery = bundle.getInt("percentage");




        chargeFrom = bundle.getInt("currentBattery");
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(chargeFrom);
        // if button is pressed => charge
        Button text = (Button) findViewById(R.id.button);
        int finalBattery = battery;
        int finalChargeFrom = chargeFrom;

        int newbattery = battery+chargeFrom;
        batteryfordb = newbattery+"";
        email = LoginActivity.emailFromDb;
        password = LoginActivity.passwordFromDb;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("res", response);
                if (response.equals("true")) {
                    //Toast.makeText(Battery.this, "Teisinga", Toast.LENGTH_SHORT).show();

                } else if (response.equals("failure")) {
                    //Toast.makeText(Battery.this, "Neteisinga", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Battery.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("password", password);
                data.put("email", email);
                data.put("battery", batteryfordb);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);


        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean finished = false;
                ValueAnimator animator = ValueAnimator.ofInt(finalChargeFrom, finalChargeFrom+finalBattery);
                animator.setDuration(3000);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation){
                        progressBar.setProgress((Integer)animation.getAnimatedValue());
                    }
                });
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        Intent intent = new Intent(Battery.this, MainActivity.class);
                        MainActivity.finishedcharging();
                        MainActivity.isloggedin();
                        Bundle bundle = new Bundle();
                        bundle.putInt("battery", finalChargeFrom +finalBattery);
                        intent.putExtras(bundle);

                        // start your activity here

                        //TODO: pakeicia db baterija
                        //for db input
                        startActivity(intent);
                    }
                });
                animator.start();

            }

        });

    }

}