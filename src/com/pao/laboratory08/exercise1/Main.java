package com.pao.laboratory08.exercise1;

import java.io.*;
import java.util.*;

public class Main {
    // Calea către fișierul cu date — relativă la rădăcina proiectului
    private static final String FILE_PATH = "com/pao/laboratory08/tests/studenti.txt";
    private static List <Student> student_array = new ArrayList<>();
    public static void read_file() throws IOException{
        student_array.clear();
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String new_line;
        while((new_line = br.readLine()) != null)
        {
            String[] split = new_line.split(",");
            if (split.length != 4)
                continue;
            String nume = split[0];
            int varsta = Integer.parseInt(split[1]);
            String oras = split[2];
            String strada = split[3];
            Adresa temp_adresa = new Adresa(oras,strada);
            Student temp_student = new Student(nume,varsta,temp_adresa);
            student_array.add(temp_student);
        }
        br.close();
    }

    public static void main(String[] args) throws Exception {
        // TODO: Implementează conform Readme.md
        //
        // 1. Citește studenții din FILE_PATH cu BufferedReader
        // 2. Citește comanda din stdin: PRINT, SHALLOW <nume> sau DEEP <nume>
        // 3. Execută comanda:
        //    - PRINT → afișează toți studenții
        //    - SHALLOW <nume> → shallow clone + modifică orașul clonei la "MODIFICAT" + afișează
        //    - DEEP <nume> → deep clone + modifică orașul clonei la "MODIFICAT" + afișează

        Scanner sc = new Scanner(System.in);
        String command;
        while(!(command = sc.nextLine()).equals(""))
        {
            String[] commandSplit = command.split(" ");
            switch (commandSplit[0]) {
                case "PRINT":
                    read_file();
                    for (Student s:student_array)
                        System.out.println(s);
                    break;
                case "SHALLOW":
                    read_file();
                    String numeShallow = commandSplit[1];
                    Student student_cautat = null;
                    for (Student s:student_array)
                        if(s.get_nume().equalsIgnoreCase(numeShallow))
                        {
                            student_cautat = s.clone();//Shallow copy the student with this name
                            student_cautat.get_adresa().set_oras("MODIFICAT");
                            System.out.println("Original: " + s);
                            System.out.println("Clona: " + student_cautat);
                        }
                    break;
                case "DEEP":
                    read_file();
                    String numeDeep = commandSplit[1];
                    Student student_Deep = null;
                    for (Student s:student_array)
                        if(s.get_nume().equalsIgnoreCase(numeDeep))
                        {
                            student_Deep = s.deepClone();
                            student_Deep.get_adresa().set_oras("MODIFICAT");
                            System.out.println("Original: " + s);
                            System.out.println("Modificat: " + student_Deep);
                        }
                    break;
                default:
                    break;
            }
        }
        sc.close();
    }
}
