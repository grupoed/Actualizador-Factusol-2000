/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Victor
 */
public class Producto {
    private String referencia;
    private String nombre;
    private double precio;
    private String mayorista;
    private int stock;//0 -> NO DISPONIBLE -- 1 -> POCAS UNIDADES -- 2 -> DISPONIBLE
    private int codigoSubfamilia;
    private String fabricante;
    private double peso;
    private String descripcion;
    private String url_imagen;

    Producto(String referencia, String nombre, double precio, String mayorista, int stock,int codigoSubfamilia, String fabricante, double peso,String descripcion,String url_imagen) {
        this.referencia = referencia;
        this.nombre = nombre;
        this.precio = precio;
        this.mayorista = mayorista;
        this.stock = stock;
        this.codigoSubfamilia = codigoSubfamilia;
        this.fabricante = fabricante;
        this.peso = peso;
        this.descripcion=descripcion;
        this.url_imagen=url_imagen;
    }

    public String getUrl_imagen() {
        return url_imagen;
    }

    public void setUrl_imagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    int getCodigoSubfamilia() {
        return codigoSubfamilia;
    }

    void setCodigoSubfamilia(int codigoSubfamilia) {
        this.codigoSubfamilia = codigoSubfamilia;
    }

    String getFabricante() {
        return fabricante;
    }

    void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getMayorista() {
        return mayorista;
    }

    void setMayorista(String mayorista) {
        this.mayorista = mayorista;
    }

    String getNombre() {
        return nombre;
    }

    void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPeso() {
        return peso;
    }

     void setPeso(double peso) {
        this.peso = peso;
    }

    public double getPrecio() {
        return precio;
    }

     void setPrecio(double precio) {
        this.precio = precio;
    }

    String getReferencia() {
        return referencia;
    }

     void setReferencia(String referencia) {
        this.referencia = referencia;
    }
     
    boolean esmayor(Producto comp){
        return this.precio<comp.precio;
    }
    
    public Map<String,String> getData(){
        Map<String,String> datos=new HashMap();
        datos.put("CODART",referencia);
        datos.put("DESART",nombre);
        datos.put("PCOART",Double.toString(precio));
        datos.put("SUWART",mayorista);
        datos.put("STOART",Integer.toString(stock));
        datos.put("FAMART",Integer.toString(this.codigoSubfamilia));
        datos.put("FTEART",this.fabricante);
        datos.put("PESART",Double.toString(peso));
        datos.put("DEWART", this.descripcion);
        return datos;
    }
}
