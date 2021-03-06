package com.anton.wifigijon.Data;

/**
 * Created by Antón on 13/05/2017.
 * Esta clase define los elementos que queremos mostrar en el recyclerview y los que queremos pasar al mapa.
 * Le pasaremos al adapter una imagen y un nombre y almacenaremos la latitud y longitud del mismo
 * índice para pasarle las coordenadas al mapa, además de su descripción para mostrar en el marcador
 */


public class Items {
    private int imagen;
    private String nombre;
    private String ubicacion;
    private float lat;
    private float lon;
    private String tipo;
    private String correo;

    //constructor para mapa
    public Items(int imagen, String nombre, float lat, float lon) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.lat = lat;
        this.lon = lon;
    }

    //constructor de copia para la lista de mostrar información
    public Items(int imagen, String nombre, String ubicacion) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

    //constructor de copia para la lista de mostrar información
    public Items(int imagen, String nombre, String ubicacion, String tipo, String correo) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.correo = correo;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getImagen() {
        return imagen;
    }

    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }

    public String getUbicacion() {return ubicacion;}

    public String getTipo() {
        return tipo;
    }

    public String getCorreo() { return correo;  }
}
