package com.anton.wifigijon.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.anton.wifigijon.Data.Adapter;
import com.anton.wifigijon.Data.Datos;
import com.anton.wifigijon.Data.GsonRequest;
import com.anton.wifigijon.Data.Items;
import com.anton.wifigijon.Data.VolleyManager;
import com.anton.wifigijon.R;

import java.util.ArrayList;
import java.util.List;

/**
 * ESTE FRAGMENTO ES IGUAL QUE EL QUE MUESTRA LAS UBICACIONES DEL MAPA PERO COGERÁ LOS DATOS
 * PARA MOSTRAR INFORMACIÓN. SE CREA UN ADAPTER DE TIPO 1 PARA LLAMAR EN onBindViewHolder AL FRAGMENTO DE INFORMACIÓN
 *
 * */

public class Fragment_info extends Fragment {
    private static final String URL = "http://datos.gijon.es/doc/ciencia-tecnologia/zona-wifi.json";
    View rootView;
    Context context;
    //código de transparencias de teoría sobre uso de recyclerview
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public Fragment_info() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_list, container, false);

        context = getActivity();

        initRecyclerView();

        return rootView;
    }


    private void initRecyclerView() {

        final List items = new ArrayList<>();
        //dentro del response, va la implementación que necesita del acceso a los datos del JSON
        //primero rellenamos la lista y pasamos los datos al Adapter y al RecyclerView
        Response.Listener<Datos> response = new Response.Listener<Datos>() {

            @Override
            public void onResponse(final Datos response) {
                //en este caso obtener información a mostrar como texto
                for(int i=0;i<67;i++){
                    String ubicacion = response.getDirectorio().get(i).getLocalizacion().getCoordenadas();
                    if(ubicacion != null){
                        String descripcion = response.getDirectorio().get(i).getNombre().getNombreMarcador();
                        items.add(new Items(R.drawable.ic_wifi, descripcion, ubicacion));
                    }
                }
                //código de transparencias de teoría sobre uso de recyclerview
                mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
                mRecyclerView.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(context); // para que los items salgan en cuadrados como si fuese una galeria de fotos cambiar el linearlayoutmanager por gridlayoutmanager
                mRecyclerView.setLayoutManager(mLayoutManager);
                //el context para lanzar la segunda actividad desde la pulsacion de un item
                //1 ES EN EL IF DEL ADAPTER, LLAMAR AL FRAGMENTO DE INFORMACIÓN
                adapter = new Adapter(context,items,1);
                mRecyclerView.setAdapter(adapter);
            }//onResponse
        };//response

        GsonRequest<Datos> request = new GsonRequest<>(URL, Datos.class, null, response, null, "directorios");
        VolleyManager.getInstance(context).addToRequestQueue(request);


    }//initRecyclerView


}
