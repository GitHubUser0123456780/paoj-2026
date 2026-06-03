package com.pao.laboratory14.exercise2;

import java.util.Scanner;
import java.util.List;

import com.pao.laboratory14.exercise2.repository.EvenimentRepository;
import com.pao.laboratory14.exercise1.TipBilet;
import com.pao.laboratory14.exercise2.model.Eveniment;

public class Main {
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        EvenimentRepository repo = new EvenimentRepository();
        repo.initSchema();
        while(sc.hasNext()){
            String[] split = sc.nextLine().split(" ");
            switch(split[0])
            {
                case "ADD":
                    String nume = split[1];
                    String data = split[2];
                    int capacitate = Integer.parseInt(split[3]);
                    TipBilet tip = TipBilet.valueOf(split[4]);
                    Eveniment ev = new Eveniment(nume, data, capacitate, tip);
                    repo.save(ev);
                    System.out.println("Adaugat: ["+ ev.getId() + "] " + ev.getNume());
                    break;
                case "LIST":
                    List<Eveniment> evenimente = repo.findAll();
                    for(Eveniment e:evenimente)
                        System.out.println(e);
                    break;
                case "DELETE":
                    int idSters = Integer.parseInt(split[1]);
                    if(repo.deleteImpl(idSters) != 0)
                        System.out.println("Sters: " + idSters);
                    else 
                        System.out.println("Nu exista: " + idSters);
                    break;
                case "COUNT":
                    System.out.println("Total: " + repo.count());
                    break;
                default:
                    break;
            }
        }
        sc.close();
    }
}
