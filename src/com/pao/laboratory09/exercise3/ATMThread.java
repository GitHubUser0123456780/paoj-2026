package com.pao.laboratory09.exercise3;

public class ATMThread extends Thread{
    private int id;
    private final CoadaTranzactii c;
    public ATMThread(int id, CoadaTranzactii c){
        this.id = id;
        this.c = c;
    }
    @Override
    public void run(){
        for(int i=1;i<=4;i++)
        {
            Tranzactie t = new Tranzactie(id*10 + i, Math.random()*1000);
            try{
                System.out.println("[ATM-" + id + "] trimite: Tranzactie #" + t.getId() + " cu suma " + t.getSuma() + " RON");
                c.adauga(t, id);
                Thread.sleep(50);
            }
            catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
}
