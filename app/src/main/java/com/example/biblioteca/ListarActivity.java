package com.example.biblioteca;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ListarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
    }

    protected void onResume() {
        super.onResume();
        SQLiteDatabase db = openOrCreateDatabase("biblioteca.db", Context.MODE_PRIVATE,
                null);
        String[] from = {"_id","titulo","autor","editora","assunto","numerodepaginas","edicao"};
        int[] to = {R.id.tVId,R.id.tVTitulo,R.id.tVAutor,R.id.tVEditora,R.id.tVAssunto,R.id.tVNumerodepaginas,R.id.tVEdicao};
        Cursor c = db.rawQuery("select * from livros order by titulo", null);
        SimpleCursorAdapter ad = new SimpleCursorAdapter(getBaseContext(),R.layout.modelo,c,from,to,0);
        ListView llv = (ListView) findViewById(R.id.lVDados);
        llv.setAdapter(ad);
        db.close();

        llv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SQLiteCursor c = (SQLiteCursor) parent.getAdapter().getItem(position);

                Intent intent = new Intent(getApplicationContext(), BuscarActivity.class);
                intent.putExtra("codigo",String.valueOf(c.getInt(0)));
                startActivity(intent);
            }
        });


    }

}
