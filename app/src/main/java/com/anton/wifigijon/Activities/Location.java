package com.anton.wifigijon.Activities;

/**
 * Created by Antón on 25/05/2017.
 */
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.Response;
import com.anton.wifigijon.Data.Datos;
import com.anton.wifigijon.Data.GsonRequest;
import com.anton.wifigijon.Data.Items;
import com.anton.wifigijon.Data.PermissionUtils;
import com.anton.wifigijon.Data.VolleyManager;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.anton.wifigijon.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;


public class Location extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener,
        ActivityCompat.OnRequestPermissionsResultCallback{
    private static final String URL = "http://datos.gijon.es/doc/ciencia-tecnologia/zona-wifi.json";
    private GoogleMap mMap;
    Context context;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private boolean mPermissionDenied = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        context = getBaseContext();
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
        //SE ASIGNA LA UBICACIÓN AL MAPA Y SE CONFIGURA LA CÁMARA Y LA ETIQUETA
        LatLng pos = new LatLng(43.5293101, -5.6773233);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(pos));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(14));

        final ArrayList<Items> items = new ArrayList<>();
        //marcas de todos los puntos para ver y situarse en base a nuestra posicion
        Response.Listener<Datos> response = new Response.Listener<Datos>() {

            @Override
            public void onResponse(final Datos response) {
                //mostramos todos los marcadores
                for(int i=0;i<67;i++){
                    String localizacion = response.getDirectorio().get(i).getLocalizacion().getCoordenadas();
                    if(localizacion != null){
                        String delimitador = "[ ]+";
                        String[] latlong = localizacion.split(delimitador);
                        String latitud = latlong[0];
                        String longitud = latlong[1];
                        String nombre = response.getDirectorio().get(i).getNombre().getNombreMarcador();
                        float lat = Float.parseFloat(latitud);
                        float lon = Float.parseFloat(longitud);
                        items.add(new Items(R.drawable.ic_wifi, nombre, lat, lon));
                    }//if
                }//for
                for(int i=0;i<items.size();i++){
                    LatLng ubicacion = new LatLng(items.get(i).getLat(), items.get(i).getLon());
                    Marker prueba = mMap.addMarker(new MarkerOptions().position(ubicacion).title(items.get(i).getNombre()));
                    prueba.showInfoWindow();
                }
            }//onResponse
        };//response
        GsonRequest<Datos> request = new GsonRequest<>(URL, Datos.class, null, response, null, "directorios");
        VolleyManager.getInstance(context).addToRequestQueue(request);

        //Y nuestra ubicación
        mMap.setOnMyLocationButtonClickListener(this);
        enableMyLocation();
        mMap.moveCamera(CameraUpdateFactory.zoomTo(14));
    }
    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            if (permissions.length == 1 &&
                    permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                                PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                mMap.setMyLocationEnabled(true);
            } else {
                // Permission was denied. Display an error message.
            }
        }}

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mPermissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
    }
}
