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
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Battery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_charging);

        // set battery percent to random
        //Random random = new Random();
        //int battery = random.nextInt(100);
        int chargeFrom = 0;
        int battery = 0;
        Bundle bundle = getIntent().getExtras();
        battery = bundle.getInt("percentage");
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(chargeFrom);
        // if button is pressed => charge
        Button text = (Button) findViewById(R.id.button);
        int finalBattery = battery;
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean finished = false;
                ValueAnimator animator = ValueAnimator.ofInt(chargeFrom, finalBattery);
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
                        startActivity(intent);
                        // start your activity here
                    }
                });
                animator.start();

            }

        });

    }

}