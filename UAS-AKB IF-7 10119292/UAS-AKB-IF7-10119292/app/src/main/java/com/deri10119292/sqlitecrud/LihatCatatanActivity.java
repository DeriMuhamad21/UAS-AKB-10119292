package com.deri10119292.sqlitecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class LihatCatatanActivity extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    TextView txtIsi, txtJudul, txtId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_catatan);

        dbHelper = new DataHelper(this);
        txtJudul = findViewById(R.id.tvJudul);
        txtIsi = findViewById(R.id.tvIsi);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM t_catatan WHERE judul = '" +
                getIntent().getStringExtra("judul") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            txtJudul.setText(cursor.getString(1).toString());
            txtIsi.setText(cursor.getString(2).toString());
        };
    }
}