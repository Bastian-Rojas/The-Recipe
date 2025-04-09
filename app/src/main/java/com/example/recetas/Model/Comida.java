package com.example.recetas.Model;

public class Comida {
    String Nombre,Imagen;

        public Comida(){}

    public Comida(String nombre, String imagen) {
        this.Nombre = nombre;
        this.Imagen = imagen;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }

}
