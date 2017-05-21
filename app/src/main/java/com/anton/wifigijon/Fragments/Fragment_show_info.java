package com.anton.wifigijon.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.anton.wifigijon.R;


public class Fragment_show_info extends Fragment {
    View rootView;
    private TextView textView;

    String ubicacion = "";
    String nombre = "";
    String contenido;
    public Fragment_show_info() {
        // Required empty public constructor
    }

    public void loadTextView(){
        contenido = new String();
        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null)
        {
            ubicacion = (String) bundle.get("ubicacion");
            nombre =(String) bundle.get("nombre");
        }
        contenido = "Nombre: "+nombre+"\n"+"Ubicación: "+ubicacion+"\n";
        textView = (TextView) rootView.findViewById(R.id.text_info);
        textView.setText(contenido);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_show_info_layout, container, false);
        loadTextView();
        return rootView;
    }

}
