package com.example.saborcitou_app;

public class Modulo_ListarHistorial {

    private String cantidad_items;
    private String descripcion_orden;
    private String id_cliente;
    private Double monto_total;

    private Modulo_ListarHistorial(){}

    private Modulo_ListarHistorial(String cantidad_items,String descripcion_orden,String id_cliente, Double monto_total){
        this.cantidad_items = cantidad_items;
        this.descripcion_orden=descripcion_orden;
        this.id_cliente = id_cliente;
        this.monto_total = monto_total;
    }

    public String getCantidad_items() {
        return cantidad_items;
    }

    public void setCantidad_items(String cantidad_items) {
        this.cantidad_items = cantidad_items;
    }

    public String getDescripcion_orden() {
        return descripcion_orden;
    }

    public void setDescripcion_orden(String descripcion_orden) {
        this.descripcion_orden = descripcion_orden;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Double getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(Double monto_total) {
        this.monto_total = monto_total;
    }
}
