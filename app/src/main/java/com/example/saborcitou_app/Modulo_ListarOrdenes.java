package com.example.saborcitou_app;

public class Modulo_ListarOrdenes {

    private String nombre_plato;
    private String descripcion;
    private Double precio;

    private Modulo_ListarOrdenes(){}

    private Modulo_ListarOrdenes(String nombre_plato,String descripcion, Double precio){
        this.nombre_plato=nombre_plato;
        this.descripcion=descripcion;
        this.precio=precio;

    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getNombre_plato() {
        return nombre_plato;
    }

    public void setNombre_plato(String nombre_plato) {
        this.nombre_plato = nombre_plato;
    }
}
