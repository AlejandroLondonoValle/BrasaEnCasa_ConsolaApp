package Models;

public class ItemPedido {

    // Propiedades
    private Producto producto;
    private Variante variante;
    private int cantidad;

    // Getters y Setters
    public Producto getProducto() {
        return this.producto;
    }

    public Variante getVariante() {
        return this.variante;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setVariante(Variante variante) {
        this.variante = variante;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Constructor
    public ItemPedido(Producto producto, Variante variante, int cantidad) {
        this.producto = producto;
        this.variante = variante;
        this.cantidad = cantidad;
    }

    // Metodos
    public int subtotal() {
        return variante.getPrecio() * cantidad;
    }
}
