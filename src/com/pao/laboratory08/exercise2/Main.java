package com.pao.laboratory08.exercise2;

import com.pao.laboratory08.exercise1.Student;
import com.pao.laboratory08.exercise1.Adresa;

import java.io.*;
import java.util.*;

public class Main {
    private static final String FILE_PATH = "com/pao/laboratory08/tests/studenti.txt";
    private static final String OUTPUT_PATH = "com/pao/laboratory08/tests/rezultate.txt";
    public static void main(String[] args) throws Exception {
        // TODO: Implementează conform Readme.md
        //
        // 1. Citește studenții din FILE_PATH cu BufferedReader
        // 2. Citește pragul de vârstă din stdin cu Scanner
        // 3. Filtrează studenții cu varsta >= prag
        // 4. Scrie filtrații în "rezultate.txt" cu BufferedWriter
        // 5. Afișează sumarul la consolă
        List<Student> students = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String newBrLine;
        while((newBrLine = br.readLine()) != null){
            String[] split = newBrLine.split(",");
            if (split.length != 4)
                continue;
            String nume = split[0];
            int varsta = Integer.parseInt(split[1]);
            String oras = split[2];
            String strada = split[3];
            Adresa temp_adresa = new Adresa(oras,strada);
            Student temp_student = new Student(nume,varsta,temp_adresa);
            students.add(temp_student);
        }
        br.close();
        Scanner sc = new Scanner(System.in);
        int prag = sc.nextInt();
        System.out.println("Filtru: varsta >= " + prag);
        sc.close();
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_PATH));
        long filterSize = students.stream().filter(s -> s.get_varsta() >= prag).count();
        System.out.println("Rezultate: " + filterSize + " studenti");
        students.stream()
            .filter(s -> s.get_varsta() >= prag)
            .forEach(s -> {
                try{
                    bw.write(s.toString());
                    bw.write("\n");
                }
                catch(IOException e)
                    {throw new RuntimeException(e);}});
        bw.close();
        BufferedReader readFromOutput = new BufferedReader(new FileReader(OUTPUT_PATH));
        while((newBrLine = readFromOutput.readLine()) != null)
            System.out.println(newBrLine);
        readFromOutput.close();
        System.out.println("Scris in: rezultate.txt");
    }
}

