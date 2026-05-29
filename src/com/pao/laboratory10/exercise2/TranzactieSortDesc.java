package com.pao.laboratory10.exercise2;
import java.util.Comparator;
import com.pao.laboratory10.exercise1.Tranzactie;
public class TranzactieSortDesc implements Comparator<Tranzactie>{
    public int compare(Tranzactie t1, Tranzactie t2){
        return Double.compare(t2.getSuma(), t1.getSuma());
    }
}
