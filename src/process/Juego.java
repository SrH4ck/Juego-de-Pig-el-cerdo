package process;
import beans.Dado;
import beans.Humano;
import beans.Maquina;
import java.util.Random;
import java.util.Scanner;

public class Juego {

    private Humano humano;
    private Maquina maquina;
    private Dado dado;
    private final Scanner sc;
    private final Random random;

    public Juego(){
        this.random = new Random();
        this.maquina = new Maquina();
        this.dado = new Dado();
        sc = new Scanner(System.in);
    }

    public void ejecutar(){
        crearJugador();
        while (humano.getPuntos() < 100 && maquina.getPuntos() < 100){
            tiradaHumano();
            tiradaMaquina();
        }
        mostrarGanador();
    }

    private void mostrarGanador() {
        if (humano.getPuntos() >= 100){
            System.out.println("Ha ganado: " + humano.getNombre());
        }
        if (maquina.getPuntos() >= 100){
            System.out.println("Ha ganado la máquina");
        }
    }

    private void mostrarPuntosTotalesMaquina() {
        System.out.println("La máquina lleva un total de puntos: "  + maquina.getPuntos());
    }

    private void sumarPuntosMaquina(int puntos, int tirada) {
        if (tirada == 1){
            puntos = 0;
            maquina.apuntarPuntos(puntos);
        }else{
            maquina.apuntarPuntos(puntos);
        }
    }

    private void repetirTiradaMaquina(int tiradaMaquina, String seguirMaquina) {
        int puntosTurnoMaquina = 0;
        puntosTurnoMaquina += tiradaMaquina;
        while(poderTirar(seguirMaquina,tiradaMaquina)){
            tiradaMaquina = maquina.tirarDado(dado);
            System.out.println("Valor de la tirada de la máquina: " + tiradaMaquina);
            if (tiradaMaquina != 1){
                puntosTurnoMaquina += tiradaMaquina;
                int randomInt = random.nextInt(2);
                seguirMaquina = maquina.getOpcionesRespuestas(randomInt);
            }

        }
        sumarPuntosMaquina(puntosTurnoMaquina,tiradaMaquina);
        mostrarPuntosTotalesMaquina();
    }
    private void repetirTiradaHumano(int tiradaHumano,String seguirHumano) {
        int puntosTurnoHumano = 0;
        puntosTurnoHumano += tiradaHumano;
        while(poderTirar(seguirHumano,tiradaHumano)){
            tiradaHumano = humano.tirarDado(dado);
            System.out.println("El valo de la tirada de: "+humano.getNombre() + " es: " + tiradaHumano);
            if (tiradaHumano != 1){
                puntosTurnoHumano += tiradaHumano;
                System.out.println("¿Quieres seguir tirando? [ si / no ]");
                seguirHumano = sc.nextLine();
            }
        }
        sumarPuntosHumano(puntosTurnoHumano,tiradaHumano);
        mostrarPuntosTotalesHumano();
    }

    private void tiradaMaquina() {
        String seguirMaquina = "";
        int tiradaMaquina = maquina.tirarDado(dado);
        System.out.println("Valor de la tirada de la máquina: " + tiradaMaquina);
        if (tiradaMaquina != 1){
            //decidir si la maquina vuelve a tirar
            int randomInt = random.nextInt(2);
            seguirMaquina = maquina.getOpcionesRespuestas(randomInt);
        }
        repetirTiradaMaquina(tiradaMaquina, seguirMaquina);
    }

    private void tiradaHumano() {
        String seguirHumano= "";
        int tiradaHumano = humano.tirarDado(dado);
        System.out.println("El valo de la tirada de: "+humano.getNombre() + " es: " + tiradaHumano);
        if (tiradaHumano != 1){
            System.out.println("¿Quieres seguir tirando? [ si / no ]");
            seguirHumano = sc.nextLine();
        }
        repetirTiradaHumano(tiradaHumano,seguirHumano);
    }

    private void mostrarPuntosTotalesHumano() {
        System.out.println("El jugador:" + humano.getNombre() + " lleva un total de puntos: " + humano.getPuntos());
    }

    private void sumarPuntosHumano(int puntos,int tiradaHumano) {
        if (tiradaHumano == 1){
            puntos = 0;
            humano.apuntarPuntos(puntos);
        }else{
            humano.apuntarPuntos(puntos);
        }
    }

    private boolean poderTirar(String seguir, int tirada) {
        if (tirada != 1 && seguir.equals("si"))return true;
        return false;
    }

    private void crearJugador() {
        String nombreJugador;
        System.out.println("Introduce tu nombre de jugador");
        nombreJugador = sc.nextLine();
        this.humano = new Humano(nombreJugador);
    }
}
