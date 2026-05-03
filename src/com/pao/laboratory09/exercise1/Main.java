package com.pao.laboratory09.exercise1;

import java.io.*;
import java.util.*;

public class Main {
    private static final String OUTPUT_FILE = "src/com/pao/laboratory09/exercise1/output/lab09_ex1.ser";

    public static void main(String[] args) throws Exception {
        // TODO: Implementează conform Readme.md
        //
        // 1. Citește N din stdin, apoi cele N tranzacții (id suma data contSursa contDestinatie tip)
        // 2. Setează câmpul note = "procesat" pe fiecare tranzacție înainte de serializare
        // 3. Serializează lista de tranzacții în OUTPUT_FILE cu ObjectOutputStream (try-with-resources)
        // 4. Deserializează lista din OUTPUT_FILE cu ObjectInputStream (try-with-resources)
        // 5. Procesează comenzile din stdin până la EOF:
        //    - LIST          → afișează toate tranzacțiile, câte una pe linie
        //    - FILTER yyyy-MM → afișează tranzacțiile cu data care începe cu yyyy-MM
        //                       sau "Niciun rezultat." dacă nu există
        //    - NOTE id        → afișează "NOTE[id]: <valoarea câmpului note>"
        //                       sau "NOTE[id]: not found" dacă id-ul nu există
        //
        // Format linie tranzacție:
        //   [id] data tip: suma RON | contSursa -> contDestinatie
        //   Ex: [1] 2024-01-15 CREDIT: 1500.00 RON | RO01SRC1 -> RO01DST1
        List<Tranzactie> tranzactii = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        for(int i=0;i<n;i++)
        {
            String newLine = sc.nextLine();
            String[] split = newLine.split(" ");
            int id = Integer.parseInt(split[0]);
            double suma = Double.parseDouble(split[1]);
            String data = split[2];
            String contSursa = split[3];
            String contDestinatie = split[4];
            TipTranzactie tip = TipTranzactie.valueOf(split[5]);
            Tranzactie tranzactieNoua = new Tranzactie(id,suma,data,contSursa,contDestinatie,tip,"procesat");
            tranzactii.add(tranzactieNoua);
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(OUTPUT_FILE))){
            for(Tranzactie t:tranzactii){
                oos.writeObject(t);
            }
        }
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(OUTPUT_FILE))){
            for(int i = 0;i<tranzactii.size();i++)
            {
                Tranzactie t = (Tranzactie)ois.readObject();
                tranzactii.set(i,t);
            }
        }
        while(sc.hasNext())
        {
            String command = sc.nextLine();
            String[] split = command.split(" ");
            switch(split[0]){
                case "LIST":
                    for(Tranzactie t:tranzactii)
                        System.out.println(t);
                    break;
                case "FILTER":
                    String date_filter = split[1];
                    Boolean found_date = false;
                    for(Tranzactie t:tranzactii){
                        if(date_filter.equals(t.data.substring(0,7)))
                        {
                            found_date = true;
                            System.out.println(t);
                        }
                    }
                    if(!found_date)
                        System.out.println("Niciun rezultat.");
                    break;
                case "NOTE":
                    Tranzactie t = tranzactii.stream()
                                   .filter(obj -> obj.id == Integer.parseInt(split[1]) )
                                   .findFirst()
                                   .orElse(null);
                    if(t!=null)
                        System.out.println("NOTE[" + split[1] + "]: " + t.note);
                    else
                        System.out.println("NOTE[" + split[1] + "]: not found");
                    break;
            }
        }
    }
}
