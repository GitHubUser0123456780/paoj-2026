package com.pao.laboratory06.exercise2;
import java.util.Scanner;
public class CIMColaborator extends PersoanaFizica{
    private boolean bonus;
    @Override
    public boolean areBonus(){
        return bonus;
    }
    @Override
    public String tipContract(){
        return "CIM";
    }
    @Override
    public void afiseaza(){
        System.out.println(tipContract() +": " + this.nume + " " + this.prenume + " venit net anual: " + calculeazaVenitNetAnual()+" lei");
    }
    @Override
    public void citeste(Scanner in){
        nume = in.next();
        prenume = in.next();
        venit_brut_lunar = in.nextDouble();
        String bonus_string = in.next();
        if(bonus_string.equals("DA")) bonus = true;
        else bonus = false;
    }
    double calculeazaVenitNetAnual(){
        double rezultat_fara_bonus = venit_brut_lunar * 12 * 0.55;
        double rezultat = rezultat_fara_bonus + ((0.1 * rezultat_fara_bonus) * (areBonus()?1d:0d));
        return rezultat;
    }
    @Override
    public TipColaborator getTip(){
        return TipColaborator.CIM;
    }
}
