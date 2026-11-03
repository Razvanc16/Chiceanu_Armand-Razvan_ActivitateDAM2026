package ro.ase.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_CAFEA = "cafea";
    public static final String KEY_LISTA = "lista";
    private static final int REQUEST_ADD = 100;
    private static final int REQUEST_ADD_AI = 101;
    private static final int REQUEST_LISTA = 102;

    private ArrayList<Cafea> cafele = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void deschideAdaugare(View view) {
        Intent intent = new Intent(getApplicationContext(), AddActivity.class);
        startActivityForResult(intent, REQUEST_ADD);
    }

    public void deschideAdaugareAi(View view) {
        Intent intent = new Intent(getApplicationContext(), AddActivityAi.class);
        startActivityForResult(intent, REQUEST_ADD_AI);
    }

    public void deschideLista(View view) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(KEY_LISTA, cafele);
        Intent intent = new Intent(getApplicationContext(), ListaActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, REQUEST_LISTA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK || data == null || data.getExtras() == null) return;

        Bundle bundle = data.getExtras();

        if (requestCode == REQUEST_ADD || requestCode == REQUEST_ADD_AI) {
            Cafea cafea = bundle.getParcelable(KEY_CAFEA);
            if (cafea != null) {
                cafele.add(cafea);
                TextView tv = findViewById(R.id.tvCafea);
                tv.setText(cafea.toString());
                Toast.makeText(this, "Cafea adaugata: " + cafea.getDenumire()
                        + " (total: " + cafele.size() + ")", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_LISTA) {
            ArrayList<Cafea> listaNoua = bundle.getParcelableArrayList(KEY_LISTA);
            if (listaNoua != null) {
                cafele = listaNoua;
            }
        }
    }
}