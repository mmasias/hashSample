package entidades;

import java.time.LocalDate;

public class Acta {
    
    protected final String codigoAsignatura;
    protected final String nombreAsignatura;
    protected final int creditosAsignatura;
    
    protected final LocalDate fecha;
    
    protected final String[] dniAlumnos;
    protected final String[] nombresAlumnos;
    protected final String[] apellidosAlumnos;
    protected final double[] notasFinales;
    protected final boolean[] estadosAprobacion;
    
    protected final String hash;
    
    public Acta(Asignatura asignatura, Matricula[] matriculas) {
        assert(asignatura.estaCerrada()) : "No se puede generar un acta para una asignatura que no está cerrada";
        
        this.codigoAsignatura = asignatura.getCodigo();
        this.nombreAsignatura = asignatura.getNombre();
        this.creditosAsignatura = asignatura.getCreditos();
        this.fecha = LocalDate.now();
        
        int count = 0;
        for (Matricula m : matriculas) {
            if (m.getAsignatura().getCodigo().equals(codigoAsignatura)) {
                count++;
            }
        }
        
        this.dniAlumnos = new String[count];
        this.nombresAlumnos = new String[count];
        this.apellidosAlumnos = new String[count];
        this.notasFinales = new double[count];
        this.estadosAprobacion = new boolean[count];
        
        int index = 0;
        for (Matricula m : matriculas) {
            if (m.getAsignatura().getCodigo().equals(codigoAsignatura)) {
                Alumno alumno = m.getAlumno();
                double notaFinal = m.calcularNotaFinal();
                
                this.dniAlumnos[index] = alumno.getDni();
                this.nombresAlumnos[index] = alumno.getNombre();
                this.apellidosAlumnos[index] = alumno.getApellido();
                this.notasFinales[index] = notaFinal;
                this.estadosAprobacion[index] = m.getAprobado();
                
                index++;
            }
        }
        
        this.hash = generarHash();
    }
    
    protected String generarHash() {
        StringBuilder hashBuilder = new StringBuilder();
        
        for (String nombre : nombresAlumnos) {
            if (nombre != null && !nombre.isEmpty()) {
                hashBuilder.append(nombre.charAt(0));
            }
        }
        
        for (String apellido : apellidosAlumnos) {
            if (apellido != null && !apellido.isEmpty()) {
                hashBuilder.append(apellido.charAt(0));
            }
        }
        
        for (String dni : dniAlumnos) {
            if (dni != null && !dni.isEmpty()) {
                hashBuilder.append(dni.charAt(dni.length() - 1));
            }
        }
        
        for (double nota : notasFinales) {
            int notaRedondeada = (int) Math.round(nota);
            hashBuilder.append(notaRedondeada);
        }
        
        for (boolean aprobado : estadosAprobacion) {
            hashBuilder.append(aprobado ? "1" : "0");
        }
        
        hashBuilder.append(codigoAsignatura);
        
        hashBuilder.append(nombreAsignatura.charAt(0));
        
        hashBuilder.append(creditosAsignatura);
        
        hashBuilder.append(fecha.getYear());
        hashBuilder.append(fecha.getMonthValue());
        
        String hash = hashBuilder.toString();
        StringBuilder codigoFinal = new StringBuilder();
        
        for (int i = 0; i < hash.length(); i++) {
            char c = hash.charAt(i);
            char desplazado = (char) (c + 3);
            codigoFinal.append(desplazado);
        }
        
        return codigoFinal.toString();
    }
    
    public boolean verificarIntegridad() {
        return this.hash.equals(generarHash());
    }
    
    public String getCodigoAsignatura() {
        return codigoAsignatura;
    }
    
    public String getNombreAsignatura() {
        return nombreAsignatura;
    }
    
    public int getCreditosAsignatura() {
        return creditosAsignatura;
    }
    
    public LocalDate getFecha() {
        return fecha;
    }
    
    public String getHash() {
        return hash;
    }
    
    public int getNumeroAlumnos() {
        return dniAlumnos.length;
    }
    
    public String getDniAlumno(int index) {
        return dniAlumnos[index];
    }
    
    public String getNombreAlumno(int index) {
        return nombresAlumnos[index];
    }
    
    public String getApellidoAlumno(int index) {
        return apellidosAlumnos[index];
    }
    
    public double getNotaFinal(int index) {
        return notasFinales[index];
    }
    
    public boolean getEstadoAprobacion(int index) {
        return estadosAprobacion[index];
    }
    
    @Override
    public String toString() {
        StringBuilder acta = new StringBuilder();
        
        acta.append("ACTA DE LA ASIGNATURA: ").append(nombreAsignatura)
            .append(" (").append(codigoAsignatura).append(")\n");
        acta.append("Créditos: ").append(creditosAsignatura).append("\n");
        acta.append("Fecha: ").append(fecha).append("\n");
        acta.append("Hash de verificación: ").append(hash).append("\n\n");
        
        acta.append("DNI          | NOMBRE                          | NOTA FINAL | ESTADO\n");
        acta.append("-------------|----------------------------------|------------|--------\n");
        
        for (int i = 0; i < dniAlumnos.length; i++) {
            String estado = estadosAprobacion[i] ? "APROBADO" : "SUSPENSO";
            
            acta.append(String.format("%-13s| %-34s| %-10.2f | %s\n", 
                    dniAlumnos[i], 
                    apellidosAlumnos[i] + ", " + nombresAlumnos[i],
                    notasFinales[i],
                    estado));
        }
        
        return acta.toString();
    }
}