package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.braintreepayments.cardform.view.CardForm;

import java.util.HashMap;
import java.util.Map;

public class CreditCardActivity extends AppCompatActivity {

    CardForm cardForm;
    Button add;
    private String URL = "http://10.0.2.2/login/card.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card);

        cardForm = findViewById(R.id.card_form);
        add = findViewById(R.id.btnAdd);
        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(false)
                .mobileNumberRequired(false)
                .setup(CreditCardActivity.this);


        cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cardForm.isValid())
                {
                    String card = cardForm.getCardNumber();
                    String email = LoginActivity.emailFromDb;
                    String password = LoginActivity.passwordFromDb;
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(CreditCardActivity.this);
                    alertBuilder.setTitle("Sėkmingai pridėta");
                    alertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("res", response);
                            if (response.equals("true")) {
                                Toast.makeText(CreditCardActivity.this, "Teisinga", Toast.LENGTH_SHORT).show();


                            } else if (response.equals("failure")) {
                                Toast.makeText(CreditCardActivity.this, "Neteisinga", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(CreditCardActivity.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> data = new HashMap<>();
                            data.put("password", password);
                            data.put("email", email);
                            data.put("card_info", card);
                            return data;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                    Intent intent = new Intent(CreditCardActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(CreditCardActivity.this,"Neteisingai užpildyta forma", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}

    /*CreditCardActivity cardForm = (CreditCardActivity) findViewById(R.id.card_form);
cardForm.cardRequired(true)
        .expirationRequired(true)
        .cvvRequired(true)
        .cardholderName(CardForm.FIELD_REQUIRED)
        .postalCodeRequired(true)
        .mobileNumberRequired(true)
        .mobileNumberExplanation("SMS is required on this number")
        .actionLabel("Purchase")
        .setup(activity);
}*/