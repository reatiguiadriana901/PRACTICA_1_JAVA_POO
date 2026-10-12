package TiendaOnline;

import java.util.ArrayList;
import java.util.Scanner;

public class GestionTienda {
    private Scanner scanner = new Scanner(System.in);
    private Inventario inventario = new Inventario();
    private Carrito carrito;

    public void iniciar() {
        Cliente cliente = iniciarSesion();
        carrito = new Carrito(cliente, inventario);

        System.out.println("\nBienvenido/a, " + cliente.getNombre());

        int opcion = 0;
        while (opcion != 6) {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    mostrarProductos(inventario.getProductos());
                    break;
                case 2:
                    filtrarCatalogo();
                    break;
                case 3:
                    agregarAlCarrito();
                    break;
                case 4:
                    verCarrito();
                    break;
                case 5:
                    finalizarCompra();
                    break;
                case 6:
                    System.out.println("Gracias por visitar la tienda.");
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
        scanner.close();
    }

    // Expansión 14: "login" pidiendo el email
    private Cliente iniciarSesion() {
        System.out.println("===== TIENDA ONLINE =====");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        String email;
        do {
            System.out.print("Email: ");
            email = scanner.nextLine();
            if (!email.contains("@") || !email.contains(".")) {
                System.out.println("Email inválido, intente de nuevo.");
            }
        } while (!email.contains("@") || !email.contains("."));

        return new Cliente(nombre, email);
    }

    private void mostrarMenu() {
        System.out.println("\n===== MENÚ =====");
        System.out.println("1. Ver catálogo");
        System.out.println("2. Filtrar catálogo por categoría");
        System.out.println("3. Agregar producto al carrito");
        System.out.println("4. Ver carrito");
        System.out.println("5. Finalizar compra");
        System.out.println("6. Salir");
        System.out.print("Elija una opción: ");
    }

    private void mostrarProductos(ArrayList<Producto> lista) {
        if (lista.size() == 0) {
            System.out.println("No hay productos para mostrar.");
            return;
        }
        System.out.println("\n--- Catálogo ---");
        for (Producto p : lista) {
            System.out.println(p.getDetalle());
        }
    }

    // Expansión 11
    private void filtrarCatalogo() {
        ArrayList<String> categorias = inventario.getCategorias();

        System.out.println("\nCategorías disponibles:");
        for (int i = 0; i < categorias.size(); i++) {
            System.out.println((i + 1) + ". " + categorias.get(i));
        }
        System.out.print("Elija una categoría: ");
        int eleccion = scanner.nextInt();
        scanner.nextLine();

        if (eleccion < 1 || eleccion > categorias.size()) {
            System.out.println("Categoría inválida.");
            return;
        }
        mostrarProductos(inventario.filtrarPorCategoria(categorias.get(eleccion - 1)));
    }

    private void agregarAlCarrito() {
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();

        Producto producto = inventario.buscarProducto(nombre);
        if (producto == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.print("Cantidad: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();

        if (carrito.agregarProducto(producto, cantidad)) {
            System.out.println("Producto agregado al carrito.");
        } else {
            System.out.println("Cantidad inválida o stock insuficiente (disponible: " + producto.getStock() + ").");
        }
    }

    private void verCarrito() {
        if (carrito.estaVacio()) {
            System.out.println("El carrito está vacío.");
            return;
        }
        mostrarResumen("Carrito de " + carrito.getCliente().getNombre());
    }

    // Expansión 12: muestra subtotal, descuento y total final
    private void mostrarResumen(String titulo) {
        System.out.println("\n--- " + titulo + " ---");
        for (ItemCarrito item : carrito.getItems()) {
            System.out.println(item.getProducto().getNombre()
                    + " x" + item.getCantidad()
                    + " = $" + String.format("%.2f", item.getSubtotal()));
        }
        System.out.println("Subtotal: $" + String.format("%.2f", carrito.calcularSubtotal()));
        System.out.println("Descuento: -$" + String.format("%.2f", carrito.calcularDescuento()));
        System.out.println("Total: $" + String.format("%.2f", carrito.calcularTotal()));
    }

    private void finalizarCompra() {
        if (carrito.estaVacio()) {
            System.out.println("El carrito está vacío.");
            return;
        }
        mostrarResumen("FACTURA - " + carrito.getCliente().getNombre()
                + " (" + carrito.getCliente().getEmail() + ")");
        carrito.vaciar();
        System.out.println("\nCompra realizada con éxito. ¡Gracias por su compra!");
    }
}