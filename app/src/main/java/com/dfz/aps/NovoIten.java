package com.dfz.aps;

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
        EditText nome =(EditText)findViewById(R.id.nt);
        EditText quantidade =(EditText)findViewById(R.id.qt);
        EditText custo =(EditText)findViewById(R.id.ct);
        String quan = quantidade.getText().toString(),cu = custo.getText().toString();
        int qt = Integer.parseInt(quan);
        Float qtt = Float.parseFloat(quan);
        Float valor = Float.parseFloat(cu);
        Double Custo = Double.valueOf(valor*qtt);
        Iten iten = new Iten(this);
        iten.setName(nome.getText().toString());
        iten.setPed_id(extras.getInt("Id"));
        iten.setValor(valor);
        iten.setQuantidade(qt);
        iten.setCusto(Custo);
        int i = iten.ItenNovo();
        if(i==1){
            nome.setText("");
            quantidade.setText("");
            custo.setText("");
            Toast.makeText(this, "Iten Adicionado", Toast.LENGTH_SHORT).show();
        }else{
            nome.setText("");
            quantidade.setText("");
            custo.setText("");
            Toast.makeText(this, "Erro ao Adicionar", Toast.LENGTH_SHORT).show();
        }
    }

    public void CL(View view){
        finish();
    }
}
