package com.example.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class BatteryCheck extends AppCompatActivity {
    ListView lv;
    String[] checks = {"Ar prijungta?", "Ar kraunama?"};

    private String isConnected, chargingStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charging_status);

        Button button = findViewById(R.id.check);
        button.setOnClickListener(v -> readChargingStatus());

//        mHandler = new Handler();
//        startRepeatingTask();


//        Bundle bundle = getIntent().getExtras();
//        int chargeFrom = bundle.getInt("battery");
//        ProgressBar progressBar = findViewById(R.id.progressBar);
//        progressBar.setProgress(chargeFrom);
//        TextView perc = findViewById(R.id.percentage);
//        perc.setText(String.format("%d %%", chargeFrom));
//        Button text = (Button) findViewById(R.id.button);
//        text.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(BatteryCheck.this, PaySelect.class);
//                startActivity(intent);
//            }
//
//        });

    }

    private void readChargingStatus(){
        String url = "http://192.168.231.121/readChargingStatus.php";
        RequestQueue queue = Volley.newRequestQueue(BatteryCheck.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, response -> {
            Log.e("TAG", "RESPONSE IS " + response);
            try {
                JSONObject jsonObject = new JSONObject(response);
                isConnected = jsonObject.getString("isConnected");
                chargingStatus = jsonObject.getString("chargingStatus");

                TextView a1 = findViewById(R.id.ans1);
                a1.setText(isConnected);

                TextView a2 = findViewById(R.id.ans2);
                a2.setText(chargingStatus);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {
            Toast.makeText(BatteryCheck.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

        };
        queue.add(request);
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        stopRepeatingTask();
//    }
//
//    Runnable mStatusChecker = new Runnable() {
//        @Override
//        public void run() {
//            try {
//                updateStatus(); //this function can change value of mInterval.
//            } finally {
//                // 100% guarantee that this always happens, even if
//                // your update method throws an exception
//                mHandler.postDelayed(mStatusChecker, mInterval);
//            }
//        }
//    };
//
//    void startRepeatingTask() {
//        mStatusChecker.run();
//    }
//
//    void stopRepeatingTask() {
//        mHandler.removeCallbacks(mStatusChecker);
//    }
}