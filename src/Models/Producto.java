package Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Producto {

    // Propiedades
    private String nombre;
    private String descripcion;
    private String categoria;
    private List<Variante> variantes;

    // Getters y Setters
    public String getNombre() {
        return this.nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public List<Variante> getVariantes() {
        return this.variantes;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setVariantes(List<Variante> variantes) {
        this.variantes = variantes;
    }


    // Constructor
    public Producto(String nombre, String descripcion, String categoria, Variante... variantes) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.variantes = new ArrayList<>(Arrays.asList(variantes));
    }

    // Metodo para Precio Minimo
    /** Precio mínimo (para mostrar "desde $X" cuando hay varias variantes) */
    public int precioMinimo() {
        return variantes.stream().mapToInt(v -> v.getPrecio()).min().orElse(0);
    }

}
