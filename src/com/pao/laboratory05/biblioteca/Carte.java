package com.pao.laboratory05.biblioteca;

public class Carte implements Comparable<Carte>{
    String titlu;
    String autor;
    int an;
    double rating;
    public Carte(String titlu, String autor, int an, double rating){
        this.titlu = titlu;
        this.autor = autor;
        this.an = an;
        this.rating = rating;
    }
    public String titlu(){
        return this.titlu;
    }
    public String autor(){
        return this.autor;
    }
    public int an(){
        return this.an;
    }
    public double rating(){
        return this.rating;
    }
    public String toString(){
        return "Carte{titlu='" + titlu() + "', autor='" + autor() + "', an=" + an() + ", rating=" + rating()+"}";
    }
    @Override
    public int compareTo(Carte c){
        return Double.compare(c.rating(), this.rating);
    }
}
