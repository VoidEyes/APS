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
        EditText nomeV = (EditText) findViewById(R.id.login);
        EditText senhaV = (EditText) findViewById(R.id.Pass);
        String nome = nomeV.getText().toString();
        String senha = senhaV.getText().toString();
        Usuario usu = new Usuario(this);
        usu.setSenha(senha);
        usu.setName(nome);
        int busca = usu.Entrar();
        try{
            if (busca == 1) {
                Intent intent = new Intent(this, TelaDeNovoPedido.class);
                intent.putExtra("nome", nome);
                startActivity(intent);
            }
        }catch (Exception e){
            Toast.makeText(this, "Senha ou Usuario errado", Toast.LENGTH_SHORT).show();
        }
    }
    public void Cadastrar(View view){
        Intent cadastrar = new Intent(this, Cadastro.class);
        startActivity(cadastrar);
    }
}
