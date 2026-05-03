package com.pao.laboratory09.exercise3;

import java.util.*;

public class CoadaTranzactii {
    private static final int CAPACITATE_MAXIMA = 5;
    private Queue<Tranzactie> coadaTranzactii = new ArrayDeque<>();
    synchronized public void adauga(Tranzactie t, int idATM) throws InterruptedException{
        while(coadaTranzactii.size() == CAPACITATE_MAXIMA)
        {
            System.out.println("[ATM-" + idATM + "] astept loc...");
            wait();
        }
        coadaTranzactii.add(t);
        notifyAll();
    }
    synchronized public Tranzactie extrage() throws InterruptedException{
        while(coadaTranzactii.isEmpty())
            wait();
        Tranzactie t = coadaTranzactii.remove();
        notifyAll();
        return t;
    }
}
