package com.example.myapplication;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    private EditText usernameIDEdt, sellToGridIDEdt,buyFromGridIDEdt;
    private TextView dateTV, priceTV;
    private Button getPriceBtn, setPriceBtn;
    private CardView priceCV;
    private String username, sellToGrid, buyFromGrid, charging;
    private RadioGroup radioGroup;
    private RadioButton radioButton1, radioButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        radioGroup = findViewById(R.id.radioGroup);
        radioButton1 = findViewById(R.id.charge);
        radioButton2 = findViewById(R.id.discharge);


        dateTV = findViewById(R.id.idTVDate);
        priceTV = findViewById(R.id.idTVPrice);

        setPriceBtn = findViewById(R.id.idBtnSetPrice);
        priceCV = findViewById(R.id.idCVPriceItem);

        usernameIDEdt = findViewById(R.id.idEdtUsername);
        sellToGridIDEdt = findViewById(R.id.idEdtSellToGrid);
        buyFromGridIDEdt = findViewById(R.id.idEdtBuyFromGrid);


        setPriceBtn.setOnClickListener(v -> {
            username = usernameIDEdt.getText().toString();
            sellToGrid = sellToGridIDEdt.getText().toString();
            buyFromGrid = buyFromGridIDEdt.getText().toString();
            if((!radioButton1.isChecked()) && (!radioButton2.isChecked())){
                Toast.makeText(MainActivity2.this, "Nepasirinkote ar norite įkrauti ar iškrauti", Toast.LENGTH_SHORT).show();
            }
            else{
                if(radioButton1.isChecked()){
                    charging = "yes";

                }
                else if(radioButton2.isChecked()){
                    charging = "no";

                }
                if(TextUtils.isEmpty(username)){
                    usernameIDEdt.setError("Please enter username");
                } else if (TextUtils.isEmpty(username)){
                    sellToGridIDEdt.setError("Please enter sell price");
                } else if (TextUtils.isEmpty(username)){
                    buyFromGridIDEdt.setError("Please enter buy price");
                }
                else {
                    setPrice(username, sellToGrid, buyFromGrid, charging);
                }
            }
        });
    }



    private void setPrice(String username, String sellToGrid, String buyFromGrid, String charging) {

        String url = "http://192.168.245.121/writeElectricityPrices.php";
        RequestQueue queue = Volley.newRequestQueue(MainActivity2.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, response -> {
            Log.e("TAG", "RESPONSE IS " + response);
            try {
                JSONObject jsonObject = new JSONObject(response);
                Toast.makeText(MainActivity2.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            usernameIDEdt.setText("");
            sellToGridIDEdt.setText("");
            buyFromGridIDEdt.setText("");
        }, error -> {
            Toast.makeText(MainActivity2.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("sellToGrid", sellToGrid);
                params.put("buyFromGrid", buyFromGrid);
                params.put("charging", charging);
                return params;
            }
        };
        queue.add(request);
    }
}
