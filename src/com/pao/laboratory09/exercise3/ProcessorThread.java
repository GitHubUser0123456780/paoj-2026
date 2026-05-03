package com.pao.laboratory09.exercise3;

import java.time.LocalDate;

public class ProcessorThread implements Runnable{
    volatile boolean activ = true;
    private final CoadaTranzactii coada;
    int total=0;
    public ProcessorThread(CoadaTranzactii coada){
        this.coada = coada;
    }
    @Override
    public void run(){
        while(total<12)
        {
            try{
                Tranzactie t = coada.extrage();
                total++;
                System.out.println("[Processor] Factura #" + t.getId() + " - " + t.getSuma() + " RON | " + LocalDate.now());
                Thread.sleep(80);
            }
            catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
}
