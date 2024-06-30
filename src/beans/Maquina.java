package beans;

public class Maquina extends Jugador {
    private final String[] opcionesRespuestas ={"si","no"};

    public String getOpcionesRespuestas(int n) {
        return opcionesRespuestas[n];
    }
}
