package blackJack;

import java.util.HashSet;

public class Jugadores {

    private String nombre;

    private int dinero;

    private HashSet<Carta> mano;


    public Jugadores() {
        this.mano = new HashSet<>();
    }

    public Jugadores(String nombre, int dinero) {
        this.nombre = nombre;
        this.dinero = dinero;
        this.mano = new HashSet<>();
    }

    public HashSet<Carta> getMano() {
        return mano;
    }

    public void setMano(HashSet<Carta> mano) {
        this.mano = mano;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    public void agregarMano(Carta carta){
        mano.add(carta);
    }

    public void reinicarMano(){
        mano.clear();
    }
}