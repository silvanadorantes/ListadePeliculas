package com.example.silvanadorantes.listadepeliculas;

/**
 * Created by silvana on 07/08/16.
 */
public class Pelicula {

    private int idPelicula;
    private String nombrePelicula;
    private String estatus;

    public Pelicula(){

    }

    public Pelicula(int idPelicula, String nombrePelicula, String estatus){
        this.idPelicula = idPelicula;
        this.nombrePelicula = nombrePelicula;
        this.estatus = estatus;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getNombrePelicula() {
        return nombrePelicula;
    }

    public void setNombrePelicula(String nombrePelicula) {
        this.nombrePelicula = nombrePelicula;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
}
