package com.example.lab9;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VremeActivity extends AppCompatActivity {

    private static final String API_KEY = "cheia mea";
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vreme);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etOras = findViewById(R.id.etOras);
        Button btnCauta = findViewById(R.id.btnCauta);
        TextView tvRezultat = findViewById(R.id.tvRezultat);

        btnCauta.setOnClickListener(v -> {
            String oras = etOras.getText().toString();

            executor.execute(() -> {
                try {

                    String raspuns1 = descarca("https://dataservice.accuweather.com/locations/v1/cities/search?q=" + oras);
                    JSONArray vector = new JSONArray(raspuns1);
                    String cheieOras = vector.getJSONObject(0).getString("Key");

                    String raspuns2 = descarca("https://dataservice.accuweather.com/forecasts/v1/daily/1day/"
                            + cheieOras + "?metric=true");
                    JSONObject obiect = new JSONObject(raspuns2);
                    JSONArray zile = obiect.getJSONArray("DailyForecasts");
                    JSONObject temperatura = zile.getJSONObject(0).getJSONObject("Temperature");
                    double min = temperatura.getJSONObject("Minimum").getDouble("Value");
                    double max = temperatura.getJSONObject("Maximum").getDouble("Value");

                    String text = "Codul orasului este " + cheieOras +  "\nMin: " + min + " C\nMax: " + max + " C";
                    handler.post(() -> tvRezultat.setText(text));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });
    }

    private String descarca(String link) throws Exception {
        URL url = new URL(link);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String linie;
        while ((linie = reader.readLine()) != null) {
            builder.append(linie);
        }
        return builder.toString();
    }
}