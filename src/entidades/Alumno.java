package entidades;

public class Alumno {
    
    private String nombre;
    private String apellido;
    private String dni;
    
    public Alumno(String nombre, String apellido, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public String getApellido() {
        return this.apellido;
    }
    
    public String getDni() {
        return this.dni;
    }
    
    @Override
    public String toString() {
        return "Alumno: " + this.nombre + " " + this.apellido + " (DNI: " + this.dni + ")";
    }
}