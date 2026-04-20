package ro.pao.proiect;

import ro.pao.proiect.exception.ComandaNotFoundException;
import ro.pao.proiect.exception.ProdusNedisponibilException;
import ro.pao.proiect.model.*;
import ro.pao.proiect.service.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * Aplicatie interactiva cu meniu pentru gestionarea
 * produselor, stocurilor, clientilor si comenzilor.
 *
 * Colectiile sunt pre-populate cu date initiale, dupa care
 * utilizatorul poate interactiona prin meniu.
 *
 * Meniu principal:
 *   1. Adaugare produs
 *   2. Adaugare / actualizare stoc
 *   3. Adaugare client
 *   4. Adaugare comanda
 *   5. Afisare comenzi
 *   6. Afisare stocuri
 *   7. Afisare catalog produse
 *   8. Afisare clienti
 *   9. Schimbare stare comanda
 *  10. Detaliu comanda
 *   0. Iesire
 */
public class Main {

    // =========================================================================
    // Servicii - partajate in toata aplicatia
    // =========================================================================
    private static final ProdusService  produsService  = new ProdusService();
    private static final StocService    stocService    = new StocService();
    private static final ClientService  clientService  = new ClientService();
    private static final ComandaService comandaService = new ComandaService();

    // Categorii predefinite accesibile din meniu
    private static final Map<Integer, Categorie> categorii = new LinkedHashMap<>();

    // Scanner reutilizat pe tot parcursul aplicatiei
    private static final Scanner scanner = new Scanner(System.in);

    // Contoare auto-increment pentru coduri generate automat
    private static int nextCodProdus = 200;
    private static int nextCodClient = 10;
    private static int nextNrComanda = 2000;

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    // =========================================================================
    // MAIN
    // =========================================================================
    public static void main(String[] args) {

        initializeazaDateInitiale();

        boolean ruleaza = true;
        while (ruleaza) {
            afiseazaMeniu();
            int optiune = citesteInt("Optiune: ");
            System.out.println();

            switch (optiune) {
                case 1  -> meniuAdaugareProdus();
                case 2  -> meniuAdaugareStoc();
                case 3  -> meniuAdaugareClient();
                case 4  -> meniuAdaugareComanda();
                case 5  -> meniuAfisareComenzi();
                case 6  -> stocService.afiseazaStocuri();
                case 7  -> produsService.afiseazaCatalog();
                case 8  -> clientService.afiseazaClienti();
                case 9  -> meniuSchimbareStare();
                case 10 -> meniuDetaliuComanda();
                case 0  -> {
                    System.out.println("La revedere!");
                    ruleaza = false;
                }
                default -> System.out.println("[!] Optiune invalida. Alegeti intre 0 si 10.");
            }
        }

        scanner.close();
    }

    // =========================================================================
    // DATE INITIALE
    // =========================================================================
    private static void initializeazaDateInitiale() {

        System.out.println("=================================================");
        System.out.println("  Sistem Gestiune Produse & Comenzi - PAO 2025  ");
        System.out.println("=================================================");
        System.out.println("  Initializare date ...");

        // -- Categorii --
        categorii.put(1, new Categorie(1, "Alimentare", "Produse alimentare generale"));
        categorii.put(2, new Categorie(2, "Bauturi",    "Bauturi racoritoare si alcoolice"));
        categorii.put(3, new Categorie(3, "Curatenie",  "Produse de curatenie si igiena"));
        categorii.put(4, new Categorie(4, "Electro",    "Electrocasnice si electronice"));
        categorii.put(5, new Categorie(5, "Papetarie",  "Produse de papetarie si birotica"));

        // -- Produse --
        Categorie cAlim  = categorii.get(1);
        Categorie cBaut  = categorii.get(2);
        Categorie cCurat = categorii.get(3);
        Categorie cElec  = categorii.get(4);

        Produs p101 = new Produs(101, "Faina alba 1kg",   "buc",   4.50,  cAlim);
        Produs p102 = new Produs(102, "Zahar 1kg",        "buc",   6.80,  cAlim);
        Produs p103 = new Produs(103, "Apa minerala 2L",  "buc",   3.20,  cBaut);
        Produs p104 = new Produs(104, "Suc portocale 1L", "buc",   8.50,  cBaut);
        Produs p105 = new Produs(105, "Detergent lichid", "buc",  22.00,  cCurat);
        Produs p106 = new Produs(106, "Bere 0.5L",        "buc",   5.00,  cBaut);
        Produs p107 = new Produs(107, "Aspirator 1200W",  "buc", 349.99,  cElec);

        for (Produs p : new Produs[]{p101, p102, p103, p104, p105, p106, p107}) {
            produsService.adaugaProdus(p);
        }
        nextCodProdus = 200;

        // -- Stocuri --
        stocService.adaugaStoc(new Stoc(101, "Faina alba 1kg",   "buc",   4.50, cAlim,   200, 0,  50));
        stocService.adaugaStoc(new Stoc(102, "Zahar 1kg",        "buc",   6.80, cAlim,    30, 0,  40)); // sub limita!
        stocService.adaugaStoc(new Stoc(103, "Apa minerala 2L",  "buc",   3.20, cBaut,   500, 0, 100));
        stocService.adaugaStoc(new Stoc(104, "Suc portocale 1L", "buc",   8.50, cBaut,   150, 0,  30));
        stocService.adaugaStoc(new Stoc(105, "Detergent lichid", "buc",  22.00, cCurat,   15, 0,  20)); // sub limita!
        stocService.adaugaStoc(new Stoc(106, "Bere 0.5L",        "buc",   5.00, cBaut,   300, 0,  50));
        stocService.adaugaStoc(new Stoc(107, "Aspirator 1200W",  "buc", 349.99, cElec,    12, 0,   5));

        // -- Clienti --
        clientService.adaugaClient(new PersoanaFizica(1, "Ionescu Maria",   "1234567890123", 5.0));
        clientService.adaugaClient(new PersoanaFizica(2, "Popescu Andrei",  "5678901234567", 3.0));
        clientService.adaugaClient(new PersoanaFizica(3, "Dumitru Elena",   "2109876543210", 2.5));
        clientService.adaugaClient(new PersoanaJuridica(4, "SC AlimPro SRL", "RO12345678", 30,  50000.0));
        clientService.adaugaClient(new PersoanaJuridica(5, "SC BevDist SA",  "RO87654321", 45, 120000.0));
        nextCodClient = 10;

        // -- Comenzi initiale --
        Client pf1 = clientService.getClientById(1);
        Client pj1 = clientService.getClientById(4);

        ComandaClient c1 = new ComandaClient(1001,
                LocalDate.of(2025, 1, 10), LocalDate.of(2025, 1, 12), pf1, "livrata");
        c1.adaugaArticol(new ArticolComanda(1001, p101,  5,  4.50));
        c1.adaugaArticol(new ArticolComanda(1001, p102,  3,  6.80));
        c1.adaugaArticol(new ArticolComanda(1001, p103, 10,  3.20));
        comandaService.plasareComanda(c1);

        ComandaClient c2 = new ComandaClient(1002,
                LocalDate.of(2025, 2, 5), LocalDate.of(2025, 2, 8), pj1, "confirmata");
        c2.adaugaArticol(new ArticolComanda(1002, p104, 50, 8.00));
        c2.adaugaArticol(new ArticolComanda(1002, p106, 100, 4.80));
        comandaService.plasareComanda(c2);

        nextNrComanda = 2000;

        System.out.println("  Date initiale incarcate cu succes.");
        System.out.println();

        stocService.afiseazaAlertaStoc();
        System.out.println();
    }

    // =========================================================================
    // MENIU PRINCIPAL
    // =========================================================================
    private static void afiseazaMeniu() {
        System.out.println("+------------------------------------------+");
        System.out.println("|           MENIU PRINCIPAL                |");
        System.out.println("+------------------------------------------+");
        System.out.println("|  1. Adaugare produs                      |");
        System.out.println("|  2. Adaugare / actualizare stoc          |");
        System.out.println("|  3. Adaugare client                      |");
        System.out.println("|  4. Adaugare comanda                     |");
        System.out.println("|  5. Afisare comenzi                      |");
        System.out.println("|  6. Afisare stocuri                      |");
        System.out.println("|  7. Afisare catalog produse              |");
        System.out.println("|  8. Afisare clienti                      |");
        System.out.println("|  9. Schimbare stare comanda              |");
        System.out.println("| 10. Detaliu comanda                      |");
        System.out.println("|  0. Iesire                               |");
        System.out.println("+------------------------------------------+");
    }

    // =========================================================================
    // 1. ADAUGARE PRODUS
    // =========================================================================
    private static void meniuAdaugareProdus() {
        System.out.println("--- Adaugare produs nou ---");

        System.out.println("Categorii disponibile:");
        categorii.forEach((k, v) -> System.out.println("  " + k + ". " + v.getDenumire()));

        int codCat = citesteInt("Selectati categoria (numar): ");
        Categorie cat = categorii.get(codCat);
        if (cat == null) {
            System.out.println("[!] Categorie invalida. Operatiune anulata.");
            return;
        }

        String denumire = citesteString("Denumire produs: ");
        String um       = citesteString("Unitate de masura (buc/kg/l): ");
        double pret     = citesteDouble("Pret lista (RON): ");

        int cod = nextCodProdus++;
        Produs produs = new Produs(cod, denumire, um, pret, cat);
        produsService.adaugaProdus(produs);
        System.out.println("  Cod atribuit automat: " + cod);

        String raspuns = citesteString("Doriti sa adaugati si stoc pentru acest produs? (da/nu): ");
        if (raspuns.equalsIgnoreCase("da")) {
            adaugaStocPentruProdus(produs);
        }
    }

    // =========================================================================
    // 2. ADAUGARE / ACTUALIZARE STOC
    // =========================================================================
    private static void meniuAdaugareStoc() {
        System.out.println("--- Adaugare / Actualizare stoc ---");
        System.out.println("  1. Stoc nou pentru un produs existent din catalog");
        System.out.println("  2. Intrare marfa (adauga cantitate la stoc existent)");

        int sub = citesteInt("Optiune: ");

        if (sub == 1) {
            produsService.afiseazaCatalog();
            int cod = citesteInt("Cod produs pentru care adaugati stoc: ");
            Produs p;
            try {
                p = produsService.getProdusById(cod);
            } catch (ProdusNedisponibilException e) {
                System.out.println("[!] " + e.getMessage());
                return;
            }
            adaugaStocPentruProdus(p);

        } else if (sub == 2) {
            stocService.afiseazaStocuri();
            int cod  = citesteInt("Cod produs (intrare marfa): ");
            int cant = citesteInt("Cantitate intrare: ");
            try {
                stocService.actualizeazaStoc(cod, cant);
            } catch (ProdusNedisponibilException e) {
                System.out.println("[!] " + e.getMessage());
            }

        } else {
            System.out.println("[!] Optiune invalida.");
        }
    }

    /** Citeste datele de stoc si inregistreaza un Stoc nou pentru produsul dat. */
    private static void adaugaStocPentruProdus(Produs p) {
        int stocCurent = citesteInt("Stoc curent (cantitate disponibila): ");
        int stocMinim  = citesteInt("Stoc minim (limita alerta): ");

        Stoc stoc = new Stoc(
                p.getCodProdus(), p.getDenumire(), p.getUnitateMasura(),
                p.getPretLista(), p.getCategorie(),
                stocCurent, 0, stocMinim
        );
        stocService.adaugaStoc(stoc);
    }

    // =========================================================================
    // 3. ADAUGARE CLIENT
    // =========================================================================
    private static void meniuAdaugareClient() {
        System.out.println("--- Adaugare client nou ---");
        System.out.println("  1. Persoana Fizica");
        System.out.println("  2. Persoana Juridica");

        int tip = citesteInt("Tip client: ");
        if (tip != 1 && tip != 2) {
            System.out.println("[!] Tip invalid. Operatiune anulata.");
            return;
        }

        int    cod  = nextCodClient++;
        String nume = citesteString("Nume client: ");

        if (tip == 1) {
            String cnp   = citesteString("CNP: ");
            double bonus = citesteDouble("Bonus % (ex: 5.0): ");
            clientService.adaugaClient(new PersoanaFizica(cod, nume, cnp, bonus));
        } else {
            String cui          = citesteString("CUI (ex: RO12345678): ");
            int    termenPlata  = citesteInt("Termen plata (zile): ");
            double limitaCredit = citesteDouble("Limita credit (RON): ");
            clientService.adaugaClient(new PersoanaJuridica(cod, nume, cui, termenPlata, limitaCredit));
        }

        System.out.println("  Cod client atribuit automat: " + cod);
    }

    // =========================================================================
    // 4. ADAUGARE COMANDA
    // =========================================================================
    private static void meniuAdaugareComanda() {
        System.out.println("--- Adaugare comanda noua ---");

        clientService.afiseazaClienti();
        int codClient = citesteInt("Cod client: ");
        Client client;
        try {
            client = clientService.getClientById(codClient);
        } catch (NoSuchElementException e) {
            System.out.println("[!] " + e.getMessage());
            return;
        }

        LocalDate dataComanda = citesteData("Data comanda (dd.MM.yyyy, Enter=azi): ", LocalDate.now());
        LocalDate dataProg    = citesteData("Data programata livrare (dd.MM.yyyy, Enter=+3 zile): ",
                                             dataComanda.plusDays(3));

        int nrComanda = nextNrComanda++;
        ComandaClient comanda = new ComandaClient(nrComanda, dataComanda, dataProg, client, "noua");

        System.out.println("\nAdaugati articole in comanda (introduceti 0 pentru a termina):");
        produsService.afiseazaCatalog();

        boolean continua = true;
        while (continua) {
            int codProdus = citesteInt("\nCod produs (0 = termina): ");
            if (codProdus == 0) {
                continua = false;
                continue;
            }

            Produs produs;
            try {
                produs = produsService.getProdusById(codProdus);
            } catch (ProdusNedisponibilException e) {
                System.out.println("[!] " + e.getMessage());
                continue;
            }

            int cantitate = citesteInt("Cantitate: ");
            if (cantitate <= 0) {
                System.out.println("[!] Cantitatea trebuie sa fie pozitiva.");
                continue;
            }

            // Verificare stoc disponibil
            try {
                Stoc stoc = stocService.getStocByProdus(codProdus);
                if (stoc.getStocDisponibil() < cantitate) {
                    System.out.println("[ATENTIE] Stoc disponibil insuficient: "
                            + stoc.getStocDisponibil() + " " + produs.getUnitateMasura()
                            + ". Continuati totusi? (da/nu): ");
                    String r = citesteString("");
                    if (!r.equalsIgnoreCase("da")) continue;
                }
            } catch (ProdusNedisponibilException e) {
                System.out.println("[ATENTIE] Nu exista stoc inregistrat pentru acest produs.");
            }

            System.out.println("  Pret lista: " + produs.getPretLista() + " RON");
            double pretVanzare = citesteDouble(
                    "Pret vanzare (Enter = " + produs.getPretLista() + "): ",
                    produs.getPretLista());

            ArticolComanda articol = new ArticolComanda(nrComanda, produs, cantitate, pretVanzare);
            comanda.adaugaArticol(articol);
            System.out.println("  [OK] Articol adaugat. Valoare linie: "
                    + String.format("%.2f", articol.calculeazaValoare()) + " RON");
        }

        if (comanda.getArticole().isEmpty()) {
            System.out.println("[!] Comanda fara articole - nu a fost salvata.");
            return;
        }

        // Rezervare stocuri
        for (ArticolComanda art : comanda.getArticole()) {
            try {
                stocService.rezervaStoc(art.getCodProdus(), art.getCantitate());
            } catch (ProdusNedisponibilException e) {
                System.out.println("[WARN] Rezervare stoc: " + e.getMessage());
            }
        }

        comandaService.plasareComanda(comanda);
        System.out.println("\n  *** COMANDA #" + nrComanda + " SALVATA ***");
        System.out.println("  Total: " + String.format("%.2f", comanda.calculeazaTotal()) + " RON");

        stocService.afiseazaAlertaStoc();
    }

    // =========================================================================
    // 5. AFISARE COMENZI
    // =========================================================================
    private static void meniuAfisareComenzi() {
        System.out.println("--- Afisare comenzi ---");
        System.out.println("  1. Toate comenzile (ordonate dupa data)");
        System.out.println("  2. Comenzile unui client");

        int sub = citesteInt("Optiune: ");

        if (sub == 1) {
            comandaService.afiseazaToateComenzieSortate();
        } else if (sub == 2) {
            clientService.afiseazaClienti();
            int codClient = citesteInt("Cod client: ");
            comandaService.afiseazaComenziClient(codClient);
        } else {
            System.out.println("[!] Optiune invalida.");
        }
    }

    // =========================================================================
    // 9. SCHIMBARE STARE COMANDA
    // =========================================================================
    private static void meniuSchimbareStare() {
        System.out.println("--- Schimbare stare comanda ---");
        comandaService.afiseazaToateComenzieSortate();

        int nrComanda = citesteInt("Nr. comanda: ");
        System.out.println("Stari posibile: noua | confirmata | livrata | anulata");
        String stareNoua = citesteString("Stare noua: ");

        try {
            comandaService.schimbaStare(nrComanda, stareNoua);
        } catch (ComandaNotFoundException e) {
            System.out.println("[!] " + e.getMessage());
        }
    }

    // =========================================================================
    // 10. DETALIU COMANDA
    // =========================================================================
    private static void meniuDetaliuComanda() {
        System.out.println("--- Detaliu comanda ---");
        comandaService.afiseazaToateComenzieSortate();

        int nrComanda = citesteInt("Nr. comanda: ");
        try {
            comandaService.afiseazaComandaDetaliat(nrComanda);
        } catch (ComandaNotFoundException e) {
            System.out.println("[!] " + e.getMessage());
        }
    }

    // =========================================================================
    // UTILITARE CITIRE
    // =========================================================================

    /** Citeste un intreg de la consola; repeta daca input-ul este invalid. */
    private static int citesteInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  [!] Introduceti un numar intreg valid.");
            }
        }
    }

    /** Citeste un double; repeta daca invalid. */
    private static double citesteDouble(String prompt) {
        return citesteDouble(prompt, -1.0);
    }

    /**
     * Citeste un double. Daca utilizatorul apasa Enter fara valoare
     * si defaultValue >= 0, returneaza defaultValue.
     */
    private static double citesteDouble(String prompt, double defaultValue) {
        while (true) {
            System.out.print(prompt);
            try {
                String linie = scanner.nextLine().trim();
                if (linie.isEmpty() && defaultValue >= 0) {
                    return defaultValue;
                }
                return Double.parseDouble(linie.replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("  [!] Introduceti un numar zecimal valid (ex: 12.50).");
            }
        }
    }

    /** Citeste un String de la consola (poate fi gol). */
    private static String citesteString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    /**
     * Citeste o data in formatul dd.MM.yyyy.
     * Daca utilizatorul apasa Enter, returneaza defaultDate.
     */
    private static LocalDate citesteData(String prompt, LocalDate defaultDate) {
        while (true) {
            System.out.print(prompt);
            String linie = scanner.nextLine().trim();
            if (linie.isEmpty()) {
                return defaultDate;
            }
            try {
                return LocalDate.parse(linie, DATE_FMT);
            } catch (DateTimeParseException e) {
                System.out.println("  [!] Format invalid. Folositi dd.MM.yyyy (ex: 15.06.2025).");
            }
        }
    }
}
