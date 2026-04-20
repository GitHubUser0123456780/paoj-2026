package ro.pao.proiect.service;

import ro.pao.proiect.exception.ProdusNedisponibilException;
import ro.pao.proiect.model.Stoc;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Serviciu pentru gestionarea stocurilor din depozit.
 * Foloseste HashMap<Integer, Stoc> (cheie = codProdus).
 */
public class StocService {

    private final Map<Integer, Stoc> stocuri = new HashMap<>();

    public void adaugaStoc(Stoc stoc) {
        stocuri.put(stoc.getCodProdus(), stoc);
        System.out.println("[OK] Stoc inregistrat pentru: " + stoc.getDenumire()
                + " | curent=" + stoc.getStocCurent());
    }

    public Stoc getStocByProdus(int codProdus) {
        Stoc s = stocuri.get(codProdus);
        if (s == null) {
            throw new ProdusNedisponibilException(codProdus,
                    "Nu exista stoc inregistrat pentru produsul cu codul " + codProdus);
        }
        return s;
    }

    // --- Actiunea 2: Actualizare stoc (intrare marfa) ---
    public void actualizeazaStoc(int codProdus, int cantitateIntrare) {
        Stoc s = getStocByProdus(codProdus);
        int vechi = s.getStocCurent();
        s.setStocCurent(vechi + cantitateIntrare);
        System.out.println("[OK] Stoc actualizat: " + s.getDenumire()
                + " | " + vechi + " -> " + s.getStocCurent());
    }

    public void rezervaStoc(int codProdus, int cantitate) {
        Stoc s = getStocByProdus(codProdus);
        if (s.getStocDisponibil() < cantitate) {
            throw new ProdusNedisponibilException(codProdus,
                    "Stoc insuficient pentru produsul '" + s.getDenumire()
                    + "'. Disponibil: " + s.getStocDisponibil() + ", necesar: " + cantitate);
        }
        s.setStocRezervar(s.getStocRezervar() + cantitate);
    }

    // --- Actiunea 8: Produse cu stoc sub limita minima ---
    public List<Stoc> getProduseSublimita() {
        return stocuri.values().stream()
                .filter(Stoc::esteSubLimita)
                .collect(Collectors.toList());
    }

    public void afiseazaAlertaStoc() {
        List<Stoc> alerte = getProduseSublimita();
        System.out.println("\n===== ALERTA STOC MINIM =====");
        if (alerte.isEmpty()) {
            System.out.println("  Toate produsele au stoc suficient.");
        } else {
            alerte.forEach(s -> System.out.println("  [!] " + s.getDenumire()
                    + " | curent=" + s.getStocCurent() + " | minim=" + s.getStocMinim()));
        }
    }

    public void afiseazaStocuri() {
        System.out.println("\n===== STOCURI DEPOZIT =====");
        stocuri.values().stream()
                .sorted(Comparator.comparing(Stoc::getDenumire))
                .forEach(System.out::println);
    }
}
