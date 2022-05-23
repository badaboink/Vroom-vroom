package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ChangeCar extends AppCompatActivity {
    private String URL = "http://10.0.2.2/login/car_change.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_car);


        Button Button = findViewById(R.id.changeCarButton);
        Button.setOnClickListener(v -> save());

    }
    public void save()
    {
        EditText bruh1 = findViewById(R.id.carText);
        String car = bruh1.getText().toString().trim();
        EditText bruh2 = findViewById(R.id.numberText);
        String numbers = bruh2.getText().toString().trim();

        String email = LoginActivity.emailFromDb;String password = LoginActivity.passwordFromDb;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("res", response);
                if (response.equals("true")) {
                    Intent intent = new Intent(ChangeCar.this, MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("car", car);
                    bundle.putString("carnumbers", numbers);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    //Toast.makeText(Battery.this, "Teisinga", Toast.LENGTH_SHORT).show();

                } else if (response.equals("failure")) {
                    //Toast.makeText(Battery.this, "Neteisinga", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ChangeCar.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("password", password);
                data.put("email", email);
                data.put("connected_auto", car);
                data.put("auto_nr", numbers);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}