package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChangePassword extends AppCompatActivity {
    String email;
    String password;
    String oldpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        Button button = findViewById(R.id.passwordButton);
        button.setOnClickListener(v -> ChangePass());
    }
    private void ChangePass(){
        String URL = "http://10.0.2.2/login/password.php";

        EditText op = findViewById(R.id.oldPassword);
        oldpassword = op.getText().toString().trim();

        EditText newp1 = findViewById(R.id.newPassword);
        String p1 = newp1.getText().toString().trim();

        EditText newp2 = findViewById(R.id.newPassword2);
        String p2 = newp2.getText().toString().trim();
        if(!p1.equals(p2))
        {
            Toast.makeText(ChangePassword.this, "Slaptažodžiai nelygūs", Toast.LENGTH_SHORT).show();
        }
        else{
            password = p1;
            email = LoginActivity.emailFromDb;
        RequestQueue queue = Volley.newRequestQueue(ChangePassword.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("res", response);
                if (response.equals("true")) {
                    Toast.makeText(ChangePassword.this, "Teisinga", Toast.LENGTH_SHORT).show();

                } else if (response.equals("failure")) {
                    Toast.makeText(ChangePassword.this, "Neteisinga", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ChangePassword.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("password", password);
                data.put("email", email);
                data.put("oldpassword", oldpassword);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);}
    }
}