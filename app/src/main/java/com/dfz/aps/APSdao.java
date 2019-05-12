package com.dfz.aps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
        String sql1 ="CREATE TABLE Usuario (User_id INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT  NOT NULL, Senha TEXT NOT NULL);";

        //Tabela Pedido
        String s1q2 ="CREATE TABLE Pedido (Ped_id INTEGER PRIMARY KEY AUTOINCREMENT,Local TEXT, Custo REAL, FOREIGN KEY (User_id) REFERENCES Usuario(User_id));";

        //Tabela Iten
        String sql3 ="CREATE TABLE Iten (Iten_id INTEGER PRIMARY KEY AUTOINCREMENT, uantidade INTEGER, Valor REAL, Name TEXT, FOREIGN KEY (Ped_id) REFERENCES Pedido(Ped_id));";

        db.execSQL(sql1);
        db.execSQL(s1q2);
        db.execSQL(sql3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int antigo, int novo){
        String sql1 = "DROP TABLE IF EXISTS Usuarios";
        String sql2= "DROP TABLE IF EXISTS Pedido";
        String sql3= "DROP TABLE IF EXISTS Iten";
        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);
        onCreate(db);
    }
    //Adicionar Usuario
    public void NovoUs(Usuario usuario){
        ContentValues usu = new ContentValues();
        usu.put("Name",usuario.getName());
        usu.put("Senha",usuario.getSenha());
        Salvar(usu);
    }
    //Continuacaoo de Adicionar Usuario
    private void Salvar(ContentValues usu){
        SQLiteDatabase dc = getWritableDatabase();
        //onUpgrade(dc, ver, 2);
        dc.insert("Usuario", null, usu);
        dc.close();
    }
    //Buscar Usuario
    public ContentValues BuscaUsu(ContentValues usuario){
        String nome = usuario.getAsString("Name");
        String senha =usuario.getAsString("Senha");
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("Usuario", new String[]{"Name","Senha","User_id"},"Name =?",new String[]{nome}, null, null, null);
        String nome1 = cursor.getString(0);
        String senha1 = cursor.getString(1);
        int id = cursor.getInt(2);
        ContentValues resultado = new ContentValues();
        resultado.put("Name", nome1);
        resultado.put("Senha",senha1);
        resultado.put("Id", id);
        return(resultado);
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
