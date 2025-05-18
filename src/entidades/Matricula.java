package entidades;

public class Matricula {

    private Alumno alumno;
    private Asignatura asignatura;
    private double[] calificaciones;
    private boolean aprobado;

    public Matricula(Alumno alumno, Asignatura asignatura, double[] calificaciones) {
        this.alumno = alumno;
        this.asignatura = asignatura;
        this.calificaciones = calificaciones;
        aprobado = false;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public double[] getCalificaciones() {
        return calificaciones;
    }

    public boolean getAprobado() {
        return aprobado;
    }

    public double calcularNotaFinal() {
        double notaFinal = 0;
        Evaluacion[] evaluaciones = asignatura.getEvaluaciones();

        for (int i = 0; i < evaluaciones.length; i++) {
            notaFinal = notaFinal + calificaciones[i] * evaluaciones[i].getPeso();
        }

        aprobado = notaFinal >= 5;

        return notaFinal;
    }

    @Override
    public String toString() {
        return "Matrícula de " + alumno.getNombre() + " " + alumno.getApellido() +
                " en " + asignatura.getNombre() + " - Nota final: " + calcularNotaFinal() + " - "
                + (aprobado ? "Aprobado" : "Suspenso");
    }
}