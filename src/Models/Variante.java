package Models;

public class Variante {

    // Propiedades
    private String tamano;
    private int precio;


    // Getters y Setters
    public String getTamano() {
        return this.tamano;
    }
    
    public int getPrecio() {
        return this.precio;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    // Constructor
    public Variante(String tamano, int precio) {
        this.tamano = tamano;
        this.precio = precio;
    }
}
