package ro.ase.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity {

    private ArrayList<Cafea> lista;
    private ArrayAdapter<Cafea> adapter;

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
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        lv.setAdapter(adapter);

        trimiteListaInapoi();


        lv.setOnItemClickListener((parent, view, position, id) ->
                Toast.makeText(this, lista.get(position).toString(), Toast.LENGTH_LONG).show());


        lv.setOnItemLongClickListener((parent, view, position, id) -> {
            Cafea stearsa = lista.remove(position);
            adapter.notifyDataSetChanged();
            trimiteListaInapoi();
            Toast.makeText(this, "Stearsa: " + stearsa.getDenumire(), Toast.LENGTH_SHORT).show();
            return true;
        });
    }

    private void trimiteListaInapoi() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(MainActivity.KEY_LISTA, lista);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
    }
}