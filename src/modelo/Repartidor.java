package modelo;

public class Repartidor {

    //Atributo
    private String nombre;

    //Constructor
    public Repartidor(String nombre) {
        this.nombre = nombre;
    }

    //Getter y Setter
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //toString
    @Override
    public String toString() {
        return nombre;
    }
}