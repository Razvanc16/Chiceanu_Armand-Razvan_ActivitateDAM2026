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

public class MainActivity extends AppCompatActivity {

    public static final String KEY_CAFEA = "cafea";
    private static final int REQUEST_ADD = 100;
    private static final int REQUEST_ADD_AI = 101;

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == REQUEST_ADD || requestCode == REQUEST_ADD_AI)
                && resultCode == RESULT_OK && data != null) {
            Bundle bundle = data.getExtras();
            Cafea cafea = bundle.getParcelable(KEY_CAFEA);
            if (cafea != null) {
                TextView tv = findViewById(R.id.tvCafea);
                tv.setText(cafea.toString());
                Toast.makeText(this, "Cafea adaugata: " + cafea.getDenumire(),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}