package com.dfz.aps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class Cadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }

    public void Salvar(View view){
        try{
        APSdao aps = new APSdao(this);
        Usuario usuario = new Usuario();
        String na, sen;

        EditText name =(EditText)findViewById(R.id.Lo);
        EditText senha = (EditText)findViewById(R.id.Passo);

        na=name.getText().toString();
        sen=senha.getText().toString();

        usuario.setName(na);
        usuario.setSenha(sen);
        aps.NovoUs(usuario);
        int rs = aps.NovoUs(usuario);
        aps.close();
        name.setText("");
        senha.setText("");
        if (rs == 1){
            Toast.makeText(this, "Usuario Cadastro =D", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Erro no Cadastro =C", Toast.LENGTH_SHORT).show();
        }
        }catch (Exception e){
            Toast.makeText(this, "Deu ruim", Toast.LENGTH_SHORT).show();
        }
    }
    public void Resetar(View view){
        try {
            APSdao d = new APSdao(this);
            int i =d.reseta();
            d.close();
            if(i==1){
                Toast.makeText(this,"Resetado", Toast.LENGTH_SHORT);
            }else{
                Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(this,"Erro", Toast.LENGTH_SHORT);
        }

    }
}
