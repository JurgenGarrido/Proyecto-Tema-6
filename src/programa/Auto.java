package programa;

import java.io.Serializable;

public class Auto implements Serializable{
    
    private String placas, nombre;
    private int precio;
    private static final int TARIFA = 12;    //Tarifa aplicada por cada hora transcurrida
    private int tiempo;

    public Auto(String placas, String nombre) {
        this.placas = placas;
        this.nombre = nombre;
        this.precio = 0;
        this.tiempo = 0;
    }

    //Getters y Setters
    public String getPlacas() {
        return placas;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }
    
    public static int generarClave(String placas) {
        placas = placas.replaceAll("-", "");
        char[] datos = placas.toCharArray();
        int clave = 0;

        for (char dato : datos) {
            clave += dato;
        }

        return clave;
    }
    
    //Otros Métodos
    public void calcularPrecio(){
        //Se obtiene el precio dado el tiempo ocupado por el auto (Tiempo en horas)
        this.precio = TARIFA * this.tiempo;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    
}
