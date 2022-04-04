package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button changeprice = (Button) findViewById(R.id.accessprice);

        changeprice.setVisibility(View.GONE);
        if(MainActivity.isitadmin())
        {
            changeprice.setVisibility(View.VISIBLE);
            changeprice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent startIntent = new Intent(getApplicationContext(), Admin.class);
                    startActivity(startIntent);
                }
            });
        }
    }
}