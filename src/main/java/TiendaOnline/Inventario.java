package TiendaOnline;

import java.util.ArrayList;

// Expansión 15: el catálogo y su stock viven aquí
public class Inventario {
    private ArrayList<Producto> productos = new ArrayList<>();

    public Inventario() {
        cargarProductos();
    }

    private void cargarProductos() {
        productos.add(new ProductoElectronico("Laptop", 1200, 5, 24));
        productos.add(new ProductoElectronico("Audífonos", 60, 15, 12));
        productos.add(new ProductoElectronico("Mouse", 25, 20, 6));
        productos.add(new ProductoAlimenticio("Leche", 2.5, 40, "30/12/2026"));
        productos.add(new ProductoAlimenticio("Pan", 1.2, 50, "20/10/2026"));
        productos.add(new ProductoAlimenticio("Arroz", 3, 60, "15/06/2027"));
        productos.add(new Producto("Lámpara", 20, 10, "Hogar"));
        productos.add(new Producto("Escoba", 8, 25, "Hogar"));
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    // Devuelve el producto con ese nombre, o null si no existe
    public Producto buscarProducto(String nombre) {
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    // Carrito le pregunta al Inventario, y el Inventario usa los métodos del Producto
    public boolean hayStock(Producto producto, int cantidad) {
        return producto.hayStock(cantidad);
    }

    public void descontarStock(Producto producto, int cantidad) {
        producto.disminuirStock(cantidad);
    }

    // Expansión 11: categorías distintas y filtro por categoría
    public ArrayList<String> getCategorias() {
        ArrayList<String> categorias = new ArrayList<>();
        for (Producto p : productos) {
            if (!categorias.contains(p.getCategoria())) {
                categorias.add(p.getCategoria());
            }
        }
        return categorias;
    }

    public ArrayList<Producto> filtrarPorCategoria(String categoria) {
        ArrayList<Producto> filtrados = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getCategoria().equalsIgnoreCase(categoria)) {
                filtrados.add(p);
            }
        }
        return filtrados;
    }
}