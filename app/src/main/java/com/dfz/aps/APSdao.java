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
        String sql1 ="CREATE TABLE Usuario ( id INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT NOT NULL, Senha TEXT NOT NULL);";

        //Tabela Pedido
        String s1q2 ="CREATE TABLE Compra (id INTEGER PRIMARY KEY AUTOINCREMENT,Local TEXT, Custo REAL, Us_Name);";

        //Tabela Iten
        String sql3 ="CREATE TABLE Iten (id INTEGER PRIMARY KEY AUTOINCREMENT, Quantidade INTEGER, Valor REAL, Name TEXT, C_id INTEGER);";

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
    //Buscar Nome Usuario
    public Usuario BNU(String nome){
        SQLiteDatabase d = getWritableDatabase();
        Cursor busca = d.rawQuery("SELECT Name, Senha FROM Usuario WHERE Name = ?", new String[]{nome});
        if(busca!=null){
            String nom = busca.getString(busca.getColumnIndex("Name"));
            String sen = busca.getString(busca.getColumnIndex("Senha"));
            Usuario usu = new Usuario();
            usu.setName(nom);
            usu.setSenha(sen);
            busca.close();
            d.close();
            return usu;
        }else{
            Usuario usu = new Usuario();
            busca.close();
            d.close();
            return usu;
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
        Float custo = pedido.getCusto();
        String usun = pedido.getNomeus();
        ped.put("Local",local);
        ped.put("Us_Name",usun);
        ped.put("Custo",custo);
        d.insert("Compra",null,ped);
        d.close();
        return 1;
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
