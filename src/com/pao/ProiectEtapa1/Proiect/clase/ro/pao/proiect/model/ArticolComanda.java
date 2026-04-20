package ro.pao.proiect.model;

/**
 * Extinde Produs cu informatii despre linia dintr-o comanda.
 * Corespunde tabelului ART_COMANDA din baza de date.
 */
public class ArticolComanda extends Produs {

    private int nrComanda;
    private int cantitate;
    private double pretVanzare;   // pretul efectiv din comanda (poate diferi de pretLista)

    public ArticolComanda() {
        super();
    }

    public ArticolComanda(int nrComanda, int codProdus, String denumire,
                          String unitateMasura, double pretLista, Categorie categorie,
                          int cantitate, double pretVanzare) {
        super(codProdus, denumire, unitateMasura, pretLista, categorie);
        this.nrComanda = nrComanda;
        this.cantitate = cantitate;
        this.pretVanzare = pretVanzare;
    }

    /**
     * Constructor convenabil: creaza ArticolComanda dintr-un Produs existent
     */
    public ArticolComanda(int nrComanda, Produs produs, int cantitate, double pretVanzare) {
        super(produs.getCodProdus(), produs.getDenumire(),
              produs.getUnitateMasura(), produs.getPretLista(), produs.getCategorie());
        this.nrComanda = nrComanda;
        this.cantitate = cantitate;
        this.pretVanzare = pretVanzare;
    }

    public int getNrComanda() { return nrComanda; }
    public void setNrComanda(int nrComanda) { this.nrComanda = nrComanda; }

    public int getCantitate() { return cantitate; }
    public void setCantitate(int cantitate) { this.cantitate = cantitate; }

    public double getPretVanzare() { return pretVanzare; }
    public void setPretVanzare(double pretVanzare) { this.pretVanzare = pretVanzare; }

    /**
     * Valoarea liniei = cantitate * pretul de vanzare
     */
    public double calculeazaValoare() {
        return cantitate * pretVanzare;
    }

    @Override
    public String toString() {
        return "ArticolComanda{" +
                "nrComanda=" + nrComanda +
                ", produs='" + getDenumire() + "' (cod=" + getCodProdus() + ")" +
                ", cantitate=" + cantitate +
                ", pretVanzare=" + pretVanzare +
                ", valoare=" + calculeazaValoare() +
                '}';
    }
}
