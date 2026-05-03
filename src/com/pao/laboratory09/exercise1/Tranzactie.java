package com.pao.laboratory09.exercise1;

import java.io.Serializable;

public class Tranzactie implements Serializable{
    int id;
    double suma;
    String data;
    String contSursa;
    String contDestinatie;
    TipTranzactie tip;
    transient String note;
    static final long serialVersionUID = 1L;

    public Tranzactie(int id, double suma, String data,String contSursa, String contDestinatie, TipTranzactie tip, String note){
        this.id = id;
        this.suma = suma;
        this.data = data;
        this.contSursa = contSursa;
        this.contDestinatie = contDestinatie;
        this.tip = tip;
        this.note = note;
    }
    @Override
    public String toString(){
        return "[" + id + "] " + data + " " + tip + ": " + String.format("%.2f",suma) + " RON | " + contSursa + " -> " + contDestinatie;
    }
}
