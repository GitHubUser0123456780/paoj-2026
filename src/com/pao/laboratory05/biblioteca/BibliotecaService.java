package com.pao.laboratory05.biblioteca;
import java.util.Arrays;
import java.util.Comparator;

public class BibliotecaService {
    private static BibliotecaService instance = null;
    Carte[] carti = new Carte[0];
    BibliotecaService(){

    }
    public static BibliotecaService getInstance(){
        if(instance == null)
            instance = new BibliotecaService();
        return instance;
    }
    void addCarte(Carte c){
        carti = Arrays.copyOf(carti, carti.length + 1);
        carti[carti.length-1] = c;
    }
    void listSortedByRating(){
        Carte[] copy_carti = carti.clone();
        Arrays.sort(copy_carti);
        for(Carte c:copy_carti)
            System.out.println(c);
    }
    void listSortedBy(Comparator<Carte> comparator){
        Carte[] copy_carti = carti.clone();
        Arrays.sort(copy_carti, comparator);
        for(Carte c:copy_carti)
            System.out.println(c);
    }
}
