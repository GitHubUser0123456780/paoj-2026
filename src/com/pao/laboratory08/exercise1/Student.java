package com.pao.laboratory08.exercise1;

public class Student implements Cloneable {
    private String nume;
    private int varsta;
    private Adresa adresa;

    // constructor(String nume, int varsta, Adresa adresa)
    public Student(String nume, int varsta, Adresa adresa){
        this.nume = nume;
        this.varsta = varsta;
        this.adresa = adresa;
    }
    // getteri, setteri
    public String get_nume(){
        return nume;
    }
    public int get_varsta(){
        return varsta;
    }
    public Adresa get_adresa(){
        return adresa;
    }
    public void set_nume(String nume){
        this.nume = nume;
    }
    public void set_varsta(int varsta){
        this.varsta = varsta;
    }
    public void set_adresa(Adresa adresa){
        this.adresa = adresa;
    }
    // toString() → "Student{nume='...', varsta=..., adresa=Adresa{oras='...', strada='...'}}"
    public String toString(){
        return "Student{nume='" + this.nume + "', varsta = " + this.varsta + ", adresa=" + this.adresa.toString() + "}";
    }
    // clone() — implementare diferită pentru shallow vs. deep (vezi mai jos)
    public Student clone() throws CloneNotSupportedException {
        return (Student) super.clone();
    }
    public Student deepClone() throws CloneNotSupportedException {
        Student copy = (Student) super.clone();
        copy.set_adresa((Adresa)this.adresa.clone());
        return copy;
    }
}
