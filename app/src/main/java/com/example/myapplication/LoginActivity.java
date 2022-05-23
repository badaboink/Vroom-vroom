package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Console;
import java.io.IOException;
import java.sql.*;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.MainActivity;
import com.example.myapplication.Register;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword, etMoney;
    private static String email, password, name, money;
    public static final String serverurl="localhost/";
    public static final String db_nameurl="register_from_android";
    public static final String userNameurl="root";
    public static final String passwordurl="";


    public static String emailFromDb, passwordFromDb, nameFromDb, autoFromDB, autoNrFromDb, cardFromDb, batteryFromDb;

    private String URL = "http://10.0.2.2/login/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = password = name = "";
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        //etMoney= findViewById(R.id.tabletextmoney);

    }

    /*public void Connect() {
        ConnectMySql task = new ConnectMySql();
        task.execute();

    }
    protected static class ConnectMySql extends AsyncTask<String, Void, String>{
        @Override
        protected void onPreExecute() {super.onPreExecute();}

        @Override
        protected String doInBackground(String... params){
            try{

                Class.forName("com.mysql.jdbc.Driver").newInstance();
            }catch(Exception e){
                e.printStackTrace();
                emailFromDb = e.getMessage();
            }
            try{
                Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/register_from_android", "root", "");
                //Connection con= DriverManager.getConnection("jdbc:mysql://"+serverurl+db_nameurl,userNameurl,passwordurl);
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM users");

                while(rs.next()){
                    emailFromDb = rs.getString("email");
                    if(email.equals(emailFromDb)){
                        nameFromDb = rs.getString("name");
                        passwordFromDb = rs.getString("password");
                    }
                }
                st.close();
                con.close();
            }catch(Exception e){
                e.printStackTrace();
                //emailFromDb = "error";
                emailFromDb = e.getMessage();
            }
            return "";
        }

    }*/
    public void login(View view) {
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        //money = etMoney.getText().toString().trim();

        //if(!email.equals("") && !password.equals("")){

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("res", response);
                    if (!response.equals("failure")) {
                        try
                        {
                            JSONObject jsonobject = new JSONObject(response);
                            JSONArray jsonarray = jsonobject.getJSONArray("users");
                            JSONObject data = jsonarray.getJSONObject(0);

                            nameFromDb = data.getString("name");
                            emailFromDb = data.getString("email");
                            autoFromDB = data.getString("connected_auto");
                            autoNrFromDb = data.getString("auto_nr");
                            cardFromDb = data.getString("card_info");
                            batteryFromDb = data.getString("battery");
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //Connect();
                        if(email.equals("admin@gmail.com") && password.equals("admin"))
                        {
                            MainActivity.adminloggedin();
                        }
                        //emailFromDb = email;
                        MainActivity.changelogin();
                        Bundle bundle = new Bundle();
                        String returnEmail = "";
                        returnEmail = email;
                        bundle.putString("email", returnEmail);

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else if (response.equals("failure")) {
                        Toast.makeText(LoginActivity.this, "Neteisingas el. paštas/slaptažodis", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(LoginActivity.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("email", email);
                    data.put("password", password);
                    //data.put("money", money);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        //}else{
            //Toast.makeText(this, "Fields can not be empty!", Toast.LENGTH_SHORT).show();
        //}
    }

    public void register(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
        finish();
    }
}