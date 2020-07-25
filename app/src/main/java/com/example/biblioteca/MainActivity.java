package com.example.biblioteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            SQLiteDatabase db = openOrCreateDatabase("biblioteca.db", Context.MODE_PRIVATE,
                    null);
            db.execSQL("create table if not exists livros(_id INTEGER NOT NULL PRIMARY KEY " +
                    ", titulo varchar(30), autor varchar(40), editora varchar(20), assunto varchar(30), numerodepaginas varchar(30),edicao varchar(15))");
            db.close();
        } catch (Exception ex){
            Toast.makeText(getBaseContext(), "Erro ao criar o bd",Toast.LENGTH_LONG).show();
        }
    }

    public void cadastro(View view){
        Intent intent = new Intent(this, CadastrarActivity.class);
        startActivity(intent);
    }

    public void busca(View view){
        Intent intent = new Intent(this, BuscarActivity.class);
        startActivity(intent);
    }

    public void lista(View v){
        Intent intent = new Intent(this, ListarActivity.class);
        startActivity(intent);
    }

    public void alterar(View view){
        Intent intent = new Intent(this, Alterar2Activity.class);
        startActivity(intent);
    }

    public void excluir(View view){
        Intent intent = new Intent(this, ExcluirActivity.class);
        startActivity(intent);
    }

}
