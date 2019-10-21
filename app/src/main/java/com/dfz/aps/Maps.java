package com.dfz.aps;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Maps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {
            mMap = googleMap;
            String lat, late, longe, longi;
            Float latt, longg;
            SQLiteOpenHelper d = new APSdao(this);
            SQLiteDatabase bm = d.getReadableDatabase();
            Cursor bcm = bm.rawQuery("SELECT _id, Estabelecimento, Endereco, Latitude, Longitude FROM Compra", null);
            bcm.moveToLast();
            int i = bcm.getInt(bcm.getColumnIndex("_id"));
            bcm.moveToFirst();
            lat = bcm.getString(bcm.getColumnIndex("Latitude"));
            longe = bcm.getString(bcm.getColumnIndex("Longitude"));
            latt = Float.parseFloat(lat);
            longg = Float.parseFloat(longe);
            LatLng refe = new LatLng(latt, longg);
            for (int c = 0; c < i; c++) {
                MarkerOptions opts = new MarkerOptions();
                late = bcm.getString(bcm.getColumnIndex("Latitude"));
                longi = bcm.getString(bcm.getColumnIndex("Longitude"));
                latt = Float.parseFloat(late);
                longg = Float.parseFloat(longi);
                opts.position(new LatLng(latt, longg));
                opts.title(bcm.getString(bcm.getColumnIndex("Estabelecimento")));
                opts.snippet(bcm.getString(bcm.getColumnIndex("Endereco")));
                mMap.addMarker(opts);
                bcm.moveToNext();
            }
            mMap.moveCamera(CameraUpdateFactory.newLatLng(refe));
        }catch (Exception e){
            finish();
        }
    }
}
