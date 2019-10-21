package com.dfz.aps;

import android.content.Context;

public class Usuario extends APSdao {
    String Name, Senha;

    public Usuario(Context context) {
        super(context);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }
    public int Entrar(){
        int i = Log(Name, Senha);
        return i;
    }
    public int Adicionar(){
        int i = NovoUs(Name, Senha);
        return i;
    }
}
