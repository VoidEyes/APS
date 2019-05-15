package com.dfz.aps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NovaCompra extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nova_compra);
    }
    public void NovoIten(View view){
        try{
            APSdao ap = new APSdao(this);
            EditText lc =(EditText)findViewById(R.id.local);
            EditText dt=(EditText)findViewById(R.id.data);
            Intent in = getIntent();
            String nome = in.getStringExtra("Name");
            String local = lc.getText().toString();
            String data = dt.getText().toString();
            Pedido compra = new Pedido();
            compra.setNomeus(nome);
            compra.setLocal(local);
            compra.setData(data);
            int id = ap.NovaCompra(compra);
            if(id!=0){
                Intent intent = new Intent(this,NovoIten.class);
                intent.putExtra("Id",id);
                startActivity(intent);
            }else{
                lc.setText("");
                dt.setText("");
                Toast.makeText(this, "Erro ao criar compra", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(this, "Erro ao criar compra", Toast.LENGTH_SHORT).show();
        }

    }
}
