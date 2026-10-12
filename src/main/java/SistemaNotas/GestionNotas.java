package SistemaNotas;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class GestionNotas {
    private static final double PROMEDIO_PARA_CURVA = 6;

    private Curso curso = new Curso();
    private Scanner scanner = new Scanner(System.in);

    public GestionNotas() {
        // Así los decimales se escriben con punto: 7.5
        scanner.useLocale(Locale.US);
    }

    public void iniciar() {
        pedirAlumnos();

        double promedio = curso.calcularPromedioOriginal();
        System.out.println("\nPromedio del curso (sin curva): " + formato(promedio));

        if (promedio < PROMEDIO_PARA_CURVA) {
            aplicarCurva();
        } else {
            System.out.println("El promedio es 6 o más, no se aplica curva.");
        }

        mostrarListado();
        mostrarEstadisticas();
        scanner.close();
    }

    private void pedirAlumnos() {
        int cantidad;
        do {
            cantidad = leerEntero("Ingrese la cantidad de alumnos: ");
            if (cantidad <= 0) {
                System.out.println("Debe ser mayor a 0.");
            }
        } while (cantidad <= 0);

        for (int i = 0; i < cantidad; i++) {
            System.out.println("\n--- Alumno " + (i + 1) + " ---");
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            double parcial1 = leerNota("Nota del parcial 1 (30%): ");
            double parcial2 = leerNota("Nota del parcial 2 (30%): ");
            double examen = leerNota("Nota del examen final (40%): ");

            curso.agregarAlumno(new Alumno(nombre, parcial1, parcial2, examen));
        }
    }

    // Expansión 24: si escriben texto en lugar de un número, se vuelve a pedir
    private int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = scanner.nextInt();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                scanner.nextLine(); // descarta lo que escribieron
                System.out.println("Debe ingresar un número entero.");
            }
        }
    }

    private double leerDecimal(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double valor = scanner.nextDouble();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Debe ingresar un número (use punto para decimales, ej. 7.5).");
            }
        }
    }

    private double leerNota(String mensaje) {
        double nota;
        do {
            nota = leerDecimal(mensaje);
            if (nota < 0 || nota > 10) {
                System.out.println("La nota debe estar entre 0 y 10.");
            }
        } while (nota < 0 || nota > 10);
        return nota;
    }

    private void aplicarCurva() {
        boolean aplicada = false;

        while (!aplicada) {
            System.out.println("\nEl promedio es menor a 6. Elija la curva:");
            System.out.println("1. Puntos fijos");
            System.out.println("2. Porcentual");
            System.out.println("3. Basada en el mejor alumno");
            int opcion = leerEntero("Opción: ");

            switch (opcion) {
                case 1:
                    double puntos = leerPositivo("Puntos a sumar (ej. 2): ");
                    curso.aplicarCurvaPuntos(puntos);
                    aplicada = true;
                    break;
                case 2:
                    double porcentaje = leerPositivo("Porcentaje a sumar (ej. 10): ");
                    curso.aplicarCurvaPorcentual(porcentaje);
                    aplicada = true;
                    break;
                case 3:
                    double aplicados = curso.aplicarCurvaMejorAlumno();
                    System.out.println("Se aplicó una curva de +" + formato(aplicados) + " puntos.");
                    aplicada = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
    }

    private double leerPositivo(String mensaje) {
        double valor;
        do {
            valor = leerDecimal(mensaje);
            if (valor < 0) {
                System.out.println("No puede ser negativo.");
            }
        } while (valor < 0);
        return valor;
    }

    private void mostrarListado() {
        System.out.println("\n===== LISTADO DE ALUMNOS =====");
        for (Alumno a : curso.getAlumnos()) {
            String estado = a.estaAprobado() ? "APROBÓ" : "REPROBÓ";
            System.out.println(a.getNombre()
                    + " | Original: " + formato(a.getNotaOriginal())
                    + " | Final: " + formato(a.getNotaFinal())
                    + " | " + estado);
        }
    }

    // Expansión 25
    private void mostrarEstadisticas() {
        System.out.println("\n===== ESTADÍSTICAS =====");
        System.out.println("Promedio original: " + formato(curso.calcularPromedioOriginal()));
        System.out.println("Promedio final (nueva media): " + formato(curso.calcularPromedioFinal()));
        System.out.println("Desviación estándar de las notas finales: " + formato(curso.calcularDesviacionEstandar()));
        System.out.println("Aprobados antes de la curva: " + curso.contarAprobadosOriginal());
        System.out.println("Aprobados después de la curva: " + curso.contarAprobadosFinal());
        System.out.println("Pasaron de reprobado a aprobado gracias a la curva: " + curso.contarAprobadosPorCurva());
    }

    private String formato(double valor) {
        return String.format(Locale.US, "%.2f", valor);
    }
}