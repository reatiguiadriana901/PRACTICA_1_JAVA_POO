package TiendaOnline;

import java.util.ArrayList;

public class Carrito {
    private static final double MONTO_PARA_DESCUENTO = 1000;
    private static final double PORCENTAJE_DESCUENTO = 0.10;

    private Cliente cliente;
    private Inventario inventario;
    private ArrayList<ItemCarrito> items = new ArrayList<>();

    public Carrito(Cliente cliente, Inventario inventario) {
        this.cliente = cliente;
        this.inventario = inventario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<ItemCarrito> getItems() {
        return items;
    }

    public boolean estaVacio() {
        return items.size() == 0;
    }

    // Expansión 15: el stock se valida y se descuenta consultando al Inventario
    public boolean agregarProducto(Producto producto, int cantidad) {
        if (!inventario.hayStock(producto, cantidad)) {
            return false;
        }
        inventario.descontarStock(producto, cantidad);

        ItemCarrito existente = buscarItem(producto);
        if (existente != null) {
            existente.aumentarCantidad(cantidad);
        } else {
            items.add(new ItemCarrito(producto, cantidad));
        }
        return true;
    }

    private ItemCarrito buscarItem(Producto producto) {
        for (ItemCarrito item : items) {
            if (item.getProducto() == producto) {
                return item;
            }
        }
        return null;
    }

    // Expansión 12: subtotal, descuento y total
    public double calcularSubtotal() {
        double subtotal = 0;
        for (ItemCarrito item : items) {
            subtotal += item.getSubtotal();
        }
        return subtotal;
    }

    public double calcularDescuento() {
        double subtotal = calcularSubtotal();
        if (subtotal > MONTO_PARA_DESCUENTO) {
            return subtotal * PORCENTAJE_DESCUENTO;
        }
        return 0;
    }

    public double calcularTotal() {
        return calcularSubtotal() - calcularDescuento();
    }

    public void vaciar() {
        items.clear();
    }
}