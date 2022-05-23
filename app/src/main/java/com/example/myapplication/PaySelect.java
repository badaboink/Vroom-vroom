package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Prices.SaveLoadData;

import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;

public class PaySelect extends AppCompatActivity {

    ListView lv;
    String[] companies = {"Elektrum", "Ignitis", "Eso"};
    double price=0;
    static Double totalSum=0.00;
    Button pay, percentChange;
    TextView totalPrice;
    Double mmoeyd;
    String saved1,saved2, saved3 ="";
    EditText percentage;
    String subPrice="";
    static int currBattery = 0;
    private String URL = "http://10.0.2.2/login/register.php";
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static boolean success = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        totalSum=0.00;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_select);
        totalPrice = findViewById(R.id.totalPrice);
        //totalSum=0.00;

        totalPrice.setText(df.format(totalSum) + " €");

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
                    saved1 = SaveLoadData.load(SaveLoadData.savedPrice1,getApplicationContext());
                    subPrice = saved1.substring(1,5);

                }
                else if(i==1){
                   saved2 = SaveLoadData.load(SaveLoadData.savedPrice2,getApplicationContext());
                    subPrice = saved2.substring(1,5);

                }
                else {
                    saved3 = SaveLoadData.load(SaveLoadData.savedPrice3,getApplicationContext());
                    subPrice = saved3.substring(1,5);

                }
                currBattery = MainActivity.Battery;
                price = Double.parseDouble(subPrice);//(savedPrice2);
                percentage=findViewById(R.id.percentChange);
                Double percent =0.00;
                try{
                     percent = Double.parseDouble(percentage.getText().toString()); //is Egles gaut reikes
                }
                catch (NumberFormatException e){
                    percent=0.00;

                }
                totalSum = percent*price;
                String stringsum = df.format(totalSum); //cia nzn kaip reikes dar konvertuot
                totalPrice.setText(stringsum + " €");

                //totalPrice.setText(totalSum + " €");
                //Toast.makeText(getApplicationContext(),(int)price, Toast.LENGTH_SHORT).show();
            }

        });


        //change button dar jei paspaus kur procentus keicia
        pay = (Button)findViewById(R.id.pay_butt);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO:tikrint ar tiek reikia procentu ir ar yra tiek pinigu

                Toast.makeText(getApplicationContext(), "Mokėjimas sėkmingas", Toast.LENGTH_SHORT).show();

                //nuskaiciuot pinigus ir irasyt i faila
                Double money = MainActivity.GetMoney();
                Double Left = money-totalSum;
                MainActivity.SetMoney(Left);

                //su duombaze

                String info = "Mokėjimas už elektrą EVVVR" +ThreadLocalRandom.current().nextInt(1000,5000);
                //arba paimt imone tik ir ne saskaita
                Double spent, gained;
                //spent = getSpent()- spent;
                //gained=getGained()+gained;
                //idet i duombaze viska

                //failas - netinka assets - jie readonly :(((
                //writeToFile("money.txt",Left.toString());

                //iseit i main
               /* Intent intent = new Intent(PaySelect.this, MainActivity.class);
                startActivity(intent);*/

                Intent i = new Intent();
                i.setClassName("com.example.myapplication", "com.example.myapplication.MainActivity");
                i.putExtra("money", Left.toString());
                //startActivity(i);

                Intent ibattery = new Intent(PaySelect.this, Battery.class);
                Bundle bundle = new Bundle();
                int percentagereturn = Integer.parseInt(percentage.getText().toString());
                bundle.putInt("percentage", percentagereturn);
                bundle.putInt("currentBattery", currBattery);
                ibattery.putExtras(bundle);
                startActivity(ibattery);



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