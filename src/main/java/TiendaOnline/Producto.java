package TiendaOnline;

// Clase base (expansión 13). Incluye la categoría (expansión 11).
public class Producto {
    private String nombre;
    private double precio;
    private int stock;
    private String categoria;

    public Producto(String nombre, double precio, int stock, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public String getCategoria() {
        return categoria;
    }

    // Métodos pedidos por el enunciado base
    public boolean hayStock(int cantidad) {
        return cantidad > 0 && cantidad <= stock;
    }

    public void disminuirStock(int cantidad) {
        stock -= cantidad;
    }

    // Las hijas lo reescriben para agregar sus datos propios
    public String getDetalle() {
        return nombre + " | $" + String.format("%.2f", precio)
                + " | Stock: " + stock + " | " + categoria;
    }
}