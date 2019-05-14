package com.dfz.aps;

public class Pedido {
    String local, nomeus;
    Float custo;

    public Float getCusto() {
        return custo;
    }

    public void setCusto(Float custo) {
        this.custo = custo;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getNomeus() {
        return nomeus;
    }

    public void setNomeus(String nomeus) {
        this.nomeus = nomeus;
    }
}
