package ro.ase.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Spinner spTip = findViewById(R.id.spTip);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.tipuri_cafea, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTip.setAdapter(adapter);
    }

    public void salveazaCafea(View view) {
        EditText etDenumire = findViewById(R.id.etDenumire);
        String denumire = etDenumire.getText().toString().trim();
        if (denumire.isEmpty()) {
            Toast.makeText(this, "Denumirea nu poate fi goala!", Toast.LENGTH_SHORT).show();
            return;
        }

        EditText etPret = findViewById(R.id.etPret);
        String pretText = etPret.getText().toString().trim();
        if (pretText.isEmpty()) {
            Toast.makeText(this, "Introdu pretul!", Toast.LENGTH_SHORT).show();
            return;
        }
        double pret = Double.parseDouble(pretText);
        if (pret <= 0) {
            Toast.makeText(this, "Pretul trebuie sa fie pozitiv!", Toast.LENGTH_SHORT).show();
            return;
        }

        Spinner spTip = findViewById(R.id.spTip);
        String tip = spTip.getSelectedItem().toString();

        RadioGroup rgMarime = findViewById(R.id.rgMarime);
        RadioButton rbMarime = findViewById(rgMarime.getCheckedRadioButtonId());
        if (rbMarime == null) {
            Toast.makeText(this, "Alege marimea!", Toast.LENGTH_SHORT).show();
            return;
        }

        CheckBox cbZahar = findViewById(R.id.cbZahar);
        Switch swPachet = findViewById(R.id.swPachet);
        RatingBar rbRating = findViewById(R.id.rbRating);

        Cafea cafea = new Cafea(denumire, pret, tip, rbMarime.getText().toString(),
                cbZahar.isChecked(), swPachet.isChecked(), rbRating.getRating());

        Bundle bundle = new Bundle();
        bundle.putParcelable(MainActivity.KEY_CAFEA, cafea);
        Intent intent = new Intent();
        intent.putExtras(bundle);

        setResult(RESULT_OK, intent);
        finish();
    }
}