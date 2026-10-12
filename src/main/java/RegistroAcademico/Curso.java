package RegistroAcademico;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Curso {
    private ArrayList<Estudiante> estudiantes = new ArrayList<>();

    public void agregarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

    public int cantidadEstudiantes() {
        return estudiantes.size();
    }

    // Expansión 7: estadísticas del curso
    public double calcularPromedioGeneral() {
        if (estudiantes.size() == 0) {
            return 0;
        }
        double suma = 0;
        for (Estudiante e : estudiantes) {
            suma += e.getPromedio();
        }
        return suma / estudiantes.size();
    }

    public int contarAprobados() {
        int cantidad = 0;
        for (Estudiante e : estudiantes) {
            if (e.aprobo()) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public int contarReprobados() {
        return estudiantes.size() - contarAprobados();
    }

    public Estudiante buscarMayorPromedio() {
        if (estudiantes.size() == 0) {
            return null;
        }
        Estudiante mayor = estudiantes.get(0);
        for (int i = 1; i < estudiantes.size(); i++) {
            if (estudiantes.get(i).getPromedio() > mayor.getPromedio()) {
                mayor = estudiantes.get(i);
            }
        }
        return mayor;
    }

    public Estudiante buscarMenorPromedio() {
        if (estudiantes.size() == 0) {
            return null;
        }
        Estudiante menor = estudiantes.get(0);
        for (int i = 1; i < estudiantes.size(); i++) {
            if (estudiantes.get(i).getPromedio() < menor.getPromedio()) {
                menor = estudiantes.get(i);
            }
        }
        return menor;
    }

    // Expansión 10: búsqueda y modificación
    public Estudiante buscarEstudiante(String nombre) {
        for (Estudiante e : estudiantes) {
            if (e.getNombre().equalsIgnoreCase(nombre)) {
                return e;
            }
        }
        return null;
    }

    public boolean modificarNota(String nombreEstudiante, String nombreAsignatura, double nuevaNota) {
        Estudiante estudiante = buscarEstudiante(nombreEstudiante);
        if (estudiante == null) {
            return false;
        }
        return estudiante.modificarNota(nombreAsignatura, nuevaNota);
    }

    // Expansión 9: guarda el reporte en un archivo .txt (true si se pudo)
    public boolean guardarReporte(String nombreArchivo) {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(nombreArchivo))) {
            escritor.println("REPORTE DEL CURSO");
            escritor.println("=================");
            escritor.println();
            escritor.println("LISTADO DE ESTUDIANTES");

            for (Estudiante e : estudiantes) {
                String estado = e.aprobo() ? "Aprobado" : "Reprobado";
                escritor.println("- " + e.getNombre()
                        + " | Promedio: " + e.getPromedio()
                        + " | Faltas: " + e.getFaltas()
                        + " | " + estado);
            }

            escritor.println();
            escritor.println("ESTADISTICAS GENERALES");
            escritor.println("Promedio general: " + calcularPromedioGeneral());
            escritor.println("Aprobados: " + contarAprobados());
            escritor.println("Reprobados: " + contarReprobados());

            Estudiante mayor = buscarMayorPromedio();
            Estudiante menor = buscarMenorPromedio();
            if (mayor != null) {
                escritor.println("Mayor promedio: " + mayor.getNombre() + " (" + mayor.getPromedio() + ")");
                escritor.println("Menor promedio: " + menor.getNombre() + " (" + menor.getPromedio() + ")");
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}