package com.anton.wifigijon.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.anton.wifigijon.Fragments.FragmentShowInfo;
import com.anton.wifigijon.R;

/**
 * Created by Antón on 25/05/2017.
 */

public class ShowInfo extends AppCompatActivity{

    String ubicacion = "";
    String nombre = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_info);

        // Existe el contenedor del fragmento?
        if (findViewById(R.id.fragment_container_show_info) != null) {

            // Si estamos restaurando desde un estado previo no hacemos nada
            if (savedInstanceState != null) {
                return;
            }

            // Crear el fragmento pasándole el parámetro
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            nombre = bundle.getString("nombre");
            ubicacion = bundle.getString("ubicacion");
            FragmentShowInfo fragment =
                    FragmentShowInfo.newInstance(nombre, ubicacion);

            // Añadir el fragmento al contenedor 'fragment_container_info'
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_show_info, fragment).commit();

        }
    }//onCreate
}
