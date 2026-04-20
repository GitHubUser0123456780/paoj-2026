package ro.pao.proiect.exception;

public class ProdusNedisponibilException extends RuntimeException {

    private final int codProdus;

    public ProdusNedisponibilException(int codProdus) {
        super("Produsul cu codul " + codProdus + " nu exista in catalog.");
        this.codProdus = codProdus;
    }

    public ProdusNedisponibilException(int codProdus, String mesaj) {
        super(mesaj);
        this.codProdus = codProdus;
    }

    public int getCodProdus() { return codProdus; }
}
