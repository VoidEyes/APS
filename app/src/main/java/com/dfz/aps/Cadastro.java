package com.dfz.aps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Cadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
         }

    public void Salvar(View view){
        APSdb aps = new APSdb(this);
        EditText name =(EditText)findViewById(R.id.Lo);
        EditText senha = (EditText)findViewById(R.id.Passo);
        Usuario usuario = new Usuario();
        String na, sen;
        na=name.getText().toString();
        sen=senha.getText().toString();
        usuario.setName(na);
        usuario.setSenha(sen);
        aps.NovoUs(usuario);
    }
}