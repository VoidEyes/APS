package com.dfz.aps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
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
        if(aps.Entrar(usuario)==1){//page89
            startActivity(entrar);
        }else{
            
        }



    }
    public void Cadastrar(View view){
        Intent cadastrar = new Intent(this, Cadastro.class);
        startActivity(cadastrar);
    }
}
