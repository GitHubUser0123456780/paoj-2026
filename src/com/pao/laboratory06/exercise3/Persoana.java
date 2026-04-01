package com.pao.laboratory06.exercise3;

public abstract class Persoana {
    protected String nume;
    protected String prenume;
    protected String telefon;
    protected double sold;
    protected String user;
    protected String parola;
    public String getNume(){
        return nume;
    }
    public String getPrenume(){
        return prenume;
    }
    public String getTelefon(){
        return telefon;
    }
    public double consultareSold(){
        return sold;
    }
}
