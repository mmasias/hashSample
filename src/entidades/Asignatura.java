package entidades;

public class Asignatura {
    
    private String codigo;
    private String nombre;
    private int creditos;
    private Evaluacion[] evaluaciones;
    
    public Asignatura(String codigo, String nombre, int creditos, Evaluacion[] evaluaciones) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
        this.evaluaciones = evaluaciones;
    }
    
    public String getCodigo() {
        return this.codigo;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public int getCreditos() {
        return this.creditos;
    }

    public Evaluacion[] getEvaluaciones() {
        return this.evaluaciones;
    }    
    
    @Override
    public String toString() {
        return "Asignatura: " + this.nombre + " (Código: " + this.codigo + ", Créditos: " + this.creditos + ")";
    }
}