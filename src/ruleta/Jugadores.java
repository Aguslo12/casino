package ruleta;

public class Jugadores {

    private String nombre;

    private int dinero = 0;

    public Jugadores() {
    }

    public Jugadores(String nombre, int dinero) {
        this.nombre = nombre;
        this.dinero = dinero;
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
}
