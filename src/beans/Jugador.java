package beans;

public class Jugador {
    private int puntos = 0;

    public int tirarDado(Dado dado){
        return dado.tirada();
        
    }
    public void apuntarPuntos(int tirada){
        puntos +=tirada;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
