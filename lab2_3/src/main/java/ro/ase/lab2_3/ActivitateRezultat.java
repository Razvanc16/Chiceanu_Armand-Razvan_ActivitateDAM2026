package ro.ase.lab2_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ro.ase.lab2_3.R;

public class ActivitateRezultat extends AppCompatActivity {

    private int valoare1;
    private int valoare2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate_rezultat);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String mesaj = bundle.getString("mesaj");
            valoare1 = bundle.getInt("valoare1", 0);
            valoare2 = bundle.getInt("valoare2", 0);
            Toast.makeText(this, mesaj + "\nValori: " + valoare1 + " si " + valoare2,
                    Toast.LENGTH_LONG).show();
        }
    }

    public void intoarceRezultat(View view) {
        Intent intent = new Intent();
        intent.putExtra("mesaj", "Raspuns din ActivitateRezultat");
        intent.putExtra("suma", valoare1 + valoare2);
        setResult(RESULT_OK, intent);
        finish();
    }
}