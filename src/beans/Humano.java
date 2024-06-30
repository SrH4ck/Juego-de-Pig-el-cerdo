package beans;

public class Humano extends Jugador {
    private String nombre;

    public Humano(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
