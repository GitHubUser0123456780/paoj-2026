package com.pao.laboratory03.collections;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;

/**
 * Exercițiul 1 — Colecții: HashMap și TreeMap
 *
 * Creează în acest main:
 *
 * PARTEA A — HashMap (frecvența cuvintelor)
 * 1. Declară un array de String-uri:
 *    String[] words = {"java", "python", "java", "c++", "python", "java", "rust", "c++", "go"};
 * 2. Creează un HashMap<String, Integer> care contorizează de câte ori apare fiecare cuvânt.
 *    - Parcurge array-ul și folosește put() + getOrDefault() pentru a incrementa contorul.
 * 3. Afișează map-ul.
 * 4. Verifică dacă există cheia "rust" cu containsKey().
 * 5. Afișează DOAR cheile (keySet()), apoi DOAR valorile (values()).
 * 6. Parcurge map-ul cu entrySet() și afișează "cheia -> valoarea" pentru fiecare intrare.
 *
 * PARTEA B — TreeMap (sortare automată)
 * 7. Creează un TreeMap<String, Integer> din același HashMap (constructor cu argument).
 * 8. Afișează TreeMap-ul — observă ordinea alfabetică a cheilor.
 * 9. Folosește firstKey() și lastKey() pentru a afișa prima și ultima cheie.
 *
 * PARTEA C — Map cu obiecte
 * 10. Creează un HashMap<String, List<String>> care asociază materii cu liste de studenți.
 *     Exemplu: "PAOJ" -> ["Ana", "Mihai", "Ion"], "BD" -> ["Ana", "Elena"]
 * 11. Afișează toți studenții de la materia "PAOJ".
 * 12. Adaugă un student nou la "BD" și afișează lista actualizată.
 *
 * Output așteptat (orientativ — ordinea HashMap poate varia):
 *
 * === PARTEA A: HashMap — frecvența cuvintelor ===
 * Frecvență: {python=2, c++=2, java=3, rust=1, go=1}
 * Conține 'rust'? true
 * Chei: [python, c++, java, rust, go]
 * Valori: [2, 2, 3, 1, 1]
 * python -> 2
 * c++ -> 2
 * java -> 3
 * rust -> 1
 * go -> 1
 *
 * === PARTEA B: TreeMap — sortare automată ===
 * Sortat: {c++=2, go=1, java=3, python=2, rust=1}
 * Prima cheie: c++
 * Ultima cheie: rust
 *
 * === PARTEA C: Map cu obiecte ===
 * Studenți la PAOJ: [Ana, Mihai, Ion]
 * Studenți la BD (actualizat): [Ana, Elena, George]
 */
public class Main {
    public static void main(String[] args) {
        // TODO: implementează cele 3 părți de mai sus
        // Partea A
        String[] words = {"java", "python", "java", "c++", "python", "java", "rust", "c++", "go"};
        HashMap<String,Integer> words_count = new HashMap<>();
        System.out.println("Construim HashMap ...\n");
        for(String s:words)
            words_count.put(s,words_count.getOrDefault(s,0)+1);
        System.out.println("Hashpmap-ul este: \n");
        System.out.println(words_count);

        System.out.println("Array-ul contine string-ul rust?: " + words_count.containsKey("rust"));

        System.out.println("Toate key-urile din map: \n" + words_count.keySet());
        System.out.println("\nToate valorile din map: \n" + words_count.values());

        System.out.println("Afisam perechile din map cu entrySet():\n");
        for(var entry:words_count.entrySet())
            System.out.println(entry.getKey() + " -> " + entry.getValue());

        //Partea B
        System.out.println("Cream TreeMap folosind Hashmap-ul...\n");
        TreeMap<String,Integer> tree_word = new TreeMap<>(words_count);
        System.out.println(tree_word);

        System.out.println("Prima cheie din tree: " + tree_word.firstKey() + "\nUltima cheie din tree: " + tree_word.lastKey());

        //Partea C
        HashMap<String,List<String>> studenti = new HashMap<>();
        List<String> studenti_PAOJ = new ArrayList<>();
        studenti_PAOJ.add("Ana");
        studenti_PAOJ.add("Mihai");
        studenti_PAOJ.add("Ion");
        studenti.put("PAOJ",studenti_PAOJ);
        List<String> studenti_BD = new ArrayList<>();
        studenti_BD.add("Ana");
        studenti_BD.add("Elena");
        studenti.put("BD",studenti_BD);

        System.out.println("HashMap-ul inainte de actualizare: " + studenti);

        System.out.println("\nStudentii la PAOJ: " + studenti.get("PAOJ"));
        studenti.putIfAbsent("BD", new ArrayList<>());
        studenti.get("BD").add("George");
        System.out.println("Studentii la BD (dupa actualizare): " + studenti.get("BD"));
    }
}

