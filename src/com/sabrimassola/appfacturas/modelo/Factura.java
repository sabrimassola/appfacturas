package com.sabrimassola.appfacturas.modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Factura {
    private int folio;
    private String descripcion;
    private Date fecha;
    private Cliente cliente;
    private ItemFactura[] items;
    private int indiceItems;
    public static final int MAX_ITEMS = 10;
    public static final float IVA = 0.21f;
    private static int ultimoFolio;


    public Factura(String descripcion, Cliente cliente) {
        this.descripcion = descripcion;
        this.cliente = cliente;
        this.items = new ItemFactura[MAX_ITEMS];
        this.folio = ++ultimoFolio;
        this.fecha = new Date();
    }

    public int getFolio() {
        return folio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ItemFactura[] getItems() {
        return items;
    }

    public void addItemFactura(ItemFactura item) {
        if (indiceItems < MAX_ITEMS) {
            this.items[indiceItems++] = item;
        }
    }

    public float calcularTotal() {
        float total = 0.0f;
        for (int i = 0; i < indiceItems ; i++) {
            total += this.items[i].calcularImporte();
        }
        return total;

    }

    public String generarDetalle() {
        StringBuilder detalle = new StringBuilder("Factura n: ");
        detalle.append(folio);
        detalle.append("\nCliente: ")
                .append(cliente.getNombre())
                .append("\nNIF: ")
                .append(cliente.getNif())
                .append("\nDescripcion:")
                .append(this.descripcion);
        SimpleDateFormat df = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");
        detalle.append("\nFecha de emision: ")
                .append(df.format(this.fecha))
                .append("\n")
                .append("\n#\tNombre\t$\ttCant.\tTotal\n");
        for(int i = 0 ; i <indiceItems ; i++ ){
            detalle.append(this.items[i].toString())
                   .append("\n");
        }
      
        detalle.append("\nSubtotal: $")
                .append(calcularTotal())
                .append("\nIVA: $")
                .append(calcularIVA())
                .append("\nTOTAL FINAL DE FACTURA CON IVA: $")
                .append(calcularTotalConIVA());
        return detalle.toString();
    }

    @Override
    public String toString() {
        return generarDetalle();
    }

    public float calcularIVA(){
        return calcularTotal() * IVA;
    }
    public float calcularTotalConIVA(){
        return calcularTotal() + calcularIVA();
    }

}
