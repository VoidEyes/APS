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
        String sql1 ="CREATE TABLE Usuario (_id INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT UNIQUE, Senha TEXT NOT NULL);";

        //Tabela Compra
        String s1q2 ="CREATE TABLE Compra (_id INTEGER PRIMARY KEY AUTOINCREMENT,Endereco TEXT,Estabelecimento TEXT, Data TEXT, Custo REAL,Latitude TEXT,Longitude TEXT, Us_Name NOT NULL);";

        //Tabela Iten
        String sql3 ="CREATE TABLE Iten (_id INTEGER PRIMARY KEY AUTOINCREMENT, Quantidade INTEGER, Valor REAL, Name TEXT,Custo REAL, C_id INTEGER NOT NULL);";

        db.execSQL(sql1);
        db.execSQL(s1q2);
        db.execSQL(sql3);
        ContentValues usu = new ContentValues();
        usu.put("Name", "Deus");
        usu.put("Senha","1");
        db.insert("Usuario",null,usu);
        ContentValues comp = new ContentValues();
        comp.put("Estabelecimento","Prezunic");
        comp.put("Endereco","R. Dias da Cruz, 579");
        comp.put("Data","20/05/2019");
        comp.put("Custo",20);
        comp.put("Latitude","-22.905381");
        comp.put("Longitude","-43.291154");
        comp.put("Us_Name","Deus");
        db.insert("Compra",null,comp);
        ContentValues ine = new ContentValues();
        ine.put("Quantidade",4);
        ine.put("Valor",5);
        ine.put("Name","Matte Leao");
        ine.put("Custo",20);
        ine.put("C_id",1);
        db.insert("Iten",null,ine);
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
    protected int NovoUs(String nome, String senha){
        try{
            SQLiteDatabase dc = getWritableDatabase();
            ContentValues usu = new ContentValues();
            usu.put("Name", nome);
            usu.put("Senha",senha);
            dc.insert("Usuario",null, usu);
            dc.close();
            return 1;
        }catch (SQLException e){
            return 0;
        }
    }

    //Metodo para logar no sistema
    protected int Log(String nome, String senha){
         try {
             SQLiteDatabase dc = getWritableDatabase();
             Cursor busca = dc.rawQuery("SELECT _id, Name, Senha FROM Usuario WHERE Name =?", new String[]{nome});
             busca.moveToFirst();
             if (senha.equals(busca.getString(busca.getColumnIndex("Senha")))) {
                 return 1;
             } else {
                 return 0;
             }
         }catch (SQLException e){
             return 0;
         }
    }

    public int NovaCompra(Pedido pedido){
        try{
        SQLiteDatabase d = getWritableDatabase();
        ContentValues ped = new ContentValues();
        String Estabelecimento = pedido.getEstabelecimento();
        String Endereco = pedido.getEndenreco();
        String usun = pedido.getNomeus();
        String data= pedido.getData();
        String lat = pedido.getLat();
        String longi = pedido.getLongi();
        ped.put("Estabelecimento",Estabelecimento);
        ped.put("Us_Name",usun);
        ped.put("Data",data);
        ped.put("Latitude",lat);
        ped.put("Longitude",longi);
        ped.put("Endereco",Endereco);
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

    //Reseta Banco de dados ~Fins de testes~
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

    public void Att(double ct, int id){;
        SQLiteDatabase d = getWritableDatabase();
        ContentValues at = new ContentValues();
        String idp = Integer.toString(id);
        at.put("Custo",ct);
        d.update("Compra",at,"_id=?",new String[]{idp});
    }
}
