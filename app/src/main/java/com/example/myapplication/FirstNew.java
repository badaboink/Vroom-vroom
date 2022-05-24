package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FirstNew extends AppCompatActivity {

    //visi duomenys
    String[] info = {"Į Europą netrukus atkeliaus Kinijos milžinės pagamintas elektrinis hečbekas",
            "Elektronika.lt",
            "2022-05-23T06:21:00Z",
            "Didžiausias Kinijos automobilių gamintojas SAIC, kuriam priklauso prekinis ženklas MG, jau pradėjo naujojo „CyberE“ modelio gamybą. Naujasis hečbekas sukurtas bendradarbiaujant su britais tam,kad būtų pasiūlytas produktas, kuris atitinka pasaulinės rinkos standartus.",
            "Didžiausias Kinijos automobilių gamintojas SAIC, kuriam priklauso prekinis ženklas MG, jau pradėjo naujojo „CyberE“ modelio gamybą. Naujasis hečbekas sukurtas bendradarbiaujant su britais tam, kad būtų pasiūlytas produktas, kuris atitinka pasaulinės rinkos vartotojų įpročius bei dizaino standartus.\n" +
                    "\n" +
                    "Į Europą netrukus atkeliaus Kinijos milžinės pagamintas elektrinis hečbekas\n" +
                    "Nuo pat šio projekto pradžios, Europos rinka buvo vienas pagrindinių regionų, kur buvo ketinama pardavinėti šį elektrinį automobilį. Su „Volkswagen ID.3“ konkuruoti pasiryžusi MG naujiena jau netrukus atkeliaus į Europą, o jo kaina turėtų prasidėti nuo maždaug 23 tūkst. eurų.\n" +
                    "\n" +
                    "Pirmosios „MG CyberE“ nuotraukos internete pasirodė dar vasario mėnesį. MG EH32 buvo sukurtas derinant naujos kartos EV architektūrą, išmanų vairavimą, automobilio saloną bei jo komfortą. Daug nuopelnų tenka Fudzian provincijos ir Ningde miesto vyriausybėms, kurios parodė tvirtą paramą ir padėjo atnaujinti darbą bei gamybą SAIC Ningde gamykloje.\n" +
                    "\n" +
                    "MG atstovų teigimu, naujasis „CyberE“ bus pagamintas naudojant naująją EV platformą, kuri palaiko 3 lygio autonominį vairavimą, išmaniąją kabiną ir 5G ryšį. Neoficialiais duomenimis, naujajame MG elektromobilyje bus analogiška jėgos pavara kaip ir „MG Marvel R“ modelyje. Jis gaminamas su dvigubu 288 AG varikliu arba 284 trijų variklių konfigūracija ir jau kuris laikas parduodamas Europoje.\n" +
                    "\n" +
                    "Kaip jau minėjome, didžiausias „MG CyberE“ hečbeko konkurentas šioje rinkoje yra „Volkswagen ID.3“. Abu modeliai pasižymi panašiu dizainu, o taip pat parduodami toje pačioje kainų grupėje. Akivaizdu, jog Kinijos milžinė nusiteikusi pasiūlyti išties konkurencingą produktą ir visiškai nebijo konkuruoti su Europoje daugelio pamėgtais gamintojais.\n" +
                    "\n" +
                    "Be būsimo „MG CyberE“, Ningde gamykla gamina ir eksportuoja automobilius į Naująją Zelandiją, Europą, Australiją, Artimuosius Rytus ir Pietų Ameriką bei kai kurias kitas užsienio rinkas. Pastaroji gamykla jau tapo didžiausia Kinijos elektromobilių eksporto baze. Tai padės prekės ženklui pasiekti užsibrėžtą tikslą 2022 metais ir Europoje pristatyti maždaug 120 000 transporto priemonių.\n"
            ,"http://www.elektronika.lt/naujienos/elektronika/82953/i-europa-netrukus-atkeliaus-kinijos-milzines-pagamintas-elektrinis-hecbekas/"};
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
        img_news.setImageResource(R.drawable.hecbeck);
        txt_url.setText(info[5]);
    }
}