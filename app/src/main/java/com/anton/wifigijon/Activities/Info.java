package com.anton.wifigijon.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import com.anton.wifigijon.Fragments.FragmentShowInfo;
import com.anton.wifigijon.R;

/**
 * Created by Antón on 21/05/2017.
 */

public class Info extends AppCompatActivity {

    String ubicacion = "";
    String nombre = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_layout);

        // Existe el contenedor del fragmento?
        if (findViewById(R.id.fragment_container_info) != null) {

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

            // Añadir el fragmento al contenedor 'fragment_container'
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_info, fragment).commit();
        }
    }//onCreate
}
