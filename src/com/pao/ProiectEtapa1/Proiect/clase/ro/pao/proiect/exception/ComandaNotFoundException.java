package ro.pao.proiect.exception;

public class ComandaNotFoundException extends RuntimeException {

    private final int nrComanda;

    public ComandaNotFoundException(int nrComanda) {
        super("Comanda cu numarul " + nrComanda + " nu a fost gasita.");
        this.nrComanda = nrComanda;
    }

    public int getNrComanda() { return nrComanda; }
}
