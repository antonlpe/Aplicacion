package com.anton.wifigijon.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anton.wifigijon.R;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class FragmentGoogleMaps extends Fragment implements OnMapReadyCallback{

    View rootView;
    private GoogleMap mMap;
    private String nombre;
    private float lat;
    private float lon;

    public FragmentGoogleMaps() {
        // Required empty public constructor
    }

    public static FragmentGoogleMaps newInstance(String nombre, float latitud, float longitud){
        FragmentGoogleMaps fragment = new FragmentGoogleMaps();

        Bundle bundle = new Bundle();
        bundle.putString("nombre", nombre);
        bundle.putFloat("latitud", latitud);
        bundle.putFloat("longitud", longitud);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_google_maps, container, false);
        SupportMapFragment mapFrag = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);

        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        Bundle bundle = getArguments();
        if(bundle!=null)
        {
            lat = bundle.getFloat("latitud");
            lon = bundle.getFloat("longitud");
            nombre =(String) bundle.get("nombre");
        }
        //SE ASIGNA LA UBICACIÓN AL MAPA Y SE CONFIGURA LA CÁMARA Y LA ETIQUETA

        LatLng ubicacion = new LatLng(lat, lon);
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"+lat);
        Marker prueba = mMap.addMarker(new MarkerOptions().position(ubicacion).title(nombre));
        prueba.showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacion));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(17));
    }
}
