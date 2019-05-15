package com.dfz.aps;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Entrar(View view) {
       try {
            SQLiteOpenHelper inu = new APSdao(this);
            SQLiteDatabase d = inu.getReadableDatabase();
            EditText nomeV = (EditText)findViewById(R.id.login);
            EditText senhaV = (EditText)findViewById(R.id.Pass);
            String nome = nomeV.getText().toString();
            String senha = senhaV.getText().toString();
            Cursor busca = d.rawQuery("SELECT Name, Senha FROM Usuario WHERE Name = ?", new String[]{nome});
            busca.moveToFirst();
            if(busca!=null){
                if(nome.equals(busca.getString(busca.getColumnIndex("Name")))&&senha.equals(busca.getString(busca.getColumnIndex("Senha")))){
                    Intent intent = new Intent(this, TelaDeNovoPedido.class);
                    intent.putExtra("nome", nome);
                    startActivity(intent);}else{
                    Toast.makeText(this, "Senha ou Login Errado", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Senha ou Login Errado", Toast.LENGTH_SHORT).show();
            }
       }catch (Exception e) {
            Toast.makeText(this, "Erro inesperado =c", Toast.LENGTH_LONG).show();
       }
    }

    public void Cadastrar(View view){
        Intent cadastrar = new Intent(this, Cadastro.class);
        startActivity(cadastrar);
    }
}
