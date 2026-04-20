package ro.pao.proiect.service;

import ro.pao.proiect.exception.ComandaNotFoundException;
import ro.pao.proiect.model.ArticolComanda;
import ro.pao.proiect.model.ComandaClient;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Serviciu principal pentru gestionarea comenzilor.
 *
 * Colectii folosite:
 *   - TreeSet<ComandaClient>: toate comenzile, sortate automat dupa data
 *     (ComandaClient implementeaza Comparable)
 *   - HashMap<Integer, ComandaClient>: lookup rapid dupa nrComanda
 */
public class ComandaService {

    // TreeSet = colectia sortata (cerinta PAO: cel putin una sortata)
    private final TreeSet<ComandaClient> comenziSortate = new TreeSet<>();

    // HashMap pentru acces rapid dupa nrComanda
    private final Map<Integer, ComandaClient> comenziMap = new HashMap<>();

    // --- Actiunea 3: Plasare comanda ---
    public void plasareComanda(ComandaClient comanda) {
        if (comenziMap.containsKey(comanda.getNrComanda())) {
            System.out.println("[WARN] Comanda " + comanda.getNrComanda() + " exista deja.");
            return;
        }
        comenziSortate.add(comanda);
        comenziMap.put(comanda.getNrComanda(), comanda);
        System.out.println("[OK] Comanda plasata: #" + comanda.getNrComanda()
                + " | client=" + comanda.getClient().getNume()
                + " | data=" + comanda.getData()
                + " | total=" + String.format("%.2f", comanda.calculeazaTotal()));
    }

    public ComandaClient getComandaById(int nrComanda) {
        ComandaClient c = comenziMap.get(nrComanda);
        if (c == null) {
            throw new ComandaNotFoundException(nrComanda);
        }
        return c;
    }

    // --- Actiunea 4: Calculare total comanda ---
    public double calculeazaTotal(int nrComanda) {
        return getComandaById(nrComanda).calculeazaTotal();
    }

    // --- Actiunea 6: Comenzi ale unui client, sortate dupa data ---
    public List<ComandaClient> getComenziClient(int codClient) {
        // TreeSet e deja sortat, filtram doar pe client
        return comenziSortate.stream()
                .filter(c -> c.getClient().getCodClient() == codClient)
                .collect(Collectors.toList());
    }

    // --- Actiunea 9: Schimbare stare comanda ---
    public void schimbaStare(int nrComanda, String stareNoua) {
        ComandaClient c = getComandaById(nrComanda);
        String stareVeche = c.getStare();
        c.setStare(stareNoua);
        System.out.println("[OK] Comanda #" + nrComanda
                + " | stare: " + stareVeche + " -> " + stareNoua);
    }

    // --- Actiunea 11: Top produse vandute (dupa cantitate totala) ---
    public List<Map.Entry<String, Integer>> getTopProduse() {
        Map<String, Integer> cantitatiTotale = new HashMap<>();
        for (ComandaClient comanda : comenziSortate) {
            for (ArticolComanda articol : comanda.getArticole()) {
                cantitatiTotale.merge(articol.getDenumire(), articol.getCantitate(), Integer::sum);
            }
        }
        return cantitatiTotale.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toList());
    }

    // --- Afisari ---

    public void afiseazaToateComenzieSortate() {
        System.out.println("\n===== COMENZI (ordonate dupa data) =====");
        if (comenziSortate.isEmpty()) {
            System.out.println("  Nu exista comenzi.");
            return;
        }
        comenziSortate.forEach(c -> {
            System.out.println("  #" + c.getNrComanda()
                    + " | " + c.getData()
                    + " | " + c.getClient().getNume()
                    + " | stare=" + c.getStare()
                    + " | total=" + String.format("%.2f", c.calculeazaTotal()));
        });
    }

    public void afiseazaComandaDetaliat(int nrComanda) {
        System.out.println("\n===== DETALIU COMANDA #" + nrComanda + " =====");
        System.out.println(getComandaById(nrComanda));
    }

    public void afiseazaComenziClient(int codClient) {
        List<ComandaClient> lista = getComenziClient(codClient);
        System.out.println("\n===== COMENZI CLIENT cod=" + codClient + " =====");
        if (lista.isEmpty()) {
            System.out.println("  Nicio comanda gasita.");
        } else {
            lista.forEach(c -> System.out.println("  " + c.getData()
                    + " | #" + c.getNrComanda()
                    + " | stare=" + c.getStare()
                    + " | total=" + String.format("%.2f", c.calculeazaTotal())));
        }
    }

    public void afiseazaTopProduse() {
        System.out.println("\n===== TOP PRODUSE VANDUTE =====");
        List<Map.Entry<String, Integer>> top = getTopProduse();
        if (top.isEmpty()) {
            System.out.println("  Nu exista date.");
        } else {
            int rang = 1;
            for (Map.Entry<String, Integer> e : top) {
                System.out.println("  " + rang++ + ". " + e.getKey()
                        + " | cantitate totala=" + e.getValue());
            }
        }
    }

    // Demonstratie equals(): doua comenzi cu acelasi total sunt "egale"
    public void demonstreazaEquals(int nrComanda1, int nrComanda2) {
        ComandaClient c1 = getComandaById(nrComanda1);
        ComandaClient c2 = getComandaById(nrComanda2);
        System.out.println("\n===== DEMO equals() =====");
        System.out.println("  Comanda #" + nrComanda1 + " total=" + String.format("%.2f", c1.calculeazaTotal()));
        System.out.println("  Comanda #" + nrComanda2 + " total=" + String.format("%.2f", c2.calculeazaTotal()));
        System.out.println("  c1.equals(c2) => " + c1.equals(c2));
    }
}
