package ro.ase.lab2_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivitateNoua extends AppCompatActivity {

    private static final int REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate_noua);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void deschideActivitate(View view) {
        Intent intent = new Intent(getApplicationContext(), ActivitateRezultat.class);

        Bundle bundle = new Bundle();
        bundle.putString("mesaj", "Salut din ActivitateNoua!");
        bundle.putInt("valoare1", 16);
        bundle.putInt("valoare2", 12);
        intent.putExtras(bundle);

        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String mesaj = data.getStringExtra("mesaj");
            int suma = data.getIntExtra("suma", 0);
            Toast.makeText(this, mesaj + "\nSuma: " + suma, Toast.LENGTH_LONG).show();
        }
    }
}