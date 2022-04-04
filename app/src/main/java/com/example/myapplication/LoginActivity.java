package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView label = (TextView) findViewById(R.id.label);
        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);
        Button loginButton = (Button) findViewById(R.id.buttonLogin);
        DAOPrice dao = new DAOPrice();


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                    MainActivity.changelogin();
                    MainActivity.adminloggedin();
                    Intent startIntent = new Intent(getApplicationContext(), Profile.class);
                    startActivity(startIntent);
                }
                else if(username.getText().toString().equals("bruh") && password.getText().toString().equals("bruh"))
                {
                    MainActivity.changelogin();
                    Intent startIntent = new Intent(getApplicationContext(), Profile.class);
                    startActivity(startIntent);
                }
                else{
                    label.setText("Wrong username or password.");
                }
            }
        });
    }
}