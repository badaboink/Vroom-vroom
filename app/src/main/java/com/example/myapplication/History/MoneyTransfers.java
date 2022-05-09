package com.example.myapplication.History;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.History.MoneyAdapter;
import com.example.myapplication.R;

import java.text.DecimalFormat;

public class MoneyTransfers extends AppCompatActivity{

   String dates[] = {"2022-05-09", "2022-05-01","2022-04-16","2022-04-12", "2022-03-15", "2022-03-14"};
   String info[]={"Elektros pirkimas iš Ignitis", "Elektros pirkimas iš Ignitis",
           "Elektros pirkimas iš Elektrum","Elektros pirkimas iš Eso",
           "Elektros pirkimas iš Elektrum","Elektros pirkimas iš Ignitis"};
   Double ammount[] = {-20.5,-15.6,-50.69,-31.1, -12.5, -5.98};
    private static final DecimalFormat df = new DecimalFormat("0.00");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_transfers);
        TextView spent, gained;
        RecyclerView history;
        spent = findViewById(R.id.spentMoney);
        gained = findViewById(R.id.gainedMoney);
        history = findViewById(R.id.moneyHistory);
        Double spentMoney=0.00;
        for(int i=0; i<ammount.length;i++){
            spentMoney+=ammount[i];
        }
        spent.setText(df.format(spentMoney).toString() +" €");
        gained.setText("0.00 €");

        MoneyAdapter adapter = new MoneyAdapter(this,dates,info,ammount);
        history.setAdapter(adapter);
        history.setLayoutManager(new LinearLayoutManager(this));

    }
}