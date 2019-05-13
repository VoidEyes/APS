package com.dfz.aps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;

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
        String s1q2 ="CREATE TABLE Pedido (id INTEGER PRIMARY KEY AUTOINCREMENT,Local TEXT, Custo REAL, Us_Name);";

        //Tabela Iten
        String sql3 ="CREATE TABLE Iten (id INTEGER PRIMARY KEY AUTOINCREMENT, uantidade INTEGER, Valor REAL, Name TEXT, Ped_id INTEGER);";

        db.execSQL(sql1);
        db.execSQL(s1q2);
        db.execSQL(sql3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int antigo, int novo){
        String sql1 = "DROP TABLE IF EXISTS Usuario";
        String sql2= "DROP TABLE IF EXISTS Pedido";
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
    public int BNU(String nome, String senha){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("Usuario", new String[]{"Name","Senha"},"Name =?",new String[]{nome}, null, null, null);
        String nome1 = cursor.getString(0);
        String senha1 = cursor.getString(1);
        if(nome.equals(nome1) && senha.equals(senha1)){
            cursor.close();
            db.close();
            return 1;
        }else {
            cursor.close();
            db.close();
            return 0;
        }
    }

    public int NovoPed(Pedido pedido){
        SQLiteDatabase dd = getWritableDatabase();
        ContentValues ped = new ContentValues();
        ped.put("local",pedido.getLocal());
        ped.put("Custo",pedido.getCusto());
        ped.put("User_id",pedido.getUser_id());
        dd.insert("Pedido", null, ped);
        Cursor ultimoped = dd.query("Pedido", new String[]{"Ped_id"}, null, null, null, null, null);
        ultimoped.moveToLast();
        int Ped_id = ultimoped.getInt(0);
        ultimoped.close();
        dd.close();
        return Ped_id;
    }

    public void NovoIten (Iten iten){
        SQLiteDatabase de = getWritableDatabase();
        ContentValues ite = new ContentValues();
        ite.put("Name", iten.getName());
        ite.put("Valor", iten.getValor());
        ite.put("Quantidade", iten.getQuantidade());
        ite.put("Ped_id", iten.getPed_id());
        de.insert("Iten", null, ite);
        de.close();
    }
}
