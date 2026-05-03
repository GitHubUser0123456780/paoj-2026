package com.pao.laboratory09.exercise3;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        // Vezi Readme.md pentru cerințe
        CoadaTranzactii coada = new CoadaTranzactii();
        ATMThread atm1 = new ATMThread(1, coada);
        ATMThread atm2 = new ATMThread(2, coada);
        ATMThread atm3 = new ATMThread(3, coada);
        ProcessorThread pt = new ProcessorThread(coada);
        atm1.start();
        atm2.start();
        atm3.start();
        Thread newThread = new Thread(pt);
        newThread.start();
        atm1.join();
        atm2.join();
        atm3.join();
        pt.activ = false;
        synchronized (coada){
            coada.notifyAll();
        }
        newThread.join();
        System.out.println("Tranzactii procesate: " + pt.total);
    }
}
