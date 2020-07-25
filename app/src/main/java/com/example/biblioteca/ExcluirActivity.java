package com.example.biblioteca;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ExcluirActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excluir);
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
        Button alt = (Button) findViewById(R.id.bTExcluir);
        alt.setEnabled(false);
        codigo.requestFocus();
    }

    public void buscar(View v){
        EditText codigo = (EditText) findViewById(R.id.eTId);
        EditText ettitulo = (EditText) findViewById(R.id.eTTitulo);
        EditText etautor = (EditText) findViewById(R.id.eTAutor);
        EditText eteditora = (EditText) findViewById(R.id.eTEditora);
        EditText etassunto = (EditText) findViewById(R.id.eTAssunto);
        EditText etnum = (EditText) findViewById(R.id.eTNum);
        EditText etedicao = (EditText) findViewById(R.id.eTEdicao);
        Button alt = (Button) findViewById(R.id.bTExcluir);
        String comando;
        if(codigo.getText().length()<=0&&ettitulo.getText().length()<=0){
            codigo.setError("Digite o código ou o titulo do livro");
            codigo.requestFocus();
        }else{
            if(codigo.getText().length()>0){
                comando = "select * from livros where _id = "+codigo.getText().toString()+";";
                alt.setEnabled(true);
            }else{
                comando = "select * from livros where titulo like '"+ettitulo.getText().toString()+"%';";
                alt.setEnabled(true);
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

    public void excluir(View v) {
        final EditText codigo = (EditText) findViewById(R.id.eTId);
        EditText ettitulo = (EditText) findViewById(R.id.eTTitulo);
        EditText etautor = (EditText) findViewById(R.id.eTAutor);
        EditText eteditora = (EditText) findViewById(R.id.eTEditora);
        EditText etassunto = (EditText) findViewById(R.id.eTAssunto);
        EditText etnum = (EditText) findViewById(R.id.eTNum);
        EditText etedicao = (EditText) findViewById(R.id.eTEdicao);
        final ContentValues ctv = new ContentValues();
        ctv.put("_id", codigo.getText().toString());
        ctv.put("titulo", ettitulo.getText().toString());
        ctv.put("autor", etautor.getText().toString());
        ctv.put("editora", eteditora.getText().toString());
        ctv.put("assunto", etassunto.getText().toString());
        ctv.put("numerodepaginas", etnum.getText().toString());
        ctv.put("edicao", etedicao.getText().toString());

        final SQLiteDatabase db = openOrCreateDatabase("biblioteca.db", Context.MODE_PRIVATE,
                null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exclusão");
        builder.setMessage("Realmente deseja excluir o contato?"+ettitulo.getText().toString());
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db.delete("livros","_id=?", new String[] {codigo.getText().toString()});
                db.close();
                limpar(null);
            }
        });
        builder.setNegativeButton("Não",null);
        AlertDialog alerta;
        alerta = builder.create();
        alerta.show();
    }
}
