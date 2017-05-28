package com.anton.wifigijon.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.anton.wifigijon.Data.Items;
import com.anton.wifigijon.Fragments.FragmentInfoMail;
import com.anton.wifigijon.Fragments.FragmentMail;
import com.anton.wifigijon.R;

/**
 * Created by Antón on 27/05/2017.
 */

public class InfoMail extends AppCompatActivity implements FragmentInfoMail.Callbacks{
    private boolean mTwoPanes = false;
    FragmentManager fragment = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_mail);
        if (findViewById(R.id.fragment_container_mail) != null) {
            mTwoPanes = true;
        }
    }//onCreate

    @Override
    public void onItemSelected(Items item) {
        if(mTwoPanes==false){
            Intent intent = new Intent(this, Mail.class);
            //aqui se pasan los datos del item a la segunda actividad
            intent.putExtra("ubicacion", item.getUbicacion());
            intent.putExtra("nombre", item.getNombre());
            intent.putExtra("tipo", item.getTipo());
            intent.putExtra("correo", item.getCorreo());

            startActivity(intent);
        }
        else{
            fragment.beginTransaction().replace(R.id.fragment_container_mail,
                    FragmentMail.newInstance(item.getNombre(),item.getUbicacion(),
                            item.getTipo(),item.getCorreo())).commit();
        }
    }
}
