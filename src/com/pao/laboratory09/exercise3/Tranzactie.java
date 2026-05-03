package com.pao.laboratory09.exercise3;

public class Tranzactie {
    private int id;
    private double suma;
    public Tranzactie(int id, double suma){
        this.id = id;
        this.suma = suma;
    }
    public int getId(){
        return id;
    }
    public double getSuma(){
        return suma;
    }
}
