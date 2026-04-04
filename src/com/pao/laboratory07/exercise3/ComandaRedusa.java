package com.pao.laboratory07.exercise3;

public final class ComandaRedusa extends Comanda{
    private int discountProcent;
    public ComandaRedusa(String nume, double pret,int discountProcent, String client){
        super(nume,pret,client);
        this.discountProcent = discountProcent;
    }
    @Override
    public double pretFinal(){
        return pret * (1-discountProcent/100.0);
    }
    @Override
    public String descriere(){
        return "DISCOUNTED: " + this.descriere_p1() + String.format("%.2f",this.pretFinal()) + " lei (-" +discountProcent+"%) "+ this.descriere_p2();
    }
    public int getDiscount(){
        return discountProcent;
    }
}
