package com.anton.wifigijon.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.anton.wifigijon.R;


public class FragmentShowInfo extends Fragment {
    View rootView;
    private TextView textView;

    String ubicacion = "";
    String nombre = "";
    String tipo="";
    String correo="";
    String contenido;
    public FragmentShowInfo() {
        // Required empty public constructor
    }


    public static FragmentShowInfo newInstance(String nombre, String ubicacion, String tipo, String correo){
        FragmentShowInfo fragment = new FragmentShowInfo();

        Bundle bundle = new Bundle();
        bundle.putString("nombre", nombre);
        bundle.putString("ubicacion", ubicacion);
        bundle.putString("tipo", tipo);
        bundle.putString("correo", correo);

        fragment.setArguments(bundle);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_show_info, container, false);
        loadTextView();
        return rootView;
    }

    public void loadTextView(){
        Bundle args = getArguments();
        textView = (TextView) rootView.findViewById(R.id.text_info);
        contenido = new String();
        if (args != null) {
            nombre = args.getString("nombre");
            ubicacion = args.getString("ubicacion");
            tipo = args.getString("tipo");
            correo = args.getString("correo");
            contenido = "Nombre: "+nombre+"\n\n"+"Ubicación: "+ubicacion+"\n\n"+"Tipo: "+tipo+"\n\n"+"Correo: "+correo;
            textView.setText(contenido);
        } else {
            textView.setText(null);
        }
    }//loadTextView

}
