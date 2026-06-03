package com.pao.laboratory14.exercise1;
import java.util.HashMap;
import java.util.Map;
public class Accumulator{
    Map<TipBilet,Long> nrPerTip = new HashMap<>();
    Map<TipBilet,Double> pretPerTip = new HashMap<>();
    double totalGlobal = 0;
    int nrBilete = 0;
}
