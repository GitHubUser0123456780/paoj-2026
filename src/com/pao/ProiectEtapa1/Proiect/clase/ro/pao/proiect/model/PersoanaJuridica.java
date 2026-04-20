package ro.pao.proiect.model;

/**
 * Client persoana juridica. Corespunde tabelului PERS_JURIDICA din BD.
 */
public class PersoanaJuridica extends Client {

    private int termenPlata;       // zile pana la scadenta platii
    private double limitaCredit;   // valoare maxima credit acordat

    public PersoanaJuridica() {
        super();
    }

    public PersoanaJuridica(int codClient, String nume, String codFiscal,
                             int termenPlata, double limitaCredit) {
        super(codClient, nume, codFiscal);
        this.termenPlata = termenPlata;
        this.limitaCredit = limitaCredit;
    }

    public int getTermenPlata() { return termenPlata; }
    public void setTermenPlata(int termenPlata) { this.termenPlata = termenPlata; }

    public double getLimitaCredit() { return limitaCredit; }
    public void setLimitaCredit(double limitaCredit) { this.limitaCredit = limitaCredit; }

    @Override
    public String getTipClient() {
        return "Persoana Juridica";
    }

    @Override
    public String toString() {
        return "PersoanaJuridica{" +
                "cod=" + getCodClient() +
                ", nume='" + getNume() + '\'' +
                ", cui='" + getCodFiscal() + '\'' +
                ", termenPlata=" + termenPlata + " zile" +
                ", limitaCredit=" + limitaCredit +
                '}';
    }
}
