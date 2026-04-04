package com.pao.laboratory07.exercise3;

import java.util.*;
import java.util.stream.Collectors;

import com.pao.laboratory07.exercise3.exceptions.IncorrectNoOfCommandArgumentsException;
import com.pao.laboratory07.exercise3.exceptions.IncorrectOrderInputException;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        List<Comanda> comenzi = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            String[] tokens = line.split(" ");
            if (tokens[0].equals("STANDARD")) {
                try{
                if(tokens.length != 4)
                    throw new IncorrectOrderInputException("Input invalid pentru tipul de comanda STANDARD. Metoda adaugare: STANDARD [nume_produs] [pret_produs] [nume_client].");
                String nume = tokens[1];
                double pret = Double.parseDouble(tokens[2]);
                String client = tokens[3];
                Comanda c = new ComandaStandard(nume, pret,client);
                comenzi.add(c);
                }
                catch(IncorrectOrderInputException e){
                    System.out.println(e.getMessage());
                }
            } else if (tokens[0].equals("DISCOUNTED")) {
                try{
                if(tokens.length != 5)
                    throw new IncorrectOrderInputException("Input invalid pentru tipul de comanda DISCOUNTED. Metoda adaugare: DISCOUNTED [nume_produs] [pret_produs] [procent_reducere] [nume_client].");
                String nume = tokens[1];
                double pret = Double.parseDouble(tokens[2]);
                int discount = Integer.parseInt(tokens[3]);
                String client = tokens[4];
                Comanda c = new ComandaRedusa(nume, pret, discount,client);
                comenzi.add(c);
                }
                catch(IncorrectOrderInputException e){
                    System.out.println(e.getMessage());
                }
            } else if (tokens[0].equals("GIFT")) {
                try{
                if(tokens.length != 3)
                    throw new IncorrectOrderInputException("Input invalid pentru tipul de comanda GIFT. Metoda adaugare: GIFT [nume_produs] [nume_client].");
                String nume = tokens[1];
                String client = tokens[2];
                Comanda c = new ComandaGratuita(nume,client);
                comenzi.add(c);
                }
                catch(IncorrectOrderInputException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        for (Comanda c : comenzi) {
            System.out.println(c.descriere());
        }
        System.out.println();
        boolean running = true;
        while(running){
            String new_line = sc.nextLine().trim();
            String command[] = new_line.split(" ");
            switch(command[0]){
                case "STATS" ->{
                    try{
                        if(command.length != 1)
                            throw new IncorrectNoOfCommandArgumentsException("Instructiunea STATS accepta doar un argument.");
                        Map<String,Double> avg_map = comenzi.stream().collect(Collectors.groupingBy(c -> c.getClass().getSimpleName(),Collectors.averagingDouble(Comanda::pretFinal)));
                        System.out.println("--- STATS ---");
                        System.out.println("STANDARD: medie = " + String.format("%.2f",avg_map.get("ComandaStandard")) + " lei");
                        System.out.println("DISCOUNTED: medie = " + String.format("%.2f",avg_map.get("ComandaRedusa")) + " lei");
                        System.out.println("GIFT: medie = 0.00 lei");
                        System.out.println();
                    }
                    catch (IncorrectNoOfCommandArgumentsException e){
                        System.out.println(e.getMessage());
                    }
                }
                case "FILTER" ->{
                    try{
                    if(command.length != 2)
                        throw new IncorrectNoOfCommandArgumentsException("Instructiunea FILTER accepta strict 2 argumente (FILTER [pret_filtru]).");
                    double filtru = Double.parseDouble(command[1]);
                    System.out.println("--- FILTER (>= " + filtru + ") ---");
                    comenzi.stream()
                            .filter(obj -> obj.pretFinal() >= filtru)
                            .forEach(obj -> System.out.println(obj.descriere()));
                    System.out.println();
                    }
                    catch(IncorrectNoOfCommandArgumentsException e){
                        System.out.println(e.getMessage());
                    }
                }
                case "SORT" ->{
                    try{
                    if(command.length != 1)
                            throw new IncorrectNoOfCommandArgumentsException("Instructiunea SORT accepta doar un argument.");
                    System.out.println("--- SORT (by client, then by pret) ---");
                    comenzi.stream().sorted(Comparator.comparing(Comanda::getClient).thenComparing(Comanda::getPret)).forEach(c -> System.out.println(c.descriere()));
                    System.out.println();
                    }
                    catch (IncorrectNoOfCommandArgumentsException e){
                        System.out.println(e.getMessage());
                    }
                }
                case "SPECIAL" ->{
                    try{
                        if(command.length != 1)
                            throw new IncorrectNoOfCommandArgumentsException("Instructiunea SPECIAL accepta doar un argument.");
                        System.out.println("--- SPECIAL (discount > 15%) ---");
                        comenzi.stream()
                            .filter(obj -> obj instanceof ComandaRedusa && ((ComandaRedusa)obj).getDiscount() > 15)
                            .forEach(obj -> System.out.println(obj.descriere()));
                        System.out.println();
                    }
                    catch(IncorrectNoOfCommandArgumentsException e){
                        System.out.println(e.getMessage());
                    }
                }
                case "QUIT" ->{
                    System.out.println("La revedere!");
                    sc.close();
                    running = false;
                }
                default ->{
                    System.out.println("Comanda invalida. ");
                }
            }
        }
    }
}
