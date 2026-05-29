package com.pao.laboratory10.exercise1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        LinkedList<Tranzactie> tranzactii = new LinkedList<>();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext())
        {
            String[] split = sc.nextLine().split(" ");
            switch(split[0]){
                case "ENQUEUE":{
                    int id = Integer.parseInt(split[1]);
                    double suma = Double.parseDouble(split[2]);
                    String data = split[3];
                    TipTranzactie tip = TipTranzactie.valueOf(split[4]);
                    Tranzactie t = new Tranzactie(id,suma,data,tip);
                    tranzactii.addLast(t);
                    break;
                }
                case "DEQUEUE":{
                    if (tranzactii.size() == 0)
                        System.out.println("Coada goala.");
                    else{
                        System.out.println("Procesat: " + tranzactii.removeFirst());
                    }
                    break;
                }
                case "PUSH":{
                    int id = Integer.parseInt(split[1]);
                    double suma = Double.parseDouble(split[2]);
                    String data = split[3];
                    TipTranzactie tip = TipTranzactie.valueOf(split[4]);
                    Tranzactie t = new Tranzactie(id,suma,data,tip);
                    tranzactii.addFirst(t);
                    break;
                }
                case "POP":{
                    if (tranzactii.size() == 0)
                        System.out.println("Coada goala.");
                    else{
                        System.out.println("Extras: " + tranzactii.removeFirst());
                    }
                    break;
                }
                case "REMOVE_DEBIT":{
                    int nrTranzactii = 0;
                    Iterator<Tranzactie> it = tranzactii.iterator();
                    while(it.hasNext()){
                        Tranzactie t = it.next();
                        if(t.getTip() == TipTranzactie.DEBIT){
                            it.remove();
                            nrTranzactii++;
                        }
                    }
                    System.out.println("Eliminat " + nrTranzactii + " tranzactii DEBIT.");
                    break;
                }
                case "REMOVE_BELOW":{
                    int nrTranzactii = 0;
                    double threshold = Double.parseDouble(split[1]);
                    Iterator<Tranzactie> it = tranzactii.iterator();
                    while(it.hasNext()){
                        Tranzactie t = it.next();
                        if(t.getSuma() < threshold){
                            it.remove();
                            nrTranzactii++;
                        }
                    }
                    System.out.println("Eliminat " + nrTranzactii + " tranzactii sub " + String.format("%.2f",threshold) + " RON.");
                    break;
                }
                case "PRINT":{
                    Iterator<Tranzactie> it = tranzactii.iterator();
                    while(it.hasNext()){
                        Tranzactie t = it.next();
                        System.out.println(t);
                    }
                    break;
                }
                case "SIZE":{
                    System.out.println("Dimensiune coada: " + tranzactii.size());
                }
            }
        }
        sc.close();
        // TODO: Implementează conform Readme.md
        //
        // Folosește LinkedList<Tranzactie> ca structură internă.
        // Citește comenzi din stdin până la EOF:
        //
        //   ENQUEUE id suma data tip   → addLast  (niciun output)
        //   DEQUEUE                    → removeFirst sau "Coada goala."
        //                                format: "Procesat: [id] data tip: suma RON"
        //   PUSH id suma data tip      → addFirst  (niciun output)
        //   POP                        → removeFirst sau "Coada goala."
        //                                format: "Extras: [id] data tip: suma RON"
        //   REMOVE_DEBIT               → Iterator.remove() pe toate DEBIT
        //                                afișează "Eliminat N tranzactii DEBIT."
        //   REMOVE_BELOW threshold     → Iterator.remove() pe suma < threshold
        //                                afișează "Eliminat N tranzactii sub threshold RON."
        //   PRINT                      → afișează toate, câte una pe linie
        //   SIZE                       → "Dimensiune coada: N"
        //
        // Format linie tranzacție: [id] data tip: suma RON
        //   Ex: [1] 2024-01-10 CREDIT: 500.00 RON
    }
}
