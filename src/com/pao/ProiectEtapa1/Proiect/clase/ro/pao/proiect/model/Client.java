package ro.pao.proiect.model;

/**
 * Clasa abstracta de baza pentru clienti.
 * Extinsa de PersoanaFizica si PersoanaJuridica.
 * Corespunde tabelului CLIENT din baza de date (relatia ISA).
 */
public abstract class Client {

    private int codClient;
    private String nume;
    private String codFiscal;

    public Client() {}

    public Client(int codClient, String nume, String codFiscal) {
        this.codClient = codClient;
        this.nume = nume;
        this.codFiscal = codFiscal;
    }

    public int getCodClient() { return codClient; }
    public void setCodClient(int codClient) { this.codClient = codClient; }

    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }

    public String getCodFiscal() { return codFiscal; }
    public void setCodFiscal(String codFiscal) { this.codFiscal = codFiscal; }

    /**
     * Fiecare subclasa precizeaza tipul sau de client
     */
    public abstract String getTipClient();

    @Override
    public String toString() {
        return "Client{cod=" + codClient +
                ", nume='" + nume + '\'' +
                ", codFiscal='" + codFiscal + '\'' +
                ", tip=" + getTipClient() +
                '}';
    }
}
