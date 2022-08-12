package com.deri10119292.sqlitecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UpdateCatatanActivity extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    EditText txtIsi, txtJudul;
    FloatingActionButton btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_catatan);

        dbHelper = new DataHelper(this);
        txtJudul = (EditText) findViewById(R.id.txtJudul);
        txtIsi = (EditText) findViewById(R.id.txtIsi);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM t_catatan WHERE judul = '" +
                getIntent().getStringExtra("judul") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            txtJudul.setText(cursor.getString(1).toString());
            txtIsi.setText(cursor.getString(2).toString());
        }
        btnUpdate = findViewById(R.id.btnUpdate);
        // daftarkan even onClick pada btnSimpan
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update t_catatan set judul='"+
                        txtJudul.getText().toString() +"', isi='" +
                        txtIsi.getText().toString()+"' where judul='"+getIntent().getStringExtra("judul")+"'");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });
    }
}