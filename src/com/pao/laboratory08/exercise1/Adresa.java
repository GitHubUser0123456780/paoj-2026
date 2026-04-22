package com.pao.laboratory08.exercise1;

public class Adresa implements Cloneable {
    private String oras;
    private String strada;

    public Adresa(String oras, String strada){
        this.oras = oras;
        this.strada = strada;
    }
    // getteri, setteri
    public String get_oras(){
        return oras;
    }
    public String get_strada(){
        return strada;
    }
    public void set_oras(String oras){
        this.oras = oras;
    }
    public void set_strada(String strada){
        this.strada = strada;
    }
    // toString() → "Adresa{oras='...', strada='...'}"
    public String toString(){
        return "Adresa{oras='" + this.oras + "', strada='" + this.strada + "'}";
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
