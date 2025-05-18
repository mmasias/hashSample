import entidades.*;
import utils.Console;

public class Simulacion {

    public static void main(String[] args) {
        Console console = new Console();

        Evaluacion[] evaluaciones = {
                new Evaluacion("Examen Parcial", 0.25),
                new Evaluacion("Evaluación Continua", 0.20),
                new Evaluacion("Examen Final", 0.50),
                new Evaluacion("Nota del Profesor", 0.05)
        };

        Asignatura eda2 = new Asignatura("EDA2", "Estructura de Datos y Algoritmos II", 6, evaluaciones);

        Alumno alumno1 = new Alumno("Ana", "García", "12345678A");
        Alumno alumno2 = new Alumno("Carlos", "Martínez", "23456789B");
        Alumno alumno3 = new Alumno("Elena", "Rodríguez", "34567890C");
        Alumno alumno4 = new Alumno("David", "López", "45678901D");

        double[] calificacionesAlumno1 = { 8.5, 7.0, 9.0, 10.0 };
        double[] calificacionesAlumno2 = { 3.0, 3.0, 3.5, 9.0 };
        double[] calificacionesAlumno3 = { 9.0, 9.5, 8.5, 10.0 };
        double[] calificacionesAlumno4 = { 5.0, 6.0, 4.5, 7.0 };

        Matricula matricula1 = new Matricula(alumno1, eda2, calificacionesAlumno1);
        Matricula matricula2 = new Matricula(alumno2, eda2, calificacionesAlumno2);
        Matricula matricula3 = new Matricula(alumno3, eda2, calificacionesAlumno3);
        Matricula matricula4 = new Matricula(alumno4, eda2, calificacionesAlumno4);

        console.writeln(" INFORMACIÓN DE ASIGNATURA ", Console.ForegroundColor.WHITE, Console.BackgroundColor.BLACK);
        console.writeln(eda2.toString());

        console.writeln("EVALUACIONES", Console.ForegroundColor.WHITE, Console.BackgroundColor.BLACK);
        for (Evaluacion eval : evaluaciones) {
            console.writeln("- " + eval.toString());
        }

        console.writeln("ALUMNOS MATRICULADOS", Console.ForegroundColor.WHITE, Console.BackgroundColor.BLACK);

        mostrarDetallesMatricula(console, matricula1);
        mostrarDetallesMatricula(console, matricula2);
        mostrarDetallesMatricula(console, matricula3);
        mostrarDetallesMatricula(console, matricula4);

        eda2.cerrarAsignatura();
        Matricula[] matriculas = { matricula1, matricula2, matricula3, matricula4 };
        Acta acta = new Acta(eda2, matriculas);

        console.writeln("GENERANDO ACTA ORIGINAL", Console.ForegroundColor.WHITE, Console.BackgroundColor.BLACK);
        console.writeln(acta.toString(), Console.ForegroundColor.CYAN);


        console.writeln("VERIFICANDO INTEGRIDAD DEL ACTA ORIGINAL", Console.ForegroundColor.WHITE, Console.BackgroundColor.BLACK);
        boolean integridadInicial = acta.verificarIntegridad();
        if (integridadInicial) {
            console.writeln("El acta no ha sido manipulada", Console.ForegroundColor.GREEN);
        } else {
            console.writeln("¡ALERTA! El acta ha sido manipulada", Console.ForegroundColor.RED);
        }

        console.writeln("CREANDO UNA COPIA DEL ACTA PARA MANIPULARLA...", Console.ForegroundColor.WHITE, Console.BackgroundColor.BLACK);
        ActaHackeada actaHackeada = new ActaHackeada(acta);


        console.writeln("INTENTANDO HACKEAR LA NOTA DE CARLOS...", Console.ForegroundColor.WHITE, Console.BackgroundColor.BLACK);
        actaHackeada.hackearNota(1, 9.0);

        console.writeln("ACTA DESPUÉS DE HACKEAR LA NOTA", Console.ForegroundColor.WHITE, Console.BackgroundColor.BLACK);
        console.writeln(actaHackeada.toString(), Console.ForegroundColor.CYAN);


        console.writeln("VERIFICANDO INTEGRIDAD DEL ACTA HACKEADA", Console.ForegroundColor.WHITE, Console.BackgroundColor.BLACK);
        boolean integridadTrasHackeo = actaHackeada.verificarIntegridad();
        if (integridadTrasHackeo) {
            console.writeln("El acta no ha sido manipulada", Console.ForegroundColor.GREEN);
        } else {
            console.writeln("¡ALERTA! El acta ha sido manipulada", Console.ForegroundColor.RED);
        }

        console.writeln("INTENTANDO FALSIFICAR EL HASH PARA OCULTAR LA MANIPULACIÓN...", Console.ForegroundColor.WHITE, Console.BackgroundColor.BLACK);
        actaHackeada.falsificarHash();

        console.writeln("ACTA CON HASH FALSIFICADO", Console.ForegroundColor.WHITE, Console.BackgroundColor.BLACK);
        console.writeln(actaHackeada.toString(), Console.ForegroundColor.CYAN);

        console.writeln("VERIFICANDO INTEGRIDAD DEL ACTA CON HASH FALSIFICADO", Console.ForegroundColor.WHITE, Console.BackgroundColor.BLACK);
        boolean integridadTrasFalsificacion = actaHackeada.verificarIntegridad();
        if (integridadTrasFalsificacion) {
            console.writeln("El acta parece estar intacta (¡pero fue manipulada!)", Console.ForegroundColor.GREEN);
        } else {
            console.writeln("¡ALERTA! El acta ha sido manipulada", Console.ForegroundColor.RED);
        }

        console.writeln("COMPARANDO ACTA ORIGINAL Y ACTA HACKEADA", Console.ForegroundColor.WHITE, Console.BackgroundColor.BLACK);
        console.writeln("Hash original: " + acta.getHash(), Console.ForegroundColor.GREEN);
        console.writeln("Hash falsificado: " + actaHackeada.getHash(), Console.ForegroundColor.RED);
        console.writeln("¿Son iguales? " + acta.getHash().equals(actaHackeada.getHash()), 
                        acta.getHash().equals(actaHackeada.getHash()) ? 
                        Console.ForegroundColor.GREEN : Console.ForegroundColor.RED);
    }

    private static void mostrarDetallesMatricula(Console console, Matricula matricula) {
        Alumno alumno = matricula.getAlumno();
        Asignatura asignatura = matricula.getAsignatura();
        double[] calificaciones = matricula.getCalificaciones();

        console.writeln("Alumno: " + alumno.getNombre() + " " + alumno.getApellido(), Console.ForegroundColor.GREEN);
        console.writeln("DNI: " + alumno.getDni());
        console.writeln("Asignatura: " + asignatura.getNombre());

        Evaluacion[] evaluaciones = asignatura.getEvaluaciones();

        for (int i = 0; i < evaluaciones.length; i++) {
            String evaluacionNombre = evaluaciones[i].getNombre();
            double calificacion = calificaciones[i];
            double peso = evaluaciones[i].getPeso();
            double notaPonderada = calificacion * peso;

            console.write(evaluacionNombre + ": ");
            if (calificacion >= 5.0) {
                console.write(calificacion, Console.ForegroundColor.GREEN);
            } else {
                console.write(calificacion, Console.ForegroundColor.RED);
            }
            console.writeln(" (Ponderada: " + String.format("%.2f", notaPonderada) + ")");
        }

        double notaFinal = matricula.calcularNotaFinal();
        console.write("Nota Final: ");

        if (matricula.getAprobado()) {
            console.writeln(String.format("%.2f", notaFinal), Console.ForegroundColor.GREEN);
            console.writeln("Estado: APROBADO", Console.ForegroundColor.GREEN);
        } else {
            console.writeln(String.format("%.2f", notaFinal), Console.ForegroundColor.RED);
            console.writeln("Estado: SUSPENDIDO", Console.ForegroundColor.RED);
        }
    }
}