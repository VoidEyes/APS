package com.dfz.aps;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;


public class DC extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_dc);
        Intent dd = getIntent();
        Bundle pos = dd.getExtras();
        String is;
        int caso =pos.getInt("caso"),cid ,i;
        SQLiteOpenHelper b = new APSdao(this);
        SQLiteDatabase bc = b.getReadableDatabase();
        CursorAdapter itim;
        TextView loc = (TextView) findViewById(R.id.Loc);
        TextView dat = (TextView) findViewById(R.id.Dat);
        TextView nom = (TextView) findViewById(R.id.UsName);
        ListView lv = (ListView) findViewById(R.id.li);
        switch(caso) {
            case 1:
                String nome = pos.getString("nome");
                i = pos.getInt("posi")+1;
                Cursor busca = bc.rawQuery("SELECT _id,Local,Data,Us_Name FROM Compra WHERE Us_Name = ?", new String[]{nome});
                for (int ii = 0; ii < i; ii++) {
                    busca.moveToNext();
                }
                loc.setText("Local-" + busca.getString(busca.getColumnIndex("Local")));
                dat.setText("Data-" + busca.getString(busca.getColumnIndex("Data")));
                cid = busca.getInt(busca.getColumnIndex("_id"));
                is = Integer.toString(cid);
                Cursor iti = bc.rawQuery("SELECT _id, Quantidade, Valor, Name FROM Iten WHERE C_id =?", new String[]{is});
                itim = new SimpleCursorAdapter(this, android.R.layout.simple_expandable_list_item_2, iti, new String[]{"Name", "Quantidade", "Valor"}, new int[]{android.R.id.text1, android.R.id.text2}, 0);
                lv.setAdapter(itim);
                break;
            case 2:
                if(pos.getInt("caso1")==1){
                    i = pos.getInt("posi")+1;
                    Cursor busc = bc.rawQuery("SELECT _id,Local,Data,Us_Name FROM Compra ORDER BY Local",null);
                    for(int cc=0; cc<i; cc++){
                        busc.moveToNext();
                    }
                    loc.setText("Local-" + busc.getString(busc.getColumnIndex("Local")));
                    dat.setText("Data-" + busc.getString(busc.getColumnIndex("Data")));
                    nom.setText("Consumidor-"+ busc.getString(busc.getColumnIndex("Us_Name")));
                    cid = busc.getInt(busc.getColumnIndex("_id"));
                    is = Integer.toString(cid);
                    Cursor ita = bc.rawQuery("SELECT _id, Quantidade, Valor, Name FROM Iten WHERE C_id =?", new String[]{is});
                    itim = new SimpleCursorAdapter(this, android.R.layout.simple_expandable_list_item_2, ita, new String[]{"Name", "Quantidade", "Valor"}, new int[]{android.R.id.text1, android.R.id.text2}, 0);
                    lv.setAdapter(itim);
                }else{
                    i = pos.getInt("posi")+1;
                    Cursor busc = bc.rawQuery("SELECT _id,Local,Data,Us_Name FROM Compra ORDER BY Data",null);
                    for(int cc=0; cc<i; cc++){
                        busc.moveToNext();
                    }
                    loc.setText("Local-" + busc.getString(busc.getColumnIndex("Local")));
                    dat.setText("Data-" + busc.getString(busc.getColumnIndex("Data")));
                    nom.setText("Consumidor-"+ busc.getString(busc.getColumnIndex("Us_Name")));
                    cid = busc.getInt(busc.getColumnIndex("_id"));
                    is = Integer.toString(cid);
                    Cursor ita = bc.rawQuery("SELECT _id, Quantidade, Valor, Name FROM Iten WHERE C_id =?", new String[]{is});
                    itim = new SimpleCursorAdapter(this, android.R.layout.simple_expandable_list_item_2, ita, new String[]{"Name", "Quantidade", "Valor"}, new int[]{android.R.id.text1, android.R.id.text2}, 0);
                    lv.setAdapter(itim);
                }
                break;
        }
        bc.close();
    }
}
