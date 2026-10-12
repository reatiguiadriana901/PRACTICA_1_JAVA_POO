package RegistroAcademico;

import java.util.Scanner;

public class GestionEstudiante {
    private Curso curso = new Curso();
    private Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        int opcion = 0;
        while (opcion != 6) {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarEstudiantes();
                    break;
                case 2:
                    mostrarEstadisticas();
                    break;
                case 3:
                    buscarEstudiante();
                    break;
                case 4:
                    modificarNota();
                    break;
                case 5:
                    guardarReporte();
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
        System.out.println("\n===== REGISTRO ACADÉMICO =====");
        System.out.println("1. Registrar estudiantes");
        System.out.println("2. Ver estadísticas del curso");
        System.out.println("3. Buscar estudiante");
        System.out.println("4. Modificar una nota");
        System.out.println("5. Guardar reporte en archivo");
        System.out.println("6. Salir");
        System.out.print("Elija una opción: ");
    }

    private void registrarEstudiantes() {
        int cantidad;
        do {
            System.out.print("Ingrese la cantidad de estudiantes: ");
            cantidad = scanner.nextInt();
            scanner.nextLine();
        } while (cantidad <= 0);

        for (int i = 0; i < cantidad; i++) {
            System.out.println("\n--- Estudiante " + (i + 1) + " ---");
            curso.agregarEstudiante(crearEstudiante());
        }
    }

    private Estudiante crearEstudiante() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Número de faltas: ");
        int faltas = scanner.nextInt();
        scanner.nextLine();

        Estudiante estudiante = new Estudiante(nombre, faltas);
        pedirAsignaturas(estudiante);
        return estudiante;
    }

    private void pedirAsignaturas(Estudiante estudiante) {
        int cantidad;
        do {
            System.out.print("¿Cuántas asignaturas tiene?: ");
            cantidad = scanner.nextInt();
            scanner.nextLine();
        } while (cantidad <= 0);

        for (int i = 0; i < cantidad; i++) {
            System.out.print("Nombre de la asignatura " + (i + 1) + ": ");
            String nombre = scanner.nextLine();

            System.out.print("Nota final: ");
            double nota = scanner.nextDouble();
            scanner.nextLine();

            estudiante.agregarAsignatura(new Asignatura(nombre, nota));
        }
    }

    private void mostrarEstadisticas() {
        if (curso.cantidadEstudiantes() == 0) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }

        Estudiante mayor = curso.buscarMayorPromedio();
        Estudiante menor = curso.buscarMenorPromedio();

        System.out.println("\n===============================");
        System.out.println("     ESTADISTICAS DEL GRUPO    ");
        System.out.println("===============================");
        System.out.println("Promedio general: " + curso.calcularPromedioGeneral());
        System.out.println("Aprobados: " + curso.contarAprobados());
        System.out.println("Reprobados: " + curso.contarReprobados());
        System.out.println("Mayor promedio: " + mayor.getNombre() + " (" + mayor.getPromedio() + ")");
        System.out.println("Menor promedio: " + menor.getNombre() + " (" + menor.getPromedio() + ")");
    }

    private void buscarEstudiante() {
        System.out.print("Nombre del estudiante: ");
        String nombre = scanner.nextLine();

        Estudiante estudiante = curso.buscarEstudiante(nombre);
        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        System.out.println("\nEstudiante: " + estudiante.getNombre());
        System.out.println("Faltas: " + estudiante.getFaltas());
        System.out.println("Promedio: " + estudiante.getPromedio());
        System.out.println("Estado: " + (estudiante.aprobo() ? "Aprobado" : "Reprobado"));
        System.out.println("Asignaturas:");
        for (Asignatura a : estudiante.getAsignaturas()) {
            System.out.println("  - " + a.getNombre() + ": " + a.getNotaFinal());
        }
    }

    private void modificarNota() {
        System.out.print("Nombre del estudiante: ");
        String nombreEstudiante = scanner.nextLine();

        System.out.print("Nombre de la asignatura: ");
        String nombreAsignatura = scanner.nextLine();

        System.out.print("Nueva nota (0 a 10): ");
        double nuevaNota = scanner.nextDouble();
        scanner.nextLine();

        if (curso.modificarNota(nombreEstudiante, nombreAsignatura, nuevaNota)) {
            System.out.println("Nota modificada correctamente.");
        } else {
            System.out.println("No se pudo modificar: revise el estudiante, la asignatura y que la nota esté entre 0 y 10.");
        }
    }

    private void guardarReporte() {
        if (curso.cantidadEstudiantes() == 0) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }

        System.out.print("Nombre del archivo (ej. reporte.txt): ");
        String nombreArchivo = scanner.nextLine();

        if (curso.guardarReporte(nombreArchivo)) {
            System.out.println("Reporte guardado en " + nombreArchivo);
        } else {
            System.out.println("No se pudo guardar el reporte.");
        }
    }
}