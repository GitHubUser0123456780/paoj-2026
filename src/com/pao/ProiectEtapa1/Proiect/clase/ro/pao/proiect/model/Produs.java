package ro.pao.proiect.model;

/**
 * Clasa de baza pentru produsele din sistem.
 * Este extinsa de Stoc si ArticolComanda.
 */
public class Produs {

    private int codProdus;
    private String denumire;
    private String unitateMasura;
    private double pretLista;
    private Categorie categorie;

    public Produs() {}

    public Produs(int codProdus, String denumire, String unitateMasura,
                  double pretLista, Categorie categorie) {
        this.codProdus = codProdus;
        this.denumire = denumire;
        this.unitateMasura = unitateMasura;
        this.pretLista = pretLista;
        this.categorie = categorie;
    }

    public int getCodProdus() { return codProdus; }
    public void setCodProdus(int codProdus) { this.codProdus = codProdus; }

    public String getDenumire() { return denumire; }
    public void setDenumire(String denumire) { this.denumire = denumire; }

    public String getUnitateMasura() { return unitateMasura; }
    public void setUnitateMasura(String unitateMasura) { this.unitateMasura = unitateMasura; }

    public double getPretLista() { return pretLista; }
    public void setPretLista(double pretLista) { this.pretLista = pretLista; }

    public Categorie getCategorie() { return categorie; }
    public void setCategorie(Categorie categorie) { this.categorie = categorie; }

    @Override
    public String toString() {
        return "Produs{" +
                "cod=" + codProdus +
                ", denumire='" + denumire + '\'' +
                ", um='" + unitateMasura + '\'' +
                ", pretLista=" + pretLista +
                ", categorie=" + (categorie != null ? categorie.getDenumire() : "N/A") +
                '}';
    }
}
