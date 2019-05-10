package com.dfz.aps;

public class Iten {
    String name;
    int quantidade, Ped_id;
    Float valor;

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
}
