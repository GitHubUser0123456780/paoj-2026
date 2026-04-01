package com.pao.laboratory06.exercise3;

public enum ConstanteFinanciare {
    TVA{
        double getTVA(){
            return 0.19;
        }
    },
    SALARIU_MINIM{
        double getSalariuMinim(){
            return 4050; 
        }
    },
    COTA_IMPOZIT{
        double getCotaImpozit(){
            return 0.16;
        }
    };
}
