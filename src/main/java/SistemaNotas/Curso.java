package SistemaNotas;

import java.util.ArrayList;

public class Curso {
    private static final double NOTA_MAXIMA = 10;

    private ArrayList<Alumno> alumnos = new ArrayList<>();

    public void agregarAlumno(Alumno alumno) {
        alumnos.add(alumno);
    }

    public ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }

    public int cantidadAlumnos() {
        return alumnos.size();
    }

    public double calcularPromedioOriginal() {
        if (alumnos.size() == 0) {
            return 0;
        }
        double suma = 0;
        for (Alumno a : alumnos) {
            suma += a.getNotaOriginal();
        }
        return suma / alumnos.size();
    }

    // Expansión 25: nueva media después de la curva
    public double calcularPromedioFinal() {
        if (alumnos.size() == 0) {
            return 0;
        }
        double suma = 0;
        for (Alumno a : alumnos) {
            suma += a.getNotaFinal();
        }
        return suma / alumnos.size();
    }

    // Curva de puntos fijos para todos (base)
    public void aplicarCurvaPuntos(double puntos) {
        for (Alumno a : alumnos) {
            a.aplicarCurva(puntos);
        }
    }

    // Expansión 21
    public void aplicarCurvaPorcentual(double porcentaje) {
        for (Alumno a : alumnos) {
            a.aplicarCurvaPorcentual(porcentaje);
        }
    }

    // Expansión 22: la curva es lo que le falta a la nota más alta para llegar a 10
    public double aplicarCurvaMejorAlumno() {
        double mejorNota = 0;
        for (Alumno a : alumnos) {
            if (a.getNotaOriginal() > mejorNota) {
                mejorNota = a.getNotaOriginal();
            }
        }
        double puntos = NOTA_MAXIMA - mejorNota;
        aplicarCurvaPuntos(puntos);
        return puntos;
    }

    public int contarAprobadosOriginal() {
        int cantidad = 0;
        for (Alumno a : alumnos) {
            if (a.aprobabaSinCurva()) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public int contarAprobadosFinal() {
        int cantidad = 0;
        for (Alumno a : alumnos) {
            if (a.estaAprobado()) {
                cantidad++;
            }
        }
        return cantidad;
    }

    // Expansión 25: reprobaban y ahora aprueban gracias a la curva
    public int contarAprobadosPorCurva() {
        int cantidad = 0;
        for (Alumno a : alumnos) {
            if (!a.aprobabaSinCurva() && a.estaAprobado()) {
                cantidad++;
            }
        }
        return cantidad;
    }

    // Expansión 25: desviación estándar (poblacional) de las notas finales
    public double calcularDesviacionEstandar() {
        if (alumnos.size() == 0) {
            return 0;
        }
        double media = calcularPromedioFinal();
        double suma = 0;
        for (Alumno a : alumnos) {
            double diferencia = a.getNotaFinal() - media;
            suma += diferencia * diferencia;
        }
        return Math.sqrt(suma / alumnos.size());
    }
}