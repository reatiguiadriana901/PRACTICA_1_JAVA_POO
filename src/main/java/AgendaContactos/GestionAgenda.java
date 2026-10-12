package AgendaContactos;

import java.util.ArrayList;
import java.util.Scanner;

public class GestionAgenda {
    private static final String[] GRUPOS = {"Familia", "Trabajo", "Amigos"};

    private Agenda agenda = new Agenda();
    private Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        if (agenda.cargarDesdeArchivo()) {
            System.out.println("Se cargaron " + agenda.cantidadContactos() + " contactos guardados.");
        } else {
            System.out.println("No hay agenda guardada, se empieza vacía.");
        }

        int opcion = 0;
        while (opcion != 6) {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarContacto();
                    break;
                case 2:
                    buscarContacto();
                    break;
                case 3:
                    listarTodos();
                    break;
                case 4:
                    listarPorGrupo();
                    break;
                case 5:
                    eliminarContacto();
                    break;
                case 6:
                    System.out.println("Hasta pronto.");
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
        scanner.close();
    }

    private void mostrarMenu() {
        System.out.println("\n===== AGENDA DE CONTACTOS =====");
        System.out.println("1. Registrar contacto");
        System.out.println("2. Buscar contacto");
        System.out.println("3. Listar todos");
        System.out.println("4. Listar por grupo");
        System.out.println("5. Eliminar contacto");
        System.out.println("6. Salir");
        System.out.print("Elija una opción: ");
    }

    private void registrarContacto() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        String telefono;
        do {
            System.out.print("Teléfono (solo números, " + Agenda.LONGITUD_TELEFONO + " dígitos): ");
            telefono = scanner.nextLine();
            if (!agenda.telefonoValido(telefono)) {
                System.out.println("Teléfono inválido.");
            }
        } while (!agenda.telefonoValido(telefono));

        String email;
        do {
            System.out.print("Email: ");
            email = scanner.nextLine();
            if (!agenda.emailValido(email)) {
                System.out.println("Email inválido (debe tener @ y .).");
            }
        } while (!agenda.emailValido(email));

        String grupo = pedirGrupo();
        if (grupo == null) {
            return;
        }

        Contacto contacto = new Contacto(nombre, telefono, email, grupo);
        if (agenda.agregarContacto(contacto)) {
            agenda.guardarEnArchivo();
            System.out.println("Contacto registrado.");
        } else {
            System.out.println("Ya existe un contacto con ese nombre o teléfono.");
        }
    }

    // Devuelve el grupo elegido, o null si la opción es inválida
    private String pedirGrupo() {
        System.out.println("Grupo:");
        for (int i = 0; i < GRUPOS.length; i++) {
            System.out.println((i + 1) + ". " + GRUPOS[i]);
        }
        System.out.print("Elija un grupo: ");
        int eleccion = scanner.nextInt();
        scanner.nextLine();

        if (eleccion < 1 || eleccion > GRUPOS.length) {
            System.out.println("Grupo inválido.");
            return null;
        }
        return GRUPOS[eleccion - 1];
    }

    private void buscarContacto() {
        System.out.print("Nombre (o parte del nombre): ");
        String texto = scanner.nextLine();

        ArrayList<Contacto> encontrados = agenda.buscarContacto(texto);
        if (encontrados.size() == 0) {
            System.out.println("No se encontraron contactos.");
            return;
        }
        System.out.println("\nResultados:");
        for (Contacto c : encontrados) {
            System.out.println(c.mostrarInfo());
        }
    }

    private void listarTodos() {
        if (agenda.cantidadContactos() == 0) {
            System.out.println("La agenda está vacía.");
            return;
        }
        System.out.println("\n--- Todos los contactos ---");
        System.out.print(agenda.mostrarTodos());
    }

    private void listarPorGrupo() {
        String grupo = pedirGrupo();
        if (grupo == null) {
            return;
        }
        String resultado = agenda.mostrarContactosPorGrupo(grupo);
        if (resultado.isEmpty()) {
            System.out.println("No hay contactos en el grupo " + grupo + ".");
            return;
        }
        System.out.println("\n--- Grupo " + grupo + " ---");
        System.out.print(resultado);
    }

    private void eliminarContacto() {
        System.out.print("Nombre del contacto a eliminar: ");
        String nombre = scanner.nextLine();

        if (agenda.eliminarContacto(nombre)) {
            agenda.guardarEnArchivo();
            System.out.println("Contacto eliminado.");
        } else {
            System.out.println("Contacto no encontrado.");
        }
    }
}