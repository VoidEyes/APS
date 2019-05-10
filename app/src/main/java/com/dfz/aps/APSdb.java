package com.dfz.aps;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class APSdb extends SQLiteOpenHelper{

    private static final String db_Name = "db";
    private static final int ver =1;

    APSdb(Context context){
        super(context db_Name, null, ver);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //Tabela Usuario
        db.execSQL("CREATE TABLE Usuario ("+ "User_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "name TEXT PRIMARY KEY,"+
                "senha TEXT);");
        //Tabela Pedido
        db.execSQL("CREATE TABLE Pedido("+ "Ped_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "Local TEXT,"+
                "Custo REAL,"+
                //"Data NUMERIC,"+
                "FOREIGN KEY (User_id) REFERENCES Usuario(User_id));");
        //Tabela Iten
        db.execSQL("CREATE TABLE Iten ("+"Iten_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "Quantidade INTEGER,"+
                "Valor REAL,"+
                "name TEXT,"+
                "FOREIGN KEY (Ped_id) REFERENCES Pedido(Ped_id));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int antigo, int novo){
    }


    public void NovoUs(Usuario usuario){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues usu = new ContentValues();
        usu.put("name",usuario.getName());
        usu.put("senha",usuario.getSenha());
        db.insert("Usuario",null, usu);
        db.close();
    }

    public int NovoPed(Pedido pedido){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues ped = new ContentValues();
        /*ped.put("Local", local);
        ped.put("Custo", Custo);
        ped.put("User_id", user_id);*/
        db.insert("Pedido", null, ped);
        Cursor ultimoped = db.query("Pedido", new String[]{"Ped_id"), null, null, null, null, null);
        ultimoped.moveToLast();
        int Ped_id = ultimoped.getInt(0);
        ultimoped.close();
        db.close();
        return Ped_id;
    }

    public void NovoIten Iten iten){
        SQLiteDatabase dd = getWritableDatabase();
        ContentValues ite = new ContentValues();
        /*iten.put("Quantidade", Quant);
        iten.put("Valor", total);
        iten.put("Ped_id", Ped_i);*/
        dd.insert("Iten", null, ite);
        dd.close();
    }

}
    //Bug que so funciona aqui
    public int Entrar (Usuario usuario){
        SQLiteDatabase dc = getWritableDatabase();
        String nome1, nome2, senha1, senha2;
        nome1=usuario.getName();
        senha1=usuario.getSenha();
        Cursor cursor = dc.query("Usuario", new String[]{"name","senha"},"name = ? and senha = ?", new String[]{nome1, senha1}, null, null, null);
        nome2 = cursor.getString(0);
        senha2 = cursor.getString(1);
        if(nome1 == nome2 && senha1==senha2){
            dc.close();
            return 1;
        }else {
            dc.close();
            return 0;
        }
    }


