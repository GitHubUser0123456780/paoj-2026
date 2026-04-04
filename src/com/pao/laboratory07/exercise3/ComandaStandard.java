package com.pao.laboratory07.exercise3;

public final class ComandaStandard extends Comanda{
    public ComandaStandard(String nume, double pret, String client){
        super(nume,pret,client);
    }
    @Override
    public double pretFinal(){
        return pret;
    }
    @Override
    public String descriere(){
        return "STANDARD: " +this.descriere_p1() + String.format("%.2f",this.pretFinal()) + " lei " + this.descriere_p2();
    }
}
