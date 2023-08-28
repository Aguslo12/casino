import java.util.HashSet;
import java.util.Set;

public class Jugadores {

    private String nombre;

    private int dinero;

    private Set<Carta> mano;


    public Jugadores() {
        this.mano = new HashSet<>();
    }

    public Jugadores(String nombre, int dinero) {
        this.nombre = nombre;
        this.dinero = dinero;
        this.mano = new HashSet<>();
    }

    public Set<Carta> getMano() {
        return mano;
    }

    public void setMano(Set<Carta> mano) {
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
}