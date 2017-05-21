package com.anton.wifigijon.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import com.anton.wifigijon.Fragments.Fragment_show_info;
import com.anton.wifigijon.R;

/**
 * Created by Antón on 21/05/2017.
 */

public class Info extends AppCompatActivity {
    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_layout);
/*
        // cambiar la actividad inicial por un layout de bienvenida para que no quede una lista de fondo
        if (savedInstanceState == null) {
            fragmentManager.beginTransaction().replace(R.id.fragment_info, new Fragment_show_info()).commit();
            setTitle(getString(R.string.actividad_info));
        }
*/
    }//onCreate
}
