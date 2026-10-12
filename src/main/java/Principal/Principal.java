package Principal;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 6) {
            mostrarMenu();

            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                opcion = 0;
                System.out.println("Debe ingresar un número.");
                continue;
            }

            switch (opcion) {
                case 1:
                    CajeroAutomatico.Main.main(new String[0]);
                    break;
                case 2:
                    RegistroAcademico.Main.main(new String[0]);
                    break;
                case 3:
                    TiendaOnline.Main.main(new String[0]);
                    break;
                case 4:
                    AgendaContactos.Main.main(new String[0]);
                    break;
                case 5:
                    SistemaNotas.Main.main(new String[0]);
                    break;
                case 6:
                    System.out.println("Hasta pronto.");
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n===== PRÁCTICA 1 JAVA =====");
        System.out.println("1. Cajero automático");
        System.out.println("2. Registro académico");
        System.out.println("3. Tienda online");
        System.out.println("4. Agenda de contactos");
        System.out.println("5. Sistema de notas con curva");
        System.out.println("6. Salir");
        System.out.print("Elija un ejercicio: ");
    }
}