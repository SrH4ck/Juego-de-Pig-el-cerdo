package beans;

import java.util.Random;

public class Dado {

    private int caras;

    public Dado(){
        this.caras = 6;
    }
    public int tirada(){
        Random random = new Random();
        return  random.nextInt(this.caras) + 1;
    }
}
