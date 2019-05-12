package com.dfz.aps;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    String nom, sen;
    Usuario usuario = new Usuario();
    APSdao aps = new APSdao(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Entrar(View view) {
       try {
            EditText nome = (EditText)findViewById(R.id.login);
            EditText senha = (EditText)findViewById(R.id.Pass);
            String nome1, senha1;
            int rn;
            nome1 = nome.getText().toString();
            senha1 = senha.getText().toString();
            usuario.setName(nome1);
            usuario.setSenha(senha1);
            rn = aps.BNU(usuario);
            aps.close();
            Toast.makeText(this, rn , Toast.LENGTH_LONG).show();
            if (rn != 0) {
                Intent intent = new Intent(this, TelaDeNovoPedido.class);
                intent.putExtra("nome", nome1);
                intent.putExtra("id", rn);
                startActivity(intent);
            } else Toast.makeText(this, "Usuario/Senha incorretos. =C", Toast.LENGTH_SHORT);
       }catch (Exception e) {
           Toast.makeText(this, "Erro inesperado =c", Toast.LENGTH_LONG).show();
        }
    }
    public void Cadastrar(View view){
        Intent cadastrar = new Intent(this, Cadastro.class);
        startActivity(cadastrar);
    }
}
