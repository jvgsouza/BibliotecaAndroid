package com.example.biblioteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class BuscarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        Intent it = getIntent();
        EditText c = (EditText) findViewById(R.id.eTId);
        c.setText(it.getStringExtra("codigo"));
        buscar(null);
    }

    public void buscar(View v){
        EditText codigo = (EditText) findViewById(R.id.eTId);
        EditText ettitulo = (EditText) findViewById(R.id.eTTitulo);
        EditText etautor = (EditText) findViewById(R.id.eTAutor);
        EditText eteditora = (EditText) findViewById(R.id.eTEditora);
        EditText etassunto = (EditText) findViewById(R.id.eTAssunto);
        EditText etnum = (EditText) findViewById(R.id.eTNum);
        EditText etedicao = (EditText) findViewById(R.id.eTEdicao);
        String comando;
        if(codigo.getText().length()<=0&&ettitulo.getText().length()<=0){
            codigo.setError("Digite o código ou o titulo do livro");
            codigo.requestFocus();
        }else{
            if(codigo.getText().length()>0){
                comando = "select * from livros where _id = "+codigo.getText().toString()+";";
            }else{
                comando = "select * from livros where titulo like '"+ettitulo.getText().toString()+"%';";
            }
            try {
                SQLiteDatabase db = openOrCreateDatabase("biblioteca.db", Context.MODE_PRIVATE,
                        null);
                Cursor c = db.rawQuery(comando, null);
                if(c.moveToFirst()){
                    codigo.setText(c.getString(0));
                    ettitulo.setText(c.getString(1));
                    etautor.setText(c.getString(2));
                    eteditora.setText(c.getString(3));
                    etassunto.setText(c.getString(4));
                    etnum.setText(c.getString(5));
                    etedicao.setText(c.getString(6));
                }else{
                    Toast.makeText(getBaseContext(),"Não cadastrado",Toast.LENGTH_LONG).show();
                }
                db.close();
            }catch(Exception ex){
                Toast.makeText(getBaseContext(),"Erro ao buscar", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void limpar(View v) {
        EditText ettitulo = (EditText) findViewById(R.id.eTTitulo);
        EditText etautor = (EditText) findViewById(R.id.eTAutor);
        EditText eteditora = (EditText) findViewById(R.id.eTEditora);
        EditText etassunto = (EditText) findViewById(R.id.eTAssunto);
        EditText etnum = (EditText) findViewById(R.id.eTNum);
        EditText etedicao = (EditText) findViewById(R.id.eTEdicao);
        EditText codigo = (EditText) findViewById(R.id.eTId);
        codigo.setText("");
        ettitulo.setText("");
        etautor.setText("");
        eteditora.setText("");
        etassunto.setText("");
        etnum.setText("");
        etedicao.setText("");
        codigo.requestFocus();
    }
}
