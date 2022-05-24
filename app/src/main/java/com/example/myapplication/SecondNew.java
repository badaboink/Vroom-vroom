package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondNew extends AppCompatActivity {
    String[] info = {"Paskelbė tyrimą, kurie automobilių gamintojai pasiruošę pereiti prie ekologijos: kitiems reikės gerokai pasistengti - Lrytas.lt",
            "Žinių radijas",
            "2022-05-24T06:21:00Z",
            "Iki 2029 metų vos keturiolika procentų „Toyota“ pagaminamų automobilių bus varomi tik elektros. 18 procentų „Honda“ ir 22 procentai „Nissan“ bus varomi elektros, kiti – vis dar terš.",
            "Organizacija „InfluenceMap“ paskelbė tyrimą, kiek automobilių gamintojai yra pasiruošę tapti klimatui neutraliais – tai yra gaminti neišmetančius į atmosferą teršalų automobilius. Tyrimas parodė, kad prasčiausiai pasiruošę iš pasaulinių automobilių gamintojų yra trys Japonijos kompanijos – „Toyota“, „Nissan“ ir „Honda“.\n" +
                    "\n" + "Iki 2029 metų vos keturiolika procentų „Toyota“ pagaminamų automobilių bus varomi tik elektros. 18 procentų „Honda“ ir 22 procentai „Nissan“ bus varomi elektros, kiti – vis dar terš.\n" +
                    "\n" + "Palyginus, „Hyundai“ iki to laiko pasieks 27 procentus, „Ford“ – 36, o „Volkswagen“ net 43 procentus. Šiuo metu Japonijos gamintojų gamoje elektromobiliai siekia vos vieną procentą nuo bendro pagaminamo automobilių kiekio.\n" +
                    "\n" + "Taigi japonams teks gerokai pasistengti, kad pasaulis jų nepasmerktų. Ir procesas vyksta – „Honda“ paskelbė, kad per kitą`dešimtmetį į elektromobilių gamybą investuos net 40 milijardų dolerių, o „Toyota“ koncentruojasi į vandeniliu varomų automobilių konstravimą.\n",
            "https://www.lrytas.lt/auto/technika/2022/05/24/news/paskelbe-tyrima-kurie-automobiliu-gamintojai-pasiruose-pereiti-prie-ekologijos-kitiems-reikes-gerokai-pasistengti-23408008"
    };
    TextView txt_title, txt_author, txt_time, txt_detail, txt_content, txt_url;
    ImageView img_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        txt_title=findViewById(R.id.text_detail_title);
        txt_author=findViewById(R.id.text_detail_author);
        txt_time=findViewById(R.id.text_detail_time);
        txt_detail=findViewById(R.id.text_detail_detail);
        txt_content=findViewById(R.id.text_detail_content);
        img_news=findViewById(R.id.img_detail_news);
        txt_url=findViewById(R.id.txt_url);

        txt_title.setText(info[0]);
        txt_author.setText(info[1]);
        txt_time.setText(info[2]);
        txt_detail.setText(info[3]);
        txt_content.setText(info[4]);
        img_news.setImageResource(R.drawable.lrytas);
        txt_url.setText(info[5]);
    }
}