package com.pao.laboratory09.exercise2;

import com.pao.laboratory09.exercise1.TipTranzactie;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    private static final String OUTPUT_FILE = "output/lab09_ex2.bin";
    private static final int RECORD_SIZE = 32;
    public static String byteLineToTranzactie(byte[] tranz){
        ByteBuffer bb = ByteBuffer.wrap(tranz).order(ByteOrder.LITTLE_ENDIAN);
        String rez = "";
        int id = bb.getInt();
        double suma = bb.getDouble();
        byte[] data_bytes = new byte[10];
        bb.get(data_bytes);
        String data = new String(data_bytes, StandardCharsets.UTF_8).replace("\0","").strip();
        byte tipByte = bb.get();
        TipTranzactie tip;
        tip = (tipByte == 0) ? TipTranzactie.CREDIT:TipTranzactie.DEBIT;
        byte statusByte = bb.get();
        Status status = Status.values()[statusByte];
        rez = "id=" + String.valueOf(id) + " data=" + data + " tip=" + tip + " suma=" + String.format("%.2f",suma) + " RON status=" + status;
        return rez;
    }
    public static void main(String[] args) throws Exception {
        // TODO: Implementează conform Readme.md
        //
        // 1. Citește N din stdin, apoi cele N tranzacții (id suma data tip)
        // 2. Scrie toate înregistrările în OUTPUT_FILE cu DataOutputStream (format binar, RECORD_SIZE=32 bytes/înreg.)
        //    - bytes 0-3:   id (int, little-endian via ByteBuffer)
        //    - bytes 4-11:  suma (double, little-endian via ByteBuffer)
        //    - bytes 12-21: data (String, 10 chars ASCII, paddat cu spații la dreapta)
        //    - byte 22:     tip (0=CREDIT, 1=DEBIT)
        //    - byte 23:     status (0=PENDING, 1=PROCESSED, 2=REJECTED)
        //    - bytes 24-31: padding (zerouri)
        // 3. Procesează comenzile din stdin până la EOF cu RandomAccessFile:
        //    - READ idx       → seek(idx * RECORD_SIZE), citește și afișează înregistrarea
        //    - UPDATE idx ST  → seek(idx * RECORD_SIZE + 23), scrie noul status (0/1/2)
        //                       afișează "Updated [idx]: STATUS"
        //    - PRINT_ALL      → citește și afișează toate înregistrările
        //
        // Format linie output:
        //   [idx] id=<id> data=<data> tip=<CREDIT|DEBIT> suma=<suma:.2f> RON status=<STATUS>
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(OUTPUT_FILE));
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        for(int i=0;i<n;i++)
        {
            String newLine = sc.nextLine();
            String[] split = newLine.split(" ");
            int id = Integer.parseInt(split[0]);
            double suma = Double.parseDouble(split[1]);
            String data = split[2];
            TipTranzactie tip = TipTranzactie.valueOf(split[3]);
            Status status = Status.PENDING;
            dos.write(ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(id).array());
            dos.write(ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putDouble(suma).array());
            dos.write(data.getBytes());
            byte[] data_padding = new byte[10-data.length()];
            dos.write(data_padding);
            dos.writeByte(tip == TipTranzactie.CREDIT?0:1);
            byte statusByte;
            switch(status){
                case PENDING: statusByte = 0;break;
                case PROCESSED: statusByte = 1;break;
                case REJECTED: statusByte = 2;break;
                default: statusByte = 0;
            }
            dos.writeByte(statusByte);
            dos.write(new byte[8]);
        }
        RandomAccessFile raf = new RandomAccessFile(OUTPUT_FILE,"rw");
        while(sc.hasNext()){
            String[] split = sc.nextLine().split(" ");
            switch(split[0]){
                case "READ":
                    int index = Integer.parseInt(split[1]);
                    raf.seek(index*32);
                    byte[] tranz = new byte[RECORD_SIZE];
                    raf.read(tranz);
                    System.out.println("[" + index + "] " + byteLineToTranzactie(tranz));
                    break;
                case "UPDATE":
                    int idx = Integer.parseInt(split[1]);
                    raf.seek(idx*RECORD_SIZE+23);
                    byte statusByte;
                    switch(split[2]){
                        case "PENDING":
                            statusByte = 0;
                            break;
                        case "PROCESSED":
                            statusByte = 1;
                            break;
                        case "REJECTED":
                            statusByte = 2;
                            break;
                        default:
                            statusByte = 0;
                    }
                    System.out.println("Updated [" + idx + "]: " + split[2]);
                    raf.write(statusByte);
                    break;
                case "PRINT_ALL":
                    for(int i=0;i<n;i++)
                    {
                        raf.seek(RECORD_SIZE*i);
                        byte[] tranzactie = new byte[RECORD_SIZE];
                        raf.read(tranzactie);
                        System.out.println("[" + i + "] " + byteLineToTranzactie(tranzactie));
                    }
                    break;
                default:
                    break;
            }
        } 
    }
}
