package programa;

public class Auto {
    
    private String placas, nombre;
    private int precio;
    private int TARIFA = 12;    //Tarifa aplicada por cada hora transcurrida
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
    
    //Otros Métodos
    public void calcularPrecio(){
        //Se obtiene el precio dado el tiempo ocupado por el auto (Tiempo en horas)
        this.precio = TARIFA * this.tiempo;
    }
}
