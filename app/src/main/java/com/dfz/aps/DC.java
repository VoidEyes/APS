package com.dfz.aps;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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
        ListView lv = (ListView) findViewById(R.id.li);
        switch(caso) {
            case 1:
                Caso1();
                break;
            case 2:
                if(pos.getInt("caso1")==1){
                    Caso2();
                }else{
                    Caso3();

                }
                break;
        }
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView < ? > parent, View view, int position, long id){
                dc(position);
            }
        });
    }

    private void Caso1(){
        Bundle pos = getIntent().getExtras();
        SQLiteOpenHelper b = new APSdao(this);
        SQLiteDatabase bc = b.getReadableDatabase();
        TextView esta = (TextView) findViewById(R.id.Esta1);
        TextView dat = (TextView) findViewById(R.id.Dat);
        TextView end = (TextView) findViewById(R.id.Ende);
        TextView cut = (TextView) findViewById(R.id.custocc);
        ListView lv = (ListView) findViewById(R.id.li);
        int i = pos.getInt("posi")+1;
        Compra compra = new Compra(this);
        compra.setUs_name(pos.getString("nome"));
        compra.setPos(i);
        compra.CompraUs();
        esta.setText("Estabelecimento- " + compra.getEstabelecimento());
        dat.setText("Data- " + compra.getData());
        end.setText("Endereco- " + compra.getEndereco());
        cut.setText("Custo-" + compra.getCusto());
        String cid = Integer.toString(compra.getId());
        Cursor iti = bc.rawQuery("SELECT _id, Quantidade, Valor, Name FROM Iten WHERE C_id =?", new String[]{cid});
        CursorAdapter itim = new SimpleCursorAdapter(this, android.R.layout.simple_expandable_list_item_2, iti, new String[]{"Name", "Quantidade"}, new int[]{android.R.id.text1, android.R.id.text2}, 0);
        lv.setAdapter(itim);
        bc.close();
    }

    private void Caso2(){
        Bundle pos = getIntent().getExtras();
        String is;
        int cid ,i;
        SQLiteOpenHelper b = new APSdao(this);
        SQLiteDatabase bc = b.getReadableDatabase();
        TextView esta = (TextView) findViewById(R.id.Esta1);
        TextView dat = (TextView) findViewById(R.id.Dat);
        TextView nom = (TextView) findViewById(R.id.UsName);
        TextView end = (TextView) findViewById(R.id.Ende);
        TextView cut = (TextView) findViewById(R.id.custocc);
        ListView lv = (ListView) findViewById(R.id.li);
        String nome = pos.getString("nome");
        i = pos.getInt("posi")+1;
        Cursor busc = bc.rawQuery("SELECT _id,Estabelecimento,Endereco,Data,Us_Name, Custo FROM Compra ORDER BY Estabelecimento",null);
        for(int cc=0; cc<i; cc++){
            busc.moveToNext();
        }
        esta.setText("Estabelecimento- " + busc.getString(busc.getColumnIndex("Estabelecimento")));
        dat.setText("Data- " + busc.getString(busc.getColumnIndex("Data")));
        nom.setText("Consumidor- "+ busc.getString(busc.getColumnIndex("Us_Name")));
        end.setText("Endereco- "+busc.getString(busc.getColumnIndex("Endereco")));
        cut.setText("Custo-"+busc.getFloat(busc.getColumnIndex("Custo")));
        cid = busc.getInt(busc.getColumnIndex("_id"));
        is = Integer.toString(cid);
        Cursor ita = bc.rawQuery("SELECT _id, Quantidade, Valor, Name FROM Iten WHERE C_id =?", new String[]{is});
        CursorAdapter itim = new SimpleCursorAdapter(this, android.R.layout.simple_expandable_list_item_2, ita, new String[]{"Name", "Quantidade"}, new int[]{android.R.id.text1, android.R.id.text2}, 0);
        lv.setAdapter(itim);
        bc.close();
    }

    private void Caso3(){
        Bundle pos = getIntent().getExtras();
        String is;
        int caso =pos.getInt("caso"),cid ,i;
        SQLiteOpenHelper b = new APSdao(this);
        SQLiteDatabase bc = b.getReadableDatabase();
        TextView esta = (TextView) findViewById(R.id.Esta1);
        TextView dat = (TextView) findViewById(R.id.Dat);
        TextView nom = (TextView) findViewById(R.id.UsName);
        TextView end = (TextView) findViewById(R.id.Ende);
        TextView cut = (TextView) findViewById(R.id.custocc);
        ListView lv = (ListView) findViewById(R.id.li);
        String nome = pos.getString("nome");
        i = pos.getInt("posi")+1;
        i = pos.getInt("posi")+1;
        Cursor busc = bc.rawQuery("SELECT _id,Estabelecimento,Endereco,Data,Us_Name,Custo FROM Compra ORDER BY Data",null);
        for(int cc=0; cc<i; cc++){
            busc.moveToNext();
        }
        esta.setText("Estabelecimento " + busc.getString(busc.getColumnIndex("Estabelecimento")));
        dat.setText("Data- " + busc.getString(busc.getColumnIndex("Data")));
        nom.setText("Consumidor- "+ busc.getString(busc.getColumnIndex("Us_Name")));
        end.setText("Endereco- "+busc.getString(busc.getColumnIndex("Endereco")));
        cut.setText("Custo-"+busc.getFloat(busc.getColumnIndex("Custo")));
        cid = busc.getInt(busc.getColumnIndex("_id"));
        is = Integer.toString(cid);
        Cursor ita = bc.rawQuery("SELECT _id, Quantidade, Valor, Name FROM Iten WHERE C_id =?", new String[]{is});
        CursorAdapter itim = new SimpleCursorAdapter(this, android.R.layout.simple_expandable_list_item_2, ita, new String[]{"Name", "Quantidade"}, new int[]{android.R.id.text1, android.R.id.text2}, 0);
        lv.setAdapter(itim);
        bc.close();
    }

    void dc (int posi){
        Intent intent = new Intent(this,DCC.class);
        Intent dd = getIntent();
        Bundle de = dd.getExtras();
        int caso = de.getInt("caso");
        int posi0 = de.getInt("posi");
        if (caso==2){
            int caso1= de.getInt("caso1");
            intent.putExtra("caso1",caso1);
        }else{
            String nome = de.getString("nome");
            intent.putExtra("nome",nome);
        }
        intent.putExtra("posi", posi);
        intent.putExtra("caso", caso);
        intent.putExtra("posic", posi0);
        startActivity(intent);

    }
}
