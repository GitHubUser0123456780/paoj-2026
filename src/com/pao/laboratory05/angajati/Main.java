package com.pao.laboratory05.angajati;

import java.util.Scanner;

/**
 * Exercise 3 — Angajați
 *
 * Cerințele complete se află în:
 *   src/com/pao/laboratory05/Readme.md  →  secțiunea "Exercise 3 — Angajați"
 *
 * Creează fișierele de la zero în acest pachet, apoi rulează Main.java
 * pentru a verifica output-ul așteptat din Readme.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AngajatService service = AngajatService.getInstance();
        boolean running = true;
        while (running) {
            System.out.println("\n===== Gestionare Angajați =====");
            System.out.println("1. Adaugă angajat");
            System.out.println("2. Listare după salariu");
            System.out.println("3. Caută după departament");
            System.out.println("0. Ieșire");
            System.out.print("Opțiune: ");
            // citește opțiunea și execută acțiunea
            String opt = scanner.nextLine().trim();
            switch (opt) {
                case "1":
                    System.out.println("Nume angajat: ");
                    String nume_angajat  = scanner.nextLine().trim();
                    System.out.println("Nume (departament): ");
                    String nume_d = scanner.nextLine().trim();
                    System.out.println("Locatie (departament): ");
                    String locatie_departament = scanner.nextLine().trim();
                    System.out.println("Salariu: ");
                    Double salariu = Double.parseDouble(scanner.nextLine().trim());
                    Departament dep = new Departament(nume_d,locatie_departament);
                    Angajat a = new Angajat(nume_angajat, dep, salariu);
                    service.addAngajat(a);
                    break;
                case "2":
                    System.out.println("Listare dupa salariu: \n");
                    service.listBySalary();
                    break;
                case "3":
                    System.out.println("Introduce departamentul: ");
                    String nume_departament = scanner.nextLine().trim();
                    service.findByDepartment(nume_departament);
                    break;
                case "0":
                    System.out.println("La revedere!");
                    running = false;
                    break;
                default:
                    System.out.println("Optiune invalida.");
                    break;
            }
        }
        scanner.close();
    }
}
