package co.edu.unipiloto.laboratoriopersistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CargaDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "cargas.db";
    private static final int DATABASE_VERSION = 1;

    public CargaDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Carga (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "origen TEXT, " +
                "destino TEXT, " +
                "peso REAL, " +
                "descripcion TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Carga");
        onCreate(db);
    }
}
