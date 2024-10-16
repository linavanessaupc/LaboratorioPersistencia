package co.edu.unipiloto.laboratoriopersistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private CargaDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new CargaDatabaseHelper(this);

        Button guardarBtn = findViewById(R.id.guardar_button);
        guardarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String origen = ((EditText) findViewById(R.id.origen_input)).getText().toString();
                String destino = ((EditText) findViewById(R.id.destino_input)).getText().toString();
                double peso = Double.parseDouble(((EditText) findViewById(R.id.peso_input)).getText().toString());
                String descripcion = ((EditText) findViewById(R.id.descripcion_input)).getText().toString();

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("origen", origen);
                values.put("destino", destino);
                values.put("peso", peso);
                values.put("descripcion", descripcion);

                long newRowId = db.insert("Carga", null, values);

                if (newRowId != -1) {
                    Toast.makeText(MainActivity.this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error al guardar los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button consultarBtn = findViewById(R.id.consultar_button);
        consultarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                Cursor cursor = db.rawQuery("SELECT * FROM Carga", null);

                StringBuilder builder = new StringBuilder();
                if (cursor.moveToFirst()) {
                    do {
                        int origenIndex = cursor.getColumnIndex("origen");
                        int destinoIndex = cursor.getColumnIndex("destino");
                        int pesoIndex = cursor.getColumnIndex("peso");
                        int descripcionIndex = cursor.getColumnIndex("descripcion");

                        if (origenIndex != -1 && destinoIndex != -1 && pesoIndex != -1 && descripcionIndex != -1) {
                            String origen = cursor.getString(origenIndex);
                            String destino = cursor.getString(destinoIndex);
                            double peso = cursor.getDouble(pesoIndex);
                            String descripcion = cursor.getString(descripcionIndex);

                            builder.append("Origen: ").append(origen)
                                    .append(", Destino: ").append(destino)
                                    .append(", Peso: ").append(peso)
                                    .append(", Descripción: ").append(descripcion).append("\n");
                        } else {
                            builder.append("Error al obtener los datos.").append("\n");
                        }
                    } while (cursor.moveToNext());
                }
                cursor.close();
                Toast.makeText(MainActivity.this, builder.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }
}