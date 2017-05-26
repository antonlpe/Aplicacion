package com.anton.wifigijon.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.anton.wifigijon.R;


public class FragmentMail extends Fragment {
    View rootView;
    EditText etDestinatario;
    EditText etAsunto;
    EditText etContenido;
    Button btEnviar;

    public FragmentMail() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_mail, container, false);
        etDestinatario = (EditText) rootView.findViewById(R.id.etDestinatario);
        etAsunto = (EditText) rootView.findViewById(R.id.etAsunto);
        etContenido = (EditText) rootView.findViewById(R.id.etContenido);
        btEnviar = (Button) rootView.findViewById(R.id.btEnviar);
        pulsar();
        return rootView;
    }

    private void pulsar(){
        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //si da tiempo probar enviar a varios o datos adjuntos
                String destinatario = etDestinatario.getText().toString();
                String asunto = etAsunto.getText().toString();
                String contenido = etContenido.getText().toString();
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);

                emailIntent.setData(Uri.fromParts("mailto", destinatario, // destinatario
                        null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, asunto);
                emailIntent.putExtra(Intent.EXTRA_TEXT, contenido);
                startActivity(emailIntent);
            }
        });
    }
}
