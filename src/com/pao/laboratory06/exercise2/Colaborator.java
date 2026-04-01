package com.pao.laboratory06.exercise2;

public abstract class Colaborator implements IOperatiiCitireScriere{
    protected String nume;
    protected String prenume;
    protected double venit_brut_lunar;
    abstract double calculeazaVenitNetAnual();
    abstract TipColaborator getTip();
}
