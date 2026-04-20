package ro.pao.proiect.model;

/**
 * Extinde Produs cu informatii despre stocul din depozit.
 * Corespunde tabelului STOC din baza de date.
 */
public class Stoc extends Produs {

    private int stocCurent;
    private int stocRezervar;
    private int stocMinim;      // sub aceasta valoare se genereaza alerta

    public Stoc() {
        super();
    }

    public Stoc(int codProdus, String denumire, String unitateMasura,
                double pretLista, Categorie categorie,
                int stocCurent, int stocRezervar, int stocMinim) {
        super(codProdus, denumire, unitateMasura, pretLista, categorie);
        this.stocCurent = stocCurent;
        this.stocRezervar = stocRezervar;
        this.stocMinim = stocMinim;
    }

    public int getStocCurent() { return stocCurent; }
    public void setStocCurent(int stocCurent) { this.stocCurent = stocCurent; }

    public int getStocRezervar() { return stocRezervar; }
    public void setStocRezervar(int stocRezervar) { this.stocRezervar = stocRezervar; }

    public int getStocMinim() { return stocMinim; }
    public void setStocMinim(int stocMinim) { this.stocMinim = stocMinim; }

    /**
     * Stoc disponibil = stoc curent minus stocul deja rezervat pentru comenzi
     */
    public int getStocDisponibil() {
        return stocCurent - stocRezervar;
    }

    /**
     * Returneaza true daca stocul curent a scazut sub limita minima
     */
    public boolean esteSubLimita() {
        return stocCurent < stocMinim;
    }

    @Override
    public String toString() {
        return "Stoc{" +
                "produs=" + super.toString() +
                ", stocCurent=" + stocCurent +
                ", stocRezervar=" + stocRezervar +
                ", stocMinim=" + stocMinim +
                ", disponibil=" + getStocDisponibil() +
                (esteSubLimita() ? " [ALERTA STOC!]" : "") +
                '}';
    }
}
