package com.dfz.aps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.dfz.aps.APSdb;

public class MainActivity extends AppCompatActivity {

    String nom, sen;
    APSdb ap= new APSdb(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Entrar(View view){
        APSdb aps= new APSdb(this);
        Usuario usuario = new Usuario();
        Intent entrar = new Intent(this, TelaDeNovoPedido.class);
        EditText nome =(EditText)findViewById(R.id.login);
        EditText senha =(EditText)findViewById(R.id.Pass);
        nom = nome.getText().toString();
        sen = senha.getText().toString();
        usuario.setName(nom);
        usuario.setSenha(sen);
        int entra = aps.Entrar(usuario);
        entrar.putExtra("Nome",nom);
        entrar.putExtra("Id",entra);
        if(entra != 0){//page89
            startActivity(entrar);
        }else{
            Toast.makeText(this, "Algum dado esta errado.", Toast.LENGTH_SHORT).show();
        }



    }
    public void Cadastrar(View view){
        Intent cadastrar = new Intent(this, Cadastro.class);
        startActivity(cadastrar);
    }
}
