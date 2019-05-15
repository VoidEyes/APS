package com.dfz.aps;

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
        String nome = intent.getStringExtra("nome");
        TextView NomeShow = (TextView)findViewById(R.id.nomeusu);
        NomeShow.setText("" + nome);
    }

    public void NovaCompra(View view){
        Intent intent = new Intent(this,NovaCompra.class);
        Intent in = getIntent();
        String nome = in.getStringExtra("nome");
        intent.putExtra("Name",nome);
        startActivity(intent);
    }
}