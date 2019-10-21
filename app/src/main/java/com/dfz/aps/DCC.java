package com.dfz.aps;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DCC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_dcc);
        Intent gg =getIntent();
        Bundle ge = gg.getExtras();
        int caso = ge.getInt("caso");
        switch(caso) {
            case 1:
                caso1();
                break;
            case 2:
                int caso1=ge.getInt("caso1");
                if(caso1 ==1){
                caso2();
                }else{
                caso3();
                }
                break;
        }
    }
    void caso1(){
        SQLiteOpenHelper db1 = new APSdao(this);
        SQLiteDatabase db = db1.getReadableDatabase();
        Intent gg = getIntent();
        Bundle ge =gg.getExtras();
        String nome = ge.getString("nome");
        int posic = ge.getInt("posic")+1, posi =ge.getInt("posi")+1;
        Cursor compra = db.rawQuery("SELECT _id,Estabelecimento,Endereco,Data,Us_Name FROM Compra WHERE Us_Name = ?", new String[]{nome});
        for(int i=0;i<posic;i++){
            compra.moveToNext();
        }
        int ic = compra.getInt(compra.getColumnIndex("_id"));
        String id = Integer.toString(ic);
        Cursor ite = db.rawQuery("SELECT _id,Quantidade,Valor,Name,C_id FROM Iten WHERE C_id=?", new String[]{id});
        for(int i=0;i<posi;i++){
            ite.moveToNext();
        }
        TextView nom = (TextView)findViewById(R.id.nomei);
        TextView quantu =(TextView)findViewById(R.id.quantu);
        TextView val = (TextView)findViewById(R.id.valor);
        String nam = ite.getString(ite.getColumnIndex("Name"));
        int qua = ite.getInt(ite.getColumnIndex("Quantidade"));
        float va = ite.getFloat(ite.getColumnIndex("Valor"));
        String vt = Float.toString(va), qt = Integer.toString(qua);
        nom.setText(nam);
        quantu.setText("Quantidade:"+qt);
        val.setText("Valor Unitário:"+vt);
        db.close();
    }
    void caso2(){
        SQLiteOpenHelper db1 = new APSdao(this);
        SQLiteDatabase db = db1.getReadableDatabase();
        Intent gg = getIntent();
        Bundle ge =gg.getExtras();
        int posic = ge.getInt("posic")+1, posi =ge.getInt("posi")+1;
        Cursor compra = db.rawQuery("SELECT _id,Estabelecimento,Endereco,Data,Us_Name FROM Compra ORDER BY Estabelecimento",null);
        for(int i=0;i<posic;i++){
            compra.moveToNext();
        }
        int ic = compra.getInt(compra.getColumnIndex("_id"));
        String id = Integer.toString(ic);
        Cursor ite = db.rawQuery("SELECT _id,Quantidade,Valor,Name,C_id FROM Iten WHERE C_id=?", new String[]{id});
        for(int i=0;i<posi;i++){
            ite.moveToNext();
        }
        TextView nom = (TextView)findViewById(R.id.nomei);
        TextView quantu =(TextView)findViewById(R.id.quantu);
        TextView val = (TextView)findViewById(R.id.valor);
        String nam = ite.getString(ite.getColumnIndex("Name"));
        int qua = ite.getInt(ite.getColumnIndex("Quantidade"));
        float va = ite.getFloat(ite.getColumnIndex("Valor"));
        String vt = Float.toString(va), qt = Integer.toString(qua);
        nom.setText(nam);
        quantu.setText("Quantidade:"+qt);
        val.setText("Valor Unitário:"+vt);
        db.close();
    }
    void caso3(){
        SQLiteOpenHelper db1 = new APSdao(this);
        SQLiteDatabase db = db1.getReadableDatabase();
        Intent gg = getIntent();
        Bundle ge =gg.getExtras();
        int posic = ge.getInt("posic")+1, posi =ge.getInt("posi")+1;
        Cursor compra = db.rawQuery("SELECT _id,Estabelecimento,Endereco,Data,Us_Name FROM Compra ORDER BY Data",null);
        for(int i=0;i<posic;i++){
            compra.moveToNext();
        }
        int ic = compra.getInt(compra.getColumnIndex("_id"));
        String id = Integer.toString(ic);
        Cursor ite = db.rawQuery("SELECT _id,Quantidade,Valor,Name,C_id FROM Iten WHERE C_id=?", new String[]{id});
        for(int i=0;i<posi;i++){
            ite.moveToNext();
        }
        TextView nom = (TextView)findViewById(R.id.nomei);
        TextView quantu =(TextView)findViewById(R.id.quantu);
        TextView val = (TextView)findViewById(R.id.valor);
        String nam = ite.getString(ite.getColumnIndex("Name"));
        int qua = ite.getInt(ite.getColumnIndex("Quantidade"));
        float va = ite.getFloat(ite.getColumnIndex("Valor"));
        String vt = Float.toString(va), qt = Integer.toString(qua);
        nom.setText(nam);
        quantu.setText("Quantidade:"+qt);
        val.setText("Valor Unitário:"+vt);
        db.close();
    }
}
