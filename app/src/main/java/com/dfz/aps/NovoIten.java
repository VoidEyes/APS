package com.dfz.aps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NovoIten extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novo_iten);
    }

    public void NI(View view){
        Bundle extras = getIntent().getExtras();
        APSdao ap = new APSdao(this);
        if(extras != null){
            int idp = extras.getInt("Id");
            EditText nome =(EditText)findViewById(R.id.nt);
            EditText quantidade =(EditText)findViewById(R.id.qt);
            EditText custo =(EditText)findViewById(R.id.ct);
            String nm = nome.getText().toString(), quan = quantidade.getText().toString(),cu = custo.getText().toString();
            int qt = Integer.parseInt(quan);
            Float ct = Float.parseFloat(cu);
            Iten iten = new Iten();
            iten.setName(nm);
            iten.setPed_id(idp);
            iten.setValor(ct);
            iten.setQuantidade(qt);
            int i = ap.NovoIten(iten);
            if(i==1){
                ap.close();
                nome.setText("");
                quantidade.setText("");
                custo.setText("");
                Toast.makeText(this, "Iten Adicionado", Toast.LENGTH_SHORT).show();
            }else{
                ap.close();
                nome.setText("");
                quantidade.setText("");
                custo.setText("");
                Toast.makeText(this, "Erro ao Adicionar", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Erro na compra", Toast.LENGTH_SHORT).show();
        }

    }
    public void CL(View view){
        finish();
    }
}
