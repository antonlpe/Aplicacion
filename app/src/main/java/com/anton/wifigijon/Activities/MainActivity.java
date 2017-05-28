package com.anton.wifigijon.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;

import com.anton.wifigijon.Data.Items;
import com.anton.wifigijon.Fragments.FragmentStart;
import com.anton.wifigijon.R;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import com.anton.wifigijon.Fragments.Fragment_list;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    FragmentManager fragmentManager = getSupportFragmentManager();
    private boolean mTwoPanes = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.map_land_fragment) != null) {
            mTwoPanes = true;
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FragmentStart fragment = new FragmentStart();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_frame_main, fragment).commit();
        initDrawer();



    }//onCreate

    private void initDrawer() {
        /*
            DrawerLayout esta en activity_main
            NavigationView esta en activity_main
            el xml para los items del drawer menu estan en menu/activity_main_drawer
         */
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //abre y cierra el menú. los string los pide así como int(con R)
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.abierto, R.string.cerrado);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // aqui los items del drawer menu (el que se abre desde la izquierda como PlayStore)
        //prueba_fragment con las etiquetas, falta crear fragments para las funcionalidades y meterlas en cada pestaña
        int id = item.getItemId();
        //replace -> dónde reemplazar, qué reemplazar
        if (id == R.id.puntos_wifi) {
             Intent intent = new Intent(this,List.class);
            startActivity(intent);
        } else if (id == R.id.mi_ubicacion) {
            Intent intent = new Intent(this,Location.class);
            startActivity(intent);
        } else if (id == R.id.informacion) {
            Intent intent = new Intent(this,Info.class);
            startActivity(intent);
        } else if (id == R.id.compartir) {
            Intent intent = new Intent(this,InfoMail.class);
            startActivity(intent);
        }
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}