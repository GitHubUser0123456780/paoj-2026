package com.pao.laboratory06.exercise2;
import java.util.Scanner;
public class PFAColaborator extends PersoanaFizica{
    private double cheltuieli_lunare;
    @Override
    public void citeste(Scanner in){
        this.nume = in.next();
        this.prenume = in.next();
        this.venit_brut_lunar = in.nextDouble();
        this.cheltuieli_lunare = in.nextDouble();
    }
    @Override
    public void afiseaza(){
        System.out.println("PFA: " + this.nume + " " + this.prenume + " venit net anual: " + calculeazaVenitNetAnual() + " lei");
    }
    @Override
    public String tipContract(){
        return "PFA";
    }
    @Override
    public double calculeazaVenitNetAnual(){
        final double salariu_minim_brut = 4050; 
        double venit_net = (venit_brut_lunar - cheltuieli_lunare) * 12;
        double impozit = 0.1*venit_net;
        double CASS =0;
        if(venit_net < 6 * salariu_minim_brut)
            CASS = 0.1*6*salariu_minim_brut;
        else if(venit_net > 72*salariu_minim_brut)
            CASS = 0.1 * (72*salariu_minim_brut);
        else CASS = 0.1*venit_net;
        double CAS = 0;
        if (venit_net < 12 * salariu_minim_brut)
            CAS = 0;
        else if(venit_net > 24 * salariu_minim_brut)
            CAS = 0.25 * 24 * salariu_minim_brut;
        else CAS  = 0.25 * 12 * salariu_minim_brut;
        return venit_net - impozit - CASS - CAS;
    }
    @Override
    public TipColaborator getTip(){
        return TipColaborator.PFA;
    }
}
