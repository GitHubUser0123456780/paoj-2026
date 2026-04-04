package com.pao.laboratory07.exercise2;

import com.pao.laboratory07.exercise1.OrderState;

public abstract sealed class Comanda permits ComandaStandard, ComandaRedusa, ComandaGratuita{
    protected String nume;
    protected double pret;
    protected OrderState state = OrderState.PLACED;
    public Comanda(String nume, double pret){
        this.nume = nume;
        this.pret = pret;
    }
    public Comanda(String nume){
        this.nume = nume;
        pret = 0.00;
    }
    public abstract double pretFinal();
    public abstract String descriere();
}
