package com.example.myapplication.News;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.News.News_Models.NewsHeadlines;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private final Context context;
    private  List<NewsHeadlines> headlines;
    private  SelectListener listener;

    private String[] news;

    public CustomAdapter(Context context, String[] news) {
        this.context = context;
        this.news = news;
    }

    public CustomAdapter(Context context, List<NewsHeadlines> headlines, SelectListener listener) {
        this.context = context;
        this.headlines = headlines;
        this.listener=listener;
    }

    //headlines = {"source":"id":null,"name":"Sc.bns.lt","author":null,"title":"Ekspertų įvertintoje Lietuvos pašto strategijoje – į besikeičiančią rinką orientuoti pokyčiai - BNS Spaudos centre","description":"Lietuvos pašto strategija pirmą kartą didelių valstybės valdomų įmonių kategorijoje įvertinta kaip viena iš pačių geriausių. Kasmet tokį vertinimą atliekantis Valdymo koordinavimo centras Lietuvos pašto 2022–2024 metų strategijai skyrė 9,8...","url":"https://sc.bns.lt/view/item/428497","urlToImage":"https://sc.bns.lt/img/1/og_lt.png","publishedAt":"2022-05-24T06:35:43Z","content":"Lietuvos pato strategija pirm kart dideli valstybs valdom moni kategorijoje vertinta kaip viena i pai geriausi. Kasmet tok vertinim atliekantis Valdymo koordinavimo centras Lietuvos pato 20222024 met… [+2562 chars]"},{"source":{"id":null,"name":"15min.lt"},"author":"15min","title":"„InMedica“ įsigijo šeimos kliniką Panevėžyje | Verslas | 15min.lt - 15min","description":"Medicinos klinikų tinklas „InMedica“ įsigijo privačią „J. Pauliuko šeimos kliniką“ Panevėžyje, teikiančią šeimos medicinos paslaugas. Tai pirmoji „InMedica“ investicija į šeimos klinikas šiame mieste, rašoma pranešime žiniasklaidai.","url":"https://www.15min.lt/verslas/naujiena/bendroves/inmedica-isigijo-seimos-klinika-panevezyje-663-1683974","urlToImage":"https://s1.15min.lt/static/cache/MTIwMHg2MjgsMTA1MHg2NTMsOTkzMjUyLG9yaWdpbmFsLCxpZD02MDU3MzE4JmRhdGU9MjAyMSUyRjA3JTJGMjYsMTAxMjczMjgwMw==/inmedica-60fe64da8a685.jpg","publishedAt":"2022-05-24T06:29:21Z","content":"„Iki iol „InMedica“ tiesiogiai neteik eimos medicinos paslaug Panevyje, todl diaugiams, jog nuo iol ias paslaugos bus prieinamos ir Panevio gyventojams. Esame dkingi klinikos krjui J.Pauliukui u pasi… [+1171 chars]"},{"source":{"id":null,"name":"15min.lt"},"author":"15min","title":"Taivano finansinių technologijų kompanija „Xrex“ pradeda veiklą Lietuvoje - 15min","description":"Siekdama įžengti į ES rinką „Xrex“ – Taivano blokų grandinių (angl. „blockchain“) finansų technologijų įmonė – tramplynu pasirinko Lietuvą. Veiklą Lietuvoje pradedanti „Xrex“ naujam padaliniui planuoja patikėti operacines, pinigų plovimo prevencijos ir kitas …","url":"https://www.15min.lt/verslas/naujiena/bendroves/taivano-finansiniu-technologiju-kompanija-xrex-pradeda-veikla-lietuvoje-663-1683972","urlToImage":"https://s1.15min.lt/static/cache/MTIwMHg2MjgsMTY2OXgxMzEsOTg5MTM4LG9yaWdpbmFsLCxpZD02MDI3NTYwJmRhdGU9MjAyMSUyRjA3JTJGMTAsMTE2Nzk5ODI0Mw==/verslas-60e8be15a958d.jpg","publishedAt":"2022-05-24T06:29:21Z","content":"„Xrex“, kurios centrin bstin yra Taipjuje, sukr blok grandine paremt produkt serij, skaitant slyginio deponavimo paslaug „BitCheck“ bei vienintel pasaulyje kriptovaliut ir tradicini valiut (angl. „fi… [+2834 chars]"},{"source":{"id":null,"name":"Www.lrt.lt"},"author":"LRT.lt","title":"„Amazon Alexa“ ir „Google Assistant“: virtualių asistentų galimybės ir skirtumai - LRT","description":"Dar neseniai balso komandos virtualiam asistentui atrodė lyg fantastinio filmo scena, tačiau per pastaruosius kelerius metus šie išmanieji padėjėjai itin išpopuliarėjo ir tapo kasdien naudojamu įrankiu. Statistikos bendrovės „Statista“ duomenimis, iki 2024 m.…","url":"https://www.lrt.lt/naujienos/mokslas-ir-it/11/1700671/amazon-alexa-ir-google-assistant-virtualiu-asistentu-galimybes-ir-skirtumai","urlToImage":"https://www.lrt.lt/img/2021/01/26/815416-217723-1287x836.jpg","publishedAt":"2022-05-24T06:25:00Z","content":"Dar neseniai balso komandos virtualiam asistentui atrod lyg fantastinio filmo scena, taiau per pastaruosius kelerius metus ie imanieji padjjai itin ipopuliarjo ir tapo kasdien naudojamu rankiu. Stati… [+3652 chars]"},{"source":{"id":null,"name":"Lrytas.lt"},"author":"„Žinių radijas“","title":"Paskelbė tyrimą, kurie automobilių gamintojai pasiruošę pereiti prie ekologijos: kitiems reikės gerokai pasistengti - Lrytas.lt","description":"Iki 2029 metų vos keturiolika procentų „Toyota“ pagaminamų automobilių bus varomi tik elektros. 18 procentų „Honda“ ir 22 procentai „Nissan“ bus varomi elektros, kiti – vis dar terš.","url":"https://www.lrytas.lt/auto/technika/2022/05/24/news/paskelbe-tyrima-kurie-automobiliu-gamintojai-pasiruose-pereiti-prie-ekologijos-kitiems-reikes-gerokai-pasistengti-23408008","urlToImage":"https://plus.lrytas.lt/_php/thumb/fb/?cosite=auto&id=https://media.lrytas.lt/images/2022/05/20/064542733-f8838230-7f21-4a15-ada8-2855f05195ab.jpg&articleID=23408008","publishedAt":"2022-05-24T06:21:00Z","content":"Iki 2029 met vos keturiolika procent „Toyota“ pagaminam automobili bus varomi tik elektros. 18 procent „Honda“ ir 22 procentai „Nissan“ bus varomi elektros, kiti – vis dar terš.\r\nPalyginus, „Hyundai“… [+446 chars]"},{"source":{"id":null,"name":"15min.lt"},"author":"15min","title":"„Capitalica“ Baltijos šalių NT fondas platina 3 mln. eurų obligacijų emisiją - 15min","description":"SBA grupės bendrovės „Capitalica Asset Management“ valdomo nekilnojamojo turto fondas „Capitalica Baltic Real Estate Fund I“ pradeda platinti 3 mln. eurų vertės obligacijas. Obligacijų emisija įsigalios nuo 2022 m. birželio 3 d.  Skolos vertybinius popierius …","url":"https://www.15min.lt/verslas/naujiena/bendroves/capitalica-baltijos-saliu-nt-fondas-platina-3-mln-euru-obligaciju-emisija-663-1683988","urlToImage":"https://s1.15min.lt/static/cache/MTIwMHg2MjgsODcxeDYyMSw4MTIzNjQsb3JpZ2luYWwsLGlkPTQ0NDY3MDAmZGF0ZT0yMDE5JTJGMDElMkYyMSwzODQwMzg1NDYz/grynieji-eurai-5c45bd9986ed4.jpg","publishedAt":"2022-05-24T06:20:21Z","content":"Pritrauktos los bus panaudotos daliniam 2019 m. ileist 5 mln. eur verts obligacij emisijos refinansavimui. Prie 3 metus u obligacijas gautos los buvo investuotos Rygoje statom dviej A klass biur komp… [+792 chars]"};

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.headline_list_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, @SuppressLint("RecyclerView") int position) { //@SuppressLint("RecyclerView")
        holder.text_title.setText(headlines.get(position).getTitle());
        holder.text_source.setText(headlines.get(position).getSource().getName());

        if(headlines.get(position).getUrlToImage()!=null) {
            Picasso.get().load(headlines.get(position).getUrlToImage()).into(holder.img_headline);
        }
       holder.cardView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               listener.OnNewsClicked(headlines.get(position));
           }
       });
    }


    @Override
    public int getItemCount() {
        return headlines.size();
    }
}
