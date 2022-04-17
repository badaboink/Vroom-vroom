package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.myapplication.Prices.SaveLoadData.savedPrice1;
import static com.example.myapplication.Prices.SaveLoadData.savedPrice2;
import static com.example.myapplication.Prices.SaveLoadData.savedPrice3;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.databinding.ActivityPaySelectBinding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class PaySelect extends AppCompatActivity {

    ListView lv;
    String[] companies = {"Elektrum", "Ignitis", "Eso"};
    double price=0;
    Double totalSum;
    Button pay;
    TextView totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_select);
        totalPrice = findViewById(R.id.totalPrice);
        totalSum=0.00;
        totalPrice.setText(totalSum.toString() + " €");

        lv=findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_activated_1,companies);
        lv.setAdapter(adapter);

       lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override //kazkas cia negerai, nes jei paspaudi, sustoja programa
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //String item = listAdapter.getItem(i);
                int index =i;
                if(index==0) {
                    price = Double.parseDouble("0.66");//(savedPrice1); NEVEIKIA?Kornelijos paklaust
                }
                else if(i==1){
                    price = Double.parseDouble("0.88");//(savedPrice2);
                }
                else {
                    price = Double.parseDouble("0.99");//(savedPrice3);
                }
                double percent = 75; //is Egles gaut reikes

                totalSum = percent*price; //cia nzn kaip reikes dar konvertuot

                totalPrice.setText(totalSum.toString() + " €");
                //Toast.makeText(getApplicationContext(),(int)price, Toast.LENGTH_SHORT).show();
            }

        });


        //change button dar jei paspaus kur procentus keicia
        pay = (Button)findViewById(R.id.pay_butt);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Mokėjimas sėkmingas", Toast.LENGTH_SHORT).show();

                //nuskaiciuot pinigus ir irasyt i faila
                Double money = MainActivity.GetMoney();
                Double Left = money-totalSum;
                MainActivity.SetMoney(Left);

                //failas - netinka assets - jie readonly :(((
                //writeToFile("money.txt",Left.toString());

                //iseit i main
                Intent intent = new Intent(PaySelect.this, MainActivity.class);
                startActivity(intent);

                //krovimo pradzia vaizdavimo
                //veliau uzdet tikrinima ar yra pinigu tiek
            }

        });

    }
    public void writeToFile(String filename, String content ) {


        File file = new File(PaySelect.this.getFilesDir(), filename);
        if (!file.exists()) {
            file.mkdir();
        }
        try {
            File gpxfile = new File(file, "sample");
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(content);
            writer.flush();
            writer.close();
        } catch (Exception e) { }


       /*File path = getApplicationContext().getFilesDir();
       try{
           FileOutputStream writer = new FileOutputStream(new File(path, filename));
           writer.write(content.getBytes(StandardCharsets.UTF_8));
            writer.close();
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }*/

    }



}