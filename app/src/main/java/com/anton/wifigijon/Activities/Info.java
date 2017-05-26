package com.anton.wifigijon.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.anton.wifigijon.Fragments.Fragment_info;
import com.anton.wifigijon.R;

/**
 * Created by Antón on 21/05/2017.
 */

public class Info extends AppCompatActivity {

    FragmentManager fragment = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        fragment.beginTransaction().replace(R.id.fragment_container_info, new Fragment_info()).commit();


    }//onCreate
}
