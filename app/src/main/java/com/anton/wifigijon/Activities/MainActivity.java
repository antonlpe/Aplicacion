package com.anton.wifigijon.Activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import com.anton.wifigijon.Fragments.Fragment_info;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initDrawer();

        // cambiar la actividad inicial por un layout de bienvenida para que no quede una lista de fondo
        if (savedInstanceState == null) {
            navigationView.setCheckedItem(R.id.visualizar);
            fragmentManager.beginTransaction().replace(R.id.content_frame, new Fragment_list(), "prueba").commit();
            setTitle(getString(R.string.app_name));
        }

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
        //prueba con las etiquetas, falta crear fragments para las funcionalidades y meterlas en cada pestaña
        int id = item.getItemId();
        //replace -> dónde reemplazar, qué reemplazar
        if (id == R.id.visualizar) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new Fragment_list()).commit();
            setTitle(getString(R.string.fragmento_lista));
        } else if (id == R.id.mi_ubicacion) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new Fragment_info()).commit();
            setTitle(getString(R.string.fragmento_mi_ubicacion));
        } else if (id == R.id.compartir) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new Fragment_list()).commit();
            setTitle(getString(R.string.fragmento_compartir));
        } else if (id == R.id.favoritos) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new Fragment_list()).commit();
            setTitle(getString(R.string.fragmento_favoritos));
        }

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}