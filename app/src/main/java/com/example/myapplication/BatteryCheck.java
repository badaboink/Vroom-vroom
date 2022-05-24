package com.example.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BatteryCheck extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_charging);

        Bundle bundle = getIntent().getExtras();
        int chargeFrom = bundle.getInt("battery");
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(chargeFrom);
        TextView perc = findViewById(R.id.percentage);
        perc.setText(String.format("%d %%", chargeFrom));
        Button text = (Button) findViewById(R.id.button);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BatteryCheck.this, PaySelect.class);
                startActivity(intent);
            }

        });

    }

}