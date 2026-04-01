package com.pao.laboratory06.exercise2;
import java.util.Scanner;
public class SRLColaborator extends PersoanaJuridica{
    private double cheltuieli_lunare;
    @Override
    public void citeste(Scanner in){
        nume = in.next();
        prenume = in.next();
        venit_brut_lunar = in.nextDouble();
        cheltuieli_lunare = in.nextDouble();
    }
    @Override
    public void afiseaza(){
        System.out.println("SRL: " + this.nume + " " + this.prenume + " venit net anual: " + calculeazaVenitNetAnual() + " lei");
    }
    @Override
    public String tipContract(){
        return "SRL";
    }
    @Override
    public double calculeazaVenitNetAnual(){
        return (venit_brut_lunar - cheltuieli_lunare) * 12 * 0.84;
    }
    @Override
    public TipColaborator getTip(){
        return TipColaborator.SRL;
    }
}
