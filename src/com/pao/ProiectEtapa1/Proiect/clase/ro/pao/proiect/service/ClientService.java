package ro.pao.proiect.service;

import ro.pao.proiect.model.Client;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Serviciu pentru gestionarea clientilor.
 * Foloseste HashSet<Client> pentru unicitate
 * si HashMap<Integer, Client> pentru acces rapid dupa cod.
 */
public class ClientService {

    // HashSet asigura unicitatea clientilor
    private final Set<Client> clientiSet = new HashSet<>();

    // HashMap pentru lookup rapid dupa codClient
    private final Map<Integer, Client> clientiMap = new HashMap<>();

    public void adaugaClient(Client client) {
        if (clientiMap.containsKey(client.getCodClient())) {
            System.out.println("[WARN] Clientul cu codul " + client.getCodClient() + " exista deja.");
            return;
        }
        clientiSet.add(client);
        clientiMap.put(client.getCodClient(), client);
        System.out.println("[OK] Client adaugat: " + client.getNume()
                + " (" + client.getTipClient() + ")");
    }

    public Client getClientById(int codClient) {
        Client c = clientiMap.get(codClient);
        if (c == null) {
            throw new NoSuchElementException("Clientul cu codul " + codClient + " nu exista.");
        }
        return c;
    }

    // --- Actiunea 10: Listare clienti dupa tip ---
    public List<Client> getClientiDupaTip(String tip) {
        return clientiMap.values().stream()
                .filter(c -> c.getTipClient().equalsIgnoreCase(tip))
                .sorted(Comparator.comparing(Client::getNume))
                .collect(Collectors.toList());
    }

    public List<Client> getTotiClienti() {
        return clientiMap.values().stream()
                .sorted(Comparator.comparing(Client::getNume))
                .collect(Collectors.toList());
    }

    public void afiseazaClienti() {
        System.out.println("\n===== CLIENTI =====");
        getTotiClienti().forEach(System.out::println);
        System.out.println("Total: " + clientiMap.size() + " clienti");
    }

    public void afiseazaClientiDupaTip(String tip) {
        System.out.println("\n===== CLIENTI - " + tip.toUpperCase() + " =====");
        List<Client> lista = getClientiDupaTip(tip);
        if (lista.isEmpty()) {
            System.out.println("  Niciun client de tipul: " + tip);
        } else {
            lista.forEach(System.out::println);
        }
    }
}
