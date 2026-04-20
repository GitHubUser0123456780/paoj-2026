package ro.pao.proiect.service;

import ro.pao.proiect.exception.ProdusNedisponibilException;
import ro.pao.proiect.model.Produs;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Serviciu pentru gestionarea catalogului de produse.
 * Foloseste HashMap<Integer, Produs> pentru acces rapid dupa cod
 * si un List<Produs> pentru listari ordonate.
 */
public class ProdusService {

    // HashMap: acces rapid produs dupa codProdus
    private final Map<Integer, Produs> catalogProduse = new HashMap<>();

    // --- Actiunea 1: Adaugare produs nou ---
    public void adaugaProdus(Produs produs) {
        if (catalogProduse.containsKey(produs.getCodProdus())) {
            System.out.println("[WARN] Produsul cu codul " + produs.getCodProdus()
                    + " exista deja. Folositi actualizareProdus().");
            return;
        }
        catalogProduse.put(produs.getCodProdus(), produs);
        System.out.println("[OK] Produs adaugat: " + produs.getDenumire());
    }

    public void actualizareProdus(Produs produs) {
        catalogProduse.put(produs.getCodProdus(), produs);
    }

    // --- Actiunea 12: Stergere produs ---
    public void stergeProdus(int codProdus) {
        Produs p = catalogProduse.remove(codProdus);
        if (p == null) {
            throw new ProdusNedisponibilException(codProdus);
        }
        System.out.println("[OK] Produs sters: " + p.getDenumire());
    }

    public Produs getProdusById(int codProdus) {
        Produs p = catalogProduse.get(codProdus);
        if (p == null) {
            throw new ProdusNedisponibilException(codProdus);
        }
        return p;
    }

    // --- Actiunea 5: Listare produse dintr-o categorie ---
    public List<Produs> getProduseDupaCategorie(String denCategorie) {
        return catalogProduse.values().stream()
                .filter(p -> p.getCategorie() != null
                        && p.getCategorie().getDenumire().equalsIgnoreCase(denCategorie))
                .collect(Collectors.toList());
    }

    // --- Actiunea 7: Cautare dupa prefix nume ---
    public List<Produs> cautaDupaNumePrefix(String prefix) {
        return catalogProduse.values().stream()
                .filter(p -> p.getDenumire().toLowerCase()
                        .startsWith(prefix.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Listare completa catalog (sortata dupa denumire)
    public List<Produs> getToateProdusele() {
        return catalogProduse.values().stream()
                .sorted(Comparator.comparing(Produs::getDenumire))
                .collect(Collectors.toList());
    }

    public void afiseazaCatalog() {
        System.out.println("\n===== CATALOG PRODUSE =====");
        getToateProdusele().forEach(System.out::println);
        System.out.println("Total: " + catalogProduse.size() + " produse");
    }
}
