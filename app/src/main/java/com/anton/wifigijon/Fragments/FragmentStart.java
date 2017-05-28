package com.anton.wifigijon.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anton.wifigijon.R;

/**
 * Created by Antón on 28/05/2017.
 */

public class FragmentStart extends Fragment {
    View rootView;
    private TextView textView;

    public FragmentStart() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_start, container, false);
        return rootView;
    }
}
