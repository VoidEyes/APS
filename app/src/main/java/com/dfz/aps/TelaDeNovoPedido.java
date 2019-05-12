package com.dfz.aps;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

public class TelaDeNovoPedido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_novo_pedido);
        Intent intent = getIntent();
        String nome = intent.getStringExtra("Nome");
        int user_id = intent.getIntExtra("id",0);
        TextView NomeShow = (TextView)findViewById(R.id.nomeusu);
        TextView IdUsu = (TextView)findViewById(R.id.idusu);
        NomeShow.setText(nome);
        IdUsu.setText(user_id);
    }

    public void NovoPedido(View view){

    }
}
