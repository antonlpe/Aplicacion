package com.anton.wifigijon.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;


import com.anton.wifigijon.Data.Items;
import com.anton.wifigijon.Fragments.FragmentShowInfo;
import com.anton.wifigijon.Fragments.Fragment_info;
import com.anton.wifigijon.R;

/**
 * Created by Antón on 21/05/2017.
 */

public class Info extends AppCompatActivity implements Fragment_info.Callbacks{
    private boolean mTwoPanes = false;
    FragmentManager fragment = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        if (findViewById(R.id.fragment_container_show_info) != null) {
            mTwoPanes = true;
        }

    }//onCreate


    @Override
    public void onItemSelected(Items item) {
        if(mTwoPanes==false){
            Intent intent = new Intent(this, ShowInfo.class);
            //aqui se pasan los datos del item a la segunda actividad
            intent.putExtra("ubicacion", item.getUbicacion());
            intent.putExtra("nombre", item.getNombre());
            intent.putExtra("tipo", item.getTipo());
            intent.putExtra("correo", item.getCorreo());
            startActivity(intent);
        }
        else{
            fragment.beginTransaction().replace(R.id.fragment_container_show_info,
                    FragmentShowInfo.newInstance(item.getNombre(),item.getUbicacion(),
                            item.getTipo(),item.getCorreo())).commit();
        }
    }
}
