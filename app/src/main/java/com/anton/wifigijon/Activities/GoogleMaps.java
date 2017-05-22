package com.anton.wifigijon.Activities;

/**
 * Created by Antón on 13/05/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.anton.wifigijon.Fragments.FragmentGoogleMaps;
import com.anton.wifigijon.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleMaps extends AppCompatActivity{

    private String nombre;
    private float latitud;
    private float longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);

        // Existe el contenedor del fragmento?
        if (findViewById(R.id.fragment_container_map) != null) {

            // Si estamos restaurando desde un estado previo no hacemos nada
            if (savedInstanceState != null) {
                return;
            }

            // Crear el fragmento pasándole el parámetro
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            nombre = bundle.getString("nombre");
            latitud = bundle.getFloat("latitud");
            longitud = bundle.getFloat("longitud");
            FragmentGoogleMaps fragment =
                    FragmentGoogleMaps.newInstance(nombre, latitud, longitud);
            // Añadir el fragmento al contenedor 'fragment_container'
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_map, fragment).commit();
        }
/*
            // Añadir el fragmento al contenedor 'fragment_container'
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment).commit();
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        */
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    /*
    MÉTODO SOBREESCRITO PARA QUE MUESTRE LA UBICACIÓN ELEGIDA
     */


}

