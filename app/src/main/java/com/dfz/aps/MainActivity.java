package com.dfz.aps;

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
            APSdao aps = new APSdao(this);
            EditText nomeV = (EditText)findViewById(R.id.login);
            EditText senhaV = (EditText)findViewById(R.id.Pass);
            int rn;
            String nome = nomeV.getText().toString();
            String senha = senhaV.getText().toString();
            //rn = aps.BNU(nome, senha);
           rn=1;
           aps.close();
            if (rn != 0) {
                Intent intent = new Intent(this, TelaDeNovoPedido.class);
                intent.putExtra("nome", nome);
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
