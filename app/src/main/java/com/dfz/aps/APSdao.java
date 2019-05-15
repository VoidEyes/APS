package com.dfz.aps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class APSdao extends SQLiteOpenHelper { private static final String db_Name = "db";
    private static final int ver =1;

    public APSdao(Context context){

        super(context, db_Name, null, ver);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tabela Usuario
        String sql1 ="CREATE TABLE Usuario (_id INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT NOT NULL, Senha TEXT NOT NULL);";

        //Tabela Compra
        String s1q2 ="CREATE TABLE Compra (_id INTEGER PRIMARY KEY AUTOINCREMENT,Local TEXT, Data TEXT, Custo REAL, Us_Name NOT NULL);";

        //Tabela Iten
        String sql3 ="CREATE TABLE Iten (_id INTEGER PRIMARY KEY AUTOINCREMENT, Quantidade INTEGER, Valor REAL, Name TEXT, C_id INTEGER NOT NULL);";

        db.execSQL(sql1);
        db.execSQL(s1q2);
        db.execSQL(sql3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int antigo, int novo){
        String sql1 = "DROP TABLE IF EXISTS Usuario";
        String sql2= "DROP TABLE IF EXISTS Compra";
        String sql3= "DROP TABLE IF EXISTS Iten";
        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);
        onCreate(db);
    }
    //Adicionar Usuario
    public int NovoUs(Usuario usuario){
        String nome =usuario.getName();
        String senha =usuario.getSenha();
        int rs = Salvar(nome, senha);
        if(rs !=0){
            return 1;
        }else return 0;
    }

    //Continuacaoo de Adicionar Usuario
    private int Salvar(String nome, String senha){
        try{
        SQLiteDatabase dc = getWritableDatabase();
        ContentValues usu = new ContentValues();
        usu.put("Name", nome);
        usu.put("Senha", senha);
        dc.insert("Usuario", null, usu);
        dc.close();
        return 1;}catch (SQLException e){
            return 0;
        }
    }

    public int reseta(){
        try{
            SQLiteDatabase d =getWritableDatabase();
            onUpgrade(d, ver, 2);
            d.close();
            return 1;
        }catch (SQLException e){
            return 0;
        }
    }

    public int NovaCompra(Pedido pedido){
        try{
        SQLiteDatabase d = getWritableDatabase();
        ContentValues ped = new ContentValues();
        String local = pedido.getLocal();
        String usun = pedido.getNomeus();
        String data= pedido.getData();
        ped.put("Local",local);
        ped.put("Us_Name",usun);
        ped.put("Data",data);
        d.insert("Compra",null,ped);
        Cursor id = d.rawQuery("SELECT _id FROM Compra",null);
        id.moveToLast();
        if (id!=null){
            int i = id.getInt(0);
            id.close();
            d.close();
            return i;
        }else{
            id.close();
            d.close();
            return 0;}
        }catch (SQLException e){
            return 0;
        }
    }

    public int NovoIten (Iten iten){
        try{
            SQLiteDatabase d = getWritableDatabase();
            ContentValues it = new ContentValues();
            String nome = iten.getName();
            int id_ped = iten.getPed_id();
            int quantidade = iten.getQuantidade();
            float valor = iten.getValor();
            it.put("Quantidade", quantidade);
            it.put("valor", valor);
            it.put("Name", nome);
            it.put("C_id",id_ped);
            d.insert("Iten",null,it);
            d.close();
            return 1;
        }catch (SQLException e){
            return 0;
        }
    }
}
