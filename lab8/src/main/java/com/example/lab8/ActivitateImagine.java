package com.example.lab8;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class ActivitateImagine extends AppCompatActivity {

    private List<ImagineCafea> lista = new ArrayList<>();
    private List<Bitmap> imagini = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitate_imagine);

        List<String> linkuriImagini = new ArrayList<>();
        linkuriImagini.add("https://commons.wikimedia.org/wiki/Special:FilePath/Tazzina_di_caff%C3%A8_a_Ventimiglia.jpg?width=400");
        linkuriImagini.add("https://commons.wikimedia.org/wiki/Special:FilePath/Cappuccino_at_Sightglass_Coffee.jpg?width=400");
        linkuriImagini.add("https://commons.wikimedia.org/wiki/Special:FilePath/Latte_at_Doppio_Ristretto_Chiang_Mai_01.jpg?width=400");
        linkuriImagini.add("https://commons.wikimedia.org/wiki/Special:FilePath/Hokitika_Cheese_and_Deli,_Hokitika_(3526706594).jpg?width=400");
        linkuriImagini.add("https://commons.wikimedia.org/wiki/Special:FilePath/A_small_cup_of_coffee.JPG?width=400");

        Handler handler = new Handler(Looper.getMainLooper());

        Executors.newSingleThreadExecutor().execute(() -> {
            for (String link : linkuriImagini) {
                try {
                    HttpURLConnection con = (HttpURLConnection) new URL(link).openConnection();
                    con.setRequestProperty("User-Agent", "Mozilla/5.0");
                    InputStream is = con.getInputStream();
                    imagini.add(BitmapFactory.decodeStream(is));
                } catch (Exception e) {
                    imagini.add(null);
                }
            }


            handler.post(() -> {
                lista.add(new ImagineCafea("Espresso", imagini.get(0), "https://en.wikipedia.org/wiki/Espresso"));
                lista.add(new ImagineCafea("Cappuccino", imagini.get(1), "https://en.wikipedia.org/wiki/Cappuccino"));
                lista.add(new ImagineCafea("Latte", imagini.get(2), "https://en.wikipedia.org/wiki/Latte"));
                lista.add(new ImagineCafea("Americano", imagini.get(3), "https://en.wikipedia.org/wiki/Caff%C3%A8_Americano"));
                lista.add(new ImagineCafea("Cafea", imagini.get(4), "https://en.wikipedia.org/wiki/Coffee"));

                ListView lv = findViewById(R.id.imagini);
                lv.setAdapter(new ImagineAdapter(this, R.layout.imagine_layout, lista));
            });
        });

        ListView lv = findViewById(R.id.imagini);
        lv.setOnItemClickListener((parent, view, i, id) -> {
            Intent it = new Intent(this, WebActivity.class);
            it.putExtra("link", lista.get(i).getLink());
            startActivity(it);
        });
    }
}