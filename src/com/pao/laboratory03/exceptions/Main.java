package com.pao.laboratory03.exceptions;
import java.util.ArrayList;
import java.util.List;
/**
 * Exercițiul 3 — Excepții (checked, unchecked, custom)
 *
 * Creează în acest pachet (lângă Main.java) două clase de excepții custom,
 * apoi demonstrează-le aici.
 *
 * PASUL 1 — Creează InvalidAgeException.java (fișier separat):
 *   - Extinde RuntimeException (unchecked)
 *   - Constructor cu String message → apelează super(message)
 *
 * PASUL 2 — Creează DuplicateEntryException.java (fișier separat):
 *   - Extinde RuntimeException (unchecked)
 *   - Constructor cu String message → apelează super(message)
 *
 * PASUL 3 — În acest Main.java, implementează și demonstrează:
 *
 *   a) UNCHECKED EXCEPTIONS — NullPointerException, ArrayIndexOutOfBoundsException:
 *      - Creează o metodă riskyMethod() care aruncă NullPointerException
 *      - Prinde-o cu try-catch, afișează mesajul erorii
 *      - Adaugă un bloc finally care se execută mereu
 *
 *   b) CUSTOM EXCEPTIONS — InvalidAgeException, DuplicateEntryException:
 *      - Creează o metodă validateAge(int age) care aruncă InvalidAgeException
 *        dacă age < 0 sau age > 150
 *      - Creează o metodă addToList(List<String> list, String name) care aruncă
 *        DuplicateEntryException dacă name există deja în listă
 *      - Demonstrează ambele cu try-catch
 *
 *   c) MULTI-CATCH:
 *      - Prinde InvalidAgeException | DuplicateEntryException într-un singur catch
 *
 *   d) CATCH ORDERING:
 *      - Demonstrează că prinderea specifică (InvalidAgeException) trebuie
 *        să fie ÎNAINTE de cea generală (RuntimeException)
 *
 *   e) THROW vs THROWS:
 *      - Creează o metodă cu semnătura: void process(int age) throws InvalidAgeException
 *      - Apeleaz-o din main cu try-catch
 *
 * Output așteptat:
 *
 * === a) Unchecked — NullPointerException ===
 * Prins: Cannot invoke "String.length()" because "s" is null
 * Finally se execută mereu!
 *
 * === b) Custom exceptions ===
 * InvalidAgeException: Vârsta -5 nu este validă (0-150)
 * DuplicateEntryException: 'Ana' există deja în listă
 *
 * === c) Multi-catch ===
 * Excepție prinsă: Vârsta 200 nu este validă (0-150)
 *
 * === d) Catch ordering (specific → general) ===
 * InvalidAgeException prinsă specific: Vârsta -1 nu este validă (0-150)
 *
 * === e) Throw vs throws ===
 * Metoda process() a aruncat: Vârsta 999 nu este validă (0-150)
 */
public class Main {
    private static class InvalidAge extends RuntimeException{
        public InvalidAge(String message){super(message);}
    }
    private static class DublicateEntryException extends RuntimeException{
        public DublicateEntryException(String message){super(message);}
    }
    private static int riskyMethod(){
        String s = null;
        return s.length();
    }
    private static void validateAge(int age){
        if(age<0 || age>150){
            throw new InvalidAgeException("Varsta " + age + " nu este intre 0 si 150 de ani.");
        }
    }
    private static void addToList(List<String> list, String name){
        if(list.contains(name)){
            throw new DublicateEntryException(name + " este deja in lista.");
        }
        list.add(name);
    }
    public static void process(int age) throws InvalidAgeException{
        validateAge(age);
        System.out.println("Procesare OK pentru varsta " + age);
    }
    public static void main(String[] args) {
        // TODO: implementează pașii de mai sus
        // Hint: creează mai întâi InvalidAgeException.java și DuplicateEntryException.java
        System.out.println("====== Null pointer exception =======\n");
        try{
            riskyMethod();
        }
        catch(NullPointerException e){
            System.out.println("Eroare prinsa: " + e.getMessage());
        }
        finally{
            System.out.println("Finally se executa mereu.");
        }
        System.out.println("\n===== Invalid age exception =====\n");
        try{
            validateAge(-5);
        }
        catch(InvalidAgeException e){
            System.out.println("Prins: " + e.getMessage());
        }
        System.out.println("\n===== Dublicate list entry =====\n");
        try{
            List<String> ls = new ArrayList<>();
            ls.add("Marius");
            addToList(ls, "Marius");
        }
        catch(DublicateEntryException e){
            System.out.println("Prins: " + e.getMessage());
        }
        System.out.println("\n===== Multi-catch =====\n");
        try{
            validateAge(200);
        }
        catch(InvalidAgeException | DublicateEntryException e){
            System.out.println("Exceptia prinsa: " + e.getMessage());
        }
        System.out.println("\n===== Catch ordering =====\n");
        try{
            validateAge(-1);
        }
        catch(InvalidAgeException e){
            System.out.println("Exceptie specifica: " + e.getMessage());
        }
        catch(RuntimeException e){
            System.out.println("Exceptie generala: "+e.getMessage());
        }
        System.out.println("\n===== Throw vs Throws =====\n");
        try{
            process(200);
        }
        catch(InvalidAgeException e){
            System.out.println("Metoda process() a aruncate exceptia: " + e.getMessage());
        }
    }
}

