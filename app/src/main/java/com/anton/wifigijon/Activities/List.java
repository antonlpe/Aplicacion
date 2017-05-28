package com.anton.wifigijon.Activities;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.anton.wifigijon.Data.Items;
import com.anton.wifigijon.Fragments.Fragment_list;
import com.anton.wifigijon.R;

/**
 * Created by Antón on 28/05/2017.
 */

public class List extends AppCompatActivity implements Fragment_list.Callbacks{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }//onCreate


    @Override
    public void onItemSelected(Items item) {
        Intent intent = new Intent(this, GoogleMaps.class);
        //aqui se pasan los datos del item a la segunda actividad
        intent.putExtra("latitud", item.getLat());
        intent.putExtra("longitud", item.getLon());
        intent.putExtra("nombre", item.getNombre());
        startActivity(intent);
    }

}