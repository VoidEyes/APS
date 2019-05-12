package com.dfz.aps;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteException;

import com.dfz.aps.APSdao;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    String nom, sen;
    APSdao ap= new APSdao(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Entrar(View view) {
        try {
            APSdao aps = new APSdao(this);
            EditText nome = (EditText) findViewById(R.id.login);
            EditText senha = (EditText) findViewById(R.id.Pass);
            String nome1 = nome.getText().toString();
            String senha1 = senha.getText().toString();
            ContentValues usuario = new ContentValues();
            usuario.put("Name", nome1);
            usuario.put("Senha", senha1);
            Toast.makeText(this, "Estou aqui", Toast.LENGTH_SHORT).show();
            usuario = aps.BuscaUsu(usuario);
            String nome2 = usuario.getAsString("Name");
            String senha2 = usuario.getAsString("Senha");
            int id = usuario.getAsInteger("Id");
            aps.close();
            if (senha1.equals(senha2) && nome1.equals(nome2)) {
                Intent intent = new Intent(this, TelaDeNovoPedido.class);
                intent.putExtra("nome", nome1);
                intent.putExtra("id", id);
                startActivity(intent);
            } else Toast.makeText(this, "Usuario/Senha incorretos. =C", Toast.LENGTH_SHORT);
        } catch (Exception e) {
            Toast.makeText(this, "Erro inesperado =c", Toast.LENGTH_LONG).show();
        }
    }
    public void Cadastrar(View view){
        Intent cadastrar = new Intent(this, Cadastro.class);
        startActivity(cadastrar);
    }
}
