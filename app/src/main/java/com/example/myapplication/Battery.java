package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.service.autofill.Transformation;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Battery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_charging);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        Button text = (Button) findViewById(R.id.button);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }

        });

    }

}