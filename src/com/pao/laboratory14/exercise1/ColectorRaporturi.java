package com.pao.laboratory14.exercise1;
import java.util.stream.Collector;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.Set;
import java.util.function.*;

public class ColectorRaporturi implements Collector<Bilet,Accumulator,RaportVanzari>{
    @Override
    public Supplier<Accumulator> supplier(){
        return Accumulator::new;
    }
    @Override
    public BiConsumer<Accumulator,Bilet> accumulator(){
        return(acc,bilet) ->{
            acc.nrPerTip.merge(
                bilet.getTip(),
                1L,
                Long::sum
            );
            acc.pretPerTip.merge(
                bilet.getTip(),
                bilet.getPret(),
                Double::sum
            );
            acc.totalGlobal += bilet.getPret();
            acc.nrBilete++;
        };
    }
    @Override
    public BinaryOperator<Accumulator> combiner(){
        return (a1,a2) ->{
            for (TipBilet tip: a2.nrPerTip.keySet()){
                a1.nrPerTip.merge(tip,a2.nrPerTip.get(tip),Long::sum);
                a1.pretPerTip.merge(tip,a2.pretPerTip.get(tip),Double::sum);
                a1.totalGlobal += a2.totalGlobal;
                a1.nrBilete += a2.nrBilete;
            }
            return a1;
        };
    }
    @Override
    public Function<Accumulator,RaportVanzari> finisher(){
        return acc ->{
            TipBilet popular = null;
            long best = -1;
            for (TipBilet tip:TipBilet.values()){
                long count = acc.nrPerTip.getOrDefault(tip,0L);
                if (count>best){
                    best = count;
                    popular = tip;
                }
            }
            double medieTotala = 0;
            if(acc.nrBilete!=0)
                medieTotala = acc.totalGlobal / acc.nrBilete;
            return new RaportVanzari(acc.nrPerTip,acc.pretPerTip,acc.totalGlobal,medieTotala,popular);
        };
    }
    @Override
    public Set<Characteristics> characteristics(){
        return Set.of();
    }
}
