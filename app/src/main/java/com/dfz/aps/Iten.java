package com.dfz.aps;

import android.content.Context;

public class Iten extends APSdao{
    String name;
    int quantidade, Ped_id;
    Float valor;
    Double custo;

    public Iten(Context context) {
        super(context);
    }

    public Double getCusto() {
        return custo;
    }

    public void setCusto(Double custo) {
        this.custo = custo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public int getPed_id() {
        return Ped_id;
    }

    public void setPed_id(int ped_id) {
        Ped_id = ped_id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int ItenNovo(){
        int i = NovoIten(name, quantidade, Ped_id, valor, custo);
        return i;
    }
}
