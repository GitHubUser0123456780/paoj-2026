package com.pao.laboratory14.exercise1;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Bilet> bilete = new ArrayList<>();
        int n;
        n = sc.nextInt();
        sc.nextLine();
        for(int i=0;i<n;i++)
        {
            String[] split = sc.nextLine().split(" ");
            int id = Integer.parseInt(split[0]);
            TipBilet tip = TipBilet.valueOf(split[2]);
            double pret = Double.parseDouble(split[3]);
            Bilet bilet = new Bilet(id, split[1], tip, pret);
            bilete.add(bilet);
        }
        RaportVanzari raport = bilete.stream().collect(new ColectorRaporturi());
        for(TipBilet tip:TipBilet.values()){
            if(raport.getNumarPerTip().getOrDefault(tip,0L) != 0)
                System.out.println(tip + ": count=" + raport.getNumarPerTip().get(tip) + " incasari=" + String.format("%.2f",raport.getIncasariPerTip().get(tip)) + " RON");
        }
        if(sc.nextLine().strip().equals("RAPORT_COMPLET")){
            System.out.println("---");
            System.out.println("Total: " + String.format("%.2f",raport.getTotalGlobal()) + " RON");
            System.out.println("Medie: " + String.format("%.2f",raport.getMedieGlobala()) + " RON");
            System.out.println("Cel mai popular: " + raport.getTipCelMaiPopular());
        }
    }
}
