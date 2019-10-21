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
            EditText Esta = (EditText)findViewById(R.id.esta);
            EditText ende =(EditText)findViewById(R.id.ende);
            EditText dt=(EditText)findViewById(R.id.data);
            EditText lati = (EditText)findViewById(R.id.lat);
            EditText longe = (EditText)findViewById(R.id.longi);
            Intent in = getIntent();
            String latt = lati.getText().toString();
            String longg = longe.getText().toString();
            latt.replaceAll(",",".");
            longg.replaceAll(",",".");
            Compra compra = new Compra(this);
            compra.setUs_name(in.getStringExtra("Name"));
            compra.setEstabelecimento(Esta.getText().toString());
            compra.setEndereco(ende.getText().toString());
            compra.setData(dt.getText().toString());
            compra.setLatitude(latt);
            compra.setLongitude(longg);
            int id = compra.CompraNova();
            if(id!=0){
                Esta.setText("");
                ende.setText("");
                dt.setText("");
                lati.setText("");
                longe.setText("");
                Intent intent = new Intent(this,NovoIten.class);
                intent.putExtra("Id",id);
                startActivity(intent);
            }else{
                Esta.setText("");
                ende.setText("");
                dt.setText("");
                lati.setText("");
                longe.setText("");
                Toast.makeText(this, "Erro ao criar compra", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(this, "Erro ao criar compra", Toast.LENGTH_SHORT).show();
        }

    }
    public void vo (View view){
        finish();
    }
}
