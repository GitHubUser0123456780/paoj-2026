package ro.pao.proiect;

import ro.pao.proiect.model.*;
import ro.pao.proiect.service.*;
import ro.pao.proiect.exception.*;

import java.time.LocalDate;

/**
 * Clasa principala. Demonstreaza toate cele 12 actiuni definite in cerinta.
 */
public class Main {

    public static void main(String[] args) {

        // =====================================================================
        // INITIALIZARE SERVICII
        // =====================================================================
        ProdusService produsService   = new ProdusService();
        StocService   stocService     = new StocService();
        ClientService clientService   = new ClientService();
        ComandaService comandaService = new ComandaService();

        // =====================================================================
        // DATE INITIALE – Categorii
        // =====================================================================
        Categorie catAlimentare  = new Categorie(1, "Alimentare", "Produse alimentare generale");
        Categorie catBauturi     = new Categorie(2, "Bauturi",    "Bauturi racoritoare si alcoolice");
        Categorie catCuratenie   = new Categorie(3, "Curatenie",  "Produse de curatenie si igiena");

        // =====================================================================
        // ACTIUNEA 1 – Adaugare produse in catalog
        // =====================================================================
        System.out.println("\n>>> ACTIUNEA 1: Adaugare produse in catalog");
        Produs p1 = new Produs(101, "Faina alba 1kg",   "buc", 4.50,  catAlimentare);
        Produs p2 = new Produs(102, "Zahar 1kg",        "buc", 6.80,  catAlimentare);
        Produs p3 = new Produs(103, "Apa minerala 2L",  "buc", 3.20,  catBauturi);
        Produs p4 = new Produs(104, "Suc portocale 1L", "buc", 8.50,  catBauturi);
        Produs p5 = new Produs(105, "Detergent lichid", "buc", 22.00, catCuratenie);
        Produs p6 = new Produs(106, "Bere 0.5L",        "buc", 5.00,  catBauturi);

        produsService.adaugaProdus(p1);
        produsService.adaugaProdus(p2);
        produsService.adaugaProdus(p3);
        produsService.adaugaProdus(p4);
        produsService.adaugaProdus(p5);
        produsService.adaugaProdus(p6);

        // =====================================================================
        // ACTIUNEA 2 – Inregistrare si actualizare stocuri
        // =====================================================================
        System.out.println("\n>>> ACTIUNEA 2: Inregistrare stocuri initiale");
        stocService.adaugaStoc(new Stoc(101, "Faina alba 1kg",   "buc", 4.50,  catAlimentare, 200, 0, 50));
        stocService.adaugaStoc(new Stoc(102, "Zahar 1kg",        "buc", 6.80,  catAlimentare,  30, 0, 40)); // sub limita!
        stocService.adaugaStoc(new Stoc(103, "Apa minerala 2L",  "buc", 3.20,  catBauturi,    500, 0, 100));
        stocService.adaugaStoc(new Stoc(104, "Suc portocale 1L", "buc", 8.50,  catBauturi,    150, 0, 30));
        stocService.adaugaStoc(new Stoc(105, "Detergent lichid", "buc", 22.00, catCuratenie,   15, 0, 20)); // sub limita!
        stocService.adaugaStoc(new Stoc(106, "Bere 0.5L",        "buc", 5.00,  catBauturi,    300, 0, 50));

        System.out.println("\n  Actualizare stoc Zahar (intrare 100 buc):");
        stocService.actualizeazaStoc(102, 100);

        // =====================================================================
        // CLIENTI – Persoana Fizica si Persoana Juridica (mostenire)
        // =====================================================================
        System.out.println("\n>>> Adaugare clienti (mostenire Client <- PF / PJ)");
        PersoanaFizica   pf1 = new PersoanaFizica(1, "Ionescu Maria",    "1234567890123",  5.0);
        PersoanaFizica   pf2 = new PersoanaFizica(2, "Popescu Andrei",   "5678901234567",  3.0);
        PersoanaJuridica pj1 = new PersoanaJuridica(3, "SC AlimPro SRL", "RO12345678",    30, 50000.0);
        PersoanaJuridica pj2 = new PersoanaJuridica(4, "SC BevDist SA",  "RO87654321",    45, 120000.0);

        clientService.adaugaClient(pf1);
        clientService.adaugaClient(pf2);
        clientService.adaugaClient(pj1);
        clientService.adaugaClient(pj2);

        // =====================================================================
        // ACTIUNEA 3 – Plasare comenzi (cu articole)
        // =====================================================================
        System.out.println("\n>>> ACTIUNEA 3: Plasare comenzi");

        // Comanda 1 – pf1, data mai veche
        ComandaClient c1 = new ComandaClient(1001,
                LocalDate.of(2025, 1, 10), LocalDate.of(2025, 1, 12), pf1, "livrata");
        c1.adaugaArticol(new ArticolComanda(1001, p1, 5,  4.50));
        c1.adaugaArticol(new ArticolComanda(1001, p2, 3,  6.80));
        c1.adaugaArticol(new ArticolComanda(1001, p3, 10, 3.20));

        // Comanda 2 – pj1, data medie
        ComandaClient c2 = new ComandaClient(1002,
                LocalDate.of(2025, 2, 5), LocalDate.of(2025, 2, 8), pj1, "confirmata");
        c2.adaugaArticol(new ArticolComanda(1002, p4, 50, 8.00));
        c2.adaugaArticol(new ArticolComanda(1002, p6, 100, 4.80));

        // Comanda 3 – pf2, data recenta
        ComandaClient c3 = new ComandaClient(1003,
                LocalDate.of(2025, 3, 1), LocalDate.of(2025, 3, 3), pf2, "noua");
        c3.adaugaArticol(new ArticolComanda(1003, p5, 2, 22.00));
        c3.adaugaArticol(new ArticolComanda(1003, p3, 6, 3.20));

        // Comanda 4 – pj1, aceeasi zi cu c3 (test sortare)
        ComandaClient c4 = new ComandaClient(1004,
                LocalDate.of(2025, 3, 1), LocalDate.of(2025, 3, 5), pj1, "noua");
        c4.adaugaArticol(new ArticolComanda(1004, p1, 5, 4.50));  // total = 22.50, ca c3 -> equals true

        comandaService.plasareComanda(c1);
        comandaService.plasareComanda(c2);
        comandaService.plasareComanda(c3);
        comandaService.plasareComanda(c4);

        // =====================================================================
        // ACTIUNEA 4 – Calculare total comanda
        // =====================================================================
        System.out.println("\n>>> ACTIUNEA 4: Calculare total comanda #1002");
        double total = comandaService.calculeazaTotal(1002);
        System.out.println("  Total comanda #1002: " + String.format("%.2f", total) + " RON");

        // =====================================================================
        // ACTIUNEA 5 – Listare produse dintr-o categorie
        // =====================================================================
        System.out.println("\n>>> ACTIUNEA 5: Produse din categoria 'Bauturi'");
        produsService.getProduseDupaCategorie("Bauturi")
                .forEach(p -> System.out.println("  " + p));

        // =====================================================================
        // ACTIUNEA 6 – Comenzi ale unui client, sortate dupa data
        // =====================================================================
        System.out.println("\n>>> ACTIUNEA 6: Comenzile clientului SC AlimPro SRL (cod=3)");
        comandaService.afiseazaComenziClient(3);

        // =====================================================================
        // ACTIUNEA 7 – Cautare produs dupa prefix
        // =====================================================================
        System.out.println("\n>>> ACTIUNEA 7: Cautare produse cu prefix 'Apa'");
        produsService.cautaDupaNumePrefix("Apa")
                .forEach(p -> System.out.println("  " + p));

        // =====================================================================
        // ACTIUNEA 8 – Produse cu stoc sub limita minima
        // =====================================================================
        stocService.afiseazaAlertaStoc();

        // =====================================================================
        // ACTIUNEA 9 – Schimbare stare comanda
        // =====================================================================
        System.out.println("\n>>> ACTIUNEA 9: Schimbare stare comenzi");
        comandaService.schimbaStare(1003, "confirmata");
        comandaService.schimbaStare(1004, "confirmata");

        // =====================================================================
        // ACTIUNEA 10 – Listare clienti dupa tip
        // =====================================================================
        clientService.afiseazaClientiDupaTip("Persoana Juridica");
        clientService.afiseazaClientiDupaTip("Persoana Fizica");

        // =====================================================================
        // ACTIUNEA 11 – Top produse vandute
        // =====================================================================
        comandaService.afiseazaTopProduse();

        // =====================================================================
        // ACTIUNEA 12 – Stergere produs din catalog
        // =====================================================================
        System.out.println("\n>>> ACTIUNEA 12: Stergere produs din catalog");
        try {
            produsService.stergeProdus(106);
            // Incercare stergere produs inexistent -> exceptie
            produsService.stergeProdus(999);
        } catch (ProdusNedisponibilException e) {
            System.out.println("[EXCEPTIE] " + e.getMessage());
        }

        // =====================================================================
        // DEMO COLECTII
        // =====================================================================
        System.out.println("\n>>> DEMO: Toate comenzile sortate dupa data (TreeSet)");
        comandaService.afiseazaToateComenzieSortate();

        System.out.println("\n>>> DEMO: Detaliu comanda #1001");
        comandaService.afiseazaComandaDetaliat(1001);

        System.out.println("\n>>> DEMO: Catalog complet produse (HashMap -> sortat dupa denumire)");
        produsService.afiseazaCatalog();

        System.out.println("\n>>> DEMO: Stocuri depozit");
        stocService.afiseazaStocuri();

        // =====================================================================
        // DEMO equals() – doua comenzi cu acelasi total sunt "egale"
        // =====================================================================
        // c3: 2*22 + 6*3.2 = 44 + 19.2 = 63.2
        // c4: 5*4.5 = 22.5
        // Construim o comanda noua cu acelasi total ca c3 (63.2) pentru demo
        System.out.println("\n>>> DEMO equals(): ComandaClient");
        ComandaClient cDemo = new ComandaClient(9999,
                LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 2), pf1, "noua");
        cDemo.adaugaArticol(new ArticolComanda(9999, p3, 10, 3.20)); // 32.0
        cDemo.adaugaArticol(new ArticolComanda(9999, p1, 5,  4.50)); // 22.5 ... nu e acelasi

        System.out.println("  Comanda #1003 total = " + String.format("%.2f", c3.calculeazaTotal()));
        System.out.println("  Comanda #9999 total = " + String.format("%.2f", cDemo.calculeazaTotal()));
        System.out.println("  c3.equals(cDemo) = " + c3.equals(cDemo));

        // Cream una cu exact acelasi total ca c3 (63.20)
        ComandaClient cEgal = new ComandaClient(8888,
                LocalDate.of(2025, 5, 1), LocalDate.of(2025, 5, 2), pf2, "noua");
        cEgal.adaugaArticol(new ArticolComanda(8888, p2, 5,  6.80)); // 34.0
        cEgal.adaugaArticol(new ArticolComanda(8888, p4, 3,  9.733333)); // ~29.2 => 34+29.2=63.2
        // Simplificam: folosim valori exacte
        ComandaClient cEgal2 = new ComandaClient(7777,
                LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 2), pf1, "noua");
        cEgal2.adaugaArticol(new ArticolComanda(7777, p5, 1, 44.00)); // 44.0
        cEgal2.adaugaArticol(new ArticolComanda(7777, p3, 6,  3.20)); // 19.2 => total=63.2

        System.out.println("  Comanda #1003 total = " + String.format("%.2f", c3.calculeazaTotal()));
        System.out.println("  Comanda #7777 total = " + String.format("%.2f", cEgal2.calculeazaTotal()));
        System.out.println("  c3.equals(cEgal2)  = " + c3.equals(cEgal2)
                + "  <-- True: totaluri identice!");

        // =====================================================================
        // DEMO exceptie ComandaNotFoundException
        // =====================================================================
        System.out.println("\n>>> DEMO: Exceptie ComandaNotFoundException");
        try {
            comandaService.getComandaById(9999);
        } catch (ComandaNotFoundException e) {
            System.out.println("[EXCEPTIE] " + e.getMessage());
        }

        System.out.println("\n=== FIN DEMO PAO – Etapa 1 ===");
    }
}
