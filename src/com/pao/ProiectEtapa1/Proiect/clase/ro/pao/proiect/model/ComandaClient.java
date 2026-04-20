package ro.pao.proiect.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Comanda plasata de un client.
 * Corespunde tabelului COMANDA_CLIENT din baza de date.
 *
 * Implementeaza Comparable pentru sortare dupa data (folosit in TreeSet).
 * Implementeaza equals/hashCode: doua comenzi sunt egale daca au acelasi total.
 */
public class ComandaClient implements Comparable<ComandaClient> {

    private int nrComanda;
    private LocalDate data;
    private LocalDate dataProgramare;
    private Client client;
    private String stare;    // "noua" | "confirmata" | "livrata" | "anulata"
    private List<ArticolComanda> articole;

    public ComandaClient() {
        this.articole = new ArrayList<>();
    }

    public ComandaClient(int nrComanda, LocalDate data, LocalDate dataProgramare,
                         Client client, String stare) {
        this.nrComanda = nrComanda;
        this.data = data;
        this.dataProgramare = dataProgramare;
        this.client = client;
        this.stare = stare;
        this.articole = new ArrayList<>();
    }

    // --- Getteri / Setteri ---

    public int getNrComanda() { return nrComanda; }
    public void setNrComanda(int nrComanda) { this.nrComanda = nrComanda; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public LocalDate getDataProgramare() { return dataProgramare; }
    public void setDataProgramare(LocalDate dataProgramare) { this.dataProgramare = dataProgramare; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public String getStare() { return stare; }
    public void setStare(String stare) { this.stare = stare; }

    public List<ArticolComanda> getArticole() { return articole; }
    public void setArticole(List<ArticolComanda> articole) { this.articole = articole; }

    // --- Metode functionale ---

    public void adaugaArticol(ArticolComanda articol) {
        articol.setNrComanda(this.nrComanda);
        this.articole.add(articol);
    }

    /**
     * Calculeaza suma tuturor liniilor din comanda
     */
    public double calculeazaTotal() {
        return articole.stream()
                .mapToDouble(ArticolComanda::calculeazaValoare)
                .sum();
    }

    // --- Comparable: sortare dupa data in TreeSet ---

    @Override
    public int compareTo(ComandaClient other) {
        // Sortare ascendenta dupa data; daca datele sunt egale, diferentiem dupa nrComanda
        int cmp = this.data.compareTo(other.data);
        if (cmp != 0) return cmp;
        return Integer.compare(this.nrComanda, other.nrComanda);
    }

    // --- equals / hashCode: doua comenzi sunt egale daca totalul este acelasi ---

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComandaClient other = (ComandaClient) o;
        // Doua comenzi sunt "egale" daca au aceeasi valoare totala (cerinta PAO)
        return Double.compare(this.calculeazaTotal(), other.calculeazaTotal()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(calculeazaTotal());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ComandaClient{\n");
        sb.append("  nrComanda=").append(nrComanda).append("\n");
        sb.append("  data=").append(data).append("\n");
        sb.append("  dataProgramare=").append(dataProgramare).append("\n");
        sb.append("  client=").append(client != null ? client.getNume() : "N/A").append("\n");
        sb.append("  stare='").append(stare).append("'\n");
        sb.append("  articole (").append(articole.size()).append("):\n");
        for (ArticolComanda a : articole) {
            sb.append("    - ").append(a).append("\n");
        }
        sb.append("  TOTAL: ").append(String.format("%.2f", calculeazaTotal())).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
