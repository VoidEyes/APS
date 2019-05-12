package com.dfz.aps;

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

import com.dfz.aps.APSdb;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    String nom, sen;
    APSdb ap= new APSdb(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        APSdb aps = new APSdb(this);
    }
    public void Entrar(View view) {
//        try{
        SQLiteOpenHelper apsdb = new APSdb(this);
        SQLiteDatabase db = apsdb.getReadableDatabase();
        EditText nome = (EditText) findViewById(R.id.login);
        EditText senha = (EditText) findViewById(R.id.Pass);
        String nome1 = nome.getText().toString();
        String senha1 = senha.getText().toString();
        Toast.makeText(this, "Aqui1", Toast.LENGTH_SHORT).show();
        Cursor cursor = db.query("Usuario", new String[]{"Name", "Senha", "User_id"}, "Name = ? ", new String[]{nome1}, null, null, null);
        String nome2 = cursor.getString(0);
        String senha2 = cursor.getString(1);
        Toast.makeText(this, "AQUI2", Toast.LENGTH_SHORT).show();
        if (senha1.equals(senha2) && nome1.equals(nome2)) {
            Intent intent = new Intent(this, TelaDeNovoPedido.class);
//            intent.putExtra("nome", nome1);
//            intent.putExtra("id", cursor.getInt(2));
            startActivity(intent);
//            }
//        }catch(SQLException e){}
        }else Toast.makeText(this,"Erro",Toast.LENGTH_SHORT);
    }
    public void Cadastrar(View view){
        Intent cadastrar = new Intent(this, Cadastro.class);
        startActivity(cadastrar);
    }
}
