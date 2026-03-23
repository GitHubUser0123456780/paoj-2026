package com.pao.laboratory05.angajati;

import java.util.Arrays;

public class AngajatService {
    static AngajatService instance = null;
    Angajat[] angajati = new Angajat[0];
    AngajatService(){

    }
    public static AngajatService getInstance(){
        if(instance == null)
            instance = new AngajatService();
        return instance;
    }
    void addAngajat(Angajat a){
        angajati = Arrays.copyOf(angajati, angajati.length + 1);
        angajati[angajati.length - 1] = a;
        System.out.println("Angajatul " + a + " a fost adaugat cu succes.");
    }
    void printAll(){
        for(Angajat a:angajati)
            System.out.println(a);
    }
    void listBySalary(){
        Angajat[] copy_angajati = angajati.clone();
        Arrays.sort(copy_angajati);
        for(Angajat a:copy_angajati)
            System.out.println(a);
    }
    void findByDepartment(String nume_departament){
        boolean gasit = false;
        for(Angajat a:angajati){
            if(a.departament().nume().equalsIgnoreCase(nume_departament))
            {
                gasit = true;
                System.out.println(a);
            }
        }
        if(!gasit)
            System.out.println("Niciun angajat în departamentul: "+nume_departament);
    }
}
