package com.pao.laboratory07.exercise3;

import com.pao.laboratory07.exercise1.OrderState;

public abstract sealed class Comanda permits ComandaStandard, ComandaRedusa, ComandaGratuita{
    protected String nume;
    protected double pret;
    protected String client;
    protected OrderState state = OrderState.PLACED;
    public Comanda(String nume, double pret, String client){
        this.nume = nume;
        this.pret = pret;
        this.client = client;
    }
    public Comanda(String nume){
        this.nume = nume;
        pret = 0.00;
    }
    public Comanda(String nume, String client){
        this.nume = nume;
        this.client = client;
        pret = 0.00;
    }
    protected String descriere_p1(){
        return this.nume + ", pret: ";
    }
    protected String descriere_p2(){
        return "[" + this.state + "] - client: " + this.client;
    }
    public abstract double pretFinal();
    public abstract String descriere();
    public String getClient(){
        return client;
    }
    public double getPret(){
        return pret;
    }
}
