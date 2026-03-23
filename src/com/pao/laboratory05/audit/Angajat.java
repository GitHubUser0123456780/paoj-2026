package com.pao.laboratory05.audit;

public class Angajat implements Comparable<Angajat>{
    String nume;
    Departament departament;
    Double salariu;
    public Angajat(String nume, Departament departament, Double salariu){
        this.nume = nume;
        this.departament = departament;
        this.salariu = salariu;
    }
    public String nume(){
        return this.nume;
    }
    public Departament departament(){
        return this.departament;
    }
    public Double salariu(){
        return this.salariu;
    }
    public String toString(){
        return "Angajat{nume='"+ nume() + "', departament=" + departament() + ", salariu="+ salariu() + "}";
    }
    @Override
    public int compareTo(Angajat a){
        return Double.compare(a.salariu(), this.salariu);
    }
}
