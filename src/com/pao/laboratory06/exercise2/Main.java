package com.pao.laboratory06.exercise2;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Colaborator> colaboratori = new ArrayList<>();
        for(int i=1;i<=n;i++){
            String tip = scanner.next();
            switch(tip){
                case "CIM":
                    {
                        CIMColaborator obj = new CIMColaborator();
                        obj.citeste(scanner);
                        colaboratori.add(obj);
                        break;
                    }
                case "PFA":
                    {
                        PFAColaborator obj = new PFAColaborator();
                        obj.citeste(scanner);
                        colaboratori.add(obj);
                        break;
                    }
                case "SRL":
                    {
                        SRLColaborator obj = new SRLColaborator();
                        obj.citeste(scanner);
                        colaboratori.add(obj);
                        break;
                    }
            }
        }
        //1. Sorteaza dupa venit net anual descrescator
        for(TipColaborator tip:TipColaborator.values())
        {
            colaboratori.stream()
                .filter(obj -> obj.getTip() == tip)
                .sorted((obj1,obj2) -> Double.compare(obj2.calculeazaVenitNetAnual(),obj1.calculeazaVenitNetAnual()))
                .forEach(Colaborator::afiseaza);
        }
        //2.Colaboratorul cu venit net maxim
        Colaborator max = colaboratori.stream().max(Comparator.comparingDouble(Colaborator::calculeazaVenitNetAnual)).orElse(null);
        if(max!=null)
        {
            System.out.println("Colaboratorul cu venit net anual maxim: ");
            max.afiseaza();
        }
        //3.Afiseaza doar persoane juridice
        System.out.println("\nColaboratorii persoane juridice:\n");
        colaboratori.stream().filter(obj -> obj instanceof PersoanaJuridica).forEach(Colaborator::afiseaza);
        //4.Pt fiercare tip suma totala si nr colaboratori
        Map<TipColaborator, Double> suma = new EnumMap<>(TipColaborator.class);
        Map<TipColaborator, Integer> numar = new EnumMap<>(TipColaborator.class);
        for(TipColaborator t:TipColaborator.values())
        {
            suma.put(t,0.0);
            numar.put(t,0);
        }
        for(Colaborator c:colaboratori)
        {
            suma.put(c.getTip(),suma.get(c.getTip()) + c.calculeazaVenitNetAnual());
            numar.put(c.getTip(),numar.get(c.getTip()) + 1);
        }
        System.out.println("\n--Suma si nr pe tipuri--\n");
        for(TipColaborator t:TipColaborator.values())
            System.out.println(t + ": suma = " + suma.get(t) + "lei, numar = " + numar.get(t)+'\n');
    }
}//venit net = 120 000, impozit = 0.1*120 000 = 12 000
// 0.1*120 000 = 12 000 CASS
// 24 300 CAS