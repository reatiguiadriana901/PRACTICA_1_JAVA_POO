package SistemaNotas;

public class Alumno {
    private static final double NOTA_MAXIMA = 10;
    private static final double NOTA_APROBACION = 6;

    private String nombre;
    private double notaParcial1;   // 30%
    private double notaParcial2;   // 30%
    private double examenFinal;    // 40%
    private double notaOriginal;
    private double notaFinal;

    // Expansión 23: la nota original sale de las ponderaciones
    public Alumno(String nombre, double notaParcial1, double notaParcial2, double examenFinal) {
        this.nombre = nombre;
        this.notaParcial1 = notaParcial1;
        this.notaParcial2 = notaParcial2;
        this.examenFinal = examenFinal;
        this.notaOriginal = calcularNotaOriginal();
        this.notaFinal = notaOriginal;
    }

    public String getNombre() {
        return nombre;
    }

    public double getNotaParcial1() {
        return notaParcial1;
    }

    public double getNotaParcial2() {
        return notaParcial2;
    }

    public double getExamenFinal() {
        return examenFinal;
    }

    public double getNotaOriginal() {
        return notaOriginal;
    }

    public double getNotaFinal() {
        return notaFinal;
    }

    // Expansión 23
    public double calcularNotaOriginal() {
        return notaParcial1 * 0.30 + notaParcial2 * 0.30 + examenFinal * 0.40;
    }

    // Base: sube la nota en puntos (máx. 10). Siempre parte de la nota original,
    // así que aplicar la curva dos veces no acumula puntos.
    public void aplicarCurva(double puntos) {
        notaFinal = Math.min(notaOriginal + puntos, NOTA_MAXIMA);
    }

    // Expansión 21: ej. 10% de 5.0 es +0.5 (sin pasar de 10)
    public void aplicarCurvaPorcentual(double porcentaje) {
        double incremento = notaOriginal * porcentaje / 100;
        notaFinal = Math.min(notaOriginal + incremento, NOTA_MAXIMA);
    }

    public boolean estaAprobado() {
        return notaFinal >= NOTA_APROBACION;
    }

    // Expansión 25: sirve para saber quién aprobó gracias a la curva
    public boolean aprobabaSinCurva() {
        return notaOriginal >= NOTA_APROBACION;
    }
}