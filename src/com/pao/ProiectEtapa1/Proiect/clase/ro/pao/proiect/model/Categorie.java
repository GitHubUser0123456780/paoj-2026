package ro.pao.proiect.model;

public class Categorie {

    private int codCategorie;
    private String denumire;
    private String descriere;

    public Categorie() {}

    public Categorie(int codCategorie, String denumire, String descriere) {
        this.codCategorie = codCategorie;
        this.denumire = denumire;
        this.descriere = descriere;
    }

    public int getCodCategorie() { return codCategorie; }
    public void setCodCategorie(int codCategorie) { this.codCategorie = codCategorie; }

    public String getDenumire() { return denumire; }
    public void setDenumire(String denumire) { this.denumire = denumire; }

    public String getDescriere() { return descriere; }
    public void setDescriere(String descriere) { this.descriere = descriere; }

    @Override
    public String toString() {
        return "Categorie{" +
                "cod=" + codCategorie +
                ", denumire='" + denumire + '\'' +
                ", descriere='" + descriere + '\'' +
                '}';
    }
}
