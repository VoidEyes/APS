package com.dfz.aps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Cadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }

    public void Salvar(View view){
        try{
        Usuario usuario = new Usuario(this);
        String na, sen;

        EditText name =(EditText)findViewById(R.id.Lo);
        EditText senha = (EditText)findViewById(R.id.Passo);

        na=name.getText().toString();
        sen=senha.getText().toString();

        usuario.setName(na);//Define nome do usuario
        usuario.setSenha(sen);//Define senha do usuario

        int rs = usuario.Adicionar();//Faz a inserção do usuário no Banco de Dados

        name.setText("");
        senha.setText("");

        if (rs == 1){
            Toast.makeText(this, "Usuario Cadastro =D", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Usuario ja Cadastrado =C", Toast.LENGTH_SHORT).show();
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
