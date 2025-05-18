package entidades;

import java.lang.reflect.Field;

public class ActaHackeada extends Acta {
    
    public ActaHackeada(Acta acta) {
        super(
            new Asignatura(acta.getCodigoAsignatura(), acta.getNombreAsignatura(), 
                           acta.getCreditosAsignatura(), new Evaluacion[0]) {
                @Override
                public boolean estaCerrada() {
                    return true;
                }
            },
            new Matricula[0]
        );
        
        copiarDatos(acta);
    }
    
    private void copiarDatos(Acta acta) {
        try {
            copiarCampo(acta, "codigoAsignatura");
            copiarCampo(acta, "nombreAsignatura");
            copiarCampo(acta, "creditosAsignatura");
            copiarCampo(acta, "fecha");
            copiarCampo(acta, "dniAlumnos");
            copiarCampo(acta, "nombresAlumnos");
            copiarCampo(acta, "apellidosAlumnos");
            copiarCampo(acta, "notasFinales");
            copiarCampo(acta, "estadosAprobacion");
            copiarCampo(acta, "hash");
        } catch (Exception e) {
            System.out.println("Error al copiar datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void copiarCampo(Acta acta, String nombreCampo) throws Exception {
        Field campoOrigen = Acta.class.getDeclaredField(nombreCampo);
        campoOrigen.setAccessible(true);
        Object valor = campoOrigen.get(acta);
        
        Field campoDestino = Acta.class.getDeclaredField(nombreCampo);
        campoDestino.setAccessible(true);
        campoDestino.set(this, valor);
    }
    
    public void hackearNota(int indiceAlumno, double nuevaNota) {
        if (indiceAlumno < 0 || indiceAlumno >= notasFinales.length) {
            System.out.println("Índice de alumno fuera de rango");
            return;
        }
        
        try {
            Field field = Acta.class.getDeclaredField("notasFinales");
            field.setAccessible(true);
            double[] notas = (double[]) field.get(this);
            notas[indiceAlumno] = nuevaNota;
            
            boolean nuevoEstado = nuevaNota >= 5.0;
            if (estadosAprobacion[indiceAlumno] != nuevoEstado) {
                Field fieldEstados = Acta.class.getDeclaredField("estadosAprobacion");
                fieldEstados.setAccessible(true);
                boolean[] estados = (boolean[]) fieldEstados.get(this);
                estados[indiceAlumno] = nuevoEstado;
            }
            
            System.out.println("¡Nota hackeada con éxito!");
            
        } catch (Exception e) {
            System.out.println("Error al hackear la nota: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void hackearNombre(int indiceAlumno, String nuevoNombre) {
        if (indiceAlumno < 0 || indiceAlumno >= nombresAlumnos.length) {
            System.out.println("Índice de alumno fuera de rango");
            return;
        }
        
        try {
            Field field = Acta.class.getDeclaredField("nombresAlumnos");
            field.setAccessible(true);
            String[] nombres = (String[]) field.get(this);
            nombres[indiceAlumno] = nuevoNombre;
            
            System.out.println("¡Nombre hackeado con éxito!");
            
        } catch (Exception e) {
            System.out.println("Error al hackear el nombre: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void hackearApellido(int indiceAlumno, String nuevoApellido) {
        if (indiceAlumno < 0 || indiceAlumno >= apellidosAlumnos.length) {
            System.out.println("Índice de alumno fuera de rango");
            return;
        }
        
        try {
            Field field = Acta.class.getDeclaredField("apellidosAlumnos");
            field.setAccessible(true);
            String[] apellidos = (String[]) field.get(this);
            apellidos[indiceAlumno] = nuevoApellido;
            
            System.out.println("¡Apellido hackeado con éxito!");
            
        } catch (Exception e) {
            System.out.println("Error al hackear el apellido: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void hackearNombreAsignatura(String nuevoNombre) {
        try {
            Field field = Acta.class.getDeclaredField("nombreAsignatura");
            field.setAccessible(true);
            field.set(this, nuevoNombre);
            
            System.out.println("¡Nombre de asignatura hackeado con éxito!");
            
        } catch (Exception e) {
            System.out.println("Error al hackear el nombre de la asignatura: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void falsificarHash() {
        try {
            String nuevoHash = generarHash();
            
            Field field = Acta.class.getDeclaredField("hash");
            field.setAccessible(true);
            field.set(this, nuevoHash);
            
            System.out.println("¡Hash falsificado con éxito! Ahora la manipulación es indetectable.");
            
        } catch (Exception e) {
            System.out.println("Error al falsificar el hash: " + e.getMessage());
            e.printStackTrace();
        }
    }
}