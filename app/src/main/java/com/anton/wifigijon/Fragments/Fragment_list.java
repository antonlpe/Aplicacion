package com.anton.wifigijon.Fragments;

import android.app.Activity;
import android.content.Context;

import com.android.volley.Response;
import com.anton.wifigijon.Data.Datos;
import com.anton.wifigijon.Data.GsonRequest;
import com.anton.wifigijon.Data.VolleyManager;
import android.os.Bundle;
import com.anton.wifigijon.R;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.anton.wifigijon.Data.Adapter;
import com.anton.wifigijon.Data.Items;

import java.util.ArrayList;
import java.util.List;

/**
 * FRAGMENTO QUE MUESTRA UNA LISTA DELAS UBICACIONES, OBTIENE LA INFORMACIÓN Y OBTIENE LOS DATOS
 * SE CREA EN UN ADAPTER TIPO 0 PARA LLAMAR EN onBindViewHolder AL MAPA
 */
public class Fragment_list extends Fragment implements AdapterView.OnItemClickListener {

    private static final String URL = "http://datos.gijon.es/doc/ciencia-tecnologia/zona-wifi.json";
    View rootView;
    Context context;
    List<Items> items;
    ListView lvItems;

    public interface Callbacks {
        public void onItemSelected(Items item);
    }

    private Adapter mAdapter = null;
    private Callbacks mCallback = null;


    public static Fragment_list newInstance() {

        Fragment_list fragment = new Fragment_list();
        return fragment;
    }

    public Fragment_list() {
    }

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);
        try {
            mCallback = (Callbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement Callbacks");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_list, container, false);
        context = getActivity();
        // Configurar la lista
        final List items = new ArrayList();
        //Aquí informacion
        Response.Listener<Datos> response = new Response.Listener<Datos>() {

            @Override
            public void onResponse(final Datos response) {
                //en este caso obtener información a mostrar como texto
                for(int i=0;i<67;i++){
                    String localizacion = response.getDirectorio().get(i).getLocalizacion().getCoordenadas();
                    if(localizacion != null){
                        String delimitador = "[ ]+";
                        String[] latlong = localizacion.split(delimitador);
                        String latitud = latlong[0];
                        String longitud = latlong[1];
                        String descripcion = response.getDirectorio().get(i).getNombre().getNombreMarcador();
                        float lat = Float.parseFloat(latitud);
                        float lon = Float.parseFloat(longitud);
                        items.add(new Items(R.drawable.ic_wifi, descripcion, lat, lon));
                    }
                }
                setupListView(items);

            }//onResponse
        };//response
        GsonRequest<Datos> request = new GsonRequest<>(URL, Datos.class, null, response, null, "directorios");
        VolleyManager.getInstance(context).addToRequestQueue(request);



        return rootView;
    }

    private void setupListView(List list){
        lvItems = (ListView) rootView.findViewById(R.id.list_view_items);
        mAdapter = new Adapter(getActivity(), list);
        lvItems.setAdapter(mAdapter);
        lvItems.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Items item = (Items) parent.getItemAtPosition(position);
        mCallback.onItemSelected(item);
    }
}