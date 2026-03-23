package com.pao.laboratory05.audit;

import java.time.LocalDateTime;
import java.util.Arrays;

public class AngajatService {
    private static AngajatService instance = null;
    private Angajat[] angajati = new Angajat[0];
    private AuditEntry[] auditLog = new AuditEntry[0];
    private AngajatService(){

    }
    private void logAction(String action, String target){
        AuditEntry ae = new AuditEntry(action,target,LocalDateTime.now().toString());
        auditLog = Arrays.copyOf(auditLog, auditLog.length+1);
        auditLog[auditLog.length-1] = ae;
    }
    public static AngajatService getInstance(){
        if(instance == null)
            instance = new AngajatService();
        return instance;
    }
    void addAngajat(Angajat a){
        angajati = Arrays.copyOf(angajati, angajati.length + 1);
        angajati[angajati.length - 1] = a;
        logAction("ADD", a.nume());
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
        logAction("FIND_BY_DEPT", nume_departament);
    }
    void printAllAudit(){
        for(AuditEntry at:auditLog)
            System.out.println(at);
    }
}
