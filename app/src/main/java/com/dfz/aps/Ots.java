package com.dfz.aps;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Ots extends AppCompatActivity {
    int caso =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_ots);
        SQLiteOpenHelper bs = new APSdao(this);
        SQLiteDatabase bc = bs.getReadableDatabase();
        ListView list =(ListView)findViewById(R.id.listact);
        Cursor busca =bc.rawQuery("SELECT _id,Estabelecimento,Endereco,Data FROM Compra ORDER BY Estabelecimento", null);
        CursorAdapter lista = new SimpleCursorAdapter(this, android.R.layout.simple_expandable_list_item_2, busca, new String[]{"Estabelecimento","Data"}, new int[]{android.R.id.text1,android.R.id.text2},0);
        list.setAdapter(lista);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView < ? > parent, View view, int position, long id){
                dc(position);
            }
        });
    }

    public void Odl(View view){
        caso = 1;
        SQLiteOpenHelper bs = new APSdao(this);
        SQLiteDatabase bc = bs.getReadableDatabase();
        ListView list =(ListView)findViewById(R.id.listact);
        Cursor busca = bc.rawQuery("SELECT _id, Estabelecimento,Endereco,Data FROM Compra ORDER BY Estabelecimento", null);
        CursorAdapter lista = new SimpleCursorAdapter(this, android.R.layout.simple_expandable_list_item_2, busca, new String[]{"Estabelecimento","Data"}, new int[]{android.R.id.text1,android.R.id.text2},0);
        list.setAdapter(lista);
    }

    public void Odt(View view){
        caso =2;
        SQLiteOpenHelper bs = new APSdao(this);
        SQLiteDatabase bc = bs.getReadableDatabase();
        ListView list =(ListView)findViewById(R.id.listact);
        Cursor busca =bc.rawQuery("SELECT _id,Estabelecimento, Endereco,Data FROM Compra ORDER BY Data", null);
        CursorAdapter lista = new SimpleCursorAdapter(this, android.R.layout.simple_expandable_list_item_2, busca, new String[]{"Estabelecimento","Data"}, new int[]{android.R.id.text1,android.R.id.text2},0);
        list.setAdapter(lista);
    }

    private void dc(int posi){
        int caso2 =2;
        Intent nt = new Intent(this,DC.class);
        Bundle posic = new Bundle();
        posic.putInt("posi",posi);
        posic.putInt("caso",caso2);
        posic.putInt("caso1",caso);
        nt.putExtras(posic);
        startActivity(nt);
    }
}
