public class Carta {

    private String valor;
    private String palo;

    public Carta(String valor, String palo) {
        this.valor = valor;
        this.palo = palo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getPalo() {
        return palo;
    }

    public void setPalo(String palo) {
        this.palo = palo;
    }

    public int getValorNumerico() {
        switch (valor) {
            case "As":
                return 11;
            case "Jota", "Reina", "Rey":
                return 10;
            default:
                return Integer.parseInt(valor);
        }
    }
}
