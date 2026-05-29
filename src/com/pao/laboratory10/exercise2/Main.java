package com.pao.laboratory10.exercise2;

import com.pao.laboratory10.exercise1.Tranzactie;
import com.pao.laboratory10.exercise1.TipTranzactie;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int nrTranzactii;
        Scanner sc = new Scanner(System.in);
        nrTranzactii = sc.nextInt();
        sc.nextLine();
        List<Tranzactie> tranzactii = new ArrayList<>(nrTranzactii);
        for(int i=0;i<nrTranzactii;i++)
        {
            String[] split = sc.nextLine().split(" ");
            Tranzactie t = new Tranzactie(Integer.parseInt(split[0]),Double.parseDouble(split[1]),split[2],TipTranzactie.valueOf(split[3]));
            tranzactii.add(t);
        }
        while(sc.hasNext())
        {
            String next = sc.nextLine();
            switch(next){
                case "UNIQUE_IDS":
                    {
                        LinkedHashSet<Integer> idUnice = new LinkedHashSet<>();
                        for(Tranzactie t:tranzactii)
                        {
                            idUnice.add(t.getId());
                        }
                        System.out.println("IDs unice (" + idUnice.size() + "): " + idUnice);
                        break;
                    }
                case "MONTHLY_REPORT":
                    {
                        TreeMap<String,double[]> sumaPeLuna = new TreeMap<>();
                        for(Tranzactie t:tranzactii){
                            String luna = t.getData().substring(0,7);
                            sumaPeLuna.putIfAbsent(luna, new double[2]);
                            double[] sume = sumaPeLuna.get(luna);
                            if (t.getTip() == TipTranzactie.CREDIT)
                                sume[0] += t.getSuma();
                            else
                                sume[1] += t.getSuma();
                        }
                        for(Map.Entry<String,double[]> elem:sumaPeLuna.entrySet()){
                            System.out.println(elem.getKey() + ": CREDIT " + String.format("%.2f",elem.getValue()[0]) + " RON, DEBIT " +
                        String.format("%.2f",elem.getValue()[1]) + " RON");
                        }
                        break;
                    }
                case "SORT_ASC":{
                    Collections.sort(tranzactii);
                    for(Tranzactie t:tranzactii)
                        System.out.println(t);
                    break;
                }
                case "SORT_DESC":{
                    Collections.sort(tranzactii, new TranzactieSortDesc());
                    for(Tranzactie t:tranzactii)
                        System.out.println(t);
                    break;
                }
                case "REVERSE":{
                    Collections.reverse(tranzactii);
                    for(Tranzactie t:tranzactii)
                        System.out.println(t);
                    break;
                }
                case "MIN_MAX":{
                    System.out.println("MIN: " + Collections.min(tranzactii));
                    System.out.println("MAX: " + Collections.max(tranzactii));
                    break;
                }
                case "CME_DEMO":{
                    try{
                        for(Tranzactie t:tranzactii)
                            tranzactii.remove(t);
                    }
                    catch(ConcurrentModificationException e){System.out.println("ConcurrentModificationException prins: modificare in iteratie detectata.");}
                    break;
                }
                default:{
                    String[] split = next.split(" ");
                    int limit = Integer.parseInt(split[1]);
                    System.out.println("Top "+limit+":");
                    tranzactii.stream()
                    .sorted(new TranzactieSortDesc())
                    .limit(limit)
                    .forEach(obj -> System.out.println(obj));
                    break;
                }
            }
        }
        sc.close();
        // TODO: Implementează conform Readme.md
        //
        // 1. Citește N din stdin, apoi cele N tranzacții (id suma data tip) — pot exista duplicate de id
        //    Stochează-le toate într-un ArrayList<Tranzactie> (cu duplicate, ordine inserare)
        //
        // 2. Procesează comenzile din stdin până la EOF:
        //
        //   UNIQUE_IDS      → LinkedHashSet<Integer> cu id-urile în ordinea primei apariții
        //                     afișează: "IDs unice (N): [1, 2, 3, ...]"
        //
        //   MONTHLY_REPORT  → TreeMap<String, ...> grupat pe yyyy-MM (substring 0-7 din data)
        //                     pentru fiecare lună, sumele CREDIT și DEBIT
        //                     format: "yyyy-MM: CREDIT X.XX RON, DEBIT Y.YY RON"
        //
        //   TOP n           → primele n tranzacții după suma descrescătoare (nu modifică lista)
        //                     afișează "Top n:" urmat de n linii
        //
        //   SORT_ASC        → Collections.sort cu suma crescătoare; afișează lista sortată
        //   SORT_DESC       → Collections.sort cu suma descrescătoare; afișează lista sortată
        //   REVERSE         → Collections.reverse; afișează lista
        //   MIN_MAX         → Collections.min/max după suma
        //                     "MIN: [id] data tip: suma RON"
        //                     "MAX: [id] data tip: suma RON"
        //
        //   CME_DEMO        → încearcă for(t : lista) lista.remove(t) în try-catch
        //                     afișează "ConcurrentModificationException prins: modificare in iteratie detectata."
        //
        // Format linie tranzacție: [id] data tip: suma RON
        //   Ex: [1] 2024-01-15 CREDIT: 1500.00 RON
    }
}
