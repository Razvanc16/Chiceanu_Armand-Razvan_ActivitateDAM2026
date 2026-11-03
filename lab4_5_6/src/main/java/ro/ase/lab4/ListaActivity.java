package ro.ase.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity {

    private ArrayList<Cafea> lista;
    private CafeaAdapter adapter = null;
    private int idModificat = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle bundle = getIntent().getExtras();
        lista = (bundle != null) ? bundle.getParcelableArrayList(MainActivity.KEY_LISTA) : null;
        if (lista == null) lista = new ArrayList<>();

        ListView lv = findViewById(R.id.lvCafele);
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
//        lv.setAdapter(adapter);
        adapter = new CafeaAdapter(lista, getApplicationContext(), R.layout.activity_listview);
        lv.setAdapter(adapter);

        trimiteListaInapoi();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentModifica = new Intent(getApplicationContext(), AddActivity.class);
                intentModifica.putExtra("cafea", lista.get(i));
                idModificat = i;
                startActivityForResult(intentModifica, 209);
            }
        });

        lv.setOnItemLongClickListener((parent, view, position, id) -> {
            Cafea stearsa = lista.remove(position);
            adapter.notifyDataSetChanged();
            trimiteListaInapoi();
            Toast.makeText(this, "Stearsa: " + stearsa.getDenumire(), Toast.LENGTH_SHORT).show();
            return true;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 209) {
                lista.set(idModificat, data.getParcelableExtra("cafea"));
                adapter.notifyDataSetChanged();
                trimiteListaInapoi();
            }
        }
    }

    private void trimiteListaInapoi() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(MainActivity.KEY_LISTA, lista);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
    }
}