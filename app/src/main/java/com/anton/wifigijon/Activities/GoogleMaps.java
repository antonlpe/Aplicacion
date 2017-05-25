package com.anton.wifigijon.Activities;

/**
 * Created by Antón on 13/05/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.anton.wifigijon.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleMaps extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private boolean mPermissionDenied = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /*
    MÉTODO SOBREESCRITO PARA QUE MUESTRE LA UBICACIÓN ELEGIDA
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //SE CREAN LAS VARIABLES Y SE ALMACENAN LOS DATOS NECESARIOS A TRAVÉS DE UN BUNDLE

        float lat = 0, lon = 0;
        String nombre = "";
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null)
        {
            lat = bundle.getFloat("latitud");
            lon = bundle.getFloat("longitud");
            nombre =(String) bundle.get("nombre");
        }

        //SE ASIGNA LA UBICACIÓN AL MAPA Y SE CONFIGURA LA CÁMARA Y LA ETIQUETA
        LatLng ubicacion = new LatLng(lat, lon);
        Marker prueba = mMap.addMarker(new MarkerOptions().position(ubicacion).title(nombre));
        prueba.showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacion));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(17));
    }
}