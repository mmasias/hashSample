package entidades;

public class Evaluacion {
    
    private String nombre;
    private double peso;
    
    public Evaluacion(String nombre, double peso) {
        this.nombre = nombre;
        this.peso = peso;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public double getPeso() {
        return this.peso;
    }
    
    @Override
    public String toString() {
        return this.nombre + " (" + (this.peso * 100) + "%)";
    }
}