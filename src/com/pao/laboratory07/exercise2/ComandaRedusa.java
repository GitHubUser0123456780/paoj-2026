package com.pao.laboratory07.exercise2;

public final class ComandaRedusa extends Comanda{
    private int discountProcent;
    public ComandaRedusa(String nume, double pret,int discountProcent){
        super(nume,pret);
        this.discountProcent = discountProcent;
    }
    @Override
    public double pretFinal(){
        return pret * (1-discountProcent/100.0);
    }
    @Override
    public String descriere(){
        return "DISCOUNTED: " + this.nume + ", pret: " + String.format("%.2f",this.pretFinal()) + " lei " + "(-" + discountProcent +"%) [" + state + "]";
    }
}
