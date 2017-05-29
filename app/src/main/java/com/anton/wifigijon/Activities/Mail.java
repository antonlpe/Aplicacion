package com.anton.wifigijon.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.anton.wifigijon.Fragments.FragmentMail;
import com.anton.wifigijon.R;

/**
 * Created by Antón on 26/05/2017.
 */

public class Mail extends AppCompatActivity {
    String nombre="";
    String ubicacion="";
    String tipo="";
    String correo="";

    FragmentManager fragment = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        nombre = bundle.getString("nombre");
        ubicacion = bundle.getString("ubicacion");
        tipo = bundle.getString("tipo");
        correo = bundle.getString("correo");
        fragment.beginTransaction().replace(R.id.fragment_container_mail,
                FragmentMail.newInstance(nombre, ubicacion, tipo, correo)).commit();


    }//onCreate
}
