package com.dfz.aps;

import android.content.ContentValues;
import android.content.Context;

public class Compra extends APSdao {
    int id;
    int pos;
    String Endereco, Estabelecimento, Data, Latitude, Longitude, Us_name;
    Float Custo;

    public Compra(Context context) {
        super(context);
    }


    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }



    public String getEndereco() {
        return Endereco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstabelecimento() {
        return Estabelecimento;
    }

    public void setEstabelecimento(String estabelecimento) {
        Estabelecimento = estabelecimento;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getUs_name() {
        return Us_name;
    }

    public void setUs_name(String us_name) {
        Us_name = us_name;
    }

    public Float getCusto() {
        return Custo;
    }

    public void setCusto(Float custo) {
        Custo = custo;
    }

    public void setEndereco(String endereco) {
        Endereco = endereco;
    }

    public int CompraNova(){
        int i=  NovaCompra(Estabelecimento, Endereco, Us_name, Data, Latitude, Longitude);
        return i;
    }

    public void CompraUs(){
        ContentValues busca = new ContentValues();
        busca =UsuComp(Us_name, pos);
        setCusto(busca.getAsFloat("Custo"));
        setEndereco(busca.getAsString("Endereco"));
        setData(busca.getAsString("Data"));
        setUs_name(busca.getAsString("Us_Name"));
        setEstabelecimento(busca.getAsString("Estabelecimento"));
    }
}
