package RegistroAcademico;

import java.util.ArrayList;

public class Estudiante {
    private String nombre;
    private ArrayList<Asignatura> asignaturas = new ArrayList<>();
    private int faltas;
    private double promedio;

    // Constructor
    public Estudiante(String nombre, int faltas) {
        this.nombre = nombre;
        this.faltas = faltas;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public int getFaltas() {
        return faltas;
    }

    public double getPromedio() {
        return promedio;
    }

    // Expansión 6: agrega una asignatura y recalcula el promedio
    public void agregarAsignatura(Asignatura asignatura) {
        asignaturas.add(asignatura);
        calcularPromedio();
    }

    // Expansión 6: promedio de todas las asignaturas
    public void calcularPromedio() {
        if (asignaturas.size() == 0) {
            promedio = 0;
            return;
        }
        double suma = 0;
        for (Asignatura a : asignaturas) {
            suma += a.getNotaFinal();
        }
        promedio = suma / asignaturas.size();
    }

    // Expansión 10: busca una asignatura por nombre (null si no existe)
    public Asignatura buscarAsignatura(String nombreAsignatura) {
        for (Asignatura a : asignaturas) {
            if (a.getNombre().equalsIgnoreCase(nombreAsignatura)) {
                return a;
            }
        }
        return null;
    }

    // Expansión 10: cambia la nota de una asignatura y recalcula el promedio
    public boolean modificarNota(String nombreAsignatura, double nuevaNota) {
        Asignatura asignatura = buscarAsignatura(nombreAsignatura);
        if (asignatura == null || nuevaNota < 0 || nuevaNota > 10) {
            return false;
        }
        asignatura.setNotaFinal(nuevaNota);
        calcularPromedio();
        return true;
    }

    // Expansión 8: aprueba si promedio >= 6 Y faltas < 10
    public boolean aprobo() {
        return promedio >= 6 && faltas < 10;
    }
}