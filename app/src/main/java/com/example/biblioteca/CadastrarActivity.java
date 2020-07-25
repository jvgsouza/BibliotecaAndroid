package com.example.biblioteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastrarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
    }

    public void limpar(View v) {
        EditText ettitulo = (EditText) findViewById(R.id.eTTitulo);
        EditText etautor = (EditText) findViewById(R.id.eTAutor);
        EditText eteditora = (EditText) findViewById(R.id.eTEditora);
        EditText etassunto = (EditText) findViewById(R.id.eTAssunto);
        EditText etnum = (EditText) findViewById(R.id.eTNum);
        EditText etedicao = (EditText) findViewById(R.id.eTEdicao);
        ettitulo.setText("");
        etautor.setText("");
        eteditora.setText("");
        etassunto.setText("");
        etnum.setText("");
        etedicao.setText("");
    }

    public void cadastrar(View v) {
        //Busquei os EditText do xml
        EditText ettitulo = (EditText) findViewById(R.id.eTTitulo);
        EditText etautor = (EditText) findViewById(R.id.eTAutor);
        EditText eteditora = (EditText) findViewById(R.id.eTEditora);
        EditText etassunto = (EditText) findViewById(R.id.eTAssunto);
        EditText etnum = (EditText) findViewById(R.id.eTNum);
        EditText etedicao = (EditText) findViewById(R.id.eTEdicao);
        //Verifiquei se o nome foi preenchido
        if (ettitulo.getText().length() > 0) {
            //peguei conteudo do EditText
            String t, a, e, as, num, ed;
            t = ettitulo.getText().toString();
            a = etautor.getText().toString();
            e = eteditora.getText().toString();
            as = etassunto.getText().toString();
            num = etnum.getText().toString();
            ed = etedicao.getText().toString();
            //criei um conponent para armazenas os valores no banco
            ContentValues ctv = new ContentValues();
            //guardei os vslores no ctv
            ctv.put("titulo", t);
            ctv.put("autor", a);
            ctv.put("editora", e);
            ctv.put("assunto", as);
            ctv.put("numerodepaginas", num);
            ctv.put("edicao", ed);
            try {
                SQLiteDatabase db = openOrCreateDatabase("biblioteca.db", Context.MODE_PRIVATE,
                        null);
                db.insert("livros", "_id", ctv);

                db.close();
                limpar(v);
            } catch (Exception ex) {
                Toast.makeText(getBaseContext(), "Erro ao cadastrar", Toast.LENGTH_LONG).show();
            }
        } else {
            ettitulo.setError("Digite o titulo");
            ettitulo.requestFocus();
        }
    }

}
