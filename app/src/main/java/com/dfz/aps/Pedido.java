package com.dfz.aps;

public class Pedido {
    String estabelecimento, endenreco, data;
    String nomeus;
    String lat,longi;

    public String getEndenreco() {
        return endenreco;
    }

    public void setEndenreco(String endenreco) {
        this.endenreco = endenreco;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public String getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(String estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNomeus() {
        return nomeus;
    }

    public void setNomeus(String nomeus) {
        this.nomeus = nomeus;
    }

}
