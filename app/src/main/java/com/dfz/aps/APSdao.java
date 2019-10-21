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

    // Metodo que Cria uma nova "Compra"
    protected int NovaCompra(String estabelecimento, String endereco, String Usu_Nome, String data, String latitude, String longitude){
        try{
            SQLiteDatabase d = getWritableDatabase();
            ContentValues ped = new ContentValues();
            ped.put("Estabelecimento",estabelecimento);
            ped.put("Us_Name",Usu_Nome);
            ped.put("Data",data);
            ped.put("Latitude",latitude);
            ped.put("Longitude",longitude);
            ped.put("Endereco",endereco);
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

    //Buscar Compras De um determinado Usuario
    protected ContentValues UsuComp(String nome, int pos){
        SQLiteDatabase d = getReadableDatabase();
        Cursor busca = d.rawQuery("SELECT _id,Estabelecimento,Endereco,Data,Us_Name,Custo FROM Compra WHERE Us_Name = ?", new String[]{nome});
        busca.moveToPosition(pos);
        ContentValues compusu = new ContentValues();
        String estab = busca.getString(1);
        compusu.put("Estabelecimento",estab);
        compusu.put("Endereoc",busca.getString(busca.getColumnIndex("Endereco")));
        compusu.put("Data",busca.getString(busca.getColumnIndex("Data")));
        compusu.put("Us_Name",busca.getString(busca.getColumnIndex("Us_Name")));
        compusu.put("Custo",busca.getFloat(busca.getColumnIndex("Custo")));
        return compusu;
    }

    // Metodo que Adiciona Itens relacionados a uma determinada Compra
    protected int NovoIten (String nome, int quantidade, int Ped_id, Float valor, Double custo){
//        try{
        SQLiteDatabase d = getWritableDatabase();
        ContentValues it = new ContentValues();
        it.put("Quantidade", quantidade);
        it.put("valor", valor);
        it.put("Name", nome);
        it.put("Custo",custo);
        it.put("C_id",Ped_id);
        d.insert("Iten",null,it);
        Att(custo,Ped_id);
        d.close();
        return 1;
//        }catch (SQLException e){
//            return 0;
//        }
    }

    //Atualiza o campo Custo da tabela Compra
    private void Att(double ct, int id){
        SQLiteDatabase d = getWritableDatabase();
        ContentValues at = new ContentValues();
        String cid = Integer.toString(id);
        Cursor busc = d.rawQuery("SELECT Custo FROM Compra WHERE _id=?", new String[]{cid});
        busc.moveToFirst();
        Double custo= ct + busc.getFloat(busc.getColumnIndex("Custo"));
        at.put("Custo",custo);
        d.update("Compra",at,"_id=?",new String[]{cid});
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
}
