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
        super(context, db_Name, null, ver);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //Tabela Usuario
        db.execSQL("CREATE TABLE Usuario ("+ "User_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "Name TEXT PRIMARY KEY,"+
                "Senha TEXT);");
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
                "Name TEXT,"+
                "FOREIGN KEY (Ped_id) REFERENCES Pedido(Ped_id));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int antigo, int novo){
    }

    public void NovoUs(Usuario usuario){
        SQLiteDatabase dc = getWritableDatabase();
        ContentValues usu = new ContentValues();
        usu.put("Name",usuario.getName());
        usu.put("Senha",usuario.getSenha());
        dc.insert("Usuario",null, usu);
        dc.close();
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

    public int Entrar (Usuario usuario){
        SQLiteDatabase dr = getWritableDatabase();
        String nome1, nome2, senha1, senha2;
        int User_ide = 0;
        nome1=usuario.getName();
        senha1=usuario.getSenha();
        Cursor cursor = dr.query("Usuario", new String[]{"Name","Senha","User_id"},"Name = ? and Senha = ?", new String[]{nome1, senha1}, null, null, null);
        nome2 = cursor.getString(0);
        senha2 = cursor.getString(1);
        User_ide= cursor.getInt(0);
        if(nome1 == nome2 && senha1==senha2){
            cursor.close();
            dr.close();
            return User_ide;
        }else {
            cursor.close();
            dr.close();
            return 0;
        }
    }

}




