package ro.pao.proiect.model;

/**
 * Client persoana fizica. Corespunde tabelului PERS_FIZICA din BD.
 */
public class PersoanaFizica extends Client {

    private double bonus;    // procent bonus acordat clientului

    public PersoanaFizica() {
        super();
    }

    public PersoanaFizica(int codClient, String nume, String codFiscal, double bonus) {
        super(codClient, nume, codFiscal);
        this.bonus = bonus;
    }

    public double getBonus() { return bonus; }
    public void setBonus(double bonus) { this.bonus = bonus; }

    @Override
    public String getTipClient() {
        return "Persoana Fizica";
    }

    @Override
    public String toString() {
        return "PersoanaFizica{" +
                "cod=" + getCodClient() +
                ", nume='" + getNume() + '\'' +
                ", cnp='" + getCodFiscal() + '\'' +
                ", bonus=" + bonus + "%" +
                '}';
    }
}
